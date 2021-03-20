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
    @BindView(R.id.event_et_3001)
    EditText event_et_3001;
    @BindView(R.id.event_et_3002)
    EditText event_et_3002;
    @BindView(R.id.event_et_3003)
    EditText event_et_3003;
    @BindView(R.id.event_et_3004)
    EditText event_et_3004;
    @BindView(R.id.event_et_3005)
    EditText event_et_3005;
    @BindView(R.id.event_et_3006)
    EditText event_et_3006;
    @BindView(R.id.event_et_3007)
    EditText event_et_3007;
    @BindView(R.id.event_et_3008)
    EditText event_et_3008;
    @BindView(R.id.event_et_3009)
    EditText event_et_3009;
    @BindView(R.id.event_et_3010)
    EditText event_et_3010;


    @BindView(R.id.event_tv_time_13)
    TextView event_tv_time_13;
    @BindView(R.id.event_tv_time_14)
    TextView event_tv_time_14;
    @BindView(R.id.event_tv_time_3001)
    TextView event_tv_time_3001;
    @BindView(R.id.event_tv_time_3002)
    TextView event_tv_time_3002;
    @BindView(R.id.event_tv_time_3003)
    TextView event_tv_time_3003;
    @BindView(R.id.event_tv_time_3004)
    TextView event_tv_time_3004;
    @BindView(R.id.event_tv_time_3005)
    TextView event_tv_time_3005;
    @BindView(R.id.event_tv_time_3006)
    TextView event_tv_time_3006;
    @BindView(R.id.event_tv_time_3007)
    TextView event_tv_time_3007;
    @BindView(R.id.event_tv_time_3008)
    TextView event_tv_time_3008;
    @BindView(R.id.event_tv_time_3009)
    TextView event_tv_time_3009;
    @BindView(R.id.event_tv_time_3010)
    TextView event_tv_time_3010;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengguang);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_13.setText(mLsListsDao.load((long) 13).strMLs);
        event_et_14.setText(mLsListsDao.load((long) 14).strMLs);
        event_et_3001.setText(mLsListsDao.load((long) 3001).strMLs);
        event_et_3002.setText(mLsListsDao.load((long) 3002).strMLs);
        event_et_3003.setText(mLsListsDao.load((long) 3003).strMLs);
        event_et_3004.setText(mLsListsDao.load((long) 3004).strMLs);
        event_et_3005.setText(mLsListsDao.load((long) 3005).strMLs);
        event_et_3006.setText(mLsListsDao.load((long) 3006).strMLs);
        event_et_3007.setText(mLsListsDao.load((long) 3007).strMLs);
        event_et_3008.setText(mLsListsDao.load((long) 3008).strMLs);
        event_et_3009.setText(mLsListsDao.load((long) 3009).strMLs);
        event_et_3010.setText(mLsListsDao.load((long) 3010).strMLs);

        event_tv_time_13.setText(mLsListsDao.load((long) 13).time);
        event_tv_time_14.setText(mLsListsDao.load((long) 14).time);
        event_tv_time_3001.setText(mLsListsDao.load((long) 3001).time);
        event_tv_time_3002.setText(mLsListsDao.load((long) 3002).time);
        event_tv_time_3003.setText(mLsListsDao.load((long) 3003).time);
        event_tv_time_3004.setText(mLsListsDao.load((long) 3004).time);
        event_tv_time_3005.setText(mLsListsDao.load((long) 3005).time);
        event_tv_time_3006.setText(mLsListsDao.load((long) 3006).time);
        event_tv_time_3007.setText(mLsListsDao.load((long) 3007).time);
        event_tv_time_3008.setText(mLsListsDao.load((long) 3008).time);
        event_tv_time_3009.setText(mLsListsDao.load((long) 3009).time);
        event_tv_time_3010.setText(mLsListsDao.load((long) 3010).time);

    }


    @OnClick(R.id.btn_event_dg_ok)
    public void btn_event_dg_ok() {
        mLsListsDao.update(new MLsLists((long) 13, "灯光开(全开)", event_et_13.getText().toString(), event_tv_time_13.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 14, "灯光关(全关)", event_et_14.getText().toString(), event_tv_time_14.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3001, "黑板灯开", event_et_3001.getText().toString(), event_tv_time_3001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3002, "黑板灯关", event_et_3002.getText().toString(), event_tv_time_3002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3003, "教室灯1开", event_et_3003.getText().toString(), event_tv_time_3003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3004, "教室灯1关", event_et_3004.getText().toString(), event_tv_time_3004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3005, "教室灯2开", event_et_3005.getText().toString(), event_tv_time_3005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3006, "教室灯2关", event_et_3006.getText().toString(), event_tv_time_3006.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3007, "教室灯3开", event_et_3007.getText().toString(), event_tv_time_3007.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3008, "教室灯3关", event_et_3008.getText().toString(), event_tv_time_3008.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3009, "教室灯4开", event_et_3009.getText().toString(), event_tv_time_3009.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3010, "教室灯4关", event_et_3010.getText().toString(), event_tv_time_3010.getText().toString()));
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