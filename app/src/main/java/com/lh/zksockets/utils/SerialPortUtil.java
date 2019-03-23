package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.model.SerialCommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.SerialPort;

public class SerialPortUtil {


    private static SerialPort serialPort1, serialPort2;
    private static InputStream inputStream1, inputStream2;
    private static OutputStream outputStream1, outputStream2;


    public static void open() {

        if (serialPort1 != null) {
            return;
        }

        try {
            serialPort1 = new SerialPort(new File("/dev/ttyS1"), 9600, 0);
            serialPort2 = new SerialPort(new File("/dev/ttyS2"), 9600, 0);
            //获取打开的串口中的输入输出流，以便于串口数据的收发
            inputStream1 = serialPort1.getInputStream();
            outputStream1 = serialPort1.getOutputStream();

            inputStream2 = serialPort2.getInputStream();
            outputStream2 = serialPort2.getOutputStream();

        } catch (IOException e) {
            ELog.e("======open_ck=====打开串口异常");
            e.printStackTrace();
        }
    }


    public static void readMsg2() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                byte[] buffer = new byte[1024];
                int size; //读取数据的大小
                try {
                    while (true && (size = inputStream2.read(buffer, 0, 1024)) > 0) {
                        if (size > 0) {
                            String msg = new String(buffer, 0, size);
                            ELog.i("=========串口2===接收到了数据=======" + msg);
                            if (msg.equals("{OK}")) {

                            } else {

                            }


                        }
                    }

                } catch (IOException e) {
                    ELog.i("=========run: 数据读取异常========" + e.toString());
                }


            }
        }.start();

    }


    public static void sendMsg(byte[] data) {
        try {
            if (data.length > 0) {
                outputStream2.write(data);
                outputStream2.flush();
                ELog.e("====sendSerialPort: 串口数据发送成功");
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


    public static void readMsg1() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                byte[] buffer = new byte[1024];
                int size; //读取数据的大小
                try {
                    while (true && (size = inputStream1.read(buffer, 0, 1024)) > 0) {
                        if (size > 0) {
                            String msg = new String(buffer, 0, size);
                            ELog.i("=========串口1===接收到了数据=======" + msg);
                            if (msg.length() == 4) {
                                sendShipinType(msg);
                            } else {
                                if (msg.equals("1")) {
                                    ELog.i("========串口指令======");
                                    makeML(Long.valueOf(msg));

                                } else if (msg.equals("2")) {
                                    ELog.i("========io口指令======");
                                    makeML(Long.valueOf(msg));

                                } else if (msg.equals("3")) {
                                    ELog.i("========继电器指令======");
                                    makeML(Long.valueOf(msg));
                                } else if (msg.equals("4")) {
                                    ELog.i("========继电器指令======");
                                } else if (msg.equals("5")) {
                                    ELog.i("========继电器指令======");
                                } else if (msg.equals("6")) {
                                    ELog.i("========继电器指令======");
                                } else {
                                    ELog.i("========视频指令======");
                                }
                            }


                        }
                    }

                } catch (IOException e) {
                    ELog.i("=========run: 数据读取异常========" + e.toString());
                }


            }
        }.start();

    }


    private static byte[] StringToBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
        }
        return bytes;
    }

    private static void makeML(Long id) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        String strMls = mLsListsDao.load(id).strMLs;

        if (strMls.length() != 0) {

            String[] mls = strMls.split(",");

            for (int i = 0; i < mls.length; i++) {
                ELog.i("========11111111========" + mls[i]);

                if (mls[i].substring(0, 1).equals("1")) {


                    doSerialPort(mls[i]);

                } else if (mls[i].substring(0, 1).equals("2")) {

                } else if (mls[i].substring(0, 1).equals("3")) {

                } else if (mls[i].substring(0, 1).equals("4")) {

                } else if (mls[i].substring(0, 1).equals("5")) {

                }


            }


        }


    }


    private static void doSerialPort(String str) {
        SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();

        SerialCommand spML = serialCommandDao.load(Long.valueOf(str.substring(2)));

        ELog.i("========spML========" + spML.toString());

        byte[] data1 = "{[COM1:DT:A003]<".getBytes();
        byte[] data2 = StringToBytes("E050D0");
        byte[] data3 = ">}".getBytes();
        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);



        sendMsg(data);

    }


    private static void sendJidianqi() {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();


        byte[] data1 = "{[REY0:DT:H001]<".getBytes();
        byte[] data2 = StringToBytes("0F");
        byte[] data3 = ">}".getBytes();

        byte[] data = new byte[data1.length + data2.length + data3.length];

        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

        sendMsg(data);

    }

    private static void sendShipinType(String str) {
        if (str.substring(0, 3).equals("VID")) {
            String msg = "{[VIDC:DT:A001]<" + str.substring(3) + ">}";
            byte[] data = msg.getBytes();
            sendMsg(data);
        }
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
