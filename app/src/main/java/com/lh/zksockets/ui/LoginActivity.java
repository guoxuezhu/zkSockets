package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.login_btn)
    public void login_btn() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
