package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.PowerCheckBoxAdapter;
import com.lh.zksockets.adapter.PowerDeviceClosedAdapter;
import com.lh.zksockets.adapter.PowerDeviceOpenAdapter;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.DbDao.PowerDeviceDao;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.PowerDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerBoxActivity extends Activity implements PowerCheckBoxAdapter.PowerCallBack,
        PowerDeviceOpenAdapter.OpenCallBack, PowerDeviceClosedAdapter.ClosedCallBack {

    @BindView(R.id.LL_check)
    LinearLayout LL_check;
    @BindView(R.id.LL_open)
    LinearLayout LL_open;
    @BindView(R.id.LL_close)
    LinearLayout LL_close;

    @BindView(R.id.power_gridView)
    GridView power_gridView;

    @BindView(R.id.open_power_recyclerView)
    RecyclerView open_power_recyclerView;
    @BindView(R.id.closed_power_recyclerView)
    RecyclerView closed_power_recyclerView;

    private List<ChazuoData> checkedPower = new ArrayList<>();
    private List<PowerDevice> powerDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_box);
        ButterKnife.bind(this);
        LL_check.setVisibility(View.VISIBLE);
        LL_open.setVisibility(View.GONE);
        LL_close.setVisibility(View.GONE);

        gridViewInit();

    }

    private void gridViewInit() {
        ChazuoDataDao chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        power_gridView.setAdapter(new PowerCheckBoxAdapter(this, chazuoDataDao.loadAll(), this));

    }

    @Override
    public void onPowerCheckItem(boolean isChecked, ChazuoData chazuoData) {
        if (isChecked) {
            checkedPower.add(chazuoData);
        } else {
            checkedPower.remove(chazuoData);
        }
        Log.i("lhlog", "=========checkedPower==========" + checkedPower.toString());
    }

    @OnClick(R.id.btn_to_open)
    public void btn_to_open() {
        if (checkedPower.size() == 0) {
            Toast.makeText(this, "请选择线路", Toast.LENGTH_SHORT).show();
            return;
        }
        LL_check.setVisibility(View.GONE);
        LL_open.setVisibility(View.VISIBLE);
        LL_close.setVisibility(View.GONE);

        open_power_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PowerDeviceOpenAdapter powerDeviceAdapter = new PowerDeviceOpenAdapter(this, checkedPower, this);
        open_power_recyclerView.setAdapter(powerDeviceAdapter);
        for (int i = 0; i < checkedPower.size(); i++) {
            powerDevices.add(new PowerDevice(checkedPower.get(i).id, checkedPower.get(i).name, checkedPower.get(i).bindName, 0, 0));
        }
    }

    @Override
    public void setOpenEditTextChanged(ChazuoData item, String openTime) {
        Log.d("TAG", "====openTime===" + item + "=====" + openTime);
        if (item == null) {
            return;
        }
        for (int i = 0; i < powerDevices.size(); i++) {
            if (powerDevices.get(i).chazuoId == item.id) {
                if (openTime.equals("")) {
                    powerDevices.get(i).setOpenTime(0);
                } else {
                    powerDevices.get(i).setOpenTime(Integer.parseInt(openTime));
                }
            }
        }
    }

    @OnClick(R.id.btn_to_close)
    public void btn_to_close() {
        for (int i = 0; i < powerDevices.size(); i++) {
            if (powerDevices.get(i).openTime == 0) {
                Toast.makeText(this, "有线路开机时间未添加，请添加", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        LL_check.setVisibility(View.GONE);
        LL_open.setVisibility(View.GONE);
        LL_close.setVisibility(View.VISIBLE);

        closed_power_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PowerDeviceClosedAdapter powerDeviceAdapter = new PowerDeviceClosedAdapter(this, checkedPower, this);
        closed_power_recyclerView.setAdapter(powerDeviceAdapter);
    }

    @Override
    public void setClosedEditTextChanged(ChazuoData item, String closedTime) {
        Log.d("TAG", "===closedTime====" + item + "=====" + closedTime);
        if (item == null) {
            return;
        }
        for (int i = 0; i < powerDevices.size(); i++) {
            if (powerDevices.get(i).chazuoId == item.id) {
                if (closedTime.equals("")) {
                    powerDevices.get(i).setClosedTime(0);
                } else {
                    powerDevices.get(i).setClosedTime(Integer.parseInt(closedTime));
                }
            }
        }
    }

    @OnClick(R.id.btn_powerbox_ok)
    public void btn_powerbox_ok() {
        for (int i = 0; i < powerDevices.size(); i++) {
            if (powerDevices.get(i).closedTime == 0) {
                Toast.makeText(this, "有线路关机时间未添加，请添加", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        PowerDeviceDao powerDeviceDao = MyApplication.getDaoSession().getPowerDeviceDao();
        if (powerDeviceDao.loadAll().size() != 0) {
            powerDeviceDao.deleteAll();
        }
        for (int j = 0; j < powerDevices.size(); j++) {
            powerDeviceDao.insert(powerDevices.get(j));
        }

        startActivity(new Intent(this, PowerDeviceActivity.class));
        finish();
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
