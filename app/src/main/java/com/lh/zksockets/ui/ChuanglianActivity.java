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
    @BindView(R.id.event_et_1001)
    EditText event_et_1001;
    @BindView(R.id.event_et_1002)
    EditText event_et_1002;
    @BindView(R.id.event_et_1003)
    EditText event_et_1003;
    @BindView(R.id.event_et_1004)
    EditText event_et_1004;
    @BindView(R.id.event_et_1005)
    EditText event_et_1005;
    @BindView(R.id.event_et_1006)
    EditText event_et_1006;


    @BindView(R.id.event_tv_time_3)
    TextView event_tv_time_3;
    @BindView(R.id.event_tv_time_4)
    TextView event_tv_time_4;
    @BindView(R.id.event_tv_time_5)
    TextView event_tv_time_5;
    @BindView(R.id.event_tv_time_1001)
    TextView event_tv_time_1001;
    @BindView(R.id.event_tv_time_1002)
    TextView event_tv_time_1002;
    @BindView(R.id.event_tv_time_1003)
    TextView event_tv_time_1003;
    @BindView(R.id.event_tv_time_1004)
    TextView event_tv_time_1004;
    @BindView(R.id.event_tv_time_1005)
    TextView event_tv_time_1005;
    @BindView(R.id.event_tv_time_1006)
    TextView event_tv_time_1006;


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
        event_et_1001.setText(mLsListsDao.load((long) 1001).strMLs);
        event_et_1002.setText(mLsListsDao.load((long) 1002).strMLs);
        event_et_1003.setText(mLsListsDao.load((long) 1003).strMLs);
        event_et_1004.setText(mLsListsDao.load((long) 1004).strMLs);
        event_et_1005.setText(mLsListsDao.load((long) 1005).strMLs);
        event_et_1006.setText(mLsListsDao.load((long) 1006).strMLs);

        event_tv_time_3.setText(mLsListsDao.load((long) 3).time);
        event_tv_time_4.setText(mLsListsDao.load((long) 4).time);
        event_tv_time_5.setText(mLsListsDao.load((long) 5).time);
        event_tv_time_1001.setText(mLsListsDao.load((long) 1001).time);
        event_tv_time_1002.setText(mLsListsDao.load((long) 1002).time);
        event_tv_time_1003.setText(mLsListsDao.load((long) 1003).time);
        event_tv_time_1004.setText(mLsListsDao.load((long) 1004).time);
        event_tv_time_1005.setText(mLsListsDao.load((long) 1005).time);
        event_tv_time_1006.setText(mLsListsDao.load((long) 1006).time);

    }


    @OnClick(R.id.btn_event_cl_ok)
    public void btn_event_cl_ok() {

        mLsListsDao.update(new MLsLists((long) 3, "窗帘开(全开)", event_et_3.getText().toString(), event_tv_time_3.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4, "窗帘关(全关)", event_et_4.getText().toString(), event_tv_time_4.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5, "窗帘暂停(全暂停)", event_et_5.getText().toString(), event_tv_time_5.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1001, "窗帘1开", event_et_1001.getText().toString(), event_tv_time_1001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1002, "窗帘1关", event_et_1002.getText().toString(), event_tv_time_1002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1003, "窗帘1暂停", event_et_1003.getText().toString(), event_tv_time_1003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1004, "窗帘2开", event_et_1004.getText().toString(), event_tv_time_1004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1005, "窗帘2关", event_et_1005.getText().toString(), event_tv_time_1005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 1006, "窗帘2暂停", event_et_1006.getText().toString(), event_tv_time_1006.getText().toString()));

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