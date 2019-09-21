package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.service.MyMqttService;
import com.lh.zksockets.service.NIOHttpServer;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;
import com.lh.zksockets.utils.TimerUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_password)
    EditText login_password;

    private Timer stimer;

    private Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    ELog.e("======splashHandler=====10====" + msg.obj.toString());
                    Toast.makeText(SplashActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        stimer = new Timer();
        stimer.schedule(new TimerTask() {
            @Override
            public void run() {

                SerialPortUtil.open();
                SerialPortUtil.readMsg1();
                SerialPortUtil.readMsg2();

                setSerialport();
                jdqOpenStatus();
                dangerOutStatus();
                ioOutStatus();

                NIOHttpServer.getInstance().startServer();

                TimerUtils.setWenshiduTimer();
                HttpUtil.setLuboTokenTimer();
                TimerUtils.setKaijiTimer();
                TimerUtils.setDuandianTimer();//电源时序器夜晚自动关机
                SerialPortUtil.sendMsg("{[VIDB:DT:A035]<1,3;2,4;3,5;4,6;5,7;6,8;7,9;8,1;9,2>}".getBytes());

                mqttServiceStart();

                stimer.cancel();
            }
        }, 500);

    }

    private void mqttServiceStart() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() != 0 && zkInfoDao.loadAll().get(0).ismqttStart == 1) {
            ELog.d("=======SplashActivity==mqtt开启服务==========" + zkInfoDao.loadAll().get(0).zkname);
            MyMqttService.startService(this); //开启服务
        }
    }

    private void setSerialport() {
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        for (int j = 0; j < serialPortDataDao.loadAll().size(); j++) {
            String spStr = serialPortDataDao.loadAll().get(j).baudRate + ",n,8,1";
            String msg = "{[COM" + (serialPortDataDao.loadAll().get(j).id - 1) + ":ST:A0" + spStr.length() + "]<" + spStr + ">}";
            SerialPortUtil.sendMsg(msg.getBytes());
        }
    }


    private void ioOutStatus() {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            return;
        }
        String status = ioPortDataDao.load((long) 4).ioOutStatus + "" + ioPortDataDao.load((long) 3).ioOutStatus + ""
                + ioPortDataDao.load((long) 2).ioOutStatus + "" + ioPortDataDao.load((long) 1).ioOutStatus;
        ELog.i("========ioOutStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[IOL0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }

    private void jdqOpenStatus() {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            return;
        }

        String status = jdqStatusDao.load((long) 8).jdqStatus + "" + jdqStatusDao.load((long) 7).jdqStatus + ""
                + jdqStatusDao.load((long) 6).jdqStatus + "" + jdqStatusDao.load((long) 5).jdqStatus + ""
                + jdqStatusDao.load((long) 4).jdqStatus + "" + jdqStatusDao.load((long) 3).jdqStatus + ""
                + jdqStatusDao.load((long) 2).jdqStatus + "" + jdqStatusDao.load((long) 1).jdqStatus;


        ELog.i("========jdqOpenStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[REY0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }


    private void dangerOutStatus() {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            return;
        }

        String status = dangerOutDao.load((long) 4).dangerOutStatus + "" + dangerOutDao.load((long) 3).dangerOutStatus + ""
                + dangerOutDao.load((long) 2).dangerOutStatus + "" + dangerOutDao.load((long) 1).dangerOutStatus;


        ELog.i("========dangerOutStatus==11======" + status);
        String hex = Integer.toString(Integer.parseInt(status, 2), 16);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        byte[] data1 = "{[ARM0:DT:H001]<".getBytes();
        byte[] data2 = SerialPortUtil.StringToBytes(hex);
        if (data2 == null) {
            return;
        }
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        SerialPortUtil.sendMsg(data);

    }

    @OnClick(R.id.login_btn)
    public void login_btn() {
        if (login_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_name", login_name.getText().toString().trim())
                .add("user_pass", login_password.getText().toString().trim())
                .build();

        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/user_login")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("==========数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========数据==11=====" + httpData.toString());

                if (httpData.flag == 1) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 10;
                    splashHandler.sendMessage(message);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
