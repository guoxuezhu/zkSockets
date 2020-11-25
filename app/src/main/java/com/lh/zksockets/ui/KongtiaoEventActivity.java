package com.lh.zksockets.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    @BindView(R.id.event_et_41)
    EditText event_et_41;
    @BindView(R.id.event_et_42)
    EditText event_et_42;
    @BindView(R.id.event_et_43)
    EditText event_et_43;
    @BindView(R.id.event_et_44)
    EditText event_et_44;
    @BindView(R.id.event_et_48)
    EditText event_et_48;
    @BindView(R.id.event_et_49)
    EditText event_et_49;

    @BindView(R.id.event_tv_time_39)
    TextView event_tv_time_39;
    @BindView(R.id.event_tv_time_40)
    TextView event_tv_time_40;
    @BindView(R.id.event_tv_time_41)
    TextView event_tv_time_41;
    @BindView(R.id.event_tv_time_42)
    TextView event_tv_time_42;
    @BindView(R.id.event_tv_time_43)
    TextView event_tv_time_43;
    @BindView(R.id.event_tv_time_44)
    TextView event_tv_time_44;
    @BindView(R.id.event_tv_time_48)
    TextView event_tv_time_48;
    @BindView(R.id.event_tv_time_49)
    TextView event_tv_time_49;

    private MLsListsDao mLsListsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kongtiao_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        event_et_39.setText(mLsListsDao.load((long) 39).strMLs);
        event_et_40.setText(mLsListsDao.load((long) 40).strMLs);
        event_et_41.setText(mLsListsDao.load((long) 41).strMLs);
        event_et_42.setText(mLsListsDao.load((long) 42).strMLs);
        event_et_43.setText(mLsListsDao.load((long) 43).strMLs);
        event_et_44.setText(mLsListsDao.load((long) 44).strMLs);
        event_et_48.setText(mLsListsDao.load((long) 48).strMLs);
        event_et_49.setText(mLsListsDao.load((long) 49).strMLs);

        event_tv_time_39.setText(mLsListsDao.load((long) 39).time);
        event_tv_time_40.setText(mLsListsDao.load((long) 40).time);
        event_tv_time_41.setText(mLsListsDao.load((long) 41).time);
        event_tv_time_42.setText(mLsListsDao.load((long) 42).time);
        event_tv_time_43.setText(mLsListsDao.load((long) 43).time);
        event_tv_time_44.setText(mLsListsDao.load((long) 44).time);
        event_tv_time_48.setText(mLsListsDao.load((long) 48).time);
        event_tv_time_49.setText(mLsListsDao.load((long) 49).time);
    }



    @OnClick(R.id.btn_event_kt_ok)
    public void btn_event_kt_ok() {
        mLsListsDao.update(new MLsLists((long) 39, "空调-开", event_et_39.getText().toString(), event_tv_time_39.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 40, "空调-关", event_et_40.getText().toString(), event_tv_time_40.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 41, "空调-自动", event_et_41.getText().toString(), event_tv_time_41.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 42, "空调-制冷", event_et_42.getText().toString(), event_tv_time_42.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 43, "空调-制热", event_et_43.getText().toString(), event_tv_time_43.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 44, "空调-风速", event_et_44.getText().toString(), event_tv_time_44.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 48, "空调-模式", event_et_48.getText().toString(), event_tv_time_48.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 49, "空调-摆风", event_et_49.getText().toString(), event_tv_time_49.getText().toString()));

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