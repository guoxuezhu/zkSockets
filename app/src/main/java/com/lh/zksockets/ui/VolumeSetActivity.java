package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolumeSetActivity extends BaseActivity {

    @BindView(R.id.volume_radio_btn_1)
    RadioButton volume_radio_btn_1;
    @BindView(R.id.volume_radio_btn_2)
    RadioButton volume_radio_btn_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_set);
        ButterKnife.bind(this);
        volume_radio_btn_1.setChecked(true);
        volume_radio_btn_2.setChecked(false);


    }

    @OnClick(R.id.volume_btn_back)
    public void volume_btn_back() {
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
