package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.WuangguanInfoDao;
import com.lh.zksockets.data.model.WuangguanInfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPUtil {

    public static void doWangguan(String ml) {
        WuangguanInfoDao wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
        if (wangguandata.loadAll().size() == 0) {
            return;
        }
        // 5-1-1 5-10-1
        String[] mls = ml.split("-");
        if (mls.length != 3) {
            return;
        }
        WuangguanInfo wuangguanInfo = wangguandata.load(Long.valueOf(mls[2]));
        if (wuangguanInfo == null || wuangguanInfo.wg_status == 0) {
            return;
        }
        String hex = Integer.toHexString(Integer.valueOf(mls[1]));
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        sendUdpMsg(wuangguanInfo.wg_ip, SerialPortUtil.StringToBytes("4C4801A9010000000100" + hex + "0A0D"));

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
