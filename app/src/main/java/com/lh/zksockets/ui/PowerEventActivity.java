package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.MLsLists;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerEventActivity extends BaseActivity {

    @BindView(R.id.event_tv_time_37)
    TextView event_tv_time_37;
    @BindView(R.id.event_tv_time_38)
    TextView event_tv_time_38;

    @BindView(R.id.event_et_37)
    EditText event_et_37;
    @BindView(R.id.event_et_38)
    EditText event_et_38;

    private MLsListsDao mLsListsDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_37.setText(mLsListsDao.load((long) 37).strMLs);
        event_et_38.setText(mLsListsDao.load((long) 38).strMLs);
        event_tv_time_37.setText(mLsListsDao.load((long) 37).time);
        event_tv_time_38.setText(mLsListsDao.load((long) 38).time);
    }



    @OnClick(R.id.btn_event_power_ok)
    public void btn_event_power_ok() {
        mLsListsDao.update(new MLsLists((long) 37, "电源-全开", event_et_37.getText().toString(), event_tv_time_37.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 38, "电源-全关", event_et_38.getText().toString(), event_tv_time_38.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.power_event_btn_back)
    public void power_event_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, EventActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}