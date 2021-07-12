package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DianliangDataDao;
import com.lh.zksockets.data.DbDao.WuangguanInfoDao;
import com.lh.zksockets.data.model.DianliangData;
import com.lh.zksockets.data.model.WuangguanInfo;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class UDPUtil {

    private static DatagramSocket udpSocket;

    private static void init() {
        try {
            udpSocket = new DatagramSocket(12001);
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

    public static void wgDianliangInfo() {
        WuangguanInfoDao wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
        for (int i = 0; i < wangguandata.loadAll().size(); i++) {
            WuangguanInfo wuangguanInfo = wangguandata.loadAll().get(i);
            if (wuangguanInfo != null && wuangguanInfo.wg_status == 1) {
                sendUdpMsg(wuangguanInfo.wg_ip, SerialPortUtil.StringToBytes("4C4800A20000000000000A0D"));
            }
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
//                        String ret = "";
//                        for (int j = 0; j < recePacket.getLength(); j++) {
//                            String hex = Integer.toHexString(recePacket.getData()[j] & 0xFF);
//                            if (hex.length() == 1) {
//                                hex = "0" + hex;
//                            }
//                            ret += hex.toUpperCase();
//                        }
//                        ELog.i("=======接收数据包===ret==111===" + ret);
                        WuangguanInfoDao wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
                        List<WuangguanInfo> wgData = wangguandata.queryBuilder()
                                .where(WuangguanInfoDao.Properties.Wg_ip.eq(recePacket.getAddress().toString().substring(1)),
                                        WuangguanInfoDao.Properties.Wg_status.eq(1))
                                .list();
                        if (wgData.size() != 0) {
                            String msgType = Integer.toHexString(recePacket.getData()[3] & 0xFF);
                            ELog.i("=======接收数据包===msgType=====" + msgType);
                            if (msgType.equals("ac")) {
                                String wgbtnStatus = "WGBTN;" + Integer.toHexString(recePacket.getData()[17] & 0xFF)
                                        + ";" + Integer.toHexString(recePacket.getData()[18] & 0xFF);
                                SerialPortUtil.sendMsg1(wgbtnStatus.getBytes());
                            }
                            if (msgType.equals("a2")) {
                                DianliangDataDao dianliangDataDao = MyApplication.getDaoSession().getDianliangDataDao();
                                dianliangDataDao.deleteAll();
                                String device_numer = Integer.toHexString(recePacket.getData()[14] & 0xFF);
                                ELog.i("=======接收数据包===device_numer=====" + device_numer);
                                int count = Integer.valueOf(device_numer);
                                for (int i = 0; i < count; i++) {
                                    String device_type = Integer.toHexString(recePacket.getData()[i * 66 + 15] & 0xFF);
                                    String device_id = Integer.toHexString(recePacket.getData()[i * 66 + 17] & 0xFF);
                                    ELog.i("=======接收数据包===device_type==111===" + device_type);
                                    ELog.i("=======接收数据包===device_id===222====" + device_id);
                                    String device_name = "";
                                    try {
                                        device_name = new String(recePacket.getData(), i * 66 + 21, 20, "GBK").trim();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    ELog.i("=======接收数据包===device_name===333====" + device_name);
                                    if (device_type.equals("2")) {
                                        byte[] buffer = new byte[4];
                                        String[] strArray = new String[8];
                                        String value1 = Integer.toHexString(recePacket.getData()[i * 66 + 52] & 0xFF);
                                        strArray[0] = value1;
                                        for (int n = 0; n < 7; n++) {
                                            System.arraycopy(recePacket.getData(), i * 66 + 53 + n * 4, buffer, 0, 4);
                                            int accum = 0;
                                            for (int shiftBy = 0; shiftBy < 4; shiftBy++) {
                                                accum |= (buffer[shiftBy] & 0xff) << shiftBy * 8;
                                            }
                                            float value = Float.intBitsToFloat(accum);
                                            ELog.i("==========value==1111=====" + value);
                                            if (Float.isNaN(value)) {
                                                strArray[1 + n] = "0";
                                            } else {
                                                strArray[1 + n] = value + "";
                                            }
                                        }
                                        ELog.i("==========value==strArray=====" + Arrays.toString(strArray));
                                        dianliangDataDao.insert(new DianliangData(Long.parseLong(device_id, 16), device_name,
                                                strArray[4], strArray[1], strArray[2],
                                                strArray[3], strArray[3], 0));
                                        ELog.i("==========dianliangDataDao====" + dianliangDataDao.loadAll().toString());
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
