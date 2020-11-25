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

public class TyjEventActivity extends BaseActivity {

    @BindView(R.id.event_et_9)
    EditText event_et_9;
    @BindView(R.id.event_et_10)
    EditText event_et_10;
    @BindView(R.id.event_et_11)
    EditText event_et_11;
    @BindView(R.id.event_et_12)
    EditText event_et_12;

    @BindView(R.id.event_tv_time_9)
    TextView event_tv_time_9;
    @BindView(R.id.event_tv_time_10)
    TextView event_tv_time_10;
    @BindView(R.id.event_tv_time_11)
    TextView event_tv_time_11;
    @BindView(R.id.event_tv_time_12)
    TextView event_tv_time_12;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyj_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        event_et_9.setText(mLsListsDao.load((long) 9).strMLs);
        event_et_10.setText(mLsListsDao.load((long) 10).strMLs);
        event_et_11.setText(mLsListsDao.load((long) 11).strMLs);
        event_et_12.setText(mLsListsDao.load((long) 12).strMLs);

        event_tv_time_9.setText(mLsListsDao.load((long) 9).time);
        event_tv_time_10.setText(mLsListsDao.load((long) 10).time);
        event_tv_time_11.setText(mLsListsDao.load((long) 11).time);
        event_tv_time_12.setText(mLsListsDao.load((long) 12).time);

    }

    @OnClick(R.id.btn_event_tyj_ok)
    public void btn_event_tyj_ok() {
        mLsListsDao.update(new MLsLists((long) 9, "投影机开", event_et_9.getText().toString(), event_tv_time_9.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 10, "投影机关", event_et_10.getText().toString(), event_tv_time_10.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 11, "幕布升", event_et_11.getText().toString(), event_tv_time_11.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 12, "幕布降", event_et_12.getText().toString(), event_tv_time_12.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tyj_event_btn_back)
    public void tyj_event_btn_back() {
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