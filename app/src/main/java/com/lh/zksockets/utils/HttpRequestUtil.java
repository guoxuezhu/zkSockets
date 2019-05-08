package com.lh.zksockets.utils;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.SerialResult;
import com.lh.zksockets.data.model.WenShiDu;

import java.math.BigDecimal;
import java.util.List;

public class HttpRequestUtil {


    private static Gson gson = new Gson();

    public static String updataSportInfo(AsyncHttpServerRequest request) {
        String sportNumer = request.getQuery().getString("sportNum");
        List<SerialCommand> serialCommandlist = gson.fromJson(request.getQuery().get("sportMls[]").toString(), new TypeToken<List<SerialCommand>>() {
        }.getType());
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
        serialPortDataDao.update(new SerialPortData(Long.valueOf(sportNumer), "串口" + sportNumer, request.getQuery().getString("deviceName"),
                Integer.valueOf(request.getQuery().getString("baudRateId")), request.getQuery().getString("baudRate"),
                Integer.valueOf(request.getQuery().getString("checkoutBitId")), request.getQuery().getString("checkoutBit"),
                Integer.valueOf(request.getQuery().getString("dataBitId")), request.getQuery().getString("dataBit"),
                Integer.valueOf(request.getQuery().getString("stopBitId")), request.getQuery().getString("stopBit"),
                Integer.valueOf(request.getQuery().getString("jinZhi"))));
        for (int i = 0; i < serialCommandlist.size(); i++) {
            serialCommandDao.update(serialCommandlist.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));

    }

    public static String getSportInfo(AsyncHttpServerRequest request) {
        String sportNum = request.getQuery().getString("sportNum");
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        SerialCommandDao serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
        List<SerialCommand> serialCommands = serialCommandDao.queryBuilder()
                .where(SerialCommandDao.Properties.SId.eq(sportNum))
                .orderAsc(SerialCommandDao.Properties.MlId)
                .list();

        SerialResult serialResult = new SerialResult(serialPortDataDao.load(Long.valueOf(sportNum)), serialCommands);
        return gson.toJson(new HttpResult("200", "", true, serialResult));

    }

    public static String getDangerInfo(AsyncHttpServerRequest request) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        return gson.toJson(new HttpResult("200", "", true, ioYuanDao.loadAll()));
    }

    public static String updataDangerInfo(AsyncHttpServerRequest request) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        List<IOYuan> dangerIoYuans = gson.fromJson(request.getQuery().get("dangerDatas[]").toString(), new TypeToken<List<IOYuan>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            ioYuanDao.update(dangerIoYuans.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String getEventList(AsyncHttpServerRequest request) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        return gson.toJson(new HttpResult("200", "", true, mLsListsDao.loadAll()));
    }

    public static String updataEventInfo(AsyncHttpServerRequest request) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        List<MLsLists> dangerIoYuans = gson.fromJson(request.getQuery().get("eventDatas[]").toString(), new TypeToken<List<MLsLists>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            mLsListsDao.update(dangerIoYuans.get(i));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }

    public static String setWsdpm(AsyncHttpServerRequest request) {
        String[] wsdpm = request.getPath().split(",");
        if (wsdpm.length > 15) {
            WenShiDuDao wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();
            BigDecimal voc = new BigDecimal(wsdpm[5]);
            BigDecimal hcho = new BigDecimal(wsdpm[6]);
            BigDecimal wendu = new BigDecimal(wsdpm[8]);
            BigDecimal shidu = new BigDecimal(wsdpm[9]);

            BigDecimal bigDecimal = new BigDecimal("0.1");

            wenShiDuDao.deleteAll();
            wenShiDuDao.insert(new WenShiDu(wsdpm[4] + "ppm", voc.multiply(bigDecimal) + "ug/m3",
                    hcho.multiply(bigDecimal) + "ug/m3", wsdpm[7] + "ug/m3",
                    wendu.multiply(bigDecimal) + "℃", shidu.multiply(bigDecimal) + "%RH",
                    wsdpm[10] + "ug/m3", 1, ""));
        }
        return gson.toJson(new HttpResult("200", "", true, null));
    }
}