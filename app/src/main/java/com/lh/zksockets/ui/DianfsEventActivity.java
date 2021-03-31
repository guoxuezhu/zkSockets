package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventAdapter;
import com.lh.zksockets.adapter.IcCardAdapter;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.utils.AddCardDialog;
import com.lh.zksockets.utils.DeleteDialog;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DianfsEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.event_recyclerView_1)
    RecyclerView event_recyclerView_1;

    private MLsListsDao mLsListsDao;
    private EventAdapter mEventAdapter;
    private List<MLsLists> dfsEventdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianfs_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        initEventRcyclerView();

    }

    private void initEventRcyclerView() {
        event_recyclerView_1.setLayoutManager(new LinearLayoutManager(this));
        dfsEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%电风扇%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, dfsEventdatas, this);
        event_recyclerView_1.setAdapter(mEventAdapter);
        ELog.i("===========dfsEventdatas===========" + dfsEventdatas.toString());
    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        ELog.i("=========onSetingMl===11=====" + mPosition);
        ELog.i("=========onSetingMl====22====" + etml);
        dfsEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(dfsEventdatas);
    }

    @OnClick(R.id.btn_event_dianfs_ok)
    public void btn_event_dianfs_ok() {
//        mLsListsDao.update(new MLsLists((long) 8001, "电风扇全关", event_et_8001.getText().toString(), event_tv_time_8001.getText().toString()));
//        mLsListsDao.update(new MLsLists((long) 8002, "电风扇全1级", event_et_8002.getText().toString(), event_tv_time_8002.getText().toString()));
//        mLsListsDao.update(new MLsLists((long) 8003, "电风扇全2级", event_et_8003.getText().toString(), event_tv_time_8003.getText().toString()));
//        mLsListsDao.update(new MLsLists((long) 8004, "电风扇全3级", event_et_8004.getText().toString(), event_tv_time_8004.getText().toString()));
        ELog.i("===========dfsEventdatas===========" + dfsEventdatas.toString());
        for (int i = 0; i < dfsEventdatas.size(); i++) {
            mLsListsDao.update(dfsEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.dianfs_event_btn_back)
    public void dianfs_event_btn_back() {
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