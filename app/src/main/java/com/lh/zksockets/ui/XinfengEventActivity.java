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

public class XinfengEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.xinfeng_event_recyclerView)
    RecyclerView xinfeng_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> xinfEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinfeng_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        xinfeng_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        xinfEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%新风%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, xinfEventdatas, this);
        xinfeng_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========xinfEventdatas===========" + xinfEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        xinfEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(xinfEventdatas);
    }

    @OnClick(R.id.btn_event_xinfeng_ok)
    public void btn_event_xinfeng_ok() {
        ELog.i("===========xinfEventdatas===========" + xinfEventdatas.toString());
        for (int i = 0; i < xinfEventdatas.size(); i++) {
            mLsListsDao.update(xinfEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.xinfeng_event_btn_back)
    public void xinfeng_event_btn_back() {
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