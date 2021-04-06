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

public class DengguangActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.dg_event_recyclerView)
    RecyclerView dg_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> dgEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengguang);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        dg_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dgEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%灯%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, dgEventdatas, this);
        dg_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========dgEventdatas===========" + dgEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        dgEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(dgEventdatas);
    }

    @OnClick(R.id.btn_event_dg_ok)
    public void btn_event_dg_ok() {
        ELog.i("===========dgEventdatas===========" + dgEventdatas.toString());
        for (int i = 0; i < dgEventdatas.size(); i++) {
            mLsListsDao.update(dgEventdatas.get(i));
        }
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