package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IcCardAdapter;
import com.lh.zksockets.adapter.PowerListAdapter;
import com.lh.zksockets.data.DbDao.PowerDeviceDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerDeviceActivity extends Activity implements PowerListAdapter.CallBack{

    @BindView(R.id.power_device_recyclerView)
    RecyclerView power_device_recyclerView;

    private PowerDeviceDao powerDeviceDao;
    private PowerListAdapter powerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_device);
        ButterKnife.bind(this);

        powerDeviceDao = MyApplication.getDaoSession().getPowerDeviceDao();
        initRcyclerView();

    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        power_device_recyclerView.setLayoutManager(manager);
        powerListAdapter = new PowerListAdapter(this, powerDeviceDao.loadAll(), this);
        power_device_recyclerView.setAdapter(powerListAdapter);
    }

    @OnClick(R.id.fix_power)
    public void fix_power() {
        startActivity(new Intent(this, PowerBoxActivity.class));
        finish();
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
