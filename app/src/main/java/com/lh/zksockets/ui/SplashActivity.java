package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SerialPortUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        SerialPortUtil.open();
        SerialPortUtil.readMsg1();
        SerialPortUtil.readMsg2();

        jdqOpenStatus();
        dangerOutStatus();


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


        ELog.i("========十六进制字符串==11======" + status);
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


        ELog.i("========十六进制字符串==11======" + status);
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

    @OnClick(R.id.to_login_btn)
    public void to_login_btn() {
        startActivity(new Intent(this, LoginActivity.class));
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
