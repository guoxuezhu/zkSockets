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

public class XinfengEventActivity extends BaseActivity {

    @BindView(R.id.event_tv_time_6001)
    TextView event_tv_time_6001;
    @BindView(R.id.event_tv_time_6002)
    TextView event_tv_time_6002;
    @BindView(R.id.event_tv_time_6003)
    TextView event_tv_time_6003;
    @BindView(R.id.event_tv_time_6004)
    TextView event_tv_time_6004;
    @BindView(R.id.event_tv_time_6005)
    TextView event_tv_time_6005;
    @BindView(R.id.event_tv_time_6006)
    TextView event_tv_time_6006;

    @BindView(R.id.event_et_6001)
    EditText event_et_6001;
    @BindView(R.id.event_et_6002)
    EditText event_et_6002;
    @BindView(R.id.event_et_6003)
    EditText event_et_6003;
    @BindView(R.id.event_et_6004)
    EditText event_et_6004;
    @BindView(R.id.event_et_6005)
    EditText event_et_6005;
    @BindView(R.id.event_et_6006)
    EditText event_et_6006;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinfeng_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_6001.setText(mLsListsDao.load((long) 6001).strMLs);
        event_et_6002.setText(mLsListsDao.load((long) 6002).strMLs);
        event_et_6003.setText(mLsListsDao.load((long) 6003).strMLs);
        event_et_6004.setText(mLsListsDao.load((long) 6004).strMLs);
        event_et_6005.setText(mLsListsDao.load((long) 6005).strMLs);
        event_et_6006.setText(mLsListsDao.load((long) 6006).strMLs);

        event_tv_time_6001.setText(mLsListsDao.load((long) 6001).time);
        event_tv_time_6002.setText(mLsListsDao.load((long) 6002).time);
        event_tv_time_6003.setText(mLsListsDao.load((long) 6003).time);
        event_tv_time_6004.setText(mLsListsDao.load((long) 6004).time);
        event_tv_time_6005.setText(mLsListsDao.load((long) 6005).time);
        event_tv_time_6006.setText(mLsListsDao.load((long) 6006).time);

    }


    @OnClick(R.id.btn_event_xinfeng_ok)
    public void btn_event_xinfeng_ok() {
        mLsListsDao.update(new MLsLists((long) 6001, "新风-开", event_et_6001.getText().toString(), event_tv_time_6001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6002, "新风-关", event_et_6002.getText().toString(), event_tv_time_6002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6003, "新风-自动", event_et_6003.getText().toString(), event_tv_time_6003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6004, "新风-低速", event_et_6004.getText().toString(), event_tv_time_6004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6005, "新风-中速", event_et_6005.getText().toString(), event_tv_time_6005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6006, "新风-高速", event_et_6006.getText().toString(), event_tv_time_6006.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.xinfeng_event_btn_back)
    public void xinfeng_event_btn_back() {
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