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

public class ChuanglianActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.cl_event_recyclerView)
    RecyclerView cl_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> clEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuanglian);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        cl_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%窗帘%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, clEventdatas, this);
        cl_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========clEventdatas===========" + clEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        clEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(clEventdatas);
    }

    @OnClick(R.id.btn_event_cl_ok)
    public void btn_event_cl_ok() {
        ELog.i("===========clEventdatas===========" + clEventdatas.toString());
        for (int i = 0; i < clEventdatas.size(); i++) {
            mLsListsDao.update(clEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cl_event_btn_back)
    public void cl_event_btn_back() {
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