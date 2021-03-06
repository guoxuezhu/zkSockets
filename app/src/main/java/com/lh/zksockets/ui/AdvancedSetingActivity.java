package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvancedSetingActivity extends BaseActivity {

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

    @OnClick(R.id.zk_seting)
    public void zk_seting() {
        startActivity(new Intent(this, ZhongKongActivity.class));
        finish();
    }

    @OnClick(R.id.io_seting)
    public void io_seting() {
        startActivity(new Intent(this, IOsetActivity.class));
        finish();
    }

    @OnClick(R.id.event_seting)
    public void event_seting() {
        startActivity(new Intent(this, EventActivity.class));
        finish();
    }

    @OnClick(R.id.volume_seting)
    public void volume_seting() {
        startActivity(new Intent(this, VolumeSetActivity.class));
        finish();
    }

    @OnClick(R.id.environmental_seting)
    public void environmental_seting() {
        startActivity(new Intent(this, EnvironmentalActivity.class));
        finish();
    }

    @OnClick(R.id.lamp_seting)
    public void lamp_seting() {
        startActivity(new Intent(this, LampActivity.class));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
