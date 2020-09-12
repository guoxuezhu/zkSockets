package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.utils.TimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerDeviceActivity extends BaseActivity {

    @BindView(R.id.et_dy_close_time)
    EditText et_dy_close_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_device);
        ButterKnife.bind(this);

        et_dy_close_time.setText(MyApplication.prefs.getCloseTimer());
    }


    @OnClick(R.id.btn_dy_time_ok)
    public void btn_dy_time_ok() {
        MyApplication.prefs.setCloseTimer(et_dy_close_time.getText().toString());
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        TimerUtils.setDuandianTimer();
    }


    @OnClick(R.id.powerdevice_btn_back)
    public void powerdevice_btn_back() {
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
