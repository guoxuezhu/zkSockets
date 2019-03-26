package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.model.JDQstatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnvironmentalActivity extends BaseActivity {

    @BindView(R.id.jdq_1_gl)
    Switch jdq_1_gl;
    @BindView(R.id.jdq_2_gl)
    Switch jdq_2_gl;
    @BindView(R.id.jdq_3_gl)
    Switch jdq_3_gl;
    @BindView(R.id.jdq_4_gl)
    Switch jdq_4_gl;
    @BindView(R.id.jdq_5_gl)
    Switch jdq_5_gl;
    @BindView(R.id.jdq_6_gl)
    Switch jdq_6_gl;
    private JDQstatusDao jdqStatusDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        ButterKnife.bind(this);

        jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            jdqStatusDao.insert(new JDQstatus((long) 1, 0, 0, 0, 0, 0, 0, 0, 0));
            jdqStatusDao.insert(new JDQstatus((long) 2, 0, 0, 0, 0, 0, 0, 0, 0));
        }

        if (jdqStatusDao.loadAll().get(0).jdq1 == 1) {
            jdq_1_gl.setChecked(true);
        } else {
            jdq_1_gl.setChecked(false);
        }

        if (jdqStatusDao.loadAll().get(0).jdq2 == 1) {
            jdq_2_gl.setChecked(true);
        } else {
            jdq_2_gl.setChecked(false);
        }

        if (jdqStatusDao.loadAll().get(0).jdq3 == 1) {
            jdq_3_gl.setChecked(true);
        } else {
            jdq_3_gl.setChecked(false);
        }

        if (jdqStatusDao.loadAll().get(0).jdq4 == 1) {
            jdq_4_gl.setChecked(true);
        } else {
            jdq_4_gl.setChecked(false);
        }

        if (jdqStatusDao.loadAll().get(0).jdq5 == 1) {
            jdq_5_gl.setChecked(true);
        } else {
            jdq_5_gl.setChecked(false);
        }

        if (jdqStatusDao.loadAll().get(0).jdq6 == 1) {
            jdq_6_gl.setChecked(true);
        } else {
            jdq_6_gl.setChecked(false);
        }

    }


    @OnClick(R.id.btn_dangerIoPort_ok)
    public void btn_dangerIoPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;
        int io5;
        int io6;

        if (jdq_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (jdq_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (jdq_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (jdq_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        if (jdq_5_gl.isChecked()) {
            io5 = 1;
        } else {
            io5 = 0;
        }

        if (jdq_6_gl.isChecked()) {
            io6 = 1;
        } else {
            io6 = 0;
        }

        jdqStatusDao.update(new JDQstatus((long) 1, io1, io2, io3, io4, io5, io6, 0, 0));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.environmental_btn_back)
    public void environmental_btn_back() {
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
