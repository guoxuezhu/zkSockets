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

public class TyjEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.tyj_event_recyclerView)
    RecyclerView tyj_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> tyjEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyj_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        tyj_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tyjEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%投影机%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, tyjEventdatas, this);
        tyj_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========tyjEventdatas===========" + tyjEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        tyjEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(tyjEventdatas);
    }

    @OnClick(R.id.btn_event_tyj_ok)
    public void btn_event_tyj_ok() {
        ELog.i("===========tyjEventdatas===========" + tyjEventdatas.toString());
        for (int i = 0; i < tyjEventdatas.size(); i++) {
            mLsListsDao.update(tyjEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tyj_event_btn_back)
    public void tyj_event_btn_back() {
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