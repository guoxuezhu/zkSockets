package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.utils.ELog;

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

    @BindView(R.id.jdq_et_time_1)
    EditText jdq_et_time_1;
    @BindView(R.id.jdq_et_time_2)
    EditText jdq_et_time_2;
    @BindView(R.id.jdq_et_time_3)
    EditText jdq_et_time_3;
    @BindView(R.id.jdq_et_time_4)
    EditText jdq_et_time_4;
    @BindView(R.id.jdq_et_time_5)
    EditText jdq_et_time_5;
    @BindView(R.id.jdq_et_time_6)
    EditText jdq_et_time_6;


    private JDQstatusDao jdqStatusDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        ButterKnife.bind(this);

        jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            for (int i = 1; i < 7; i++) {
                jdqStatusDao.insert(new JDQstatus((long) i, "继电器" + i, 0, 10));
            }
            jdqStatusDao.insert(new JDQstatus((long) 7, "继电器" + 7, 0, 180));
            jdqStatusDao.insert(new JDQstatus((long) 8, "继电器" + 8, 0, 180));
        }

        if (jdqStatusDao.load((long) 1).jdqStatus == 1) {
            jdq_1_gl.setChecked(true);
        } else {
            jdq_1_gl.setChecked(false);
        }

        if (jdqStatusDao.load((long) 2).jdqStatus == 1) {
            jdq_2_gl.setChecked(true);
        } else {
            jdq_2_gl.setChecked(false);
        }

        if (jdqStatusDao.load((long) 3).jdqStatus == 1) {
            jdq_3_gl.setChecked(true);
        } else {
            jdq_3_gl.setChecked(false);
        }

        if (jdqStatusDao.load((long) 4).jdqStatus == 1) {
            jdq_4_gl.setChecked(true);
        } else {
            jdq_4_gl.setChecked(false);
        }

        if (jdqStatusDao.load((long) 5).jdqStatus == 1) {
            jdq_5_gl.setChecked(true);
        } else {
            jdq_5_gl.setChecked(false);
        }

        if (jdqStatusDao.load((long) 6).jdqStatus == 1) {
            jdq_6_gl.setChecked(true);
        } else {
            jdq_6_gl.setChecked(false);
        }
        ELog.d("=========jdqStatusDao==========" + jdqStatusDao.loadAll().toString());
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

        jdqStatusDao.update(new JDQstatus((long) 1, "继电器1", io1, Integer.valueOf(jdq_et_time_1.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 2, "继电器2", io2, Integer.valueOf(jdq_et_time_2.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 3, "继电器3", io3, Integer.valueOf(jdq_et_time_3.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 4, "继电器4", io4, Integer.valueOf(jdq_et_time_4.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 5, "继电器5", io5, Integer.valueOf(jdq_et_time_5.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 6, "继电器6", io6, Integer.valueOf(jdq_et_time_6.getText().toString())));


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
