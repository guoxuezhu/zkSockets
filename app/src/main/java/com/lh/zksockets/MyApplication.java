package com.lh.zksockets;

import android.app.Application;
import android.content.Context;

import com.baidu.mobstat.StatService;
import com.lh.zksockets.data.DbDao.DaoMaster;
import com.lh.zksockets.data.DbDao.DaoSession;
import com.lh.zksockets.utils.DateUtil;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SharePreferenceUtil;

public class MyApplication extends Application {

    public static SharePreferenceUtil prefs;
    public static DaoSession daoSession;
    public static Context context;
    public static int geendaoVersion;
    // http://lihong.h09.66571.com/    http://192.168.0.100/
    public static final String BASEURL = "http://192.168.0.100/";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        prefs = new SharePreferenceUtil(this, "zkcSaveDates");

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lhzks.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        geendaoVersion = daoMaster.getSchemaVersion();
        StatService.start(this);

        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static int geendaoVersion() {
        return geendaoVersion;
    }

    // 创建服务用于捕获崩溃异常
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
//            String error = DateUtil.getNow() + " 发生崩溃异常时,重启应用 bug : " + ex.toString();
            StatService.recordException(context, ex);
        }
    };
}
