package com.lh.zksockets.utils;

import android.os.Handler;
import android.os.Message;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.MicDatasDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.UIsetDataDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.MicDatas;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.data.model.WenShiDu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android_serialport_api.SerialPort;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class SerialPortUtil {


    private static SerialPort serialPort1, serialPort2, serialPort3;
    private static InputStream inputStream1, inputStream2, inputStream3;
    private static OutputStream outputStream1, outputStream2, outputStream3;
    private static Timer xiakeTimer;
    private static Handler readCradHandler;
    private static boolean isxiake;


    public static boolean open() {
        try {
            serialPort1 = new SerialPort(new File("/dev/ttyS1"), 9600, 0);
            serialPort2 = new SerialPort(new File("/dev/ttyS2"), 9600, 0);
//            serialPort3 = new SerialPort(new File("/dev/ttyS3"), 9600, 0);
            //获取打开的串口中的输入输出流，以便于串口数据的收发
            inputStream1 = serialPort1.getInputStream();
            outputStream1 = serialPort1.getOutputStream();

            inputStream2 = serialPort2.getInputStream();
            outputStream2 = serialPort2.getOutputStream();

//            inputStream3 = serialPort3.getInputStream();
//            outputStream3 = serialPort3.getOutputStream();
            return true;
        } catch (Exception e) {
            ELog.e("======open_ck=====打开串口异常");
            e.printStackTrace();
            return false;
        }
    }

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
                            try {
                                ELog.i("=========串口2===接收到了数据==size=====" + size);
                                ELog.i("=========串口2===接收到了数据=======" + new String(buffer, 0, size));
                                SportDataUtil.readSptdata(buffer, size);
                            } catch (Exception e) {
                                ELog.e("=======串口2==run: 数据读取异常===222222=====" + e.toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    ELog.e("=======串口2==run: 数据读取异常========" + e.toString());
                }


            }
        }.start();

    }

    public static void bjbokvid() {
        if (!isxiake) {
            closeXiakeTimer();
        }
    }

    public static void bjbnovid() {
        if (!isxiake) {
            ontimerXiake();
        }
    }

    private static void ontimerXiake() {
        closeXiakeTimer();
        xiakeTimer = new Timer();
        xiakeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMsg1("SKJBB".getBytes());
                makeML((long) 2);
                closeXiakeTimer();
            }
        }, 60 * 1500);
    }

    public static void sendMsg1(byte[] data) {
        try {
            synchronized (data) {
                if (data.length > 0) {
                    outputStream1.write(data);
                    outputStream1.flush();
                    ELog.i("=====sendMsg1=========串口数据发送成功");
                }
            }
        } catch (IOException e) {
            ELog.e("====sendMsg1===串口数据发送失败：" + e.toString());
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
                        } else if (mls[i].substring(0, 1).equals("5")) {
                            UDPUtil.doWangguan(mls[i]);
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
        if (data != null) {
            try {
                synchronized (data) {
                    if (data.length > 0) {
                        outputStream2.write(data);
                        outputStream2.flush();
                        ELog.i("============串口2===给单片机=======串口数据发送成功==================");
                    }
                }
            } catch (IOException e) {
                ELog.e("============串口2====给单片机=======串口数据发送失败==================" + e.toString());
            }
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
                            try {
                                String msg = new String(buffer, 0, size);
                                ELog.i("=========串口1===接收到了数据=======" + msg);
                                if (msg.length() > 3) {
                                    skMakeReadMsg(msg);
                                }
                            } catch (Exception e) {
                                ELog.e("======串口1===接收数据===Exception======" + e.toString());
                            }
                        }
                    }

                } catch (IOException e) {
                    ELog.e("=======串口1==run: 数据读取异常========" + e.toString());
                }
            }
        }.start();

    }

    public static void skMakeReadMsg(String msg) {
        if (msg.substring(0, 3).equals("VID")) {
            sendShipinType(msg);
        } else if (msg.substring(0, 3).equals("FWS")) {//复位
            sendFWstatus(msg);
        } else if (msg.substring(0, 3).equals("SKJ")) {//打开面板
            sendKJban(msg);
        } else if (msg.substring(0, 3).equals("MJD")) {//门禁
            makemenjin(msg);
        } else if (msg.substring(0, 3).equals("LUB")) {
            HttpUtil.setlubo(msg);
        } else if (msg.substring(0, 3).equals("JZF")) {
            sendShipinFenping(msg);
        } else if (msg.substring(0, 3).equals("USE")) {
            loginMsg(msg);
        } else if (msg.substring(0, 3).equals("ICK")) {
            shuaka(msg);
        } else if (msg.substring(0, 3).equals("VOL")) {
//            getYinliang();
        } else if (msg.substring(0, 3).equals("UIS")) {
            mbuiset(msg);
        } else if (msg.substring(0, 3).equals("MIC")) {
//            yinpin(msg);
        } else if (msg.substring(0, 3).equals("MBS")) {
            try {
                makeML(Long.valueOf(msg.substring(3)));
            } catch (Exception e) {
                ELog.i("=========串口1===接收到了数据====Long.valueOf==异常========" + e.toString());
            }
        }
    }

    private static void mbuiset(String msg) {
        UIsetDataDao uIsetDataDao = MyApplication.getDaoSession().getUIsetDataDao();
        if (uIsetDataDao.loadAll().size() != 0) {
            String uiMsg = "UIS;";
            for (int i = 1; i < uIsetDataDao.loadAll().size() + 1; i++) {
                uiMsg = uiMsg + uIsetDataDao.load((long) i).btn_status;
            }
            ELog.i("=======mbuiset======uiMsg====" + uiMsg);
            sendMsg1(uiMsg.getBytes());
        }
    }

    private static void getYinliang() {
        MicDatasDao micDatasDao = MyApplication.getDaoSession().getMicDatasDao();
        if (micDatasDao.loadAll().size() == 0) {
            micDatasDao.insert(new MicDatas((long) 1, "22", 0));
            micDatasDao.insert(new MicDatas((long) 2, "22", 0));
            micDatasDao.insert(new MicDatas((long) 3, "22", 0));
            micDatasDao.insert(new MicDatas((long) 4, "22", 0));
        }
        // MIC;221;221;220;220
        String micMsg = "MIC;" + micDatasDao.load((long) 1).mic_index + micDatasDao.load((long) 1).mic_status + ";"
                + micDatasDao.load((long) 2).mic_index + micDatasDao.load((long) 2).mic_status + ";"
                + micDatasDao.load((long) 3).mic_index + micDatasDao.load((long) 3).mic_status + ";"
                + micDatasDao.load((long) 4).mic_index + micDatasDao.load((long) 4).mic_status;
        ELog.i("=======getYinliang===micMsg====" + micMsg);
        sendMsg1(micMsg.getBytes());
    }

    private static void yinpin(String str) {
        synchronized (str) {
            MicDatasDao micDatasDao = MyApplication.getDaoSession().getMicDatasDao();
            if (micDatasDao.loadAll().size() == 0) {
                micDatasDao.insert(new MicDatas((long) 1, "22", 0));
                micDatasDao.insert(new MicDatas((long) 2, "22", 0));
                micDatasDao.insert(new MicDatas((long) 3, "22", 0));
                micDatasDao.insert(new MicDatas((long) 4, "22", 0));
            }
            ELog.i("=======yinpin===str====" + str);
            String msg = "";
            MicDatas micdata = micDatasDao.loadAll().get(0);
            if (str.substring(4).equals("JY")) {
                if (str.substring(0, 4).equals("MICA")) {
                    msg = "BB0154DD";
                    micDatasDao.update(new MicDatas((long) 1, micDatasDao.load((long) 1).mic_index, 0));
                } else if (str.substring(0, 4).equals("MICB")) {
                    msg = "BB0254DD";
                    micDatasDao.update(new MicDatas((long) 2, micDatasDao.load((long) 2).mic_index, 0));
                } else if (str.substring(0, 4).equals("MICC")) {
                    msg = "BB0354DD";
                    micDatasDao.update(new MicDatas((long) 3, micDatasDao.load((long) 3).mic_index, 0));
                } else if (str.substring(0, 4).equals("MICD")) {
                    msg = "BB0454DD";
                    micDatasDao.update(new MicDatas((long) 4, micDatasDao.load((long) 4).mic_index, 0));
                }
            } else {
                String hexstr = Integer.toHexString(Integer.valueOf(str.substring(4)));
                if (hexstr.length() == 1) {
                    hexstr = "0" + hexstr;
                }
                if (str.substring(0, 4).equals("MICA")) {
                    msg = "BB01" + hexstr + "DD";
                    micDatasDao.update(new MicDatas((long) 1, str.substring(4), 1));
                } else if (str.substring(0, 4).equals("MICB")) {
                    msg = "BB02" + hexstr + "DD";
                    micDatasDao.update(new MicDatas((long) 2, str.substring(4), 1));
                } else if (str.substring(0, 4).equals("MICC")) {
                    msg = "BB03" + hexstr + "DD";
                    micDatasDao.update(new MicDatas((long) 3, str.substring(4), 1));
                } else if (str.substring(0, 4).equals("MICD")) {
                    msg = "BB04" + hexstr + "DD";
                    micDatasDao.update(new MicDatas((long) 4, str.substring(4), 1));
                }
            }
            byte[] data = StringToBytes(msg);
            sendMsg3(data);
            ELog.i("=======yinpin===micDatasDao====" + micDatasDao.loadAll().toString());
        }
    }

    private static void sendMsg3(byte[] data) {
        try {
            synchronized (data) {
                if (data.length > 0) {
                    outputStream3.write(data);
                    outputStream3.flush();
                    ELog.i("=====串口3=========数据发送成功");
                }
            }
        } catch (IOException e) {
            ELog.e("====串口3===数据发送失败：" + e.toString());
        }
    }

    public static void readMsg3() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                byte[] buffer = new byte[1024];
                int size; //读取数据的大小
                try {
                    while (true && (size = inputStream3.read(buffer, 0, 1024)) > 0) {
                        if (size > 0) {
                            try {
                                String msg = new String(buffer, 0, size);
                                ELog.i("=========串口3===接收到了数据=======" + msg);
                                String ret = "";
                                for (int j = 0; j < size; j++) {
                                    String hex = Integer.toHexString(buffer[j] & 0xFF);
                                    if (hex.length() == 1) {
                                        hex = "0" + hex;
                                    }
                                    ret += hex.toUpperCase();
                                }
                                ELog.i("======串口3===接收到了数据====hex=======" + ret);
                            } catch (Exception e) {
                                ELog.e("======串口3===接收数据===Exception======" + e.toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    ELog.e("========串口3=run: 数据读取异常========" + e.toString());
                }
            }
        }.start();

    }


    private static void loginMsg(String msg) {
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        if (usersDao.loadAll().size() == 0) {
            sendMsg1("LOGIN;100;5".getBytes());
        } else {
            String[] msglist = msg.split(",");
            List<Users> users = usersDao.queryBuilder()
                    .where(UsersDao.Properties.Username.eq(msglist[1]))
                    .list();
            if (users.size() != 0) {
                Users user = users.get(0);
                if (user.user_status == 1) {
                    if (user.userPaw.equals(msglist[2])) {
                        user.setLogin_count(3);
                        usersDao.update(user);
                        sendMsg1("LOGIN;200;6".getBytes());
                    } else {
                        int count = user.login_count - 1;
                        if (count == 0) {
                            user.setUser_status(0);
                            sendMsg1("LOGIN;100;4".getBytes());
                        } else {
                            String msg1 = "LOGIN;100;" + count;
                            sendMsg1(msg1.getBytes());
                        }
                        user.setLogin_count(count);
                        usersDao.update(user);
                    }
                } else {
                    sendMsg1("LOGIN;100;8".getBytes());
                }
            } else {
                sendMsg1("LOGIN;100;7".getBytes());
            }
        }
    }

    public static void setReadCradNum(Handler addCardDialogHandler) {
        readCradHandler = addCardDialogHandler;
    }

    public static void disReadCradNum() {
        readCradHandler = null;
    }

    private static void shuaka(String msg) {
        String kahao = Long.parseLong(msg.substring(3), 16) + "";
        if (kahao.length() == 9) {
            kahao = "0" + kahao;
        } else if (kahao.length() == 8) {
            kahao = "00" + kahao;
        } else if (kahao.length() == 7) {
            kahao = "000" + kahao;
        } else if (kahao.length() == 6) {
            kahao = "0000" + kahao;
        } else if (kahao.length() == 5) {
            kahao = "00000" + kahao;
        } else if (kahao.length() == 4) {
            kahao = "000000" + kahao;
        } else if (kahao.length() == 3) {
            kahao = "0000000" + kahao;
        } else if (kahao.length() == 2) {
            kahao = "00000000" + kahao;
        } else if (kahao.length() == 1) {
            kahao = "000000000" + kahao;
        }
        ELog.d("=====shuaka==kahao=======" + kahao);
        if (readCradHandler != null) {
            Message message = new Message();
            message.obj = kahao;
            message.what = 369;
            readCradHandler.sendMessage(message);
            sendMsg1("ICKERROR".getBytes());
        } else {
            IcCardDao icCardDao = MyApplication.getDaoSession().getIcCardDao();
            List<IcCard> icCards = icCardDao.queryBuilder()
                    .where(IcCardDao.Properties.Card_no.eq(kahao))
                    .orderAsc(IcCardDao.Properties.CardNumId)
                    .list();
            if (icCards.size() != 0) {
                sendMsg1("SKJAA".getBytes());
                sendCardLog(kahao);
            } else {
                sendMsg1("ICKERROR".getBytes());
            }
        }
    }

    public static void wsdSendLog(WenShiDu wenShiDu) {
        if (wenShiDu == null) {
            return;
        }
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("ip", zkInfoDao.loadAll().get(0).zkip)
                .add("CO2", wenShiDu.CO2)
                .add("HCHO", wenShiDu.HCHO)
                .add("PM10", wenShiDu.PM10)
                .add("PM25", wenShiDu.PM25)
                .add("VOC", wenShiDu.VOC)
                .add("shiStr", wenShiDu.shiStr)
                .add("wenStr", wenShiDu.wenStr)
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/env_log")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======wsdSendLog===onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========wsdSendLog==数据=======" + responseText);
//                Gson gson = new Gson();
//                HttpData httpData = gson.fromJson(responseText, HttpData.class);
//                ELog.e("==========数据==11=====" + httpData.toString());
            }
        });
    }

    private static void sendCardLog(String kahao) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("card_no", kahao)
                .add("detail", "刷卡打开 " + zkInfoDao.loadAll().get(0).zkname + "(" + zkInfoDao.loadAll().get(0).zkip + ") 的操作面板")
                .add("addtime", DateUtil.getNow())
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/add_ic_logs")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("==========数据=======" + responseText);
//                Gson gson = new Gson();
//                HttpData httpData = gson.fromJson(responseText, HttpData.class);
//                ELog.e("==========数据==11=====" + httpData.toString());
            }
        });
    }

    private static void makemenjin(String msg) {
        DoorInfoDao doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();
        if (doorInfoDao.loadAll().size() != 0 && doorInfoDao.loadAll().get(0).isStart == 1) {
            DoorUtil.opendoor(doorInfoDao.loadAll().get(0).IP);
        } else {
            try {
                makeML(Long.valueOf(msg.substring(3)));
            } catch (Exception e) {
                ELog.i("=========门禁====Long.valueOf==异常========" + e.toString());
            }
        }
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
            try {
                if (id == 1) {
                    isxiake = false;
                    closeXiakeTimer();
                    sendMsg("{[REY5:DT:A005]<CLOSE>}".getBytes());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendMsg("{[REY6:DT:A005]<CLOSE>}".getBytes());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
                if (mLsListsDao.loadAll().size() != 0) {
                    if (mLsListsDao.load(id) == null) {
                        return;
                    }
                    mLsListsDao.load(id).setTime(DateUtil.getNow());
                    mLsListsDao.update(mLsListsDao.load(id));
                    String strMls = mLsListsDao.load(id).strMLs;
                    ELog.i("========串口1===========makeML=================" + id);
                    if (id == 1 || id == 2 || id == 215 || id == 45) {
                        getEventId(strMls);
                    } else {
                        makeBaojing(strMls);
                    }
                }
                if (id == 2) {
                    isxiake = true;
                    DiannaoUDPUtil.diannaogj();
                    sendMsg(StringToBytes("BB0A0180"));
                    setXiakeTimer();
                }
                DeviceStatusUtil.setDeviceStatus(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getEventId(String strMls) {
        if (strMls.length() != 0) {
            String[] mls = strMls.split(",");
            for (int i = 0; i < mls.length; i++) {
                ELog.i("=======getEventId========" + mls[i]);
                try {
                    if (!mls[i].equals("1") && !mls[i].equals("2") && !mls[i].equals("215") && !mls[i].equals("45")) {
                        makeML(Long.valueOf(mls[i]));
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setXiakeTimer() {
        closeXiakeTimer();
        xiakeTimer = new Timer();
        xiakeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMsg("{[REY6:DT:A004]<OPEN>}".getBytes());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMsg("{[REY5:DT:A004]<OPEN>}".getBytes());
                makeML((long) 215);
                closeXiakeTimer();
            }
        }, 60 * 1500);
    }

    private static void closeXiakeTimer() {
        if (xiakeTimer != null) {
            xiakeTimer.cancel();
            xiakeTimer = null;
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
//            if (dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).dangerOutStatus == 0) {
//                TimerUtils.setHuifuDangerOutstatus(ml.substring(2, 3), dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
//            }
        } else if (ml.substring(4).equals("0")) {
            msg = "{[ARM" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
//            if (dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).dangerOutStatus == 1) {
//                TimerUtils.setHuifuDangerOutstatus(ml.substring(2, 3), dangerOutDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
//            }
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
            IoPortData ioPortData = ioPortDataDao.load(Long.valueOf(ml.substring(2, 3)));
            ioPortData.setIoOutStatus(1);
            ioPortDataDao.update(ioPortData);
//            if (ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).ioOutStatus == 0) {
//                TimerUtils.setHuifuIoOutstatus(ml.substring(2, 3), ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
//            }
        } else if (ml.substring(4).equals("0")) {
            msg = "{[IOL" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
            IoPortData ioPortData = ioPortDataDao.load(Long.valueOf(ml.substring(2, 3)));
            ioPortData.setIoOutStatus(0);
            ioPortDataDao.update(ioPortData);
//            if (ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).ioOutStatus == 1) {
//                TimerUtils.setHuifuIoOutstatus(ml.substring(2, 3), ioPortDataDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
//            }
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
//            if (ml.substring(2, 3).equals("7")) {
//                msg = "{[REY8:DT:A005]<CLOSE>}";
//                byte[] data = msg.getBytes();
//                sendMsg(data);
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            } else if (ml.substring(2, 3).equals("8")) {
//                msg = "{[REY7:DT:A005]<CLOSE>}";
//                byte[] data = msg.getBytes();
//                sendMsg(data);
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            msg = "{[REY" + ml.substring(2, 3) + ":DT:A004]<OPEN>}";
            if (!ml.substring(2, 3).equals("5") && !ml.substring(2, 3).equals("6")) {
                if (jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).jdqStatus == 0) {
                    TimerUtils.setHuifuJDQstatus(ml.substring(2, 3), jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).time, 0);
                }
            }
        } else if (ml.substring(4).equals("0")) {
            if (ml.substring(2, 3).equals("7")) {
                msg = "{[REY8:DT:A004]<OPEN>}";
                byte[] data = msg.getBytes();
                sendMsg(data);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (ml.substring(2, 3).equals("8")) {
                msg = "{[REY7:DT:A004]<OPEN>}";
                byte[] data = msg.getBytes();
                sendMsg(data);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = "{[REY" + ml.substring(2, 3) + ":DT:A005]<CLOSE>}";
            if (!ml.substring(2, 3).equals("5") && !ml.substring(2, 3).equals("6")) {
                if (jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).jdqStatus == 1) {
                    TimerUtils.setHuifuJDQstatus(ml.substring(2, 3), jdqStatusDao.load(Long.valueOf(ml.substring(2, 3))).time, 1);
                }
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
            serialCommandDao.detachAll();
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

    private static void sendShipinType(String str) {
        synchronized (str) {
            if (str.substring(0, 4).equals("VIDA")) {
                sendMsg(StringToBytes("BB0" + str.substring(6) + "0" + str.substring(4, 5) + "80"));
            } else if (str.substring(0, 4).equals("VIDC")) {
                sendMsg(StringToBytes("BB050" + str.substring(4) + "80"));
            }
            if (str.substring(4, 5).equals("1")) {
                makeML(Long.valueOf("5003"));
            } else {
                makeML(Long.valueOf("5004"));
            }
        }
    }

    private static void sendShipinFenping(String str) {
        synchronized (str) {
            String msg = "";
            if (str.substring(0, 5).equals("JZFGB")) {
                msg = "BB0300090" + str.substring(5) + "000055";
            } else if (str.equals("JZFFP2")) {
                msg = "BB02005200000055";
            } else if (str.equals("JZFFP1")) {
                msg = "BB02005100000055";
                sendshipinCommand(msg);
                try {
                    sleep(600);
                    sendshipinCommand("BB04000201000055");
                    sleep(600);
                    sendshipinCommand("BB04000202020055");
                    sleep(600);
                    sendshipinCommand("BB04000203030055");
                    sleep(600);
                    sendshipinCommand("BB04000204040055");
                    sleep(600);
                    sendshipinCommand("BB04000205050055");
                    sleep(600);
                    sendshipinCommand("BB04000206060055");
                    sleep(600);
                    sendshipinCommand("BB04000207070055");
                    sleep(600);
                    msg = "BB04000208080055";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (str.substring(0, 5).equals("JZFAA")) {
                msg = "BB0400020" + str.substring(5) + "000055";
            } else if (str.substring(0, 5).equals("JZFBB")) {
                msg = "BB0400020" + str.substring(5) + "010055";
            }
            sendshipinCommand(msg);

        }
    }

    private static void sendshipinCommand(String msg) {
        byte[] data1 = "{[COM0:DT:H008]<".getBytes();
        byte[] data2 = StringToBytes(msg);
        byte[] data3 = ">}".getBytes();
        byte[] data = new byte[data1.length + data2.length + data3.length];
        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);
        System.arraycopy(data3, 0, data, data1.length + data2.length, data3.length);
        ELog.i("========分屏=========" + msg);
        sendMsg(data);
    }


    private static void sendFWstatus(String str) {
        synchronized (str) {
            if (str.equals("FWS0")) {
                sendMsg("{[VIDB:DT:A035]<0,2;1,3;2,4;3,5;4,6;5,7;6,8;7,0;8,1>}".getBytes());
            }
        }
    }

    public static void sendKJban(String str) {
        synchronized (str) {
            if (str.equals("SKJAA")) {
                sendMsg1("SKJAA".getBytes());
            }
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
