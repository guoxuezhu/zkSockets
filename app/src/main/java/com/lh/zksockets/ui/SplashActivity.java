package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.service.MyMqttService;
import com.lh.zksockets.service.NIOHttpServer;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;
import com.lh.zksockets.utils.TimerUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_password)
    EditText login_password;

    private Timer stimer;

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
        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        if (baseInfoDao.loadAll().size() != 0) {
            if (!baseInfoDao.loadAll().get(0).classRoom.equals("") && !baseInfoDao.loadAll().get(0).mqttuser.equals("")
                    && !baseInfoDao.loadAll().get(0).mqttpassword.equals("")) {
                MyMqttService.startService(this); //开启服务
            }
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
        if (login_name.getText().toString().trim().equals("admin") &&
                login_password.getText().toString().trim().equals("admin")) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }

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
