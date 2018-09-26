package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseSetingActivity extends Activity {

    @BindView(R.id.tv_IP)
    TextView tv_IP;
    @BindView(R.id.et_PORT)
    EditText et_PORT;
    @BindView(R.id.tv_zkVersionName)
    TextView tv_zkVersionName;
    @BindView(R.id.tv_zkDeviceName)
    TextView tv_zkDeviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_seting);
        ButterKnife.bind(this);

        tv_IP.setText(DisplayTools.getIPAddress(this));
        tv_zkVersionName.setText(DisplayTools.getVersionName(this));
        tv_zkDeviceName.setText("orangepi android_" + Build.VERSION.RELEASE);


    }

    @OnClick(R.id.btn_baseset_ok)
    public void btn_baseset_ok() {
        back();
    }

    @OnClick(R.id.baseSet_btn_back)
    public void baseSet_btn_back() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
