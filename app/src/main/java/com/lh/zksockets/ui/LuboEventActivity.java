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

public class LuboEventActivity extends BaseActivity implements EventAdapter.CallBack {

    @BindView(R.id.lubo_event_recyclerView)
    RecyclerView lubo_event_recyclerView;

    private MLsListsDao mLsListsDao;
    private List<MLsLists> luboEventdatas;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lubo_event);
        ButterKnife.bind(this);
        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();

        lubo_event_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        luboEventdatas = mLsListsDao.queryBuilder()
                .where(MLsListsDao.Properties.Name.like("%录播%"))
                .orderAsc(MLsListsDao.Properties.Id)
                .list();
        mEventAdapter = new EventAdapter(this, luboEventdatas, this);
        lubo_event_recyclerView.setAdapter(mEventAdapter);
        ELog.i("===========luboEventdatas===========" + luboEventdatas.toString());

    }

    @Override
    public void onSetingMl(int mPosition, String etml) {
        luboEventdatas.get(mPosition).setStrMLs(etml);
        mEventAdapter.setDatas(luboEventdatas);
    }

    @OnClick(R.id.btn_event_lubo_ok)
    public void btn_event_lubo_ok() {
        ELog.i("===========luboEventdatas===========" + luboEventdatas.toString());
        for (int i = 0; i < luboEventdatas.size(); i++) {
            mLsListsDao.update(luboEventdatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.lubo_event_btn_back)
    public void lubo_event_btn_back() {
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