package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.LampAdapter;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.utils.AddLampDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LampActivity extends BaseActivity implements AddLampDialog.DialogCallBack, LampAdapter.CallBack {

    @BindView(R.id.lamp_recyclerView)
    RecyclerView lamp_recyclerView;

    private AddLampDialog addLampDialog;
    private LampDao lampDao;
    private LampAdapter lampAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        ButterKnife.bind(this);

        lampDao = MyApplication.getDaoSession().getLampDao();
        initRcyclerView();
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lamp_recyclerView.setLayoutManager(manager);
        lampAdapter = new LampAdapter(this, lampDao.loadAll(), this);
        lamp_recyclerView.setAdapter(lampAdapter);
    }


    @Override
    public void onClickItemFix(Lamp item) {
        if (addLampDialog == null) {
            addLampDialog = new AddLampDialog(this, item, this);
        }

        if (addLampDialog != null) {
            addLampDialog.show();
            addLampDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onClickItemDelect(Lamp item) {
        lampDao.delete(item);
        lampAdapter.setData(lampDao.loadAll());
    }

    @OnClick(R.id.add_lamp)
    public void add_lamp() {

        if (addLampDialog == null) {
            addLampDialog = new AddLampDialog(this, null, this);
        }

        if (addLampDialog != null) {
            addLampDialog.show();
            addLampDialog.setCanceledOnTouchOutside(false);
        }

    }

    @Override
    public void addLampOk(boolean isOk) {
        if (isOk) {
            lampAdapter.setData(lampDao.loadAll());
        }
        if (addLampDialog != null) {
            addLampDialog.dismiss();
            addLampDialog = null;
        }
    }

    @OnClick(R.id.lamp_btn_back)
    public void lamp_btn_back() {
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
