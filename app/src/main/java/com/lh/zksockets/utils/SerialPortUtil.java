package com.lh.zksockets.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class SerialPortUtil {


    private static SerialPort serialPort;
    private static OutputStream outputStream;

    public static void open() {
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

    public static void sendMsg(int type, String msg) {
        ELog.d("===========串口数据发送=============");
    }
}
