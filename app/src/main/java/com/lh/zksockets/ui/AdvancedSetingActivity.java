package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvancedSetingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_seting);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back_btn)
    public void back_btn() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.projector_seting)
    public void projector_seting() {
        startActivity(new Intent(this, ProjectorSetingActivity.class));
        finish();
    }

    @OnClick(R.id.diannao_seting)
    public void diannao_seting() {
        startActivity(new Intent(this, ComputerActivity.class));
        finish();
    }

    @OnClick(R.id.power_box)
    public void power_box() {
        startActivity(new Intent(this, PowerDeviceActivity.class));
        finish();
    }

    @OnClick(R.id.ic_seting)
    public void ic_seting() {
        startActivity(new Intent(this, ICcardActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
