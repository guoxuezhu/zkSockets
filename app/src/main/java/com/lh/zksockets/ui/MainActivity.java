package com.lh.zksockets.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.lh.zksockets.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.net_btn)
    public void net_btn() {
        startActivity(new Intent(this, NetStatusActivity.class));
        finish();
    }

    @OnClick(R.id.base_set)
    public void base_set() {
        startActivity(new Intent(Settings.ACTION_SETTINGS));
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
        File apkFile = new File("/mnt/usbhost/Storage01/lh/中控.apk");
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
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
