package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lh.zksockets.R;
import com.lh.zksockets.utils.ELog;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangDaoActivity extends Activity {

    private SerialPort serialPort;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_dao);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.open_ck)
    public void open_ck() {
        try {
            serialPort = new SerialPort(new File("/dev/ttyS1"), 9600, 0);

            //获取打开的串口中的输入输出流，以便于串口数据的收发
            //inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
            ELog.e("======open_ck=====打开串口异常");
            e.printStackTrace();
        }

    }

    @OnClick(R.id.send_ck)
    public void send_ck() {
        sendSerialPort("BB 04 00 02 02 01 00 55");

    }

    private void sendSerialPort(String data) {
        try {
            byte[] sendData = data.getBytes(); //string转byte[]
            //this.data_ = new String(sendData); //byte[]转string
            if (sendData.length > 0) {
                outputStream.write(sendData);
                outputStream.write('\n');
                //outputStream.write('\r'+'\n');
                outputStream.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }

    @OnClick(R.id.closed_ck)
    public void closed_ck() {

        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (serialPort != null) {
            serialPort.close();
        }

    }

    @OnClick(R.id.xiangdao_btn_back)
    public void xiangdao_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
