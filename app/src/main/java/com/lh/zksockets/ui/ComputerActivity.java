package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.model.Computer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComputerActivity extends Activity {

    @BindView(R.id.et_computer_ip)
    EditText et_computer_ip;
    @BindView(R.id.et_computer_port)
    EditText et_computer_port;
    @BindView(R.id.et_computer_name)
    EditText et_computer_name;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_open_ml)
    EditText et_open_ml;
    @BindView(R.id.et_closed_ml)
    EditText et_closed_ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_computer_ok)
    public void btn_computer_ok() {
        ComputerDao computerDao = MyApplication.getDaoSession().getComputerDao();
        if(computerDao.loadAll().size()!=0){
            computerDao.deleteAll();
        }
        computerDao.insert(new Computer(et_computer_ip.getText().toString(), et_computer_port.getText().toString(),
                et_computer_name.getText().toString(), et_password.getText().toString(),
                et_open_ml.getText().toString(), et_closed_ml.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.computer_btn_back)
    public void computer_btn_back() {
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
