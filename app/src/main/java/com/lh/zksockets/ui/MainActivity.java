package com.lh.zksockets.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.utils.ELog;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final long MIN_CLICK_INTERVAL = 5000;
    private long mLastClickTime;
    private int mSecretNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ChazuoDataDao chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        if (chazuoDataDao.loadAll().size() == 0) {
            chazuoDataDao.insert(new ChazuoData((long) 0, "无", null, false, 0, 0));
            for (int i = 1; i < 21; i++) {
                chazuoDataDao.insert(new ChazuoData((long) i, "插座" + i, null, false, 0, 0));
            }
        }
        ELog.i("=========chazuoDataDao========" + chazuoDataDao.loadAll().toString());
    }


    //连续点击5次，调出系统设置界面
    @OnClick(R.id.tv_set)
    public void setting() {
        try {
            long currentClickTime = SystemClock.uptimeMillis();
            long elapsedTime = currentClickTime - mLastClickTime;
            mLastClickTime = currentClickTime;
            if (elapsedTime < MIN_CLICK_INTERVAL) {
                ++mSecretNumber;
                if (5 == mSecretNumber) {
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
//                    MainActivity.this.finish();
//                    Process.killProcess(Process.myPid());//杀死进程，防止dialog.show()出现错误
                }
            } else {
                mSecretNumber = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @OnClick(R.id.net_btn)
    public void net_btn() {
        startActivity(new Intent(this, NetStatusActivity.class));
        finish();
    }

    @OnClick(R.id.base_set)
    public void base_set() {
        startActivity(new Intent(this, BaseSetingActivity.class));
        finish();
    }

    @OnClick(R.id.sets_btn)
    public void sets_btn() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @OnClick(R.id.user_btn)
    public void user_btn() {
        startActivity(new Intent(this, UsersActivity.class));
        finish();
    }

    @OnClick(R.id.xiangdao)
    public void xiangdao() {
        startActivity(new Intent(this, XiangDaoActivity.class));
        finish();
    }

    @OnClick(R.id.shengji)
    public void shengji() {
        File apkFile = new File("/mnt/usbhost/Storage01/lh/中控-release-1.0.3.apk");
        if (apkFile.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            startActivity(intent);
        } else {
            Toast.makeText(this, "请插入有升级包的U盘", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.main_btn_back)
    public void main_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    private void back() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
