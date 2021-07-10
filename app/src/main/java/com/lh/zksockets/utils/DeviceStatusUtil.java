package com.lh.zksockets.utils;

import com.google.gson.Gson;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.DianliangDataDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.DbDao.ZksDataDao;
import com.lh.zksockets.data.model.DianliangData;
import com.lh.zksockets.data.model.EventShangke;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeviceStatusUtil {

    private static EventShangke deviceStatus;

    public static void setDeviceStatus(Long id) {
        deviceStatus = null;
        EventShangkeDao eventShangkeDao = MyApplication.getDaoSession().getEventShangkeDao();
        ZksDatasUtil.getDeviceStatusDatas(eventShangkeDao);
        if (id == 9) {
            deviceStatus = new EventShangke(0, (long) 1, "投影机", 1, false, 0);
        }
        if (id == 10) {
            deviceStatus = new EventShangke(0, (long) 1, "投影机", 0, false, 0);
        }


        if (id == 3) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 1, false, 0);
        }
        if (id == 4) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 0, false, 0);
        }
        if (id == 5) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 2, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 2, false, 0);
        }
        if (id == 1001) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 1, false, 0);
        }
        if (id == 1002) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 0, false, 0);
        }
        if (id == 1003) {
            deviceStatus = new EventShangke(0, (long) 201, "窗帘1", 2, false, 0);
        }
        if (id == 1004) {
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 1, false, 0);
        }
        if (id == 1005) {
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 0, false, 0);
        }
        if (id == 1006) {
            deviceStatus = new EventShangke(0, (long) 202, "窗帘2", 2, false, 0);
        }


        if (id == 13) {
            deviceStatus = new EventShangke(0, (long) 301, "黑板灯", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 302, "教室灯1", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 303, "教室灯2", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 304, "教室灯3", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 305, "教室灯4", 1, false, 0);
        }
        if (id == 14) {
            deviceStatus = new EventShangke(0, (long) 301, "黑板灯", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 302, "教室灯1", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 303, "教室灯2", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 304, "教室灯3", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            deviceStatus = new EventShangke(0, (long) 305, "教室灯4", 0, false, 0);
        }
        if (id == 3001) {
            deviceStatus = new EventShangke(0, (long) 301, "黑板灯", 1, false, 0);
        }
        if (id == 3002) {
            deviceStatus = new EventShangke(0, (long) 301, "黑板灯", 0, false, 0);
        }
        if (id == 3003) {
            deviceStatus = new EventShangke(0, (long) 302, "教室灯1", 1, false, 0);
        }
        if (id == 3004) {
            deviceStatus = new EventShangke(0, (long) 302, "教室灯1", 0, false, 0);
        }
        if (id == 3005) {
            deviceStatus = new EventShangke(0, (long) 303, "教室灯2", 1, false, 0);
        }
        if (id == 3006) {
            deviceStatus = new EventShangke(0, (long) 303, "教室灯2", 0, false, 0);
        }
        if (id == 3007) {
            deviceStatus = new EventShangke(0, (long) 304, "教室灯3", 1, false, 0);
        }
        if (id == 3008) {
            deviceStatus = new EventShangke(0, (long) 304, "教室灯3", 0, false, 0);
        }
        if (id == 3009) {
            deviceStatus = new EventShangke(0, (long) 305, "教室灯4", 1, false, 0);
        }
        if (id == 3010) {
            deviceStatus = new EventShangke(0, (long) 305, "教室灯4", 0, false, 0);
        }


        if (id == 5001) {
            deviceStatus = new EventShangke(0, (long) 4, "大屏一体机", 1, false, 0);
        }
        if (id == 5002) {
            deviceStatus = new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0);
        }

        if (id == 39) {
            deviceStatus = new EventShangke(0, (long) 5, "空调", 1, false, 0);
        }
        if (id == 40) {
            deviceStatus = new EventShangke(0, (long) 5, "空调", 0, false, 0);
        }

        if (id == 54 || id == 56) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 1, false, 0);
        }
        if (id == 55) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 2, false, 0);
        }
        if (id == 58) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 3, false, 0);
        }
        if (id == 57 || id == 59) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 0, false, 0);
        }

        if (deviceStatus != null) {
            eventShangkeDao.update(deviceStatus);
            sendServiceDeviceStatus(deviceStatus);
        }
    }

    private static void sendServiceDeviceStatus(EventShangke deviceStatus) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", deviceStatus.name)
                .add("status", deviceStatus.status + "")
                .add("ip", zkInfoDao.loadAll().get(0).zkip)
                .add("time", DateUtil.getNow())
                .build();
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/sets_status")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======sendServiceDeviceStatus===onFailure=======" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("=======sendServiceDeviceStatus===数据=======" + responseText);
            }
        });
    }

    public static void dianliangSendLog() {
        DianliangDataDao dianliangDataDao = MyApplication.getDaoSession().getDianliangDataDao();
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        List<DianliangData> dianliangDataList = dianliangDataDao.loadAll();
        ZksDataDao zksDataDao = MyApplication.getDaoSession().getZksDataDao();
        if (zksDataDao.loadAll().size() != 0) {
            try {
                DianliangData dianliangData = new DianliangData((long) 100, "电能表",
                        zksDataDao.load((long) 1).strMsg,
                        zksDataDao.load((long) 2).strMsg,
                        zksDataDao.load((long) 3).strMsg,
                        zksDataDao.load((long) 4).strMsg,
                        zksDataDao.load((long) 5).strMsg, 0);
                dianliangDataList.add(dianliangData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (dianliangDataList.size() == 0) {
            return;
        }
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("ip", zkInfoDao.loadAll().get(0).zkip)
                .add("energy", gson.toJson(dianliangDataList))
                .build();
        ELog.e("==========dianliangDataDao==gson.toJson=====" + gson.toJson(dianliangDataList));
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/energy_log")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======dianliangSendLog===onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========dianliangSendLog==数据=======" + responseText);
//                Gson gson = new Gson();
//                HttpData httpData = gson.fromJson(responseText, HttpData.class);
//                ELog.e("==========数据==11=====" + httpData.toString());
            }
        });
    }

}
