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
        DatagramSocket cUdpSocket = null;
        try {
            cUdpSocket = new DatagramSocket(Integer.parseInt(computerDao.loadAll().get(0).PORT));
            DatagramPacket dp = new DatagramPacket(computerDao.loadAll().get(0).userName.getBytes(), computerDao.loadAll().get(0).userName.getBytes().length);
            dp.setSocketAddress(new InetSocketAddress(computerDao.loadAll().get(0).IP, Integer.parseInt(computerDao.loadAll().get(0).PORT)));
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
