package com.lh.zksockets.utils;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.model.EventShangke;

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


    }


}
