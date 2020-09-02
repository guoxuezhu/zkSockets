package com.lh.zksockets;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import com.baidu.mobstat.StatService;
import com.lh.zksockets.data.DbDao.DaoMaster;
import com.lh.zksockets.data.DbDao.DaoSession;
import com.lh.zksockets.ui.LauncherActivity;
import com.lh.zksockets.utils.DateUtil;
import com.lh.zksockets.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.Map;

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
            StatService.recordException(context, ex);
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("时间", DateUtil.getNow());
            StatService.onEvent(MyApplication.context,  "test", "崩溃异常", 1, attributes);
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
