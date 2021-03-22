package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPUtil {

    public static void makeWangguan(Long id) {
        EventKejianRestDao wangguandata = MyApplication.getDaoSession().getEventKejianRestDao();
        if (wangguandata.loadAll().size() == 0) {
            return;
        }

        if (wangguandata.loadAll().get(0).status == 0) {
            return;
        }

        if (id == 3) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100010A0D"));
        }
        if (id == 4) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100020A0D"));
        }
        if (id == 3001) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100030A0D"));
        }
        if (id == 3002) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100040A0D"));
        }
        if (id == 3003) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100050A0D"));
        }
        if (id == 3004) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100060A0D"));
        }
        if (id == 7005) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100070A0D"));
        }
        if (id == 7006) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100080A0D"));
        }
        if (id == 13) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100030A0D"));
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100050A0D"));
        }
        if (id == 14) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100040A0D"));
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100060A0D"));
        }


        if (id == 8001) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A90100000001000F0A0D"));
        }

        if (id == 8002) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100100A0D"));
        }

        if (id == 8003) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100110A0D"));
        }

        if (id == 8004) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100120A0D"));
        }

        if (id == 8005) {
            sendUdpMsg(wangguandata.loadAll().get(0).name, SerialPortUtil.StringToBytes("4C4801A9010000000100130A0D"));
        }

    }

    private static void sendUdpMsg(String ip, byte[] msgbyte) {
        DatagramSocket cUdpSocket = null;
        try {
            cUdpSocket = new DatagramSocket(10101);
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(ip, 10101));
            cUdpSocket.send(dp);//发送一条广播信息
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cUdpSocket != null) {
                cUdpSocket.close();
            }
        }

    }

}
