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

public class WangguanEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.wangguan_event_recyclerView)
    RecyclerView wangguan_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> wangguanEventdatas;
    private EventAdapter mEventAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangguan_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        wangguan_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wangguanEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%网关%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, wangguanEventdatas, this);
        wangguan_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========wangguanEventdatas===========" + wangguanEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        wangguanEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(wangguanEventdatas);
    }

    @OnClick(R.id.btn_event_wgkzq_ok)
    public void btn_event_wgkzq_ok() {
        ELog.i("===========wangguanEventdatas===========" + wangguanEventdatas.toString());
        for (int i = 0; i < wangguanEventdatas.size(); i++) {
            mLsListsDao.update(wangguanEventdatas.get(i));
        }
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