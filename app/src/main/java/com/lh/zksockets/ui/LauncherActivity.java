package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class LauncherActivity extends BaseActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity.this, SplashActivity.class));
                timer.cancel();
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
