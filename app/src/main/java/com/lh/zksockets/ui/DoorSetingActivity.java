package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DoorInfoDao;
import com.lh.zksockets.data.model.DoorInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoorSetingActivity extends BaseActivity {

    @BindView(R.id.et_door_name)
    EditText et_door_name;
    @BindView(R.id.et_door_ip)
    EditText et_door_ip;

    @BindView(R.id.door_switch)
    Switch door_switch;


    private DoorInfoDao doorInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_seting);
        ButterKnife.bind(this);

        doorInfoDao = MyApplication.getDaoSession().getDoorInfoDao();

        if (doorInfoDao.loadAll().size() == 0) {
            doorInfoDao.insert(new DoorInfo("", "", 0));
        }

        et_door_name.setText(doorInfoDao.loadAll().get(0).name);
        et_door_ip.setText(doorInfoDao.loadAll().get(0).IP);

        if (doorInfoDao.loadAll().get(0).isStart == 1) {
            door_switch.setChecked(true);
        }

    }

    @OnClick(R.id.btn_door_ok)
    public void btn_door_ok() {
        doorInfoDao.deleteAll();
        if (door_switch.isChecked()) {
            doorInfoDao.insert(new DoorInfo(et_door_ip.getText().toString(), et_door_name.getText().toString(), 1));
        } else {
            doorInfoDao.insert(new DoorInfo(et_door_ip.getText().toString(), et_door_name.getText().toString(), 0));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.door_btn_back)
    public void door_btn_back() {
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
