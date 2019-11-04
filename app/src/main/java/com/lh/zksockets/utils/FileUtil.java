package com.lh.zksockets.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static String createFile() {
        String path = Environment.getExternalStorageDirectory() + "/lhFile/apkFile/";
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
        ELog.i("=====FileUtil==file.getPath()========" + file.getPath());
        return file.getPath();

    }
}
