package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.KongTiaoDataDao;
import com.lh.zksockets.data.model.KongTiaoData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComputerActivity extends BaseActivity {


    @BindView(R.id.et_leng_wen)
    EditText et_leng_wen;
    @BindView(R.id.et_leng_time_start)
    EditText et_leng_time_start;
    @BindView(R.id.et_leng_time_end)
    EditText et_leng_time_end;
    @BindView(R.id.et_leng_ml)
    EditText et_leng_ml;
    @BindView(R.id.et_re_wen)
    EditText et_re_wen;
    @BindView(R.id.et_re_time_start)
    EditText et_re_time_start;
    @BindView(R.id.et_re_time_end)
    EditText et_re_time_end;
    @BindView(R.id.et_re_ml)
    EditText et_re_ml;

    @BindView(R.id.rbtn_ktset_ok)
    RadioButton rbtn_ktset_ok;
    @BindView(R.id.rbtn_ktset_no)
    RadioButton rbtn_ktset_no;

    private KongTiaoDataDao kongTiaoDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);

        kongTiaoDataDao = MyApplication.getDaoSession().getKongTiaoDataDao();

        if (kongTiaoDataDao.loadAll().size() == 0) {
            kongTiaoDataDao.insert(new KongTiaoData("", "", "", "", "", "", "", "1", 0));
        }

        initView();

    }

    private void initView() {
        et_leng_wen.setText(kongTiaoDataDao.loadAll().get(0).wenstr_leng);
        et_leng_time_start.setText(kongTiaoDataDao.loadAll().get(0).leng_timeStart);
        et_leng_time_end.setText(kongTiaoDataDao.loadAll().get(0).leng_timeEnd);
        et_leng_ml.setText(kongTiaoDataDao.loadAll().get(0).leng_ml);

        et_re_wen.setText(kongTiaoDataDao.loadAll().get(0).wenstr_re);
        et_re_time_start.setText(kongTiaoDataDao.loadAll().get(0).re_timeStart);
        et_re_time_end.setText(kongTiaoDataDao.loadAll().get(0).re_timeEnd);
        et_re_ml.setText(kongTiaoDataDao.loadAll().get(0).re_ml);

        if (kongTiaoDataDao.loadAll().get(0).kt_status == 1) {
            rbtn_ktset_ok.setChecked(true);
        } else {
            rbtn_ktset_no.setChecked(true);
        }

    }

    @OnClick(R.id.btn_ktset_ok)
    public void btn_ktset_ok() {
        kongTiaoDataDao.deleteAll();
        kongTiaoDataDao.insert(new KongTiaoData(et_re_wen.getText().toString().trim(), et_re_time_start.getText().toString().trim(),
                et_re_time_end.getText().toString().trim(), et_re_ml.getText().toString().trim(), et_leng_wen.getText().toString().trim(),
                et_leng_time_start.getText().toString().trim(), et_leng_time_end.getText().toString().trim(), et_leng_ml.getText().toString().trim(), 0));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
//        TimerUtils.setWenshiduTimer();
    }

    @OnClick(R.id.ktset_btn_back)
    public void ktset_btn_back() {
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
