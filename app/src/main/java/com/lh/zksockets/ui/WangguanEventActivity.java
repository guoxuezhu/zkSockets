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

public class WangguanEventActivity extends BaseActivity {

    @BindView(R.id.event_et_62)
    EditText event_et_62;
    @BindView(R.id.event_et_63)
    EditText event_et_63;
    @BindView(R.id.event_et_64)
    EditText event_et_64;
    @BindView(R.id.event_et_65)
    EditText event_et_65;
    @BindView(R.id.event_et_66)
    EditText event_et_66;
    @BindView(R.id.event_et_67)
    EditText event_et_67;
    @BindView(R.id.event_et_68)
    EditText event_et_68;
    @BindView(R.id.event_et_69)
    EditText event_et_69;


    @BindView(R.id.event_tv_time_62)
    TextView event_tv_time_62;
    @BindView(R.id.event_tv_time_63)
    TextView event_tv_time_63;
    @BindView(R.id.event_tv_time_64)
    TextView event_tv_time_64;
    @BindView(R.id.event_tv_time_65)
    TextView event_tv_time_65;
    @BindView(R.id.event_tv_time_66)
    TextView event_tv_time_66;
    @BindView(R.id.event_tv_time_67)
    TextView event_tv_time_67;
    @BindView(R.id.event_tv_time_68)
    TextView event_tv_time_68;
    @BindView(R.id.event_tv_time_69)
    TextView event_tv_time_69;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangguan_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_62.setText(mLsListsDao.load((long) 62).strMLs);
        event_et_63.setText(mLsListsDao.load((long) 63).strMLs);
        event_et_64.setText(mLsListsDao.load((long) 64).strMLs);
        event_et_65.setText(mLsListsDao.load((long) 65).strMLs);
        event_et_66.setText(mLsListsDao.load((long) 66).strMLs);
        event_et_67.setText(mLsListsDao.load((long) 67).strMLs);
        event_et_68.setText(mLsListsDao.load((long) 68).strMLs);
        event_et_69.setText(mLsListsDao.load((long) 69).strMLs);

        event_tv_time_62.setText(mLsListsDao.load((long) 62).time);
        event_tv_time_63.setText(mLsListsDao.load((long) 63).time);
        event_tv_time_64.setText(mLsListsDao.load((long) 64).time);
        event_tv_time_65.setText(mLsListsDao.load((long) 65).time);
        event_tv_time_66.setText(mLsListsDao.load((long) 66).time);
        event_tv_time_67.setText(mLsListsDao.load((long) 67).time);
        event_tv_time_68.setText(mLsListsDao.load((long) 68).time);
        event_tv_time_69.setText(mLsListsDao.load((long) 69).time);

    }

    @OnClick(R.id.btn_event_wgkzq_ok)
    public void btn_event_wgkzq_ok() {
        mLsListsDao.update(new MLsLists((long) 62, "黑板灯开", event_et_62.getText().toString(), event_tv_time_62.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 63, "黑板灯关", event_et_63.getText().toString(), event_tv_time_63.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 64, "教室灯开", event_et_64.getText().toString(), event_tv_time_64.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 65, "教室灯关", event_et_65.getText().toString(), event_tv_time_65.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 66, "场景开", event_et_66.getText().toString(), event_tv_time_66.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 67, "场景关", event_et_67.getText().toString(), event_tv_time_67.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 68, "插座开(全开)", event_et_68.getText().toString(), event_tv_time_68.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 69, "插座关(全关)", event_et_69.getText().toString(), event_tv_time_69.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.wgkzq_event_btn_back)
    public void wgkzq_event_btn_back() {
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