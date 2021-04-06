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

public class OnekeyEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.yijian_event_recyclerView)
    RecyclerView yijian_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> yijianEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onekey_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        yijian_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        yijianEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%一键%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, yijianEventdatas, this);
        yijian_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========yijianEventdatas===========" + yijianEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        yijianEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(yijianEventdatas);
    }

    @OnClick(R.id.btn_event_cj_ok)
    public void btn_event_cj_ok() {
        ELog.i("===========yijianEventdatas===========" + yijianEventdatas.toString());
        for (int i = 0; i < yijianEventdatas.size(); i++) {
            mLsListsDao.update(yijianEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.one_event_btn_back)
    public void one_event_btn_back() {
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