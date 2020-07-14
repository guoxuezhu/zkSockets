package com.lh.zksockets.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/08
 * Desc         ${MQTT服务}
 */

public class MyMqttService extends Service {

    private MqttConnectOptions mMqttConnectOptions;
    public String HOST = "wss://uc5xuva.mqtt.iot.gz.baidubce.com:8884/mqtt";//服务器地址（协议+地址+端口号）
    public String USERNAME = "uc5xuva/admin";//用户名 uc5xuva/admin
    public String PASSWORD = "aYBMf7Ci9eCKkx57";//密码 aYBMf7Ci9eCKkx57
    public static String PUBLISH_TOPIC = "";//发布主题

    public String CLIENTID = "";//客户端ID，一般以客户端唯一标识符表示，这里用设备序列号表示
    private static MqttClient mqttClient;
    private Timer mqttTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CreateTimer();
    }

    /**
     * 开启服务
     */
    public static void startService(Context mContext) {
        mContext.startService(new Intent(mContext, MyMqttService.class));
//        if (mqttClient != null) {
//            if (mqttClient.isConnected()) {
//                BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
//                try {
//                    mqttClient.unsubscribe(PUBLISH_TOPIC);
//                    PUBLISH_TOPIC = "lhzktopic/device" + baseInfoDao.loadAll().get(0).classRoom;
//                    mqttClient.subscribe(PUBLISH_TOPIC, 0);//订阅主题，参数：主题、服务质量
//                } catch (MqttException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            mContext.startService(new Intent(mContext, MyMqttService.class));
//        }
    }


    private void CreateTimer() {
        if (mqttTimer != null) {
            mqttTimer.cancel();
            mqttTimer = null;
        }
        mqttTimer = new Timer();
        mqttTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mqttClient != null) {
                    ELog.d("=========mqttTimer==========" + mqttClient.isConnected());
                    if (!mqttClient.isConnected()) {
                        init();
                    }
                } else {
                    ELog.d("=========mqttTimer======init====");
                    init();
                }
            }
        }, 0, 1000 * 30);
    }


    /**
     * 初始化
     */
    private void init() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        PUBLISH_TOPIC = "lhzktopic/device" + zkInfoDao.loadAll().get(0).zkname;
        CLIENTID = zkInfoDao.loadAll().get(0).uuid;
        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        try {
            mqttClient = new MqttClient(HOST, CLIENTID, dataStore);
            mqttClient.setCallback(mqttCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }


//        mqttAndroidClient = new MqttAndroidClient(this, HOST, CLIENTID);
//        mqttAndroidClient.setCallback(mqttCallback); //设置监听订阅消息的回调
        mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setCleanSession(true); //设置是否清除缓存
        mMqttConnectOptions.setConnectionTimeout(10); //设置超时时间，单位：秒
        mMqttConnectOptions.setKeepAliveInterval(20); //设置心跳包发送间隔，单位：秒
        mMqttConnectOptions.setUserName(USERNAME); //设置用户名
        mMqttConnectOptions.setPassword(PASSWORD.toCharArray()); //设置密码


        // last will message
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + CLIENTID + "\"}";
        String topic = PUBLISH_TOPIC;
        Integer qos = 0;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            try {
                mMqttConnectOptions.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                ELog.i("======mqtt===Exception Occured==============" + e);
                doConnect = false;
            }
        }
        if (doConnect) {
            doClientConnection();
        }
    }


    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        if (!mqttClient.isConnected() && isConnectIsNomarl()) {
            try {
                ELog.i("============mqtt===doClientConnection=========");
                mqttClient.connect(mMqttConnectOptions);
                ELog.i("============mqtt===连接MQTT服务器===ok======");
                mqttClient.subscribe(PUBLISH_TOPIC, 0);//订阅主题，参数：主题、服务质量
                ELog.i("============mqtt===订阅主题==ok=======");
            } catch (MqttException e) {
                e.printStackTrace();
                ELog.i("============mqtt=====MqttException=======" + e.toString());
            }
        }
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            ELog.i("=======mqtt==当前网络名称：" + name);
            return true;
        } else {
            ELog.i("=======mqtt==没有可用网络");
            return false;
        }
    }

    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            ELog.i("===========mqtt====收到消息========" + message.toString());
            String msg = message.toString();
            if (msg.length() > 3) {
                if (msg.substring(0, 3).equals("VID")) {
                    SerialPortUtil.sendShipinType(msg);
                } else if (msg.substring(0, 3).equals("FWS")) {
                    SerialPortUtil.sendFWstatus(msg);
                } else if (msg.substring(0, 3).equals("SKJ")) {//远程无卡开机
                    SerialPortUtil.sendKJban(msg);
                } else if (msg.substring(0, 3).equals("MJD")) {//门禁
                    SerialPortUtil.makemenjin(msg);
                } else if (msg.substring(0, 3).equals("LUB")) {
                    HttpUtil.setlubo(msg);
                } else if (msg.substring(0, 3).equals("JZF")) {
                    SerialPortUtil.sendShipinFenping(msg);
                } else if (msg.substring(0, 3).equals("MBS")) {
                    try {
                        SerialPortUtil.makeML(Long.valueOf(msg.substring(3)));
                    } catch (Exception e) {
                        ELog.i("=========mqtt===接收到了数据====Long.valueOf==异常========" + e.toString());
                    }
                }
            }

        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            ELog.i("=====mqtt====连接断开==========" + mqttClient.isConnected());
        }
    };


    public static void stopMqtt(Context mContext) {
        if (mqttClient != null) {
            if (mqttClient.isConnected()) {
                try {
                    mqttClient.unsubscribe(PUBLISH_TOPIC);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        try {
            mqttClient.disconnect(); //断开连接
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
