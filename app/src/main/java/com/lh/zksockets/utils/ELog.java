package com.lh.zksockets.utils;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.lh.zksockets.BuildConfig;

import java.io.File;

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

    private static void readSDCard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long blockSize = sf.getBlockSize();
            long blockCount = sf.getBlockCount();
            long availCount = sf.getAvailableBlocks();
            Log.d("", "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + blockSize * blockCount / 1024 + "KB");
            Log.d("", "可用的block数目：:" + availCount + ",剩余空间:" + availCount * blockSize / 1024 + "KB");
        }
    }
}