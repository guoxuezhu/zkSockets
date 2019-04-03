package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.utils.ELog;

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


    @BindView(R.id.danger_et_time_1)
    EditText danger_et_time_1;
    @BindView(R.id.danger_et_time_2)
    EditText danger_et_time_2;
    @BindView(R.id.danger_et_time_3)
    EditText danger_et_time_3;
    @BindView(R.id.danger_et_time_4)
    EditText danger_et_time_4;


    private DangerOutDao dangerOutDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_set);
        ButterKnife.bind(this);


        dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                dangerOutDao.insert(new DangerOut((long) i, "报警输出" + i, 1, 10));
            }
        }

        if (dangerOutDao.load((long) 1).dangerOutStatus == 0) {
            bjout_1_gl.setChecked(true);
        } else {
            bjout_1_gl.setChecked(false);
        }

        if (dangerOutDao.load((long) 2).dangerOutStatus == 0) {
            bjout_2_gl.setChecked(true);
        } else {
            bjout_2_gl.setChecked(false);
        }

        if (dangerOutDao.load((long) 3).dangerOutStatus == 0) {
            bjout_3_gl.setChecked(true);
        } else {
            bjout_3_gl.setChecked(false);
        }

        if (dangerOutDao.load((long) 4).dangerOutStatus == 0) {
            bjout_4_gl.setChecked(true);
        } else {
            bjout_4_gl.setChecked(false);
        }
        ELog.i("========dangerOutDao========" + dangerOutDao.loadAll().toString());

    }


    @OnClick(R.id.btn_bjout_ok)
    public void btn_bjout_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (bjout_1_gl.isChecked()) {
            io1 = 0;
        } else {
            io1 = 1;
        }

        if (bjout_2_gl.isChecked()) {
            io2 = 0;
        } else {
            io2 = 1;
        }

        if (bjout_3_gl.isChecked()) {
            io3 = 0;
        } else {
            io3 = 1;
        }

        if (bjout_4_gl.isChecked()) {
            io4 = 0;
        } else {
            io4 = 1;
        }

        dangerOutDao.update(new DangerOut((long) 1, "报警输出" + 1, io1, Integer.valueOf(danger_et_time_1.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 2, "报警输出" + 2, io2, Integer.valueOf(danger_et_time_2.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 3, "报警输出" + 3, io3, Integer.valueOf(danger_et_time_3.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 4, "报警输出" + 4, io4, Integer.valueOf(danger_et_time_4.getText().toString())));

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
