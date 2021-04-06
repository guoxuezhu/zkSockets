package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventAdapter;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.power_event_recyclerView)
    RecyclerView power_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> powerEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        power_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        powerEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%电源%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, powerEventdatas, this);
        power_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========powerEventdatas===========" + powerEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        powerEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(powerEventdatas);
    }

    @OnClick(R.id.btn_event_power_ok)
    public void btn_event_power_ok() {
        ELog.i("===========powerEventdatas===========" + powerEventdatas.toString());
        for (int i = 0; i < powerEventdatas.size(); i++) {
            mLsListsDao.update(powerEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.power_event_btn_back)
    public void power_event_btn_back() {
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