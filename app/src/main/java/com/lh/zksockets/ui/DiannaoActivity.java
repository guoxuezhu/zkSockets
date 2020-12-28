package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.model.Computer;
import com.lh.zksockets.utils.TimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiannaoActivity extends BaseActivity {

    @BindView(R.id.et_dn_ip)
    EditText et_dn_ip;
    @BindView(R.id.et_dn_port)
    EditText et_dn_port;
    @BindView(R.id.et_dn_ml)
    EditText et_dn_ml;
    private ComputerDao computerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diannao);
        ButterKnife.bind(this);

        computerDao = MyApplication.getDaoSession().getComputerDao();
        if (computerDao.loadAll().size() == 0) {
            computerDao.insert(new Computer("192.168.1.19", "8080", "FFFF00DD", ""));
        }
        et_dn_ip.setText(computerDao.loadAll().get(0).IP);
        et_dn_port.setText(computerDao.loadAll().get(0).PORT);
        et_dn_ml.setText(computerDao.loadAll().get(0).userName);
    }


    @OnClick(R.id.btn_dn_ok)
    public void btn_dn_ok() {
        computerDao.deleteAll();
        computerDao.insert(new Computer(et_dn_ip.getText().toString(), et_dn_port.getText().toString(), et_dn_ml.getText().toString(), ""));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.diannao_btn_back)
    public void diannao_btn_back() {
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
