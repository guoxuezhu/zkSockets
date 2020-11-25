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

public class DoorEventActivity extends BaseActivity {

    @BindView(R.id.event_tv_time_46)
    TextView event_tv_time_46;
    @BindView(R.id.event_tv_time_47)
    TextView event_tv_time_47;

    @BindView(R.id.event_et_46)
    EditText event_et_46;
    @BindView(R.id.event_et_47)
    EditText event_et_47;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_46.setText(mLsListsDao.load((long) 46).strMLs);
        event_et_47.setText(mLsListsDao.load((long) 47).strMLs);
        event_tv_time_46.setText(mLsListsDao.load((long) 46).time);
        event_tv_time_47.setText(mLsListsDao.load((long) 47).time);

    }


    @OnClick(R.id.btn_event_door_ok)
    public void btn_event_door_ok() {
        mLsListsDao.update(new MLsLists((long) 46, "门禁-前门", event_et_46.getText().toString(), event_tv_time_46.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 47, "门禁-后门", event_et_47.getText().toString(), event_tv_time_47.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.door_event_btn_back)
    public void door_event_btn_back() {
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