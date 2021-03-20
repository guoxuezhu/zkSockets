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

public class KongtiaoEventActivity extends BaseActivity {

    @BindView(R.id.event_et_39)
    EditText event_et_39;
    @BindView(R.id.event_et_40)
    EditText event_et_40;
    @BindView(R.id.event_et_2001)
    EditText event_et_2001;
    @BindView(R.id.event_et_2002)
    EditText event_et_2002;
    @BindView(R.id.event_et_2003)
    EditText event_et_2003;
    @BindView(R.id.event_et_2004)
    EditText event_et_2004;
    @BindView(R.id.event_et_2005)
    EditText event_et_2005;
    @BindView(R.id.event_et_2006)
    EditText event_et_2006;
    @BindView(R.id.event_et_2007)
    EditText event_et_2007;


    @BindView(R.id.event_tv_time_39)
    TextView event_tv_time_39;
    @BindView(R.id.event_tv_time_40)
    TextView event_tv_time_40;
    @BindView(R.id.event_tv_time_2001)
    TextView event_tv_time_2001;
    @BindView(R.id.event_tv_time_2002)
    TextView event_tv_time_2002;
    @BindView(R.id.event_tv_time_2003)
    TextView event_tv_time_2003;
    @BindView(R.id.event_tv_time_2004)
    TextView event_tv_time_2004;
    @BindView(R.id.event_tv_time_2005)
    TextView event_tv_time_2005;
    @BindView(R.id.event_tv_time_2006)
    TextView event_tv_time_2006;
    @BindView(R.id.event_tv_time_2007)
    TextView event_tv_time_2007;


    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kongtiao_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_39.setText(mLsListsDao.load((long) 39).strMLs);
        event_et_40.setText(mLsListsDao.load((long) 40).strMLs);
        event_et_2001.setText(mLsListsDao.load((long) 2001).strMLs);
        event_et_2002.setText(mLsListsDao.load((long) 2002).strMLs);
        event_et_2003.setText(mLsListsDao.load((long) 2003).strMLs);
        event_et_2004.setText(mLsListsDao.load((long) 2004).strMLs);
        event_et_2005.setText(mLsListsDao.load((long) 2005).strMLs);
        event_et_2006.setText(mLsListsDao.load((long) 2006).strMLs);
        event_et_2007.setText(mLsListsDao.load((long) 2007).strMLs);

        event_tv_time_39.setText(mLsListsDao.load((long) 39).time);
        event_tv_time_40.setText(mLsListsDao.load((long) 40).time);
        event_tv_time_2001.setText(mLsListsDao.load((long) 2001).time);
        event_tv_time_2002.setText(mLsListsDao.load((long) 2002).time);
        event_tv_time_2003.setText(mLsListsDao.load((long) 2003).time);
        event_tv_time_2004.setText(mLsListsDao.load((long) 2004).time);
        event_tv_time_2005.setText(mLsListsDao.load((long) 2005).time);
        event_tv_time_2006.setText(mLsListsDao.load((long) 2006).time);
        event_tv_time_2007.setText(mLsListsDao.load((long) 2007).time);
    }


    @OnClick(R.id.btn_event_kt_ok)
    public void btn_event_kt_ok() {
        mLsListsDao.update(new MLsLists((long) 39, "空调-开", event_et_39.getText().toString(), event_tv_time_39.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 40, "空调-关", event_et_40.getText().toString(), event_tv_time_40.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2001, "空调-自动", event_et_2001.getText().toString(), event_tv_time_2001.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2002, "空调-制冷", event_et_2002.getText().toString(), event_tv_time_2002.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2003, "空调-制热", event_et_2003.getText().toString(), event_tv_time_2003.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2004, "空调-风速低", event_et_2004.getText().toString(), event_tv_time_2004.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2005, "空调-风速中", event_et_2005.getText().toString(), event_tv_time_2005.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2006, "空调-风速高", event_et_2006.getText().toString(), event_tv_time_2006.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2007, "空调-摆风", event_et_2007.getText().toString(), event_tv_time_2007.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.kt_event_btn_back)
    public void kt_event_btn_back() {
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