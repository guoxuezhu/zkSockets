package com.lh.zksockets.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/08
 * Desc         ${MQTT服务}
 */

public class MyMqttService extends Service {

    private static MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mMqttConnectOptions;
    public String HOST = "wss://cmt7p9p.mqtt.iot.gz.baidubce.com:8884/mqtt";//服务器地址（协议+地址+端口号）
    public String USERNAME = "cmt7p9p/zk_user_1";//用户名
    public String PASSWORD = "vZakahehxugRHnZs";//密码
    public static String PUBLISH_TOPIC = "";//发布主题
//    public static String RESPONSE_TOPIC = "message_arrived";//响应主题

    public String CLIENTID = "";//客户端ID，一般以客户端唯一标识符表示，这里用设备序列号表示

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 开启服务
     */
    public static void startService(Context mContext) {
        if (mqttAndroidClient != null) {
            if (mqttAndroidClient.isConnected()) {
                BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
                PUBLISH_TOPIC = "mytopic/DeviceId-numer" + baseInfoDao.loadAll().get(0).classRoom;
                try {
                    mqttAndroidClient.subscribe(PUBLISH_TOPIC, 0);//订阅主题，参数：主题、服务质量
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        } else {
            mContext.startService(new Intent(mContext, MyMqttService.class));
        }
    }

//    /**
//     * 发布 （模拟其他客户端发布消息）
//     *
//     * @param message 消息
//     */
//    public static void publish(String message) {
//        String topic = PUBLISH_TOPIC;
//        Integer qos = 0;
//        Boolean retained = false;
//        try {
//            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
//            mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 响应 （收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等）
//     *
//     * @param message 消息
//     */
//    public void response(String message) {
//        String topic = RESPONSE_TOPIC;
//        Integer qos = 0;
//        Boolean retained = false;
//        try {
//            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
//            mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 初始化
     */
    private void init() {

        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        PUBLISH_TOPIC = "mytopic/DeviceId-numer" + baseInfoDao.loadAll().get(0).classRoom;
        CLIENTID = baseInfoDao.loadAll().get(0).uuid;

        String serverURI = HOST; //服务器地址（协议+地址+端口号）
        mqttAndroidClient = new MqttAndroidClient(this, serverURI, CLIENTID);
        mqttAndroidClient.setCallback(mqttCallback); //设置监听订阅消息的回调
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
                iMqttActionListener.onFailure(null, e);
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
        if (!mqttAndroidClient.isConnected() && isConnectIsNomarl()) {
            try {
                ELog.i("============mqtt===doClientConnection=========");
                mqttAndroidClient.connect(mMqttConnectOptions, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
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
            /*没有可用网络的时候，延迟3秒再尝试重连*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doClientConnection();
                }
            }, 1000 * 60);
            return false;
        }
    }

    //MQTT是否连接成功的监听
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            ELog.i("=======mqtt==连接成功 ");
            try {
                mqttAndroidClient.subscribe(PUBLISH_TOPIC, 0);//订阅主题，参数：主题、服务质量
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            ELog.i("=======mqtt==连接失败 ");
        }
    };

    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            ELog.i("===========mqtt====收到消息========" + message.toString());
            String msg = message.toString();
            if (msg.length() > 3) {
                if (msg.substring(0, 3).equals("VID")) {
                    SerialPortUtil.sendShipinType(msg);
                } else if (msg.substring(0, 3).equals("LUB")) {
                    HttpUtil.setlubo(msg);
                }
            } else if (msg.length() > 0 && msg.length() <= 3) {
                SerialPortUtil.makeML(Long.valueOf(msg));
            }

//            ELog.i("=========收到消息： =====" + new String(message.getPayload()));
            //收到消息，这里弹出Toast表示。如果需要更新UI，可以使用广播或者EventBus进行发送
//            Toast.makeText(getApplicationContext(), "messageArrived: " + new String(message.getPayload()), Toast.LENGTH_LONG).show();
            //收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等
//            response("message arrived");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            ELog.i("=====mqtt====连接断开 ");
        }
    };

    @Override
    public void onDestroy() {
        try {
            mqttAndroidClient.disconnect(); //断开连接
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
