package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.WenShiDu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.SerialPort;

import static java.lang.Thread.sleep;

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


    private static int bslength = 0;
    private static byte[] buffer1 = new byte[1024];
    private static byte[] buffer2 = new byte[1024];

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
                            ELog.i("=========串口2===接收到了数据==size=====" + size);
                            String msg = new String(buffer, 0, size);
                            ELog.i("=========串口2===接收到了数据=======" + msg);

                            if (bslength > 50) {
                                bslength = 0;
                                buffer1 = new byte[1024];
                                buffer2 = new byte[1024];
                            }

                            System.arraycopy(buffer, 0, buffer1, bslength, size);
                            bslength = bslength + size;
                            makeData(msg);
                        }
                    }

                } catch (IOException e) {
                    ELog.i("=========run: 数据读取异常========" + e.toString());
                }


            }
        }.start();

    }

    private static void makeData(String msg) {
        if (msg.indexOf("]") != -1) {
            String msgdata = new String(buffer1, 0, bslength);
            ELog.i("============msgdata====11111=============" + msgdata);
            if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[OK]")) {
                bslength = bslength - 4;
                System.arraycopy(buffer1, 4, buffer2, 0, bslength);
                ELog.i("==========OK======44444444444444444=============" + new String(buffer2, 0, bslength));
                buffer1 = new byte[1024];
                System.arraycopy(buffer2, 0, buffer1, 0, bslength);
                buffer2 = new byte[1024];
                makeData(new String(buffer1, 0, bslength));
            } else if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[COM7]")) {

                if (msgdata.indexOf(">") != -1) {
                    if (bslength - 8 == 9) {
                        buffer2 = new byte[1024];
                        System.arraycopy(buffer1, 7, buffer2, 0, 9);

                        setWenshidu();

                        bslength = bslength - 17;
                        System.arraycopy(buffer1, 17, buffer2, 0, bslength);
                        ELog.i("===========COM7=====111111111111111111=============" + new String(buffer2, 0, bslength));
                        buffer1 = new byte[1024];
                        System.arraycopy(buffer2, 0, buffer1, 0, bslength);
                        buffer2 = new byte[1024];
                        makeData(new String(buffer1, 0, bslength));

                    } else if (bslength - 16 == 9) {
                        buffer2 = new byte[1024];
                        int length1 = msgdata.indexOf(">") + 1;
                        System.arraycopy(buffer1, 7, buffer2, 0, length1 - 8);
                        System.arraycopy(buffer1, length1 + 7, buffer2, length1 - 8, bslength - length1 - 8);

                        setWenshidu();

                        bslength = bslength - 25;
                        System.arraycopy(buffer1, 25, buffer2, 0, bslength);
                        ELog.i("==========COM7======2222222222222222222222=============" + new String(buffer2, 0, bslength));
                        buffer1 = new byte[1024];
                        System.arraycopy(buffer2, 0, buffer1, 0, bslength);
                        buffer2 = new byte[1024];
                        makeData(new String(buffer1, 0, bslength));
                    }

                }

            } else if (msgdata.substring(0, msgdata.indexOf("]") + 1).equals("[ARM0]")) {
                if (msgdata.indexOf(">") != -1) {
                    if (bslength - 8 == 1) {
                        buffer2 = new byte[1024];
                        System.arraycopy(buffer1, 7, buffer2, 0, 1);

                        baojin(Integer.toHexString(buffer2[0] & 0xFF));

                        bslength = bslength - 9;
                        System.arraycopy(buffer1, 9, buffer2, 0, bslength);
                        ELog.i("===========ARM0=====3333333333333333=============" + new String(buffer2, 0, bslength));
                        buffer1 = new byte[1024];
                        System.arraycopy(buffer2, 0, buffer1, 0, bslength);
                        buffer2 = new byte[1024];
                        makeData(new String(buffer1, 0, bslength));
                    }

                }
            } else {
                if (msgdata.indexOf(">") != -1) {
                    ELog.i("==========其它接收数据=======1111111111111111111111=====" + msgdata.indexOf(">"));
                    bslength = bslength - msgdata.indexOf(">") - 1;
                    System.arraycopy(buffer1, msgdata.indexOf(">") + 1, buffer2, 0, bslength);
                    ELog.i("===========其它接收数据======22222222222222222222222======" + new String(buffer2, 0, bslength));
                    buffer1 = new byte[1024];
                    System.arraycopy(buffer2, 0, buffer1, 0, bslength);
                    buffer2 = new byte[1024];
                    makeData(new String(buffer1, 0, bslength));
                }

            }


        }


    }

    private static void setWenshidu() {

        String ret = "";
        for (int j = 0; j < 9; j++) {
            String hex = Integer.toHexString(buffer2[j] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
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

                WenShiDu wenShiDu = new WenShiDu(wendu.multiply(bigDecimal) + "℃", shidu.multiply(bigDecimal) + "%",
                        wenShiDuDao.loadAll().get(0).timeStr, wenShiDuDao.loadAll().get(0).serialportML);
                wenShiDuDao.deleteAll();
                wenShiDuDao.insert(wenShiDu);

                String wsd = "WSD;" + wendu.multiply(bigDecimal) + "℃" + ";" + shidu.multiply(bigDecimal) + "%";
                sendMsg1(wsd.getBytes());
            }
        }

    }

    public static void sendMsg1(byte[] data) {
        try {
            synchronized (data) {
                if (data.length > 0) {
                    outputStream1.write(data);
                    outputStream1.flush();
                    ELog.e("=====sendMsg1=========串口数据发送成功");
                }
            }
        } catch (IOException e) {
            ELog.e("====sendSerialPort: 串口数据发送失败：" + e.toString());
        }
    }


    private static void baojin(String hex) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            return;
        }
        String str2jz = JinzhiUtil.get2String(hex);
        ELog.i("=========报警口==000====" + str2jz);
        if (str2jz != null) {
            if (str2jz.substring(0, 1).equals(ioYuanDao.load((long) 4).dangerIoStatus + "")) {
                ELog.i("=========报警口======" + 4);
                makeBaojing(ioYuanDao.load((long) 4).dangerMl);
            } else {
                makeBaojing(ioYuanDao.load((long) 4).noDangerMl);
            }

            if (str2jz.substring(1, 2).equals(ioYuanDao.load((long) 3).dangerIoStatus + "")) {
                ELog.i("=========报警口======" + 3);
                makeBaojing(ioYuanDao.load((long) 3).dangerMl);
            } else {
                makeBaojing(ioYuanDao.load((long) 3).noDangerMl);
            }

            if (str2jz.substring(2, 3).equals(ioYuanDao.load((long) 2).dangerIoStatus + "")) {
                ELog.i("=========报警口======" + 2);
                makeBaojing(ioYuanDao.load((long) 2).dangerMl);
            } else {
                makeBaojing(ioYuanDao.load((long) 2).noDangerMl);
            }

            if (str2jz.substring(3, 4).equals(ioYuanDao.load((long) 1).dangerIoStatus + "")) {
                ELog.i("=========报警口======" + 1);
                makeBaojing(ioYuanDao.load((long) 1).dangerMl);
            } else {
                makeBaojing(ioYuanDao.load((long) 1).noDangerMl);
            }

        }


    }

    private static void makeBaojing(String strMls) {
        synchronized (strMls) {
            if (strMls.length() != 0) {
                String[] mls = strMls.split(",");
                for (int i = 0; i < mls.length; i++) {
                    ELog.i("=======串口1============makeBaojing========" + mls[i]);
                    try {
                        if (mls[i].substring(0, 1).equals("1")) {
                            doSerialPort(mls[i]);
                        } else if (mls[i].substring(0, 1).equals("2")) {
                            doJDQ(mls[i]);
                        } else if (mls[i].substring(0, 1).equals("3")) {
                            doIO(mls[i]);
                        } else if (mls[i].substring(0, 1).equals("4")) {
                            doDanger(mls[i]);
                        }
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void sendMsg(byte[] data) {
        try {
            synchronized (data) {
                if (data.length > 0) {
                    outputStream2.write(data);
                    outputStream2.flush();
                    ELog.e("============串口===给单片机=======串口数据发送成功==================");
                }
            }
        } catch (IOException e) {
            ELog.e("============串口====给单片机=======串口数据发送失败==================" + e.toString());
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
                            if (msg.length() > 3) {
                                if (msg.substring(0, 3).equals("VID")) {
                                    sendShipinType(msg);
                                } else if (msg.substring(0, 3).equals("LUB")) {
                                    HttpUtil.setlubo(msg);
                                } else if (msg.substring(0, 3).equals("MBS")) {
                                    try {
                                        makeML(Long.valueOf(msg.substring(3)));
                                    } catch (Exception e) {
                                        ELog.i("=========串口1===接收到了数据====Long.valueOf==异常========" + e.toString());
                                    }
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


    public static byte[] StringToBytes(String str) {
        if (str.length() % 2 == 1) {   //是奇数
            return null;
        }
        try {
            byte[] bytes = new byte[str.length() / 2];
            for (int i = 0; i < str.length(); i = i + 2) {
                bytes[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
            }
            return bytes;
        } catch (Exception e) {
            return null;
        }

    }

    public static void makeML(Long id) {
        synchronized (id) {
            MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
            if (mLsListsDao.loadAll().size() != 0) {
                if (mLsListsDao.load(id) == null) {
                    return;
                }
                String strMls = mLsListsDao.load(id).strMLs;
                ELog.i("========串口1===========makeML=================" + id);
                makeBaojing(strMls);
            }
        }

    }

    private static void doDanger(String ml) {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        ELog.i("========doDanger====msg====" + ml);
        if (dangerOutDao.loadAll().size() == 0) {
            return;
        }

        String msg = "";
        if (ml.substring(4).equals("1")) {
            msg = "{[ARM" + ml.substring(2, 3) + ":DT:A004]<OPEN>}";
            if (dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).dangerOutStatus == 0) {
                TimerUtils.setHuifuDangerOutstatus(ml.substring(2, 3), dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
            }
        } else if (ml.substring(4).equals("0")) {
            msg = "{[ARM" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
            if (dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).dangerOutStatus == 1) {
                TimerUtils.setHuifuDangerOutstatus(ml.substring(2, 3), dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
            }
        }
        ELog.i("========doDanger====msg====" + msg);
        byte[] data = msg.getBytes();
        sendMsg(data);


    }

    private static void doIO(String ml) {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            return;
        }

        String msg = "";
        if (ml.substring(4).equals("1")) {
            msg = "{[IOL" + ml.substring(2, 3) + ":DT:A004]<OPEN>}";
            if (ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).ioOutStatus == 0) {
                TimerUtils.setHuifuIoOutstatus(ml.substring(2, 3), ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
            }
        } else if (ml.substring(4).equals("0")) {
            msg = "{[IOL" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
            if (ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).ioOutStatus == 1) {
                TimerUtils.setHuifuIoOutstatus(ml.substring(2, 3), ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
            }
        }
        ELog.i("========doIO====msg====" + msg);
        byte[] data = msg.getBytes();
        sendMsg(data);

    }

    public static void doJDQ(String ml) {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            return;
        }
        String msg = "";
        if (ml.substring(4).equals("1")) {

            if (ml.substring(2, 3).equals("7")) {
                msg = "{[REY8:DT:A005]<CLOSE>}";
                byte[] data = msg.getBytes();
                sendMsg(data);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (ml.substring(2, 3).equals("8")) {
                msg = "{[REY7:DT:A005]<CLOSE>}";
                byte[] data = msg.getBytes();
                sendMsg(data);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = "{[REY" + ml.substring(2, 3) + ":DT:A004]<OPEN>}";
            if (jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).jdqStatus == 0) {
                TimerUtils.setHuifuJDQstatus(ml.substring(2, 3), jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
            }
        } else if (ml.substring(4).equals("0")) {
            msg = "{[REY" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
            if (jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).jdqStatus == 1) {
                TimerUtils.setHuifuJDQstatus(ml.substring(2, 3), jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
            }
        }
        ELog.i("========doJDQ====msg====" + msg);
        byte[] data = msg.getBytes();
        sendMsg(data);

    }


    public static void doSerialPort(String str) {
        synchronized (str) {
            ELog.i("===========doSerialPort========str========" + str);
            if (str.equals("")) {
                return;
            }
            SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
            if (serialCommandDao.loadAll().size() == 0) {
                return;
            }
            SerialCommand spML = serialCommandDao.load(Long.valueOf(str.substring(2)));
            if (spML == null) {
                return;
            }
            ELog.i("==========doSerialPort===========spML========" + spML.toString());
            if (spML.commandStr.length() != 0) {
                byte[] data = null;
                if (spML.jinZhi == 16) {
                    String msg1 = "";
                    int datalength = spML.commandStr.length() / 2;
                    if (datalength < 10) {
                        msg1 = "{[COM" + (spML.sId - 1) + ":DT:H00" + datalength + "]<";
                    } else if (datalength >= 10 && datalength < 100) {
                        msg1 = "{[COM" + (spML.sId - 1) + ":DT:H0" + datalength + "]<";
                    } else if (datalength >= 100) {
                        msg1 = "{[COM" + (spML.sId - 1) + ":DT:H" + datalength + "]<";
                    }
                    ELog.i("=====doSerialPort===msg===0000000=====" + msg1);
                    byte[] data1 = msg1.getBytes();
                    byte[] data2 = StringToBytes(spML.commandStr);
                    if (data2 == null) {
                        return;
                    }
                    byte[] data3 = ">}".getBytes();
                    data = new byte[data1.length + data2.length + data3.length];

                    System.arraycopy(data1, 0, data, 0, data1.length);
                    System.arraycopy(data2, 0, data, data1.length, data2.length);
                    System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);

                } else {
                    String msg = "";
                    if (spML.commandStr.length() < 10) {
                        msg = "{[COM" + (spML.sId - 1) + ":DT:A00" + spML.commandStr.length() + "]<" + spML.commandStr + ">}";
                    } else if (spML.commandStr.length() >= 10 && spML.commandStr.length() < 100) {
                        msg = "{[COM" + (spML.sId - 1) + ":DT:A0" + spML.commandStr.length() + "]<" + spML.commandStr + ">}";
                    } else if (spML.commandStr.length() >= 100) {
                        msg = "{[COM" + (spML.sId - 1) + ":DT:A" + spML.commandStr.length() + "]<" + spML.commandStr + ">}";
                    }
                    ELog.i("=====doSerialPort===============msg================111111=====" + msg);
                    data = msg.getBytes();
                }
                sendMsg(data);
            }
        }

    }


    public static void sendShipinType(String str) {
        synchronized (str) {
            String msg = "";
            if (str.substring(0, 4).equals("VIDA")) {
                msg = "{[VIDA:DT:A003]<" + str.substring(4) + ">}";
            } else if (str.substring(0, 4).equals("VIDC")) {
                msg = "{[VIDC:DT:A001]<" + str.substring(4) + ">}";
            }
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
        checkoutBitList.add("NONE");
        checkoutBitList.add("ODD");
        checkoutBitList.add("EVEN");
        checkoutBitList.add("MAAR");
        checkoutBitList.add("SPACE");
        return checkoutBitList;
    }


    public static List<String> getBaudRateDatas() {
        List<String> baudRateList = new ArrayList<>();
//        baudRateList.add("600");
        baudRateList.add("1200");
        baudRateList.add("2400");
        baudRateList.add("4800");
        baudRateList.add("9600");
        baudRateList.add("19200");
        baudRateList.add("38400");
        baudRateList.add("57600");
        baudRateList.add("115200");


//        baudRateList.add("128000");
//        baudRateList.add("256000");
//
//        baudRateList.add("110");
//        baudRateList.add("300");
//
//        baudRateList.add("14400");
//        baudRateList.add("56000");


        return baudRateList;
    }


}
