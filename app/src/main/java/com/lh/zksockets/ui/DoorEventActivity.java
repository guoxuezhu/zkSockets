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

public class DoorEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.door_event_recyclerView)
    RecyclerView door_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> doorEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        door_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doorEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%门禁%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, doorEventdatas, this);
        door_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========doorEventdatas===========" + doorEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        doorEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(doorEventdatas);
    }

    @OnClick(R.id.btn_event_door_ok)
    public void btn_event_door_ok() {
        ELog.i("===========doorEventdatas===========" + doorEventdatas.toString());
        for (int i = 0; i < doorEventdatas.size(); i++) {
            mLsListsDao.update(doorEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.door_event_btn_back)
    public void door_event_btn_back() {
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