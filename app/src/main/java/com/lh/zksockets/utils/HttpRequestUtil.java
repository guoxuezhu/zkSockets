package com.lh.zksockets.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.SerialResult;

import java.util.List;

public class HttpRequestUtil {


    private static Gson gson = new Gson();
    private static HttpResult httpResult = null;

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
        httpResult = new HttpResult("200", "", true, null);
        return gson.toJson(httpResult);

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
        httpResult = new HttpResult("200", "", true, serialResult);
        return gson.toJson(httpResult);

    }

    public static String getDangerInfo(AsyncHttpServerRequest request) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        httpResult = new HttpResult("200", "", true, ioYuanDao.loadAll());
        return gson.toJson(httpResult);
    }

    public static String updataDangerInfo(AsyncHttpServerRequest request) {
        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        List<IOYuan> dangerIoYuans = gson.fromJson(request.getQuery().get("dangerDatas[]").toString(), new TypeToken<List<IOYuan>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            ioYuanDao.update(dangerIoYuans.get(i));
        }
        httpResult = new HttpResult("200", "", true, null);
        return gson.toJson(httpResult);
    }

    public static String getEventList(AsyncHttpServerRequest request) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        httpResult = new HttpResult("200", "", true, mLsListsDao.loadAll());
        return gson.toJson(httpResult);
    }

    public static String updataEventInfo(AsyncHttpServerRequest request) {
        MLsListsDao mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        List<MLsLists> dangerIoYuans = gson.fromJson(request.getQuery().get("eventDatas[]").toString(), new TypeToken<List<MLsLists>>() {
        }.getType());
        for (int i = 0; i < dangerIoYuans.size(); i++) {
            mLsListsDao.update(dangerIoYuans.get(i));
        }
        httpResult = new HttpResult("200", "", true, null);
        return gson.toJson(httpResult);
    }
}