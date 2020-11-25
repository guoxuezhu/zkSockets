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

public class ChuanglianActivity extends BaseActivity {

    @BindView(R.id.event_et_3)
    EditText event_et_3;
    @BindView(R.id.event_et_4)
    EditText event_et_4;
    @BindView(R.id.event_et_5)
    EditText event_et_5;
    @BindView(R.id.event_et_6)
    EditText event_et_6;
    @BindView(R.id.event_et_7)
    EditText event_et_7;
    @BindView(R.id.event_et_8)
    EditText event_et_8;

    @BindView(R.id.event_tv_time_3)
    TextView event_tv_time_3;
    @BindView(R.id.event_tv_time_4)
    TextView event_tv_time_4;
    @BindView(R.id.event_tv_time_5)
    TextView event_tv_time_5;
    @BindView(R.id.event_tv_time_6)
    TextView event_tv_time_6;
    @BindView(R.id.event_tv_time_7)
    TextView event_tv_time_7;
    @BindView(R.id.event_tv_time_8)
    TextView event_tv_time_8;


    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuanglian);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        event_et_3.setText(mLsListsDao.load((long) 3).strMLs);
        event_et_4.setText(mLsListsDao.load((long) 4).strMLs);
        event_et_5.setText(mLsListsDao.load((long) 5).strMLs);
        event_et_6.setText(mLsListsDao.load((long) 6).strMLs);
        event_et_7.setText(mLsListsDao.load((long) 7).strMLs);
        event_et_8.setText(mLsListsDao.load((long) 8).strMLs);

        event_tv_time_3.setText(mLsListsDao.load((long) 3).time);
        event_tv_time_4.setText(mLsListsDao.load((long) 4).time);
        event_tv_time_5.setText(mLsListsDao.load((long) 5).time);
        event_tv_time_6.setText(mLsListsDao.load((long) 6).time);
        event_tv_time_7.setText(mLsListsDao.load((long) 7).time);
        event_tv_time_8.setText(mLsListsDao.load((long) 8).time);

    }


    @OnClick(R.id.btn_event_cl_ok)
    public void btn_event_cl_ok() {

        mLsListsDao.update(new MLsLists((long) 3, "窗帘开(全开)", event_et_3.getText().toString(), event_tv_time_3.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4, "窗帘关(全关)", event_et_4.getText().toString(), event_tv_time_4.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5, "窗帘1开", event_et_5.getText().toString(), event_tv_time_5.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6, "窗帘1关", event_et_6.getText().toString(), event_tv_time_6.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7, "窗帘2开", event_et_7.getText().toString(), event_tv_time_7.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8, "窗帘2关", event_et_8.getText().toString(), event_tv_time_8.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.cl_event_btn_back)
    public void cl_event_btn_back() {
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