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

public class OnekeyEventActivity extends BaseActivity {

    @BindView(R.id.event_et_1)
    EditText event_et_1;
    @BindView(R.id.event_et_2)
    EditText event_et_2;
    @BindView(R.id.event_et_45)
    EditText event_et_45;

    @BindView(R.id.event_tv_time_1)
    TextView event_tv_time_1;
    @BindView(R.id.event_tv_time_2)
    TextView event_tv_time_2;
    @BindView(R.id.event_tv_time_45)
    TextView event_tv_time_45;

    private MLsListsDao mLsListsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onekey_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        event_et_1.setText(mLsListsDao.load((long) 1).strMLs);
        event_et_2.setText(mLsListsDao.load((long) 2).strMLs);
        event_et_45.setText(mLsListsDao.load((long) 45).strMLs);

        event_tv_time_1.setText(mLsListsDao.load((long) 1).time);
        event_tv_time_2.setText(mLsListsDao.load((long) 2).time);
        event_tv_time_45.setText(mLsListsDao.load((long) 45).time);
    }


    @OnClick(R.id.btn_event_cj_ok)
    public void btn_event_cj_ok() {
        mLsListsDao.update(new MLsLists((long) 1, "上课", event_et_1.getText().toString(), event_tv_time_1.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2, "下课", event_et_2.getText().toString(), event_tv_time_2.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 45, "开机", event_et_45.getText().toString(), event_tv_time_45.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.one_event_btn_back)
    public void one_event_btn_back() {
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