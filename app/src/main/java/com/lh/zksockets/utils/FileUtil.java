package com.lh.zksockets.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static String createFile() {
        String path = Environment.getExternalStorageDirectory() + "lhFile/apkFile/主机.apk";
        File file = new File(path);
        if (file.exists()) {
            ELog.i("=======FileUtil====2====" + path);
        }
        ELog.i("=======file.getPath()========" + file.getPath());
        return file.getPath();

    }
}
