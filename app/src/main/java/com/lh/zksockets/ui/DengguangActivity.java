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

public class DengguangActivity extends BaseActivity {

    @BindView(R.id.event_et_13)
    EditText event_et_13;
    @BindView(R.id.event_et_14)
    EditText event_et_14;
    @BindView(R.id.event_et_15)
    EditText event_et_15;
    @BindView(R.id.event_et_16)
    EditText event_et_16;
    @BindView(R.id.event_et_17)
    EditText event_et_17;
    @BindView(R.id.event_et_18)
    EditText event_et_18;
    @BindView(R.id.event_et_19)
    EditText event_et_19;
    @BindView(R.id.event_et_20)
    EditText event_et_20;

    @BindView(R.id.event_tv_time_13)
    TextView event_tv_time_13;
    @BindView(R.id.event_tv_time_14)
    TextView event_tv_time_14;
    @BindView(R.id.event_tv_time_15)
    TextView event_tv_time_15;
    @BindView(R.id.event_tv_time_16)
    TextView event_tv_time_16;
    @BindView(R.id.event_tv_time_17)
    TextView event_tv_time_17;
    @BindView(R.id.event_tv_time_18)
    TextView event_tv_time_18;
    @BindView(R.id.event_tv_time_19)
    TextView event_tv_time_19;
    @BindView(R.id.event_tv_time_20)
    TextView event_tv_time_20;



    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengguang);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_13.setText(mLsListsDao.load((long) 13).strMLs);
        event_et_14.setText(mLsListsDao.load((long) 14).strMLs);
        event_et_15.setText(mLsListsDao.load((long) 15).strMLs);
        event_et_16.setText(mLsListsDao.load((long) 16).strMLs);
        event_et_17.setText(mLsListsDao.load((long) 17).strMLs);
        event_et_18.setText(mLsListsDao.load((long) 18).strMLs);
        event_et_19.setText(mLsListsDao.load((long) 19).strMLs);
        event_et_20.setText(mLsListsDao.load((long) 20).strMLs);

        event_tv_time_13.setText(mLsListsDao.load((long) 13).time);
        event_tv_time_14.setText(mLsListsDao.load((long) 14).time);
        event_tv_time_15.setText(mLsListsDao.load((long) 15).time);
        event_tv_time_16.setText(mLsListsDao.load((long) 16).time);
        event_tv_time_17.setText(mLsListsDao.load((long) 17).time);
        event_tv_time_18.setText(mLsListsDao.load((long) 18).time);
        event_tv_time_19.setText(mLsListsDao.load((long) 19).time);
        event_tv_time_20.setText(mLsListsDao.load((long) 20).time);

    }


    @OnClick(R.id.btn_event_dg_ok)
    public void btn_event_dg_ok() {
        mLsListsDao.update(new MLsLists((long) 13, "灯光开(全开)", event_et_13.getText().toString(), event_tv_time_13.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 14, "灯光关(全关)", event_et_14.getText().toString(), event_tv_time_14.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 15, "灯光1开", event_et_15.getText().toString(), event_tv_time_15.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 16, "灯光1关", event_et_16.getText().toString(), event_tv_time_16.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 17, "灯光2开", event_et_17.getText().toString(), event_tv_time_17.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 18, "灯光2关", event_et_18.getText().toString(), event_tv_time_18.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 19, "灯光3开", event_et_19.getText().toString(), event_tv_time_19.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 20, "灯光3关", event_et_20.getText().toString(), event_tv_time_20.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.dg_event_btn_back)
    public void dg_event_btn_back() {
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