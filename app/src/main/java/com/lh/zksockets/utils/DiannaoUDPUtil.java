package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.ComputerDao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class DiannaoUDPUtil {

    public static void diannaogj() {
        ComputerDao computerDao = MyApplication.getDaoSession().getComputerDao();
        if (computerDao.loadAll().size() == 0) {
            return;
        }
        if (!computerDao.loadAll().get(0).dn_status.equals("1")) {
            return;
        }
        byte[] msgbyte = SerialPortUtil.StringToBytes(computerDao.loadAll().get(0).dn_ml);
        DatagramSocket cUdpSocket = null;
        try {
            cUdpSocket = new DatagramSocket(Integer.parseInt(computerDao.loadAll().get(0).dn_port));
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(computerDao.loadAll().get(0).dn_ip, Integer.parseInt(computerDao.loadAll().get(0).dn_port)));
            cUdpSocket.send(dp);
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
