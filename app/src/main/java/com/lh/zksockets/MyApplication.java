package com.lh.zksockets;

import android.app.Application;
import android.content.Intent;
import android.os.Process;

import com.lh.zksockets.data.DbDao.DaoMaster;
import com.lh.zksockets.data.DbDao.DaoSession;
import com.lh.zksockets.ui.LauncherActivity;
import com.lh.zksockets.utils.SharePreferenceUtil;


public class MyApplication extends Application {

    public static SharePreferenceUtil prefs;
    public static DaoSession daoSession;
    public static int geendaoVersion;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initDatas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDatas() {
        prefs = new SharePreferenceUtil(this, "zkcSaveDates");
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lhzks.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        geendaoVersion = daoMaster.getSchemaVersion();
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
            restartApp(); //发生崩溃异常时,重启应用
        }
    };

    public void restartApp() {
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        Process.killProcess(Process.myPid()); //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
        System.exit(0);
        System.gc();
    }
}
