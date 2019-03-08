package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IOportAdapter;
import com.lh.zksockets.adapter.SerialportAdapter;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.model.IoPortData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IOsetingActivity extends BaseActivity implements IOportAdapter.CallBack {


    @BindView(R.id.ioport_recyclerView)
    RecyclerView ioport_recyclerView;
    private IoPortDataDao ioPortDataDao;
    private IOportAdapter serialportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioseting);
        ButterKnife.bind(this);


        ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 9; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "IO口" + i, false, ""));
            }
        }

        initRcyclerView();


    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ioport_recyclerView.setLayoutManager(manager);
        serialportAdapter = new IOportAdapter(this, ioPortDataDao.loadAll(), this);
        ioport_recyclerView.setAdapter(serialportAdapter);
    }


    @OnClick(R.id.ioport_back_btn)
    public void ioport_back_btn() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }


}
