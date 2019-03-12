package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.model.IoPortData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IOsetingActivity extends BaseActivity {


    @BindView(R.id.switch_1_gl)
    Switch switch_1_gl;
    @BindView(R.id.switch_2_gl)
    Switch switch_2_gl;
    @BindView(R.id.switch_3_gl)
    Switch switch_3_gl;
    @BindView(R.id.switch_4_gl)
    Switch switch_4_gl;

    private IoPortDataDao ioPortDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioseting);
        ButterKnife.bind(this);


        ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            ioPortDataDao.insert(new IoPortData((long) 1, 0, 0, 0, 0));
        }

        if (ioPortDataDao.loadAll().get(0).io1 == 1) {
            switch_1_gl.setChecked(true);
        } else {
            switch_1_gl.setChecked(false);
        }

        if (ioPortDataDao.loadAll().get(0).io2 == 1) {
            switch_2_gl.setChecked(true);
        } else {
            switch_2_gl.setChecked(false);
        }

        if (ioPortDataDao.loadAll().get(0).io3 == 1) {
            switch_3_gl.setChecked(true);
        } else {
            switch_3_gl.setChecked(false);
        }

        if (ioPortDataDao.loadAll().get(0).io4 == 1) {
            switch_4_gl.setChecked(true);
        } else {
            switch_4_gl.setChecked(false);
        }
    }


    @OnClick(R.id.btn_ioPort_ok)
    public void btn_ioPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (switch_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (switch_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (switch_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (switch_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        ioPortDataDao.update(new IoPortData((long) 1, io1, io2, io3, io4));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
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
