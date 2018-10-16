package com.lh.zksockets.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static void createFile() {
        String path = Environment.getExternalStorageDirectory() + "";
        File file = new File(path);
        if (!file.exists()) {
            ELog.i("=======FileUtil====2====" + path);
        }

    }
}
