package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.model.IOYuan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IOsetActivity extends BaseActivity {

    @BindView(R.id.danger_1_gl)
    Switch danger_1_gl;
    @BindView(R.id.danger_2_gl)
    Switch danger_2_gl;
    @BindView(R.id.danger_3_gl)
    Switch danger_3_gl;
    @BindView(R.id.danger_4_gl)
    Switch danger_4_gl;

    @BindView(R.id.bj_event_ok_1)
    EditText bj_event_ok_1;
    @BindView(R.id.bj_event_ok_2)
    EditText bj_event_ok_2;
    @BindView(R.id.bj_event_ok_3)
    EditText bj_event_ok_3;
    @BindView(R.id.bj_event_ok_4)
    EditText bj_event_ok_4;

    @BindView(R.id.bj_event_no_1)
    EditText bj_event_no_1;
    @BindView(R.id.bj_event_no_2)
    EditText bj_event_no_2;
    @BindView(R.id.bj_event_no_3)
    EditText bj_event_no_3;
    @BindView(R.id.bj_event_no_4)
    EditText bj_event_no_4;

    @BindView(R.id.bj_devicename_1)
    EditText bj_devicename_1;
    @BindView(R.id.bj_devicename_2)
    EditText bj_devicename_2;
    @BindView(R.id.bj_devicename_3)
    EditText bj_devicename_3;
    @BindView(R.id.bj_devicename_4)
    EditText bj_devicename_4;

    private IOYuanDao ioYuanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioset);
        ButterKnife.bind(this);

        ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            ioYuanDao.insert(new IOYuan((long) 1, "报警1", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 2, "报警2", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 3, "报警3", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 4, "报警4", "", 0, "", ""));
        }

        if (ioYuanDao.load((long) 1).dangerIoStatus == 1) {
            danger_1_gl.setChecked(true);
        } else {
            danger_1_gl.setChecked(false);
        }
        bj_event_ok_1.setText(ioYuanDao.load((long) 1).dangerMl);
        bj_event_no_1.setText(ioYuanDao.load((long) 1).noDangerMl);
        bj_devicename_1.setText(ioYuanDao.load((long) 1).deviceName);

        if (ioYuanDao.load((long) 2).dangerIoStatus == 1) {
            danger_2_gl.setChecked(true);
        } else {
            danger_2_gl.setChecked(false);
        }
        bj_event_ok_2.setText(ioYuanDao.load((long) 2).dangerMl);
        bj_event_no_2.setText(ioYuanDao.load((long) 2).noDangerMl);
        bj_devicename_2.setText(ioYuanDao.load((long) 2).deviceName);

        if (ioYuanDao.load((long) 3).dangerIoStatus == 1) {
            danger_3_gl.setChecked(true);
        } else {
            danger_3_gl.setChecked(false);
        }
        bj_event_ok_3.setText(ioYuanDao.load((long) 3).dangerMl);
        bj_event_no_3.setText(ioYuanDao.load((long) 3).noDangerMl);
        bj_devicename_3.setText(ioYuanDao.load((long) 3).deviceName);

        if (ioYuanDao.load((long) 4).dangerIoStatus == 1) {
            danger_4_gl.setChecked(true);
        } else {
            danger_4_gl.setChecked(false);
        }
        bj_event_ok_4.setText(ioYuanDao.load((long) 4).dangerMl);
        bj_event_no_4.setText(ioYuanDao.load((long) 4).noDangerMl);
        bj_devicename_4.setText(ioYuanDao.load((long) 4).deviceName);

    }


    @OnClick(R.id.btn_dangerIoPort_ok)
    public void btn_dangerIoPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (danger_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (danger_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (danger_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (danger_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        ioYuanDao.update(new IOYuan((long) 1, "报警1", bj_devicename_1.getText().toString(), io1, bj_event_ok_1.getText().toString(), bj_event_no_1.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 2, "报警2", bj_devicename_2.getText().toString(), io2, bj_event_ok_2.getText().toString(), bj_event_no_2.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 3, "报警3", bj_devicename_3.getText().toString(), io3, bj_event_ok_3.getText().toString(), bj_event_no_3.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 4, "报警4", bj_devicename_4.getText().toString(), io4, bj_event_ok_4.getText().toString(), bj_event_no_4.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
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
