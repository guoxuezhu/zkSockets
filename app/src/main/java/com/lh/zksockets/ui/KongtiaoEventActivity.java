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

public class KongtiaoEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.kt_event_recyclerView)
    RecyclerView kt_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> ktEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kongtiao_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        kt_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ktEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%空调%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, ktEventdatas, this);
        kt_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========ktEventdatas===========" + ktEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        ktEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(ktEventdatas);
    }

    @OnClick(R.id.btn_event_kt_ok)
    public void btn_event_kt_ok() {
        ELog.i("===========ktEventdatas===========" + ktEventdatas.toString());
        for (int i = 0; i < ktEventdatas.size(); i++) {
            mLsListsDao.update(ktEventdatas.get(i));
        }
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