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
                .add("device_name", deviceStatus.name)
                .add("device_status", deviceStatus.status + "")
                .add("zks_ip", zkInfoDao.loadAll().get(0).zkip)
                .add("time", DateUtil.getNow())
                .build();
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/send_device_status")
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


}
