package com.lh.zksockets.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.DoorInfo;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.SerialResult;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.data.model.ZkInfo;

import java.math.BigDecimal;
import java.util.List;

public class HttpRequestUtil {


    private static Gson gson = new Gson();

    public static String updataSportInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("==============1==333====" + parms);
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

        return gson.toJson(new HttpResult("200", "", true, null));

    }

    public static String getSportInfo(AsyncHttpServerRequest request) {
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
        String sportNum = request.getQuery().getString("sportNum");
        List<SerialCommand> serialCommands = serialCommandDao.queryBuilder()
                .where(SerialCommandDao.Properties.SId.eq(sportNum))
                .orderAsc(SerialCommandDao.Properties.MlId)
                .list();

        SerialResult serialResult = new SerialResult(serialPortDataDao.load(Long.valueOf(sportNum)), serialCommands);
        return gson.toJson(new HttpResult("200", "", true, serialResult));

    }

    public static String getDangerInfo(AsyncHttpServerRequest request) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            ioYuanDao.insert(new IOYuan((long) 1, "报警1", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 2, "报警2", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 3, "报警3", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 4, "报警4", "", 0, "", ""));
        }
        return gson.toJson(new HttpResult("200", "", true, ioYuanDao.loadAll()));
    }

    public static String updataDangerInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        List<IOYuan> dangerIoYuans = gson.fromJson(parms.getString("dangerDatas"), new TypeToken<List<IOYuan>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            ioYuanDao.update(dangerIoYuans.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getEventList(AsyncHttpServerRequest request) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3, "窗帘开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "窗帘关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6, "窗帘1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7, "窗帘2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8, "窗帘2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-1", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-1", ""));
            mLsListsDao.insert(new MLsLists((long) 13, "灯光开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 15, "灯光1开(关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 16, "灯光1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 17, "灯光2开(关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 18, "灯光2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 19, "灯光3开(关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 20, "灯光3关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 21, "总音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 22, "总音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 23, "总音量静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 24, "总音量静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 25, "音响音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 26, "音响音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 27, "音响静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 28, "音响静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 29, "麦克风音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 30, "麦克风音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 31, "麦克风静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 32, "麦克风静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 33, "新风-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 34, "新风-低速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 35, "新风-中速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 36, "新风-高速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 41, "空调-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 42, "空调-制冷", "", ""));
            mLsListsDao.insert(new MLsLists((long) 43, "空调-制热", "", ""));
            mLsListsDao.insert(new MLsLists((long) 44, "空调-风速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "开机", "", ""));
            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 48, "空调-模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 49, "空调-摆风", "", ""));
            mLsListsDao.insert(new MLsLists((long) 50, "一体机-内置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 51, "一体机-外置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 52, "电视机1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 53, "电视机2", "", ""));
            mLsListsDao.insert(new MLsLists((long) 54, "开始录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "暂停录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "继续录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "停止录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "开始直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "停止直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 60, "新风开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 61, "新风关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 62, "黑板灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 63, "黑板灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 64, "教室灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 65, "教室灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 66, "场景开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 67, "场景关", "", ""));
        }

        return gson.toJson(new HttpResult("200", "", true, mLsListsDao.loadAll()));
    }

    public static String updataEventInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
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

    public static String getJDQList(AsyncHttpServerRequest request) {
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            jdqStatusDao.insert(new JDQstatus((long) 1, "继电器1", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 2, "继电器2", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 3, "继电器3", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 4, "继电器4", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 5, "继电器5", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 6, "继电器6", "", 0, 1));
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器7", "", 0, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器8", "", 0, 180));
        }
        return gson.toJson(new HttpResult("200", "", true, jdqStatusDao.loadAll()));
    }

    public static String updataJdqInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        List<JDQstatus> jdQstatuses = gson.fromJson(parms.getString("jdqDatas"), new TypeToken<List<JDQstatus>>() {
        }.getType());
        ELog.i("==============1==333====" + jdQstatuses.toString());
        for (int i = 0; i < jdQstatuses.size(); i++) {
            jdqStatusDao.update(jdQstatuses.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getLuboList(AsyncHttpServerRequest request) {
        LuboInfoDao luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        if (luboInfoDao.loadAll().size() == 0) {
            luboInfoDao.insert(new LuboInfo("", "", "", "", 0));
        }
        return gson.toJson(new HttpResult("200", "", true, luboInfoDao.loadAll()));
    }

    public static String updataLuboInfo(AsyncHttpServerRequest request) {

        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        LuboInfoDao luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();
        LuboInfo luboInfos = gson.fromJson(parms.getString("luboDatas"), LuboInfo.class);
        luboInfoDao.deleteAll();
        luboInfoDao.insert(luboInfos);
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getMqttinfo(AsyncHttpServerRequest request) {
        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        if (baseInfoDao.loadAll().size() == 0) {
            baseInfoDao.insert(new BaseInfo("", "",
                    "", java.util.UUID.randomUUID().toString(), 0));
        }
        return gson.toJson(new HttpResult("200", "", true, baseInfoDao.loadAll().get(0)));
    }

    public static String updataMqttInfo(AsyncHttpServerRequest request) {
        BaseInfoDao baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        BaseInfo baseInfo = gson.fromJson(request.getQuery().getString("mqttData"), BaseInfo.class);
        baseInfoDao.deleteAll();
        baseInfoDao.insert(baseInfo);
        return gson.toJson(new HttpResult("200", "", true, null));
    }


    public static String getZkBaseInfo(AsyncHttpServerRequest request) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            zkInfoDao.insert(new ZkInfo("", "0.0.0.0", "1.0.1",
                    "1", 0, java.util.UUID.randomUUID().toString(), 0));
        }
        return gson.toJson(new HttpResult("200", "", true, zkInfoDao.loadAll().get(0)));
    }

    public static String updataZkBaseInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        ZkInfo zkInfo = gson.fromJson(parms.getString("zkbaseInfoData"), ZkInfo.class);
        zkInfo.setUuid(zkInfoDao.loadAll().get(0).uuid);
        zkInfoDao.deleteAll();
        zkInfoDao.insert(zkInfo);
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getIoOutinfo(AsyncHttpServerRequest request) {
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "io输出" + i, "", 0, 10));
            }
        }
        return gson.toJson(new HttpResult("200", "", true, ioPortDataDao.loadAll()));
    }

    public static String updataIoOutInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        List<IoPortData> ioPortDatas = gson.fromJson(parms.getString("ioOutDatas"), new TypeToken<List<IoPortData>>() {
        }.getType());
        ELog.i("==============1==333====" + ioPortDatas.toString());
        for (int i = 0; i < ioPortDatas.size(); i++) {
            ioPortDataDao.update(ioPortDatas.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getDangerOutInfo(AsyncHttpServerRequest request) {
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                dangerOutDao.insert(new DangerOut((long) i, "报警输出" + i, "", 1, 10));
            }
        }
        return gson.toJson(new HttpResult("200", "", true, dangerOutDao.loadAll()));
    }

    public static String updataDangerOutInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        List<DangerOut> dangerOuts = gson.fromJson(parms.getString("dangerOutDatas"), new TypeToken<List<DangerOut>>() {
        }.getType());
        ELog.i("=============1==333====" + dangerOuts.toString());
        for (int i = 0; i < dangerOuts.size(); i++) {
            dangerOutDao.update(dangerOuts.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getWSD(AsyncHttpServerRequest request) {
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

    public static String zksendmsg(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        String msg = parms.getString("zkbtn");
//        String msg = request.getQuery().getString("zkbtn");
        ELog.i("========http======zkbtn======msg========" + msg);
        if (msg.length() > 3) {
            try {
                if (msg.substring(0, 3).equals("VID")) {
                    SerialPortUtil.sendShipinType(msg);
                } else if (msg.substring(0, 3).equals("SKJ")) {//远程无卡开机
                    SerialPortUtil.sendKJban(msg);
                } else if (msg.substring(0, 3).equals("MJD")) {//门禁
                    SerialPortUtil.makemenjin(msg);
                } else if (msg.substring(0, 3).equals("FWS")) {
                    SerialPortUtil.sendFWstatus(msg);
                } else if (msg.substring(0, 3).equals("LUB")) {
                    HttpUtil.setlubo(msg);
                } else if (msg.substring(0, 3).equals("JZF")) {
                    SerialPortUtil.sendShipinFenping(msg);
                } else if (msg.substring(0, 3).equals("MBS")) {
                    try {
                        SerialPortUtil.makeML(Long.valueOf(msg.substring(3)));
                    } catch (Exception e) {
                        ELog.i("============Long.valueOf==异常========" + e.toString());
                        return gson.toJson(new HttpResult("-101", "命令格式错误", false, null));
                    }
                }
            } catch (Exception e) {
                return gson.toJson(new HttpResult("-101", "Exception", false, null));
            }
            return gson.toJson(new HttpResult("200", "", true, null));
        } else {
            return gson.toJson(new HttpResult("-200", "命令格式错误", false, null));
        }

    }

    public static String getRebootTime(AsyncHttpServerRequest request) {
        return gson.toJson(new HttpResult("200", "", true, MyApplication.prefs.getCloseTimer()));
    }

    public static String updataRebootTime(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        MyApplication.prefs.setCloseTimer(parms.getString("rebootTime"));
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getDoorInfo(AsyncHttpServerRequest request) {
        DoorInfoDao doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();
        if (doorInfoDao.loadAll().size() == 0) {
            doorInfoDao.insert(new DoorInfo("", "", 0));
        }
        return gson.toJson(new HttpResult("200", "", true, doorInfoDao.loadAll().get(0)));
    }


    public static String updataDoorInfo(AsyncHttpServerRequest request) {
        Multimap parms = ((AsyncHttpRequestBody<Multimap>) request.getBody()).get();
        ELog.i("=================" + parms.toString());
        DoorInfoDao doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();
        DoorInfo doorInfo = gson.fromJson(parms.getString("doorDatas"), DoorInfo.class);
        doorInfoDao.deleteAll();
        doorInfoDao.insert(doorInfo);
        return gson.toJson(new HttpResult("200", "", true, null));
    }
}