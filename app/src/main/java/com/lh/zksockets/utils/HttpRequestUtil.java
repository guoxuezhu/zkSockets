package com.lh.zksockets.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.DbDao.ZksDataDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.DoorInfo;
import com.lh.zksockets.data.model.EventKejianRest;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.SerialResult;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.data.model.ZkInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestUtil {


    private static Gson gson = new Gson();

    public static String updataSportInfo(Multimap parms) {
        String sportNumer = parms.getString("sportNum");
        List<SerialCommand> serialCommandlist = gson.fromJson(parms.getString("sportMls"), new TypeToken<List<SerialCommand>>() {
        }.getType());
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
        serialPortDataDao.update(new SerialPortData(Long.valueOf(sportNumer), "串口" + sportNumer, parms.getString("deviceName"),
                Integer.valueOf(parms.getString("baudRateId")), parms.getString("baudRate"),
                Integer.valueOf(parms.getString("checkoutBitId")), parms.getString("checkoutBit"),
                Integer.valueOf(parms.getString("dataBitId")), parms.getString("dataBit"),
                Integer.valueOf(parms.getString("stopBitId")), parms.getString("stopBit"),
                Integer.valueOf(parms.getString("jinZhi"))));
        for (int i = 0; i < serialCommandlist.size(); i++) {
            serialCommandDao.update(serialCommandlist.get(i));
        }
        String spStr = parms.getString("baudRate") + ",n,8,1";
        String msg = "{[COM" + (Long.valueOf(sportNumer) - 1) + ":ST:A0" + spStr.length() + "]<" + spStr + ">}";
        ELog.i("========updataSportInfo==SerialPortUtil===" + msg);
        byte[] data = msg.getBytes();
        SerialPortUtil.sendMsg(data);
        return gson.toJson(new HttpResult("200", "", true, null));

    }

    public static String getSportInfo(Multimap parms) {
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
        if (serialPortDataDao.loadAll().size() < 4) {
            for (int i = 1; i < 9; i++) {
                serialPortDataDao.insert(new SerialPortData((long) i, "串口" + i, "", 3,
                        "9600", 0, "NONE", 0, "8", 0, "1", 10));
                for (int j = 1; j < 31; j++) {
                    if (j >= 10) {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "" + j), i, j, "1-" + i + "" + j, "", "", 10));
                    } else {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "0" + j), i, j, "1-" + i + "0" + j, "", "", 10));
                    }
                }
            }
        }
        String sportNum = parms.getString("sportNum");
        List<SerialCommand> serialCommands = serialCommandDao.queryBuilder()
                .where(SerialCommandDao.Properties.SId.eq(sportNum))
                .orderAsc(SerialCommandDao.Properties.MlId)
                .list();

        SerialResult serialResult = new SerialResult(serialPortDataDao.load(Long.valueOf(sportNum)), serialCommands);
        return gson.toJson(new HttpResult("200", "", true, serialResult));

    }

    public static String getDangerInfo(Multimap parms) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            ioYuanDao.insert(new IOYuan((long) 1, "报警1", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 2, "报警2", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 3, "报警3", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 4, "报警4", "", 0, "", ""));
        }
        return gson.toJson(new HttpResult("200", "", true, ioYuanDao.loadAll()));
    }

    public static String updataDangerInfo(Multimap parms) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        List<IOYuan> dangerIoYuans = gson.fromJson(parms.getString("dangerDatas"), new TypeToken<List<IOYuan>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            ioYuanDao.update(dangerIoYuans.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getEventList(Multimap parms) {
        ZksDatasUtil.getEventDatas();
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        return gson.toJson(new HttpResult("200", "", true, mLsListsDao.loadAll()));
    }

    public static String updataEventInfo(Multimap parms) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        List<MLsLists> mLsLists = gson.fromJson(parms.getString("eventDatas"), new TypeToken<List<MLsLists>>() {
        }.getType());
        ELog.i("==============1==333====" + mLsLists.toString());
        for (int i = 0; i < mLsLists.size(); i++) {
            mLsListsDao.update(mLsLists.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String setWsdpm(AsyncHttpServerRequest request) {
        String[] wsdpm = request.getPath().split(",");
        if (wsdpm.length > 15) {
            WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();
            BigDecimal voc = new BigDecimal(wsdpm[5]);
            BigDecimal hcho = new BigDecimal(wsdpm[6]);
            BigDecimal wendu = new BigDecimal(wsdpm[9]);
            BigDecimal shidu = new BigDecimal(wsdpm[8]);

            BigDecimal bigDecimal = new BigDecimal("0.1");

            wenShiDuDao.deleteAll();
            wenShiDuDao.insert(new WenShiDu(wsdpm[4] + "ppm", voc.multiply(bigDecimal) + "ug/m3",
                    hcho.multiply(bigDecimal) + "ug/m3", wsdpm[7] + "ug/m3",
                    wendu.multiply(bigDecimal) + "℃", shidu.multiply(bigDecimal) + "%RH",
                    wsdpm[10] + "ug/m3", 1, ""));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getJDQList(Multimap parms) {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            jdqStatusDao.insert(new JDQstatus((long) 1, "继电器1", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 2, "继电器2", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 3, "继电器3", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 4, "继电器4", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 5, "继电器5", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 6, "继电器6", "", 1, 1));
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器7", "", 1, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器8", "", 1, 180));
        }
        return gson.toJson(new HttpResult("200", "", true, jdqStatusDao.loadAll()));
    }

    public static String updataJdqInfo(Multimap parms) {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        List<JDQstatus> jdQstatuses = gson.fromJson(parms.getString("jdqDatas"), new TypeToken<List<JDQstatus>>() {
        }.getType());
        ELog.i("==============1==333====" + jdQstatuses.toString());
        for (int i = 0; i < jdQstatuses.size(); i++) {
            jdqStatusDao.update(jdQstatuses.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getLuboList(Multimap parms) {
        LuboInfoDao luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        if (luboInfoDao.loadAll().size() == 0) {
            luboInfoDao.insert(new LuboInfo("", "", "", "", 0));
        }
        return gson.toJson(new HttpResult("200", "", true, luboInfoDao.loadAll()));
    }

    public static String updataLuboInfo(Multimap parms) {
        LuboInfoDao luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        LuboInfo luboInfos = gson.fromJson(parms.getString("luboDatas"), LuboInfo.class);
        luboInfoDao.deleteAll();
        luboInfoDao.insert(luboInfos);
        return gson.toJson(new HttpResult("200", "", true, null));
    }
//
//    public static String getMqttinfo(Multimap parms) {
//        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
//        if (baseInfoDao.loadAll().size() == 0) {
//            baseInfoDao.insert(new BaseInfo("", "",
//                    "", java.util.UUID.randomUUID().toString(), 0));
//        }
//        return gson.toJson(new HttpResult("200", "", true, baseInfoDao.loadAll().get(0)));
//    }
//
//    public static String updataMqttInfo(Multimap parms) {
//        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
//        BaseInfo baseInfo = gson.fromJson(request.getQuery().getString("mqttData"), BaseInfo.class);
//        baseInfoDao.deleteAll();
//        baseInfoDao.insert(baseInfo);
//        return gson.toJson(new HttpResult("200", "", true, null));
//    }


    public static String getZkBaseInfo(Multimap parms) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            zkInfoDao.insert(new ZkInfo("", "0.0.0.0", "1.0.1", "1", 0,
                    "http://192.168.0.115/", java.util.UUID.randomUUID().toString(), 0));
        }
        return gson.toJson(new HttpResult("200", "", true, zkInfoDao.loadAll().get(0)));
    }

    public static String updataZkBaseInfo(Multimap parms) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        ZkInfo zkInfo = gson.fromJson(parms.getString("zkbaseInfoData"), ZkInfo.class);
        zkInfo.setUuid(zkInfoDao.loadAll().get(0).uuid);
        zkInfoDao.deleteAll();
        zkInfoDao.insert(zkInfo);
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getIoOutinfo(Multimap parms) {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "io输出" + i, "", 0, 10));
            }
        }
        return gson.toJson(new HttpResult("200", "", true, ioPortDataDao.loadAll()));
    }

    public static String updataIoOutInfo(Multimap parms) {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        List<IoPortData> ioPortDatas = gson.fromJson(parms.getString("ioOutDatas"), new TypeToken<List<IoPortData>>() {
        }.getType());
        ELog.i("==============1==333====" + ioPortDatas.toString());
        for (int i = 0; i < ioPortDatas.size(); i++) {
            ioPortDataDao.update(ioPortDatas.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getDangerOutInfo(Multimap parms) {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                dangerOutDao.insert(new DangerOut((long) i, "报警输出" + i, "", 1, 10));
            }
        }
        return gson.toJson(new HttpResult("200", "", true, dangerOutDao.loadAll()));
    }

    public static String updataDangerOutInfo(Multimap parms) {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        List<DangerOut> dangerOuts = gson.fromJson(parms.getString("dangerOutDatas"), new TypeToken<List<DangerOut>>() {
        }.getType());
        ELog.i("=============1==333====" + dangerOuts.toString());
        for (int i = 0; i < dangerOuts.size(); i++) {
            dangerOutDao.update(dangerOuts.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getWSD(Multimap parms) {
        try {
            WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();
            if (wenShiDuDao.loadAll().size() != 0) {
                return gson.toJson(new HttpResult("200", "", true, wenShiDuDao.loadAll()));
            }
        } catch (Exception e) {
            ELog.d("=========getWSD===Exception=======" + e.toString());
            return gson.toJson(new HttpResult("-200", "温湿度数据错误", false, null));
        }
        return gson.toJson(new HttpResult("200", "无数据", true, null));
    }

    public static String updataMsg(Multimap parms) {
        String msg = parms.getString("msg");
        if (msg.equals("icdata")) {
            //更新卡号
            getIcData();
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    private static void getIcData() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/get_ic_card_list?zk_ip=" + zkInfoDao.loadAll().get(0).zkip)
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
                ELog.e("=======getIcdata===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========getIcdata==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<IcCard>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<IcCard>>>>() {
                    }.getType());
                    ELog.i("=========ic=====数据=======" + httpRowHttpData.getData());
                    IcCardDao icCardDao = MyApplication.getDaoSession().getIcCardDao();
                    icCardDao.deleteAll();
                    List<IcCard> icCards = httpRowHttpData.getData().getRows();
                    for (int i = 0; i < icCards.size(); i++) {
                        icCardDao.insert(icCards.get(i));
                    }
                }
            }
        });
    }

    public static String zksendmsg(Multimap parms) {
        String msg = parms.getString("zkbtn");
        ELog.i("========http============zkbtn========" + msg);
        String login_user_name = parms.getString("login_user_name");
        if (login_user_name == null) {
            return gson.toJson(new HttpResult("-200", "参数错误", false, null));
        }
        ELog.i("========http============login_user_name========" + login_user_name);
        if (login_user_name.equals("lhcctvadmin")) {
            SerialPortUtil.skMakeReadMsg(msg);
            return gson.toJson(new HttpResult("200", "", true, null));
        }
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        List<Users> users = usersDao.queryBuilder()
                .where(UsersDao.Properties.Username.eq(login_user_name))
                .list();
        if (users.size() != 0) {
            Users user = users.get(0);
            if (user.user_status == 1) {
                if (msg.length() > 3) {
                    try {
                        SerialPortUtil.skMakeReadMsg(msg);
                        return gson.toJson(new HttpResult("200", "", true, null));
                    } catch (Exception e) {
                        return gson.toJson(new HttpResult("-101", "Exception", false, null));
                    }
                } else {
                    return gson.toJson(new HttpResult("-200", "命令格式错误", false, null));
                }
            } else {
                return gson.toJson(new HttpResult("-200", "请重新连接", false, null));
            }
        } else {
            return gson.toJson(new HttpResult("-200", "请重新连接", false, null));
        }
    }

    public static String getRebootTime(Multimap parms) {
        return gson.toJson(new HttpResult("200", "", true, MyApplication.prefs.getCloseTimer()));
    }

    public static String updataRebootTime(Multimap parms) {
        MyApplication.prefs.setCloseTimer(parms.getString("rebootTime"));
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getDoorInfo(Multimap parms) {
        DoorInfoDao doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();
        if (doorInfoDao.loadAll().size() == 0) {
            doorInfoDao.insert(new DoorInfo("", "", 0));
        }
        return gson.toJson(new HttpResult("200", "", true, doorInfoDao.loadAll().get(0)));
    }


    public static String updataDoorInfo(Multimap parms) {
        DoorInfoDao doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();
        DoorInfo doorInfo = gson.fromJson(parms.getString("doorDatas"), DoorInfo.class);
        doorInfoDao.deleteAll();
        doorInfoDao.insert(doorInfo);
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getDeviceStatus(Multimap parms) {
        EventShangkeDao eventShangkeDao = MyApplication.getDaoSession().getEventShangkeDao();
        if (eventShangkeDao.loadAll().size() == 0) {
            eventShangkeDao.insert(new EventShangke(0, (long) 1, "投影机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 2, "窗帘", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 3, "灯光", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 5, "空调", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 6, "录播", 0, false, 0));
        }
        return gson.toJson(new HttpResult("200", "", true, eventShangkeDao.loadAll()));
    }

    public static String getWgkzqInfo(Multimap parms) {
        EventKejianRestDao wangguandata = MyApplication.getDaoSession().getEventKejianRestDao();
        if (wangguandata.loadAll().size() == 0) {
            wangguandata.insert(new EventKejianRest(1, (long) 1, "192.168.0.220",
                    0, false, 0));
        }
        return gson.toJson(new HttpResult("200", "", true, wangguandata.loadAll().get(0)));
    }

    public static String updataWgkzqInfo(Multimap parms) {
        EventKejianRestDao wangguandata = MyApplication.getDaoSession().getEventKejianRestDao();
        EventKejianRest wgkzqData = gson.fromJson(parms.getString("wgkzqDatas"), EventKejianRest.class);
        wangguandata.deleteAll();
        wangguandata.insert(wgkzqData);
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getLoginToken(Multimap parms) {
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        List<Users> users = usersDao.queryBuilder()
                .where(UsersDao.Properties.Username.eq(parms.getString("user_name")))
                .list();
        if (users.size() != 0) {
            Users user = users.get(0);
            if (user.user_status == 1) {
                String queryString = user.username + "SWQxcGJxM2RrRkoyOTAxNGU" + user.userPaw;
                String md5Password = "";
                try {
                    md5Password = Coder.hashMD5(queryString.getBytes("utf8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (parms.getString("user_password").equals(md5Password)) {
                    user.setLogin_count(3);
                    usersDao.update(user);
                    String tokenString = user.username + "&" + System.currentTimeMillis() + "&" + user.userPaw;
                    try {
                        return gson.toJson(new HttpResult("200", "登录成功", true, Coder.hashMD5(tokenString.getBytes("utf8"))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int count = user.login_count - 1;
                    if (count == 0) {
                        user.setUser_status(0);
                        user.setLogin_count(count);
                        usersDao.update(user);
                        return gson.toJson(new HttpResult("-1004", "此帐号已锁定", false, null));
                    } else if (count == 1) {
                        user.setLogin_count(count);
                        usersDao.update(user);
                        return gson.toJson(new HttpResult("-1005", "密码错误,1次后此帐号锁定", false, null));
                    } else if (count == 2) {
                        user.setLogin_count(count);
                        usersDao.update(user);
                        return gson.toJson(new HttpResult("-1006", "密码错误,2次后此帐号锁定", false, null));
                    }
                }
            } else {
                return gson.toJson(new HttpResult("-1004", "此帐号已锁定", false, null));
            }
        } else {
            return gson.toJson(new HttpResult("-1002", "帐号错误", false, null));
        }
        return gson.toJson(new HttpResult("-1001", "数据异常", false, null));
    }

    public static String getUserLists(Multimap parms) {
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        return gson.toJson(new HttpResult("200", "", true, usersDao.loadAll()));
    }

    public static String deleteUseradmin(Multimap parms) {
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        usersDao.deleteByKey(Long.parseLong(parms.getString("user_id")));
        return gson.toJson(new HttpResult("200", "", true, usersDao.loadAll()));
    }

    public static String addUseradmin(Multimap parms) {
        UsersDao usersDao = MyApplication.getDaoSession().getUsersDao();
        if (parms.getString("user_id").equals("") || parms.getString("user_id") == null) {
            List<Users> users = usersDao.queryBuilder()
                    .where(UsersDao.Properties.Username.eq(parms.getString("user_name")))
                    .list();
            if (users.size() != 0) {
                return gson.toJson(new HttpResult("200", "此用户已经存在", false, usersDao.loadAll()));
            } else {
                usersDao.insert(new Users(null, parms.getString("user_name"), parms.getString("user_password"), 1, 1, (long) 1, 3, 1));
            }
        } else {
            usersDao.update(new Users(Long.parseLong(parms.getString("user_id")), parms.getString("user_name"), parms.getString("user_password"), 1, 1, (long) 1, 3, 1));
        }
        return gson.toJson(new HttpResult("200", "", true, usersDao.loadAll()));
    }

    public static String getIcdataLists(Multimap parms) {
        IcCardDao icCardDao = MyApplication.getDaoSession().getIcCardDao();
        return gson.toJson(new HttpResult("200", "", true, icCardDao.loadAll()));
    }

    public static String addIcData(Multimap parms) {
        IcCardDao icCardDao = MyApplication.getDaoSession().getIcCardDao();
        if (parms.getString("ic_id").equals("") || parms.getString("ic_id") == null) {
            List<IcCard> icCards = icCardDao.queryBuilder()
                    .where(IcCardDao.Properties.Card_no.eq(parms.getString("ic_num")))
                    .orderAsc(IcCardDao.Properties.CardNumId)
                    .list();
            if (icCards.size() != 0) {
                return gson.toJson(new HttpResult("200", "卡号已经存在", false, icCardDao.loadAll()));
            } else {
                icCardDao.insert(new IcCard(parms.getString("work_number"), parms.getString("admin_name"), parms.getString("ic_num"), Long.parseLong(parms.getString("ic_num")), 1, DateUtil.getNow(), "on"));
            }
        } else {
            icCardDao.deleteByKey(Long.parseLong(parms.getString("ic_id")));
            icCardDao.insert(new IcCard(parms.getString("work_number"), parms.getString("admin_name"), parms.getString("ic_num"), Long.parseLong(parms.getString("ic_num")), 1, DateUtil.getNow(), "on"));
        }
        return gson.toJson(new HttpResult("200", "", true, icCardDao.loadAll()));
    }

    public static String deleteIcData(Multimap parms) {
        IcCardDao icCardDao = MyApplication.getDaoSession().getIcCardDao();
        icCardDao.deleteByKey(Long.parseLong(parms.getString("ic_id")));
        return gson.toJson(new HttpResult("200", "", true, icCardDao.loadAll()));
    }

    public static String getWSDdatadao(Multimap parms) {
        WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();
        return gson.toJson(new HttpResult("200", "", true, wenShiDuDao.loadAll()));
    }

    public static String getDNBdatadao(Multimap parms) {
        ZksDataDao zksDataDao = MyApplication.getDaoSession().getZksDataDao();
        return gson.toJson(new HttpResult("200", "", true, zksDataDao.loadAll()));
    }
}