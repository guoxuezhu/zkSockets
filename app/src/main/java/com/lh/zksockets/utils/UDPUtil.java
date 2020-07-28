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
    private static Timer timer2;

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
            if (cUdpSocket == null) {
                cUdpSocket.send(dp);//发送一条信息
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
            if (cUdpSocket == null) {
                cUdpSocket.send(dp);//发送一条信息
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
                            if (mls[i].equals("0D6902027AFC010600000A0100001001FF")) {
                                //窗帘开
                                ELog.d("==========窗帘开=====");
                            } else if (mls[i].equals("0D6902027AFC010600000A0100001000")) {
                                //窗帘关
                                ELog.d("=========窗帘关=====");
                            } else if (mls[i].equals("0D6902027AFC020600000A0100001001FC")) {
                                //教室灯开
                                ELog.d("==========教室灯开=====");
                            } else if (mls[i].equals("0D6902027AFC020600000A0100001000FD")) {
                                //教室灯关
                                ELog.d("=========教室灯关=====");
                            } else if (mls[i].equals("0D6902027AFC030600000A0100001001FD")) {
                                //场景开
                                ELog.d("========场景开=====");
                            } else if (mls[i].equals("0D6902027AFC030600000A0100001000FC")) {
                                //场景关
                                ELog.d("==========场景关=====");
                            } else if (mls[i].equals("0D6902027AFC040600000A0100001001FA")) {
                                //黑板灯开
                                ELog.d("==========黑板灯开=====");
                            } else if (mls[i].equals("0D6902027AFC040600000A0100001000FB")) {
                                //黑板灯关
                                ELog.d("=========黑板灯关=====");
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
