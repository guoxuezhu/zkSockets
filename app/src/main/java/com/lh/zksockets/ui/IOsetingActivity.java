package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.utils.ELog;

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


    @BindView(R.id.io_et_time_1)
    EditText io_et_time_1;
    @BindView(R.id.io_et_time_2)
    EditText io_et_time_2;
    @BindView(R.id.io_et_time_3)
    EditText io_et_time_3;
    @BindView(R.id.io_et_time_4)
    EditText io_et_time_4;


    private IoPortDataDao ioPortDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioseting);
        ButterKnife.bind(this);


        ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "io输出" + i, 0, 10));
            }
        }

        if (ioPortDataDao.load((long) 1).ioOutStatus == 1) {
            switch_1_gl.setChecked(true);
        } else {
            switch_1_gl.setChecked(false);
        }
        io_et_time_1.setText(ioPortDataDao.load((long) 1).time + "");

        if (ioPortDataDao.load((long) 2).ioOutStatus == 1) {
            switch_2_gl.setChecked(true);
        } else {
            switch_2_gl.setChecked(false);
        }
        io_et_time_2.setText(ioPortDataDao.load((long) 2).time + "");

        if (ioPortDataDao.load((long) 3).ioOutStatus == 1) {
            switch_3_gl.setChecked(true);
        } else {
            switch_3_gl.setChecked(false);
        }
        io_et_time_3.setText(ioPortDataDao.load((long) 3).time + "");

        if (ioPortDataDao.load((long) 4).ioOutStatus == 1) {
            switch_4_gl.setChecked(true);
        } else {
            switch_4_gl.setChecked(false);
        }
        io_et_time_4.setText(ioPortDataDao.load((long) 4).time + "");
        ELog.i("========ioPortDataDao========" + ioPortDataDao.loadAll().toString());
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

        ioPortDataDao.update(new IoPortData((long) 1, "io输出" + 1, io1, Integer.valueOf(io_et_time_1.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 2, "io输出" + 2, io2, Integer.valueOf(io_et_time_2.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 3, "io输出" + 3, io3, Integer.valueOf(io_et_time_3.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 4, "io输出" + 4, io4, Integer.valueOf(io_et_time_4.getText().toString())));

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
