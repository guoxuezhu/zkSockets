package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.service.MyMqttService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongKongActivity extends BaseActivity {

    @BindView(R.id.et_yc_weizhi)
    EditText et_yc_weizhi;
    @BindView(R.id.et_yc_deviceId)
    TextView et_yc_deviceId;

    private BaseInfoDao baseInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_kong);
        ButterKnife.bind(this);

        baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        if (baseInfoDao.loadAll().size() == 0) {
            baseInfoDao.insert(new BaseInfo("", java.util.UUID.randomUUID().toString()));
        }
        et_yc_weizhi.setText(baseInfoDao.loadAll().get(0).classRoom);
        et_yc_deviceId.setText(baseInfoDao.loadAll().get(0).uuid);
    }


    @OnClick(R.id.btn_zkyc_ok)
    public void btn_zkyc_ok() {
        if (et_yc_weizhi.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "请输入中控位置", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseInfo baseInfo = new BaseInfo(et_yc_weizhi.getText().toString().trim(), baseInfoDao.loadAll().get(0).uuid);
        baseInfoDao.deleteAll();
        baseInfoDao.insert(baseInfo);
        MyMqttService.startService(this); //开启服务
        Toast.makeText(this, "保存成功，正在启动", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.zk_back_btn)
    public void zk_back_btn() {
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
