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

public class PcEventActivity extends BaseActivity {

    @BindView(R.id.event_et_5001)
    EditText event_et_5001;
    @BindView(R.id.event_et_5002)
    EditText event_et_5002;
    @BindView(R.id.event_et_5003)
    EditText event_et_5003;
    @BindView(R.id.event_et_5004)
    EditText event_et_5004;
    @BindView(R.id.event_et_5005)
    EditText event_et_5005;
    @BindView(R.id.event_et_5006)
    EditText event_et_5006;
    @BindView(R.id.event_et_5007)
    EditText event_et_5007;
    @BindView(R.id.event_et_5008)
    EditText event_et_5008;

    @BindView(R.id.event_tv_time_5001)
    TextView event_tv_time_5001;
    @BindView(R.id.event_tv_time_5002)
    TextView event_tv_time_5002;
    @BindView(R.id.event_tv_time_5003)
    TextView event_tv_time_5003;
    @BindView(R.id.event_tv_time_5004)
    TextView event_tv_time_5004;
    @BindView(R.id.event_tv_time_5005)
    TextView event_tv_time_5005;
    @BindView(R.id.event_tv_time_5006)
    TextView event_tv_time_5006;
    @BindView(R.id.event_tv_time_5007)
    TextView event_tv_time_5007;
    @BindView(R.id.event_tv_time_5008)
    TextView event_tv_time_5008;


    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_5001.setText(mLsListsDao.load((long) 5001).strMLs);
        event_et_5002.setText(mLsListsDao.load((long) 5002).strMLs);
        event_et_5003.setText(mLsListsDao.load((long) 5003).strMLs);
        event_et_5004.setText(mLsListsDao.load((long) 5004).strMLs);
        event_et_5005.setText(mLsListsDao.load((long) 5005).strMLs);
        event_et_5006.setText(mLsListsDao.load((long) 5006).strMLs);
        event_et_5007.setText(mLsListsDao.load((long) 5007).strMLs);
        event_et_5008.setText(mLsListsDao.load((long) 5008).strMLs);

        event_tv_time_5001.setText(mLsListsDao.load((long) 5001).time);
        event_tv_time_5002.setText(mLsListsDao.load((long) 5002).time);
        event_tv_time_5003.setText(mLsListsDao.load((long) 5003).time);
        event_tv_time_5004.setText(mLsListsDao.load((long) 5004).time);
        event_tv_time_5005.setText(mLsListsDao.load((long) 5005).time);
        event_tv_time_5006.setText(mLsListsDao.load((long) 5006).time);
        event_tv_time_5007.setText(mLsListsDao.load((long) 5007).time);
        event_tv_time_5008.setText(mLsListsDao.load((long) 5008).time);

    }


    @OnClick(R.id.btn_event_pc_ok)
    public void btn_event_pc_ok() {

        mLsListsDao.update(new MLsLists((long) 5001, "大屏一体机开", event_et_5001.getText().toString(), event_tv_time_5001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5002, "大屏一体机关", event_et_5002.getText().toString(), event_tv_time_5002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5003, "大屏一体机内置显示", event_et_5003.getText().toString(), event_tv_time_5003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5004, "大屏一体机外置HDMI", event_et_5004.getText().toString(), event_tv_time_5004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5005, "电视机1", event_et_5005.getText().toString(), event_tv_time_5005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5006, "电视机2", event_et_5006.getText().toString(), event_tv_time_5006.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5007, "电视机3", event_et_5007.getText().toString(), event_tv_time_5007.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5008, "电视机4", event_et_5008.getText().toString(), event_tv_time_5008.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.pc_event_btn_back)
    public void pc_event_btn_back() {
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