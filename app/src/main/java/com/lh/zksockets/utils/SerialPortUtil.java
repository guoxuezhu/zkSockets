package com.lh.zksockets.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
        ELog.d("===========串口数据发送=============" + msg);
    }


    public static List<String> getIOnumDatas() {
        List<String> IoNumList = new ArrayList<String>();
        IoNumList.add("IO口-1");
        IoNumList.add("IO口-2");
        IoNumList.add("IO口-3");
        IoNumList.add("IO口-4");
        IoNumList.add("IO口-5");
        IoNumList.add("IO口-6");
        return IoNumList;
    }

    public static List<String> getSerialPortNumDatas() {
        List<String> serialPortNumList = new ArrayList<String>();
        serialPortNumList.add("串口-1");
        serialPortNumList.add("串口-2");
        serialPortNumList.add("串口-3");
        serialPortNumList.add("串口-4");
        serialPortNumList.add("串口-5");
        serialPortNumList.add("串口-6");
        serialPortNumList.add("串口-7");
        serialPortNumList.add("串口-8");
        return serialPortNumList;
    }

    public static List<String> getStopBitDatas() {
        List<String> stopBitList = new ArrayList<String>();
        stopBitList.add("1");
        stopBitList.add("1.5");
        stopBitList.add("2");
        return stopBitList;
    }


    public static List<String> getDataBitDatas() {
        List<String> dataBitList = new ArrayList<>();
        dataBitList.add("8");
        dataBitList.add("7");
        dataBitList.add("6");
        dataBitList.add("5");
        return dataBitList;
    }

    public static List<String> getCheckoutBitDatas() {
        List<String> checkoutBitList = new ArrayList<>();
        checkoutBitList.add("无");
        checkoutBitList.add("奇");
        checkoutBitList.add("偶");
        return checkoutBitList;
    }


    public static List<String> getBaudRateDatas() {
        List<String> baudRateList = new ArrayList<>();
        baudRateList.add("2400");
        baudRateList.add("4800");
        baudRateList.add("9600");
        baudRateList.add("14400");
        baudRateList.add("19200");
        baudRateList.add("38400");
        baudRateList.add("57600");
        baudRateList.add("115200");
        return baudRateList;
    }


}
