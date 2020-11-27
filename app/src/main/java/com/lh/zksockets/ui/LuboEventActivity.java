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

public class LuboEventActivity extends BaseActivity {

    @BindView(R.id.event_et_54)
    EditText event_et_54;
    @BindView(R.id.event_et_55)
    EditText event_et_55;
    @BindView(R.id.event_et_56)
    EditText event_et_56;
    @BindView(R.id.event_et_57)
    EditText event_et_57;
    @BindView(R.id.event_et_58)
    EditText event_et_58;
    @BindView(R.id.event_et_59)
    EditText event_et_59;

    @BindView(R.id.event_tv_time_54)
    TextView event_tv_time_54;
    @BindView(R.id.event_tv_time_55)
    TextView event_tv_time_55;
    @BindView(R.id.event_tv_time_56)
    TextView event_tv_time_56;
    @BindView(R.id.event_tv_time_57)
    TextView event_tv_time_57;
    @BindView(R.id.event_tv_time_58)
    TextView event_tv_time_58;
    @BindView(R.id.event_tv_time_59)
    TextView event_tv_time_59;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lubo_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        event_et_54.setText(mLsListsDao.load((long) 54).strMLs);
        event_et_55.setText(mLsListsDao.load((long) 55).strMLs);
        event_et_56.setText(mLsListsDao.load((long) 56).strMLs);
        event_et_57.setText(mLsListsDao.load((long) 57).strMLs);
        event_et_58.setText(mLsListsDao.load((long) 58).strMLs);
        event_et_59.setText(mLsListsDao.load((long) 59).strMLs);

        event_tv_time_54.setText(mLsListsDao.load((long) 54).time);
        event_tv_time_55.setText(mLsListsDao.load((long) 55).time);
        event_tv_time_56.setText(mLsListsDao.load((long) 56).time);
        event_tv_time_57.setText(mLsListsDao.load((long) 57).time);
        event_tv_time_58.setText(mLsListsDao.load((long) 58).time);
        event_tv_time_59.setText(mLsListsDao.load((long) 59).time);
    }




    @OnClick(R.id.btn_event_lubo_ok)
    public void btn_event_lubo_ok() {
        mLsListsDao.update(new MLsLists((long) 54, "开始录制", event_et_54.getText().toString(), event_tv_time_54.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 55, "暂停录制", event_et_55.getText().toString(), event_tv_time_55.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 56, "继续录制", event_et_56.getText().toString(), event_tv_time_56.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 57, "停止录制", event_et_57.getText().toString(), event_tv_time_57.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 58, "开始直播", event_et_58.getText().toString(), event_tv_time_58.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 59, "停止直播", event_et_59.getText().toString(), event_tv_time_59.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.lubo_event_btn_back)
    public void lubo_event_btn_back() {
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