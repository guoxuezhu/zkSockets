package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.R;
import com.lh.zksockets.SocketService;
import com.lh.zksockets.utils.DisplayTools;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {

    @BindView(R.id.tv_info)
    TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Intent startIntent = new Intent(this, SocketService.class);
        startService(startIntent);

        tv_info.setText("版本号: " + DisplayTools.getVersionName(this));

    }


    @OnClick(R.id.to_file_btn)
    public void to_file_btn() {
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

    @OnClick(R.id.to_login_btn)
    public void to_login_btn() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
