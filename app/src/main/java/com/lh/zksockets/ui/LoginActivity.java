package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_password)
    EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    private static final long MIN_CLICK_INTERVAL = 5000;
    private long mLastClickTime;
    private int mSecretNumber = 0;

    //连续点击5次，调出系统设置界面
    @OnClick(R.id.login_tv)
    void setting() {
        try {
            long currentClickTime = SystemClock.uptimeMillis();
            long elapsedTime = currentClickTime - mLastClickTime;
            mLastClickTime = currentClickTime;
            if (elapsedTime < MIN_CLICK_INTERVAL) {
                ++mSecretNumber;
                if (5 == mSecretNumber) {
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                    LoginActivity.this.finish();
                    Process.killProcess(Process.myPid());//杀死进程，防止dialog.show()出现错误
                }
            } else {
                mSecretNumber = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @OnClick(R.id.login_btn)
    public void login_btn() {
        if (login_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_name.getText().toString().trim().equals("admin") &&
                login_password.getText().toString().trim().equals("admin")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick(R.id.login_btn_back)
    public void login_btn_back() {
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
