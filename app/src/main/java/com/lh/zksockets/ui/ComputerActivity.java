package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectChazuoAdapter;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.ComputerDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.Computer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComputerActivity extends BaseActivity {

    @BindView(R.id.et_computer_ip)
    EditText et_computer_ip;
    @BindView(R.id.et_computer_port)
    EditText et_computer_port;
    @BindView(R.id.et_computer_name)
    EditText et_computer_name;
    @BindView(R.id.et_password)
    EditText et_password;

    private ComputerDao computerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);



        computerDao = MyApplication.getDaoSession().getComputerDao();

        initView();

    }

    private void initView() {
        if (computerDao.loadAll().size() != 0) {
            et_computer_ip.setText(computerDao.loadAll().get(0).IP);
            et_computer_port.setText(computerDao.loadAll().get(0).PORT);
            et_computer_name.setText(computerDao.loadAll().get(0).userName);
            et_password.setText(computerDao.loadAll().get(0).Password);
        } else {
            et_computer_ip.setText("");
            et_computer_port.setText("");
            et_computer_name.setText("");
            et_password.setText("");
        }

    }

    @OnClick(R.id.btn_computer_ok)
    public void btn_computer_ok() {
        computerDao.insert(new Computer(et_computer_ip.getText().toString(), et_computer_port.getText().toString(),
                et_computer_name.getText().toString(), et_password.getText().toString()));

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
