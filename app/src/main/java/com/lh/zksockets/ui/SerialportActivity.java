package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SerialportAdapter;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.SerialPortData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerialportActivity extends BaseActivity implements SerialportAdapter.CallBack {


    @BindView(R.id.serialport_recyclerView)
    RecyclerView serialport_recyclerView;
    private SerialPortDataDao serialPortDataDao;
    private SerialportAdapter serialportAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialport);
        ButterKnife.bind(this);

        serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        if (serialPortDataDao.loadAll().size() == 0) {
            for (int i = 2; i < 9; i++) {
                serialPortDataDao.insert(new SerialPortData((long) i, "串口" + i, "9600",
                        "无", "8", "1"));
            }
        }

        initRcyclerView();


    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        serialport_recyclerView.setLayoutManager(manager);
        serialportAdapter = new SerialportAdapter(this, serialPortDataDao.loadAll(), this);
        serialport_recyclerView.setAdapter(serialportAdapter);
    }


    @OnClick(R.id.serialport_back_btn)
    public void serialport_back_btn() {
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
