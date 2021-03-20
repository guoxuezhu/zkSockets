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

public class WangguanEventActivity extends BaseActivity {

    @BindView(R.id.event_et_7001)
    EditText event_et_7001;
    @BindView(R.id.event_et_7002)
    EditText event_et_7002;
    @BindView(R.id.event_et_7003)
    EditText event_et_7003;
    @BindView(R.id.event_et_7004)
    EditText event_et_7004;
    @BindView(R.id.event_et_7005)
    EditText event_et_7005;
    @BindView(R.id.event_et_7006)
    EditText event_et_7006;

    @BindView(R.id.event_tv_time_7001)
    TextView event_tv_time_7001;
    @BindView(R.id.event_tv_time_7002)
    TextView event_tv_time_7002;
    @BindView(R.id.event_tv_time_7003)
    TextView event_tv_time_7003;
    @BindView(R.id.event_tv_time_7004)
    TextView event_tv_time_7004;
    @BindView(R.id.event_tv_time_7005)
    TextView event_tv_time_7005;
    @BindView(R.id.event_tv_time_7006)
    TextView event_tv_time_7006;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangguan_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_7001.setText(mLsListsDao.load((long) 7001).strMLs);
        event_et_7002.setText(mLsListsDao.load((long) 7002).strMLs);
        event_et_7003.setText(mLsListsDao.load((long) 7003).strMLs);
        event_et_7004.setText(mLsListsDao.load((long) 7004).strMLs);
        event_et_7005.setText(mLsListsDao.load((long) 7005).strMLs);
        event_et_7006.setText(mLsListsDao.load((long) 7006).strMLs);

        event_tv_time_7001.setText(mLsListsDao.load((long) 7001).time);
        event_tv_time_7002.setText(mLsListsDao.load((long) 7002).time);
        event_tv_time_7003.setText(mLsListsDao.load((long) 7003).time);
        event_tv_time_7004.setText(mLsListsDao.load((long) 7004).time);
        event_tv_time_7005.setText(mLsListsDao.load((long) 7005).time);
        event_tv_time_7006.setText(mLsListsDao.load((long) 7006).time);

    }

    @OnClick(R.id.btn_event_wgkzq_ok)
    public void btn_event_wgkzq_ok() {
        mLsListsDao.update(new MLsLists((long) 7001, "自习模式", event_et_7001.getText().toString(), event_tv_time_7001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7002, "投影模式", event_et_7002.getText().toString(), event_tv_time_7002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7003, "课件模式", event_et_7003.getText().toString(), event_tv_time_7003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7004, "自动模式", event_et_7004.getText().toString(), event_tv_time_7004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7005, "模式1", event_et_7005.getText().toString(), event_tv_time_7005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7006, "模式2", event_et_7006.getText().toString(), event_tv_time_7006.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.wgkzq_event_btn_back)
    public void wgkzq_event_btn_back() {
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