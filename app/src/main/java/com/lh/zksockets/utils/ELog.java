package com.lh.zksockets.utils;

import android.os.Environment;
import android.util.Log;

import com.lh.zksockets.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 打印log日志
 */
public class ELog {
    public static boolean isDebug = BuildConfig.JAVA_LOG_ENABLED;

    public static void i(Object msg) {
        if (isDebug) {
            Log.i("mylog", "" + msg);
        }
    }

    public static void d(Object msg) {
        if (isDebug) {
            Log.d("mylog", "" + msg);
        }
    }

    public static void e(Object msg) {
        if (isDebug) {
            Log.e("mylog", "" + msg);
        }
    }

    public static void getMyLogcat() {
        String path = Environment.getExternalStorageDirectory() + "/lhzksFile/logcat/";
        File file = new File(path);
        if (!file.exists()) {
            boolean isok = file.mkdirs();
            if (!isok) {
                Log.d("mylog", "======logfile===创建失败");
                return;
            }
        }
        Log.d("mylog", "======logfile======创建成功======");
        String cmdCollect = path + DateUtil.getNow();
        try {
            Runtime.getRuntime().exec("logcat -v time -f " + cmdCollect);
            Thread.sleep(1000);
            Log.d("mylog", "=========收集日志循环已完全启动!!!");
        } catch (Exception e) {
            Log.e("mylog", "=======logcat Exception====== >" + e.getMessage(), e);
        }
    }
}