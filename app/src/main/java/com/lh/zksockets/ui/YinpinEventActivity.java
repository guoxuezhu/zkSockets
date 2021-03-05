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
    @BindView(R.id.event_et_25)
    EditText event_et_25;
    @BindView(R.id.event_et_26)
    EditText event_et_26;
    @BindView(R.id.event_et_27)
    EditText event_et_27;
    @BindView(R.id.event_et_28)
    EditText event_et_28;
    @BindView(R.id.event_et_29)
    EditText event_et_29;
    @BindView(R.id.event_et_30)
    EditText event_et_30;
    @BindView(R.id.event_et_31)
    EditText event_et_31;


    @BindView(R.id.event_tv_time_21)
    TextView event_tv_time_21;
    @BindView(R.id.event_tv_time_22)
    TextView event_tv_time_22;
    @BindView(R.id.event_tv_time_23)
    TextView event_tv_time_23;
    @BindView(R.id.event_tv_time_24)
    TextView event_tv_time_24;
    @BindView(R.id.event_tv_time_25)
    TextView event_tv_time_25;
    @BindView(R.id.event_tv_time_26)
    TextView event_tv_time_26;
    @BindView(R.id.event_tv_time_27)
    TextView event_tv_time_27;
    @BindView(R.id.event_tv_time_28)
    TextView event_tv_time_28;
    @BindView(R.id.event_tv_time_29)
    TextView event_tv_time_29;
    @BindView(R.id.event_tv_time_30)
    TextView event_tv_time_30;
    @BindView(R.id.event_tv_time_31)
    TextView event_tv_time_31;

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
        event_et_25.setText(mLsListsDao.load((long) 25).strMLs);
        event_et_26.setText(mLsListsDao.load((long) 26).strMLs);
        event_et_27.setText(mLsListsDao.load((long) 27).strMLs);
        event_et_28.setText(mLsListsDao.load((long) 28).strMLs);
        event_et_29.setText(mLsListsDao.load((long) 29).strMLs);
        event_et_30.setText(mLsListsDao.load((long) 30).strMLs);
        event_et_31.setText(mLsListsDao.load((long) 31).strMLs);

        event_tv_time_21.setText(mLsListsDao.load((long) 21).time);
        event_tv_time_22.setText(mLsListsDao.load((long) 22).time);
        event_tv_time_23.setText(mLsListsDao.load((long) 23).time);
        event_tv_time_24.setText(mLsListsDao.load((long) 24).time);
        event_tv_time_25.setText(mLsListsDao.load((long) 25).time);
        event_tv_time_26.setText(mLsListsDao.load((long) 26).time);
        event_tv_time_27.setText(mLsListsDao.load((long) 27).time);
        event_tv_time_28.setText(mLsListsDao.load((long) 28).time);
        event_tv_time_29.setText(mLsListsDao.load((long) 29).time);
        event_tv_time_30.setText(mLsListsDao.load((long) 30).time);
        event_tv_time_31.setText(mLsListsDao.load((long) 31).time);

    }


    @OnClick(R.id.btn_event_yinpin_ok)
    public void btn_event_yinpin_ok() {
        mLsListsDao.update(new MLsLists((long) 21, "音量1级", event_et_21.getText().toString(), event_tv_time_21.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 22, "音量2级", event_et_22.getText().toString(), event_tv_time_22.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 23, "音量3级", event_et_23.getText().toString(), event_tv_time_23.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 24, "音量4级", event_et_24.getText().toString(), event_tv_time_24.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 25, "音量5级", event_et_25.getText().toString(), event_tv_time_25.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 26, "音量6级", event_et_26.getText().toString(), event_tv_time_26.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 27, "音量7级", event_et_27.getText().toString(), event_tv_time_27.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 28, "音量8级", event_et_28.getText().toString(), event_tv_time_28.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 29, "音量9级", event_et_29.getText().toString(), event_tv_time_29.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 30, "音量10", event_et_30.getText().toString(), event_tv_time_30.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 31, "静音", event_et_31.getText().toString(), event_tv_time_31.getText().toString()));

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