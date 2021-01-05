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
                Log.d("mylog", "======logcat===创建失败");
                return;
            }
        }
        Log.d("mylog", "====1111====logcat===logfile==创建成功======");

        String cmdCollect = path + DateUtil.getNow() + ".txt";
        List<String> commandList = new ArrayList<>();
        commandList.add("logcat");
        commandList.add("-f");
        commandList.add(cmdCollect);
        commandList.add("-v");
        commandList.add("time");

//        commandList.add("System.err:W");// 过滤所有的错误信息
//        commandList.add("System.out:I");// 过滤所有的错误信息
//        commandList.add("AndroidRuntime:E"); //运行报错
//
//        commandList.add("TAG" + ":I");// 过滤指定TAG的信息
//        commandList.add("TAG" + ":V");
//        commandList.add("TAG" + ":D");
//        commandList.add("*:S");
        try {
            Runtime.getRuntime().exec(commandList.toArray(new String[commandList.size()]));
            Thread.sleep(1000);
            Log.d("mylog", "=========收集日志循环已完全启动!!!");
        } catch (Exception e) {
            Log.e("mylog", "=======logcat Exception====== >" + e.getMessage(), e);
        }
    }
}