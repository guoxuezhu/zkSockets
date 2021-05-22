package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.WuangguanInfoDao;
import com.lh.zksockets.data.model.WuangguanInfo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPUtil {

    private static DatagramSocket udpSocket;

    private static void init() {
        try {
            udpSocket = new DatagramSocket(10101);
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

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
        if (udpSocket == null) {
            init();
        }
        try {
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(ip, 10101));
            udpSocket.send(dp);//发送一条广播信息
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

    public static void startReadUdpMsg() {
        if (udpSocket == null) {
            init();
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    byte[] receBuf = new byte[1024];
                    try {
                        DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
                        udpSocket.receive(recePacket);
                        String readStr = new String(recePacket.getData(), 0, recePacket.getLength());
                        ELog.d("=====接收数据包=======" + readStr);
                        ELog.d("=====接收数据==服务端ip======" + recePacket.getAddress());
//                        WuangguanInfoDao wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
//                        List<WuangguanInfo> wgData = wangguandata.queryBuilder()
//                                .where(WuangguanInfoDao.Properties.Wg_ip.eq(recePacket.getAddress()),
//                                        WuangguanInfoDao.Properties.Wg_status.eq(1))
//                                .list();
//                        if (wgData.size() != 0) {
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
