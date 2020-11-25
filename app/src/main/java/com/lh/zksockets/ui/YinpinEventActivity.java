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

public class YinpinEventActivity extends BaseActivity {

    @BindView(R.id.event_et_21)
    EditText event_et_21;
    @BindView(R.id.event_et_22)
    EditText event_et_22;
    @BindView(R.id.event_et_23)
    EditText event_et_23;
    @BindView(R.id.event_et_24)
    EditText event_et_24;

    @BindView(R.id.event_tv_time_21)
    TextView event_tv_time_21;
    @BindView(R.id.event_tv_time_22)
    TextView event_tv_time_22;
    @BindView(R.id.event_tv_time_23)
    TextView event_tv_time_23;
    @BindView(R.id.event_tv_time_24)
    TextView event_tv_time_24;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinpin_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_21.setText(mLsListsDao.load((long) 21).strMLs);
        event_et_22.setText(mLsListsDao.load((long) 22).strMLs);
        event_et_23.setText(mLsListsDao.load((long) 23).strMLs);
        event_et_24.setText(mLsListsDao.load((long) 24).strMLs);

        event_tv_time_21.setText(mLsListsDao.load((long) 21).time);
        event_tv_time_22.setText(mLsListsDao.load((long) 22).time);
        event_tv_time_23.setText(mLsListsDao.load((long) 23).time);
        event_tv_time_24.setText(mLsListsDao.load((long) 24).time);

    }



    @OnClick(R.id.btn_event_yinpin_ok)
    public void btn_event_yinpin_ok() {
        mLsListsDao.update(new MLsLists((long) 21, "总音量+", event_et_21.getText().toString(), event_tv_time_21.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 22, "总音量-", event_et_22.getText().toString(), event_tv_time_22.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 23, "总音量静音开", event_et_23.getText().toString(), event_tv_time_23.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 24, "总音量静音关", event_et_24.getText().toString(), event_tv_time_24.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.yinpin_event_btn_back)
    public void yinpin_event_btn_back() {
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