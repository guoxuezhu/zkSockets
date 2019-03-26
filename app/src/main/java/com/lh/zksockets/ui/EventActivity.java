package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.utils.ELog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends BaseActivity {

    @BindView(R.id.event_et_1)
    EditText event_et_1;
    @BindView(R.id.event_et_2)
    EditText event_et_2;
    @BindView(R.id.event_et_3)
    EditText event_et_3;
    @BindView(R.id.event_et_4)
    EditText event_et_4;


    private MLsListsDao mLsListsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", ""));
            mLsListsDao.insert(new MLsLists((long) 3, "自习", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "休息", ""));
        }


        DataInit();

    }

    private void DataInit() {
        event_et_1.setText(mLsListsDao.load((long) 1).strMLs);
        event_et_2.setText(mLsListsDao.load((long) 2).strMLs);
        event_et_3.setText(mLsListsDao.load((long) 3).strMLs);
        event_et_4.setText(mLsListsDao.load((long) 4).strMLs);
        ELog.i("=======mLsListsDao=======" + mLsListsDao.loadAll().toString());

    }


    @OnClick(R.id.evbtn_ok)
    public void evbtn_ok() {
        mLsListsDao.update(new MLsLists((long) 1, "上课", event_et_1.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2, "下课", event_et_2.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3, "自习", event_et_3.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4, "休息", event_et_4.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.event_btn_back)
    public void event_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
