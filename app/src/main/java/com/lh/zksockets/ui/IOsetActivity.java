package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IoYuanAdapter;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.utils.ELog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IOsetActivity extends BaseActivity implements IoYuanAdapter.CallBack {

    @BindView(R.id.io_recyclerView)
    RecyclerView io_recyclerView;


    private List<IOYuan> ioYuanDatas = new ArrayList<IOYuan>();
    private List<String> ioOutDatas = new ArrayList<String>();
    private IoYuanAdapter ioYuanAdapter;
    private IOYuanDao ioYuanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioset);
        ButterKnife.bind(this);

        ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();

        if (ioYuanDao.loadAll().size() != 0) {
            ioYuanDatas.clear();
            ioYuanDatas = ioYuanDao.loadAll();
        } else {
            for (int i = 1; i < 9; i++) {
                ioYuanDatas.add(new IOYuan((long) i, i + "路输入", false, 0, "", 0));
            }
        }


        ioOutDatas.add("无");
        for (int i = 1; i < 9; i++) {
            ioOutDatas.add(i + "路");
        }

        initRcyclerView();

    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        io_recyclerView.setLayoutManager(manager);
        ioYuanAdapter = new IoYuanAdapter(this, ioYuanDatas, ioOutDatas, this);
        io_recyclerView.setAdapter(ioYuanAdapter);
    }

    @Override
    public void onCheckBoxClickItem(boolean isChecked, int adapterPosition) {
        ioYuanDatas.get(adapterPosition).setIsOK(isChecked);
    }

    @Override
    public void onSpinnerSelected(int adapterPosition, int spinnerPosition) {
        ioYuanDatas.get(adapterPosition).setOutId(spinnerPosition);
        if (spinnerPosition == 0) {
            ioYuanDatas.get(adapterPosition).setOutName("无");
        } else {
            ioYuanDatas.get(adapterPosition).setOutName(spinnerPosition + "路");
        }
    }

    @Override
    public void setIOSendTime(int adapterPosition, String time) {
        if (time.equals("")) {
            ioYuanDatas.get(adapterPosition).setSendTime(0);
        } else {
            ioYuanDatas.get(adapterPosition).setSendTime(Integer.parseInt(time));
        }
    }

    @OnClick(R.id.btn_io_ok)
    public void btn_io_ok() {
        if (ioYuanDao.loadAll().size() != 0) {
            ioYuanDao.deleteAll();
        }
        for (int i = 0; i < ioYuanDatas.size(); i++) {
            ioYuanDao.insert(ioYuanDatas.get(i));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        ELog.i("=========ioYuanDao========" + ioYuanDao.loadAll().toString());
    }

    @OnClick(R.id.io_btn_back)
    public void io_btn_back() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
