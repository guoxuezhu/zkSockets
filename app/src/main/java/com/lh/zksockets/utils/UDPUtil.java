package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.UDPInfoDao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

public class UDPUtil {


    private static DatagramSocket cUdpSocket;
    private static boolean isRun;
    private static Timer timer1;

    public static void getMsg() {
        try {
            cUdpSocket = new DatagramSocket(8999);
            isRun = true;
            getReadMsg();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void sendUDPMsg(String hostname, int port, byte[] msgbyte) {
        try {
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(hostname, port));
            if (cUdpSocket != null) {
                cUdpSocket.send(dp);//发送一条信息
                ELog.d("=======发送一条信息===ok========");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void ClientSend(final byte[] msgbyte) {
        UDPInfoDao udpInfoDao = MyApplication.getDaoSession().getUDPInfoDao();
        if (udpInfoDao.loadAll().size() == 0) {
            return;
        }
        ELog.d("=======udpInfoDao===========" + udpInfoDao.loadAll().toString());
        try {
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(udpInfoDao.loadAll().get(0).wlip, 8989));
            if (cUdpSocket != null) {
                cUdpSocket.send(dp);//发送一条信息
                ELog.d("=======发送一条信息==11=ok========");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getReadMsg() {
        if (timer1 == null) {
            timer1 = new Timer();
        }
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                //准备空的数据包用于存放数据。
                byte[] buf = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024

                while (isRun) {
                    try {
                        cUdpSocket.receive(datagramPacket);
                        ELog.d("=======接收到消息===========" + datagramPacket.getLength());
                        String ret = "";
                        for (int i = 0; i < datagramPacket.getLength(); i++) {
                            String hex = Integer.toHexString(buf[i] & 0xFF);
                            if (hex.length() == 1) {
                                hex = "0" + hex;
                            }
                            ret += hex.toUpperCase();
                        }
                        ELog.d("==========ret=====" + ret);
                        String[] mls = ret.split("FE");
                        for (int i = 0; i < mls.length; i++) {
                            ELog.d("========ret=====" + mls[i]);
                            if (mls[i].equals("0D690202AAEE010600000A01000010013D") || mls[i].equals("0D690202C7FA010600000A010000100144")) {
                                //窗帘开 0D690202AAEE010600000A01000010013D  0D690202C7FA010600000A010000100144
                                ELog.d("==========窗帘开=====");
                                SerialPortUtil.makeML((long)3);
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202C7FA0106000101"));
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202AAEE0106000101"));
                                SerialPortUtil.sendMsg1("KZQ;0101".getBytes());
                            } else if (mls[i].equals("0D690202AAEE010600000A01000010003C") || mls[i].equals("0D690202C7FA010600000A010000100045")) {
                                //窗帘关 0D690202AAEE010600000A01000010003C  0D690202C7FA010600000A010000100045
                                ELog.d("=========窗帘关=====");
                                SerialPortUtil.makeML((long)4);
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202C7FA0106000100"));
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202AAEE0106000100"));
                                SerialPortUtil.sendMsg1("KZQ;0100".getBytes());
                            } else if (mls[i].equals("0D690202AAEE020600000A01000010013E") || mls[i].equals("0D690202C7FA020600000A010000100147")) {
                                //教室灯开
                                ELog.d("==========教室灯开=====");
//                                SerialPortUtil.makeML((long)15);
                                SerialPortUtil.sendMsg1("KZQ;0201".getBytes());
                            } else if (mls[i].equals("0D690202AAEE020600000A01000010003F") || mls[i].equals("0D690202C7FA020600000A010000100046")) {
                                //教室灯关
                                ELog.d("=========教室灯关=====");
//                                SerialPortUtil.makeML((long)16);
                                SerialPortUtil.sendMsg1("KZQ;0200".getBytes());
                            } else if (mls[i].equals("0D690202AAEE030600000A01000010013F") || mls[i].equals("0D690202C7FA030600000A010000100146")) {
                                //场景开
                                ELog.d("========场景开=====");
                                SerialPortUtil.makeML((long)66);
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202C7FA0306000101"));
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202AAEE0306000101"));
                                SerialPortUtil.sendMsg1("KZQ;0301".getBytes());
                            } else if (mls[i].equals("0D690202AAEE030600000A01000010003E") || mls[i].equals("0D690202C7FA030600000A010000100047")) {
                                //场景关
                                ELog.d("==========场景关=====");
                                SerialPortUtil.makeML((long)67);
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202C7FA0306000100"));
//                                ClientSend(SerialPortUtil.StringToBytes("FE08290202AAEE0306000100"));
                                SerialPortUtil.sendMsg1("KZQ;0300".getBytes());
                            } else if (mls[i].equals("0D690202AAEE040600000A010000100138") || mls[i].equals("0D690202C7FA040600000A010000100141")) {
                                //黑板灯开
                                ELog.d("==========黑板灯开=====");
//                                SerialPortUtil.makeML((long)13);
                                SerialPortUtil.sendMsg1("KZQ;0401".getBytes());
                            } else if (mls[i].equals("0D690202AAEE040600000A010000100039") || mls[i].equals("0D690202C7FA040600000A010000100040")) {
                                //黑板灯关
                                ELog.d("=========黑板灯关=====");
//                                SerialPortUtil.makeML((long)14);
                                SerialPortUtil.sendMsg1("KZQ;0400".getBytes());
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 1);

    }

    public static void stopUDP() {
        isRun = false;
        if (timer1 != null) {
            timer1.cancel();
            timer1 = null;
        }
        cUdpSocket.disconnect();
        cUdpSocket.close();
    }


}
