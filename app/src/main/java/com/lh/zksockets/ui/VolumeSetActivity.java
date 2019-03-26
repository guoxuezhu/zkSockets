package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.IoPortData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolumeSetActivity extends BaseActivity {

    @BindView(R.id.bjout_1_gl)
    Switch bjout_1_gl;
    @BindView(R.id.bjout_2_gl)
    Switch bjout_2_gl;
    @BindView(R.id.bjout_3_gl)
    Switch bjout_3_gl;
    @BindView(R.id.bjout_4_gl)
    Switch bjout_4_gl;

    private DangerOutDao dangerOutDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_set);
        ButterKnife.bind(this);


        dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            dangerOutDao.insert(new DangerOut((long) 1, 0, 0, 0, 0));
            dangerOutDao.insert(new DangerOut((long) 2, 0, 0, 0, 0));
        }

        if (dangerOutDao.loadAll().get(0).dangerOut1 == 1) {
            bjout_1_gl.setChecked(true);
        } else {
            bjout_1_gl.setChecked(false);
        }

        if (dangerOutDao.loadAll().get(0).dangerOut2 == 1) {
            bjout_2_gl.setChecked(true);
        } else {
            bjout_2_gl.setChecked(false);
        }

        if (dangerOutDao.loadAll().get(0).dangerOut3 == 1) {
            bjout_3_gl.setChecked(true);
        } else {
            bjout_3_gl.setChecked(false);
        }

        if (dangerOutDao.loadAll().get(0).dangerOut4 == 1) {
            bjout_4_gl.setChecked(true);
        } else {
            bjout_4_gl.setChecked(false);
        }


    }


    @OnClick(R.id.btn_bjout_ok)
    public void btn_bjout_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (bjout_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (bjout_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (bjout_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (bjout_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        dangerOutDao.update(new DangerOut((long) 1, io1, io2, io3, io4));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.volume_btn_back)
    public void volume_btn_back() {
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
