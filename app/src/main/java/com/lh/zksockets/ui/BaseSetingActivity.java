package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.ZkInfo;
import com.lh.zksockets.utils.DisplayTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseSetingActivity extends BaseActivity {

    @BindView(R.id.et_classRoom)
    EditText et_classRoom;
    @BindView(R.id.tv_IP)
    TextView tv_IP;
    @BindView(R.id.tv_zkVersionName)
    TextView tv_zkVersionName;
//    @BindView(R.id.tv_zkDeviceName)
//    TextView tv_zkDeviceName;

    private ZkInfoDao zkInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_seting);
        ButterKnife.bind(this);

        tv_IP.setText(DisplayTools.getIPAddress(this));
        tv_zkVersionName.setText(DisplayTools.getVersionName(this));
//        tv_zkDeviceName.setText("orangepi android_" + Build.VERSION.RELEASE);

        zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            et_classRoom.setText("");
        } else {
            et_classRoom.setText(zkInfoDao.loadAll().get(0).zkname);
        }
    }

    @OnClick(R.id.btn_baseset_ok)
    public void btn_baseset_ok() {
        zkInfoDao.deleteAll();
        zkInfoDao.insert(new ZkInfo(et_classRoom.getText().toString(), tv_IP.getText().toString(),
                tv_zkVersionName.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.baseSet_btn_back)
    public void baseSet_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
