package com.lh.zksockets.utils;

import com.google.gson.Gson;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.EventShangke;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeviceStatusUtil {


    public static void setDeviceStatus(Long id) {
        EventShangke deviceStatus = null;
        EventShangkeDao eventShangkeDao = MyApplication.getDaoSession().getEventShangkeDao();
        if (eventShangkeDao.loadAll().size() == 0) {
            eventShangkeDao.insert(new EventShangke(0, (long) 1, "投影机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 2, "窗帘", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 3, "灯光", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 5, "空调", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 6, "录播", 0, false, 0));
        }
        if (id == 9) {
            deviceStatus = new EventShangke(0, (long) 1, "投影机", 1, false, eventShangkeDao.load((long) 1).time);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }
        if (id == 10) {
            deviceStatus = new EventShangke(0, (long) 1, "投影机", 0, false, eventShangkeDao.load((long) 1).time);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 3) {
            deviceStatus = new EventShangke(0, (long) 2, "窗帘", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 4) {
            deviceStatus = new EventShangke(0, (long) 2, "窗帘", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 13) {
            deviceStatus = new EventShangke(0, (long) 3, "灯光", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 14) {
            deviceStatus = new EventShangke(0, (long) 3, "灯光", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 5001) {
            deviceStatus = new EventShangke(0, (long) 4, "大屏一体机", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 5002) {
            deviceStatus = new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 39) {
            deviceStatus = new EventShangke(0, (long) 5, "空调", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 40) {
            deviceStatus = new EventShangke(0, (long) 5, "空调", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 54 || id == 56) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 1, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 55) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 2, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 58) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 3, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }

        if (id == 57 || id == 59) {
            deviceStatus = new EventShangke(0, (long) 6, "录播", 0, false, 0);
            eventShangkeDao.update(deviceStatus);
            postDevicestatus(deviceStatus);
        }


    }

    private static void postDevicestatus(EventShangke deviceStatus) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("device_name", deviceStatus.name)
                .add("device_status", deviceStatus.status + "")
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/send_devicestatus")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======postDevicestatus===onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.i("========postDevicestatus==数据=======" + responseText);
            }
        });
    }

    public static void postDevicestatusList() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        EventShangkeDao eventShangkeDao = MyApplication.getDaoSession().getEventShangkeDao();
        if (eventShangkeDao.loadAll().size() == 0) {
            eventShangkeDao.insert(new EventShangke(0, (long) 1, "投影机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 2, "窗帘", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 3, "灯光", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 5, "空调", 0, false, 0));
            eventShangkeDao.insert(new EventShangke(0, (long) 6, "录播", 0, false, 0));
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("device_list", new Gson().toJson(eventShangkeDao.loadAll()))
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/send_list_devicestatus")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======postDevicestatus===onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.i("========postDevicestatus==数据=======" + responseText);
            }
        });
    }

    public static void xintiao() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("msg", "这是心跳数据")
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/xintiao")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======xintiao===onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.i("========xintiao==数据=======" + responseText);
            }
        });
    }
}
