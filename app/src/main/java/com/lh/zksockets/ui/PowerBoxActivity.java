package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerBoxActivity extends Activity {

    @BindView(R.id.LL_check)
    LinearLayout LL_check;
    @BindView(R.id.LL_open)
    LinearLayout LL_open;
    @BindView(R.id.LL_close)
    LinearLayout LL_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_box);
        ButterKnife.bind(this);
        LL_check.setVisibility(View.VISIBLE);
        LL_open.setVisibility(View.GONE);
        LL_close.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_to_open)
    public void btn_to_open() {
        LL_check.setVisibility(View.GONE);
        LL_open.setVisibility(View.VISIBLE);
        LL_close.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_to_close)
    public void btn_to_close() {
        LL_check.setVisibility(View.GONE);
        LL_open.setVisibility(View.GONE);
        LL_close.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_powerbox_ok)
    public void btn_powerbox_ok() {
        back();
    }

    @OnClick(R.id.power_btn_back)
    public void power_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
