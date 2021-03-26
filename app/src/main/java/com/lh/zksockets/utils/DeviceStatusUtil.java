package com.lh.zksockets.utils;

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
            eventShangkeDao.update(new EventShangke(0, (long) 1, "投影机", 1, false, 0));
        }
        if (id == 10) {
            eventShangkeDao.update(new EventShangke(0, (long) 1, "投影机", 0, false, 0));
        }

        if (id == 3) {
            eventShangkeDao.update(new EventShangke(0, (long) 2, "窗帘", 1, false, 0));
        }

        if (id == 4) {
            eventShangkeDao.update(new EventShangke(0, (long) 2, "窗帘", 0, false, 0));
        }

        if (id == 13) {
            eventShangkeDao.update(new EventShangke(0, (long) 3, "灯光", 1, false, 0));
        }

        if (id == 14) {
            eventShangkeDao.update(new EventShangke(0, (long) 3, "灯光", 0, false, 0));
        }

        if (id == 5001) {
            eventShangkeDao.update(new EventShangke(0, (long) 4, "大屏一体机", 1, false, 0));
        }

        if (id == 5002) {
            eventShangkeDao.update(new EventShangke(0, (long) 4, "大屏一体机", 0, false, 0));
        }

        if (id == 39) {
            eventShangkeDao.update(new EventShangke(0, (long) 5, "空调", 1, false, 0));
        }

        if (id == 40) {
            eventShangkeDao.update(new EventShangke(0, (long) 5, "空调", 0, false, 0));
        }

        if (id == 54 || id == 56) {
            eventShangkeDao.update(new EventShangke(0, (long) 6, "录播", 1, false, 0));
        }

        if (id == 55) {
            eventShangkeDao.update(new EventShangke(0, (long) 6, "录播", 2, false, 0));
        }

        if (id == 58) {
            eventShangkeDao.update(new EventShangke(0, (long) 6, "录播", 3, false, 0));
        }

        if (id == 57 || id == 59) {
            eventShangkeDao.update(new EventShangke(0, (long) 6, "录播", 0, false, 0));
        }

        postEventstatus(id);
    }

    private static void postEventstatus(Long id) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("event_id", id + "")
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/send_events_id")
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


    public static void sendService(String readMsgdata) {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("hkiwg_data", readMsgdata)
                .build();

        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/send_hkiwgdata")
                .post(requestBody)
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("=======sendService======wg=====onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.i("========sendService=====wg===数据=======" + responseText);
            }
        });
    }


}
