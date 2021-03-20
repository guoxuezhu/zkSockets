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

    @BindView(R.id.event_et_4101)
    EditText event_et_4101;
    @BindView(R.id.event_et_4102)
    EditText event_et_4102;
    @BindView(R.id.event_et_4103)
    EditText event_et_4103;
    @BindView(R.id.event_et_4104)
    EditText event_et_4104;
    @BindView(R.id.event_et_4105)
    EditText event_et_4105;
    @BindView(R.id.event_et_4106)
    EditText event_et_4106;
    @BindView(R.id.event_et_4107)
    EditText event_et_4107;
    @BindView(R.id.event_et_4108)
    EditText event_et_4108;
    @BindView(R.id.event_et_4109)
    EditText event_et_4109;
    @BindView(R.id.event_et_4110)
    EditText event_et_4110;
    @BindView(R.id.event_et_4111)
    EditText event_et_4111;
    @BindView(R.id.event_et_4112)
    EditText event_et_4112;

    @BindView(R.id.event_tv_time_4101)
    TextView event_tv_time_4101;
    @BindView(R.id.event_tv_time_4102)
    TextView event_tv_time_4102;
    @BindView(R.id.event_tv_time_4103)
    TextView event_tv_time_4103;
    @BindView(R.id.event_tv_time_4104)
    TextView event_tv_time_4104;
    @BindView(R.id.event_tv_time_4105)
    TextView event_tv_time_4105;
    @BindView(R.id.event_tv_time_4106)
    TextView event_tv_time_4106;
    @BindView(R.id.event_tv_time_4107)
    TextView event_tv_time_4107;
    @BindView(R.id.event_tv_time_4108)
    TextView event_tv_time_4108;
    @BindView(R.id.event_tv_time_4109)
    TextView event_tv_time_4109;
    @BindView(R.id.event_tv_time_4110)
    TextView event_tv_time_4110;
    @BindView(R.id.event_tv_time_4111)
    TextView event_tv_time_4111;
    @BindView(R.id.event_tv_time_4112)
    TextView event_tv_time_4112;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinpin_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_4101.setText(mLsListsDao.load((long) 4101).strMLs);
        event_et_4102.setText(mLsListsDao.load((long) 4102).strMLs);
        event_et_4103.setText(mLsListsDao.load((long) 4103).strMLs);
        event_et_4104.setText(mLsListsDao.load((long) 4104).strMLs);
        event_et_4105.setText(mLsListsDao.load((long) 4105).strMLs);
        event_et_4106.setText(mLsListsDao.load((long) 4106).strMLs);
        event_et_4107.setText(mLsListsDao.load((long) 4107).strMLs);
        event_et_4108.setText(mLsListsDao.load((long) 4108).strMLs);
        event_et_4109.setText(mLsListsDao.load((long) 4109).strMLs);
        event_et_4110.setText(mLsListsDao.load((long) 4110).strMLs);
        event_et_4111.setText(mLsListsDao.load((long) 4111).strMLs);
        event_et_4112.setText(mLsListsDao.load((long) 4112).strMLs);

        event_tv_time_4101.setText(mLsListsDao.load((long) 4101).time);
        event_tv_time_4102.setText(mLsListsDao.load((long) 4102).time);
        event_tv_time_4103.setText(mLsListsDao.load((long) 4103).time);
        event_tv_time_4104.setText(mLsListsDao.load((long) 4104).time);
        event_tv_time_4105.setText(mLsListsDao.load((long) 4105).time);
        event_tv_time_4106.setText(mLsListsDao.load((long) 4106).time);
        event_tv_time_4107.setText(mLsListsDao.load((long) 4107).time);
        event_tv_time_4108.setText(mLsListsDao.load((long) 4108).time);
        event_tv_time_4109.setText(mLsListsDao.load((long) 4109).time);
        event_tv_time_4110.setText(mLsListsDao.load((long) 4110).time);
        event_tv_time_4111.setText(mLsListsDao.load((long) 4111).time);
        event_tv_time_4112.setText(mLsListsDao.load((long) 4112).time);

    }


    @OnClick(R.id.btn_event_yinpin_ok)
    public void btn_event_yinpin_ok() {
        mLsListsDao.update(new MLsLists((long) 4101, "音量1级", event_et_4101.getText().toString(), event_tv_time_4101.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4102, "音量2级", event_et_4102.getText().toString(), event_tv_time_4102.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4103, "音量3级", event_et_4103.getText().toString(), event_tv_time_4103.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4104, "音量4级", event_et_4104.getText().toString(), event_tv_time_4104.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4105, "音量5级", event_et_4105.getText().toString(), event_tv_time_4105.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4106, "音量6级", event_et_4106.getText().toString(), event_tv_time_4106.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4107, "音量7级", event_et_4107.getText().toString(), event_tv_time_4107.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4108, "音量8级", event_et_4108.getText().toString(), event_tv_time_4108.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4109, "音量9级", event_et_4109.getText().toString(), event_tv_time_4109.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4110, "音量10级", event_et_4110.getText().toString(), event_tv_time_4110.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4111, "静音开", event_et_4111.getText().toString(), event_tv_time_4111.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4112, "静音关", event_et_4112.getText().toString(), event_tv_time_4112.getText().toString()));

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