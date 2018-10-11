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
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.PowerDeviceDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerDeviceActivity extends Activity implements PowerListAdapter.CallBack {

    @BindView(R.id.power_device_recyclerView)
    RecyclerView power_device_recyclerView;

    private PowerListAdapter powerListAdapter;
    private ChazuoDataDao chazuoDataDao;
    private List<ChazuoData> chazuoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_device);
        ButterKnife.bind(this);

        chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        chazuoDataList = chazuoDataDao.loadAll();
        chazuoDataList.remove(0);
        initRcyclerView();

    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        power_device_recyclerView.setLayoutManager(manager);
        powerListAdapter = new PowerListAdapter(this, chazuoDataList, this);
        power_device_recyclerView.setAdapter(powerListAdapter);
    }

    @Override
    public void setOpenEditTextChanged(ChazuoData item, int position, String time) {
        if (time.equals("")) {
            chazuoDataList.get(position).setOpenTime(0);
        } else {
            chazuoDataList.get(position).setOpenTime(Integer.parseInt(time));
        }
    }

    @Override
    public void setClosedEditTextChanged(ChazuoData item, int position, String time) {
        if (time.equals("")) {
            chazuoDataList.get(position).setClosedTime(0);
        } else {
            chazuoDataList.get(position).setClosedTime(Integer.parseInt(time));
        }
    }

    @Override
    public void onCheckBoxClickItem(boolean isChecked, int position) {
        if (isChecked) {
            chazuoDataList.get(position).setIsOk(true);
        } else {
            chazuoDataList.get(position).setIsOk(false);
        }
    }

    @OnClick(R.id.baocun_power)
    public void baocun_power() {
        ELog.i("======baocun_power=======" + chazuoDataList.toString());
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
