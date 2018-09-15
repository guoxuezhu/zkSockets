package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;
import com.lh.zksockets.SocketService;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent startIntent = new Intent(this, SocketService.class);
        startService(startIntent);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
