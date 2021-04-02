package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DangerStatusDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.VidStatusDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.DangerStatus;
import com.lh.zksockets.data.model.VidStatus;
import com.lh.zksockets.data.model.WenShiDu;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class SportDataUtil {


    private static int bslength = 0;
    private static byte[] buffer1 = new byte[1024];
    private static byte[] buffer2 = new byte[1024];

    public static void readSptdata(byte[] buffer, int size) {
        try {
            System.arraycopy(buffer, 0, buffer1, bslength, size);
            bslength = bslength + size;
            makeData(new String(buffer1, 0, bslength));
        } catch (Exception e) {
            ELog.e("=====串口2===run====System.arraycopy====" + e.toString());
        }
    }

    private static void makeData(String msgdata) {
        ELog.i("============msgdata====00=============" + msgdata);
        if (msgdata.length() > 1 && msgdata.substring(0, 1).equals("[")) {
            if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[OK]")) {
                makeOK(msgdata);
            } else if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[VID]")) {
                makeVID(msgdata);
            } else if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[ARM0]")) {
                makeARM(msgdata);
            } else if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[COM7]")) {
                makeCOM(msgdata);
            } else {
                makeNOData(msgdata);
            }
        } else {
            ELog.i("========串口2====无数据处理===========" + msgdata);
            bslength = 0;
            buffer1 = new byte[1024];
            buffer2 = new byte[1024];
        }
    }

    private static void delectNobuf(int index) {
        bslength = bslength - index;
        buffer2 = new byte[1024];
        System.arraycopy(buffer1, index, buffer2, 0, bslength);
        ELog.i("============delectNobuf========" + new String(buffer2, 0, bslength));
        buffer1 = new byte[1024];
        System.arraycopy(buffer2, 0, buffer1, 0, bslength);
        buffer2 = new byte[1024];
        makeData(new String(buffer1, 0, bslength));
    }

    private static void makeOK(String msgdata) {
        delectNobuf(4);
    }

    private static void makeNOData(String msgdata) {
        if (msgdata.indexOf("[", 2) == -1) {
            ELog.i("==========其它接收数据==========" + msgdata);
            int endsize = msgdata.indexOf("[", 2) + 1;
            delectNobuf(endsize);
        }
    }

    private static void makeCOM(String msgdata) {
        //size=25 [COM7]<����>����>[COM7]<����lz>
        if (msgdata.indexOf("[", 2) == -1) {
            if (msgdata.length() == 17) {
                buffer2 = new byte[1024];
                System.arraycopy(buffer1, 7, buffer2, 0, 9);
                getDianLiang2();
                bslength = 0;
                buffer1 = new byte[1024];
                buffer2 = new byte[1024];
            }
        } else {
            if (msgdata.indexOf("]<", 7) != -1 && msgdata.indexOf(">", msgdata.indexOf("]<", 7)) != -1) {
                int endsize = msgdata.indexOf(">", msgdata.indexOf("]<", 7)) + 1;
                ELog.i("===========COM=======endsize===========" + endsize);
                ELog.i("===========COM=======bslength===========" + bslength);
                if (msgdata.substring(msgdata.indexOf(">[", 3) + 1, msgdata.indexOf("]<", 6) + 1).equals("[COM7]")) {
                    if (bslength >= 25) {
                        String str25 = msgdata.substring(24, 25);
                        ELog.i("===========COM======str25=========" + str25);
                        if (str25.equals(">")) {
                            buffer2 = new byte[1024];
                            int length1 = msgdata.indexOf(">[", 3) + 1;
                            ELog.i("==========COM======两条数据====length1============" + length1);
                            System.arraycopy(buffer1, 7, buffer2, 0, length1 - 8);
                            System.arraycopy(buffer1, length1 + 7, buffer2, length1 - 8, bslength - length1 - 8);
                            getDianLiang2();
                            delectNobuf(25);
                        } else {
                            delectNobuf(endsize);
                        }
                    } else {
                        delectNobuf(endsize);
                    }
                } else {
                    int endsize2 = msgdata.indexOf(">") + 1;
                    delectNobuf(endsize2);
                }
            }
        }
    }

    private static void makeARM(String msgdata) {
        // size=9  [ARM0]<X>
        if (msgdata.indexOf(">") != -1) {
            ELog.i("===========ARM0=======bslength===========" + bslength);
            if (bslength >= 9) {
                baojin(Integer.toHexString(buffer1[7] & 0xFF));
                delectNobuf(9);
            }
        }
    }

    private static void makeVID(String msgdata) {
        // size=18  [VID]<BB 06 08 80>
        if (msgdata.indexOf(">") != -1) {
            int endsize = msgdata.indexOf(">") + 1;
            if (endsize == 18) {
                if (msgdata.substring(6, 11).equals("BB 06") && msgdata.substring(15, 17).equals("80")) {
                    onVIDinStatus(msgdata.substring(12, 14));
                }
            }
            delectNobuf(endsize);
        }
    }

    private static void onVIDinStatus(String str) {
        ELog.i("=======输入信号====00====" + str);
        String strInStatus = JinzhiUtil.get2String(str.substring(1));
        ELog.i("=======输入信号====status====" + strInStatus);
        String strOutStatus = JinzhiUtil.get2String(str.substring(0, 1));
        ELog.i("=======输出信号====status====" + strOutStatus);
        VidStatusDao vidStatusDao = MyApplication.getDaoSession().getVidStatusDao();
        if (vidStatusDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                vidStatusDao.insert(new VidStatus((long) i, "输入口" + i, strInStatus.substring(4 - i, 5 - i)));
            }
            for (int n = 1; n < 5; n++) {
                vidStatusDao.insert(new VidStatus((long) n + 10, "输出口" + n, strOutStatus.substring(4 - n, 5 - n)));
            }
        } else {
            if (!strInStatus.substring(1, 2).equals(vidStatusDao.load((long) 3).vidinStatus)) {
                if (strInStatus.substring(1, 2).equals("0")) {
                    ELog.i("======老师笔记本====无信号===");
                    SerialPortUtil.bjbnovid();
                } else {
                    ELog.i("======老师笔记本====有信号====");
                    SerialPortUtil.bjbokvid();
                }
            }
            for (int i = 1; i < 5; i++) {
                vidStatusDao.update(new VidStatus((long) i, "输入口" + i, strInStatus.substring(4 - i, 5 - i)));
            }
            for (int n = 1; n < 5; n++) {
                vidStatusDao.update(new VidStatus((long) n + 10, "输出口" + n, strOutStatus.substring(4 - n, 5 - n)));
            }
        }
        ELog.i("======vidStatusDao========" + vidStatusDao.loadAll().toString());
    }

    private static void baojin(String hex) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            return;
        }
        ELog.i("=========报警口==hex====" + hex);
        String str2jz = JinzhiUtil.get2String(hex.substring(1));
        ELog.i("=========报警口==000====" + str2jz);
        if (str2jz != null) {
            DangerStatusDao dangerStatusDao = MyApplication.getDaoSession().getDangerStatusDao();

            if (!str2jz.substring(0, 1).equals(dangerStatusDao.load((long) 1).dangerStatus4 + "")) {
                if (str2jz.substring(0, 1).equals(ioYuanDao.load((long) 4).dangerIoStatus + "")) {
                    ELog.i("=========报警口======" + 4);
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 4).dangerMl);
                } else {
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 4).noDangerMl);
                }
            }

            if (!str2jz.substring(1, 2).equals(dangerStatusDao.load((long) 1).dangerStatus3 + "")) {
                if (str2jz.substring(1, 2).equals(ioYuanDao.load((long) 3).dangerIoStatus + "")) {
                    ELog.i("=========报警口======" + 3);
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 3).dangerMl);
                } else {
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 3).noDangerMl);
                }
            }

            if (!str2jz.substring(2, 3).equals(dangerStatusDao.load((long) 1).dangerStatus2 + "")) {
                if (str2jz.substring(2, 3).equals(ioYuanDao.load((long) 2).dangerIoStatus + "")) {
                    ELog.i("=========报警口======" + 2);
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 2).dangerMl);
                } else {
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 2).noDangerMl);
                }
            }

            if (!str2jz.substring(3, 4).equals(dangerStatusDao.load((long) 1).dangerStatus1 + "")) {
                if (str2jz.substring(3, 4).equals(ioYuanDao.load((long) 1).dangerIoStatus + "")) {
                    ELog.i("=========报警口======" + 1);
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 1).dangerMl);
                } else {
                    SerialPortUtil.getEventId(ioYuanDao.load((long) 1).noDangerMl);
                }
            }

            dangerStatusDao.deleteAll();
            dangerStatusDao.insert(new DangerStatus((long) 1, Integer.valueOf(str2jz.substring(3, 4)),
                    Integer.valueOf(str2jz.substring(2, 3)),
                    Integer.valueOf(str2jz.substring(1, 2)),
                    Integer.valueOf(str2jz.substring(0, 1))));

        }
    }

    private static void getDianLiang() {
        byte[] buffer3 = new byte[4];
        System.arraycopy(buffer2, 3, buffer3, 0, 4);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer3);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            float dianliang = dataInputStream.readFloat();
            ELog.i("=======电能表====dianliang=========" + dianliang);
        } catch (IOException e) {
            e.printStackTrace();
            ELog.i("=======电能表====dianliang===IOException======");
        }
    }

    private static void getDianLiang2() {
        String ret = "";
        for (int j = 0; j < 9; j++) {
            String hex = Integer.toHexString(buffer2[j] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            ret += hex.toUpperCase();
        }
        ELog.i("=======电能表====dianliang=====111========" + ret);
        ELog.i("=======电能表====dianliang=============" + ret.substring(6, 14));
        int dianliang = Integer.parseInt(ret.substring(6, 14), 16);
        ELog.i("=======电能表====dianliang======111=======" + dianliang * 0.01);
    }

    private static void setWenshidu() {

        String ret = "";
        for (int j = 0; j < 9; j++) {
            String hex = Integer.toHexString(buffer2[j] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            ret += hex.toUpperCase();
        }
        ELog.i("=======温度=============" + ret);
        if (ret.substring(0, 6).equals("010404")) {
            if (!ret.substring(6, 8).equals("FF")) {
                ELog.i("=======温度====" + Integer.parseInt(ret.substring(6, 10), 16));
                ELog.i("=======湿度====" + Integer.parseInt(ret.substring(10, 14), 16));

                BigDecimal wendu = new BigDecimal(Integer.parseInt(ret.substring(6, 10), 16));

                BigDecimal shidu = new BigDecimal(Integer.parseInt(ret.substring(10, 14), 16));

                BigDecimal bigDecimal = new BigDecimal("0.1");

                WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();

                WenShiDu wenShiDu = new WenShiDu("", "", "", "", wendu.multiply(bigDecimal) + "℃", shidu.multiply(bigDecimal) + "%",
                        "", 0, "1-401");
                wenShiDuDao.deleteAll();
                wenShiDuDao.insert(wenShiDu);

//                String wsd = "WSD;" + wendu.multiply(bigDecimal) + "℃" + ";" + shidu.multiply(bigDecimal) + "%" + ";" + "0" + "ug/m3";
//                sendMsg1(wsd.getBytes());
            }
        }

    }
}