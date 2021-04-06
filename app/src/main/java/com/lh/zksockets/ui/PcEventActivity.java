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

public class PcEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.pc_event_recyclerView)
    RecyclerView pc_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> pcEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();


        pc_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pcEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%大屏%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, pcEventdatas, this);
        pc_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========pcEventdatas===========" + pcEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        pcEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(pcEventdatas);
    }

    @OnClick(R.id.btn_event_pc_ok)
    public void btn_event_pc_ok() {
        ELog.i("===========pcEventdatas===========" + pcEventdatas.toString());
        for (int i = 0; i < pcEventdatas.size(); i++) {
            mLsListsDao.update(pcEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.pc_event_btn_back)
    public void pc_event_btn_back() {
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