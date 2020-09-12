package com.lh.zksockets.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 共享参数类
 */
@SuppressLint("CommitPrefEdits")
public class SharePreferenceUtil {
    private SharedPreferences sp;
    private Editor editor;


    /**
     * 构造函数
     */
    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        // 利用edit()方法获取Editor对象。
        editor = sp.edit();
    }

    public void setCloseTimer(String closeTimer) {
        editor.putString("closeTimer", closeTimer);
        editor.commit();
    }

    public String getCloseTimer() {
        return sp.getString("closeTimer", "22:00:00");
    }



    public void setIsReboot(boolean isReboot) {
        editor.putBoolean("isReboot", isReboot);
        editor.commit();
    }

    public boolean getIsReboot() {
        return sp.getBoolean("isReboot", false);
    }


}
