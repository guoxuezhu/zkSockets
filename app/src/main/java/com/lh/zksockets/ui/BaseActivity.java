package com.lh.zksockets.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mobstat.StatService;


public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

}
