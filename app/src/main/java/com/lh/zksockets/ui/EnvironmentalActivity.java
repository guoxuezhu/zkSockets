package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnvironmentalActivity extends BaseActivity {

    @BindView(R.id.environmental_radio_btn_1)
    RadioButton environmental_radio_btn_1;
    @BindView(R.id.environmental_radio_btn_2)
    RadioButton environmental_radio_btn_2;
    @BindView(R.id.environmental_radio_btn_3)
    RadioButton environmental_radio_btn_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        ButterKnife.bind(this);
        environmental_radio_btn_1.setChecked(true);
        environmental_radio_btn_2.setChecked(false);
        environmental_radio_btn_3.setChecked(false);


    }

    @OnClick(R.id.environmental_btn_back)
    public void environmental_btn_back() {
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
