package com.lh.zksockets;

import android.app.Application;

import com.lh.zksockets.data.DbDao.DaoMaster;
import com.lh.zksockets.data.DbDao.DaoSession;

public class MyApplication extends Application {


    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lhzks.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
