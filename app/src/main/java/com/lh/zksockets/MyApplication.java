package com.lh.zksockets;

import android.app.Application;

import com.baidu.mobstat.StatService;
import com.lh.zksockets.data.DbDao.DaoMaster;
import com.lh.zksockets.data.DbDao.DaoSession;
import com.lh.zksockets.utils.SharePreferenceUtil;

public class MyApplication extends Application {

    public static SharePreferenceUtil prefs;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new SharePreferenceUtil(this, "zkcSaveDates");

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lhzks.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        StatService.start(this);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
