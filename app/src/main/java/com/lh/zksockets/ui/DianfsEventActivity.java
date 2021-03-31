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

public class DianfsEventActivity extends BaseActivity {

    @BindView(R.id.event_tv_time_8001)
    TextView event_tv_time_8001;
    @BindView(R.id.event_tv_time_8002)
    TextView event_tv_time_8002;
    @BindView(R.id.event_tv_time_8003)
    TextView event_tv_time_8003;
    @BindView(R.id.event_tv_time_8004)
    TextView event_tv_time_8004;

    @BindView(R.id.event_et_8001)
    EditText event_et_8001;
    @BindView(R.id.event_et_8002)
    EditText event_et_8002;
    @BindView(R.id.event_et_8003)
    EditText event_et_8003;
    @BindView(R.id.event_et_8004)
    EditText event_et_8004;


    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianfs_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_8001.setText(mLsListsDao.load((long) 8001).strMLs);
        event_et_8002.setText(mLsListsDao.load((long) 8002).strMLs);
        event_et_8003.setText(mLsListsDao.load((long) 8003).strMLs);
        event_et_8004.setText(mLsListsDao.load((long) 8004).strMLs);

        event_tv_time_8001.setText(mLsListsDao.load((long) 8001).time);
        event_tv_time_8002.setText(mLsListsDao.load((long) 8002).time);
        event_tv_time_8003.setText(mLsListsDao.load((long) 8003).time);
        event_tv_time_8004.setText(mLsListsDao.load((long) 8004).time);

    }


    @OnClick(R.id.btn_event_dianfs_ok)
    public void btn_event_dianfs_ok() {
        mLsListsDao.update(new MLsLists((long) 8001, "电风扇全关", event_et_8001.getText().toString(), event_tv_time_8001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8002, "电风扇全1级", event_et_8002.getText().toString(), event_tv_time_8002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8003, "电风扇全2级", event_et_8003.getText().toString(), event_tv_time_8003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8004, "电风扇全3级", event_et_8004.getText().toString(), event_tv_time_8004.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.dianfs_event_btn_back)
    public void dianfs_event_btn_back() {
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