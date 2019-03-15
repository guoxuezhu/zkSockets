package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;
import com.lh.zksockets.SocketService;
import com.lh.zksockets.utils.ELog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    private SerialPort serialPort1, serialPort2, serialPort3;
    private InputStream inputStream1, inputStream2, inputStream3;
    private OutputStream outputStream1, outputStream2, outputStream3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

//        Intent startIntent = new Intent(this, SocketService.class);
//        startService(startIntent);


        try {
            serialPort1 = new SerialPort(new File("/dev/ttyS1"), 9600, 0);
            serialPort2 = new SerialPort(new File("/dev/ttyS2"), 9600, 0);
            serialPort3 = new SerialPort(new File("/dev/ttyS0"), 9600, 0);
            //获取打开的串口中的输入输出流，以便于串口数据的收发
            inputStream1 = serialPort1.getInputStream();
            outputStream1 = serialPort1.getOutputStream();

            inputStream2 = serialPort2.getInputStream();
            outputStream2 = serialPort2.getOutputStream();

            inputStream3 = serialPort3.getInputStream();
            outputStream3 = serialPort3.getOutputStream();
            new ReadThread().start(); //开始线程监控是否有数据要接收
        } catch (IOException e) {
            ELog.e("======open_ck=====打开串口异常");
            e.printStackTrace();
        }


    }


    /**
     * 单开一线程，来读数据
     */
    private class ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            //判断进程是否在运行，更安全的结束进程
            byte[] buffer = new byte[1024];
            int size; //读取数据的大小
            try {
                while (true && (size = inputStream1.read(buffer, 0, 1024)) > 0) {
                    if (size > 0) {
                        String msg = new String(buffer, 0, size);
                        ELog.i("=========run: 接收到了数据=======" + msg);
                        ELog.i("=========run: 接收到了数据=======" + msg.substring(0, 1));
                        ELog.i("=========run: 接收到了数据大小=====" + size);

                        if (msg.substring(0, 1).equals("1")) {
                            ELog.i("========串口指令======");
                            getCKML();


                        } else if (msg.substring(0, 1).equals("2")) {
                            ELog.i("========io口指令======");
                        } else {
                            ELog.i("========继电器指令======");
                        }
                    }
                }

            } catch (IOException e) {
                ELog.i("=========run: 数据读取异常========" + e.toString());
            }
        }
    }

    private void getCKML() {
        sendSerialPortData1("串口指令");
    }


    private void sendSerialPortData1(String strDatas) {
        byte[] data = StringToBytes(strDatas);
        try {
            if (data.length > 0) {
                outputStream1.write(data);
                outputStream1.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


    private byte[] StringToBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
        }
        return bytes;
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
