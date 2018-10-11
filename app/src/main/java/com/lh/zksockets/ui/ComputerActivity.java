package com.lh.zksockets.ui;

import android.app.Activity;
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

public class ComputerActivity extends Activity {

    @BindView(R.id.et_computer_ip)
    EditText et_computer_ip;
    @BindView(R.id.et_computer_port)
    EditText et_computer_port;
    @BindView(R.id.et_computer_name)
    EditText et_computer_name;
    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.chazuo_computer)
    Spinner chazuo_computer;
    private ChazuoDataDao chazuoDataDao;
    private SelectChazuoAdapter chazuoAdapter;
    private String chazuoSelectName;
    private int chazuoSelectId;
    private ComputerDao computerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);

        chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        chazuoAdapter = new SelectChazuoAdapter(this, chazuoDataDao.loadAll());

        chazuo_computer.setAdapter(chazuoAdapter);
        chazuo_computer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chazuoSelectName = chazuoDataDao.loadAll().get(position).name;
                chazuoSelectId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        computerDao = MyApplication.getDaoSession().getComputerDao();

        initView();

    }

    private void initView() {
        if (computerDao.loadAll().size() != 0) {
            et_computer_ip.setText(computerDao.loadAll().get(0).IP);
            et_computer_port.setText(computerDao.loadAll().get(0).PORT);
            et_computer_name.setText(computerDao.loadAll().get(0).userName);
            et_password.setText(computerDao.loadAll().get(0).Password);
            chazuo_computer.setSelection(computerDao.loadAll().get(0).chazuoId);
        } else {
            et_computer_ip.setText("");
            et_computer_port.setText("");
            et_computer_name.setText("");
            et_password.setText("");
            chazuo_computer.setSelection(0);
        }

    }

    @OnClick(R.id.btn_computer_ok)
    public void btn_computer_ok() {
        if (chazuoDataDao.loadAll().get(chazuoSelectId).bindName != null && !chazuoDataDao.loadAll().get(chazuoSelectId).bindName.equals("电脑")) {
            Toast.makeText(this, "电脑选择的插座已经被" + chazuoDataDao.loadAll().get(chazuoSelectId).bindName + "使用", Toast.LENGTH_SHORT).show();
            return;
        }
        if (computerDao.loadAll().size() != 0) {
            int id = computerDao.loadAll().get(0).chazuoId;
            if (id != chazuoSelectId) {
                Toast.makeText(this, "电脑插座线路有变动，需要重新修改电源箱设置", Toast.LENGTH_SHORT).show();
            }
            if (id != 0) {
                chazuoDataDao.update(new ChazuoData((long) id, "插座" + id, null, chazuoDataDao.loadAll().get(id).isOk,
                        chazuoDataDao.loadAll().get(id).openTime, chazuoDataDao.loadAll().get(id).closedTime));
            }
            computerDao.deleteAll();
        }
        if (chazuoSelectId != 0) {
            chazuoDataDao.update(new ChazuoData((long) chazuoSelectId, "插座" + chazuoSelectId,
                    "电脑", chazuoDataDao.loadAll().get(chazuoSelectId).isOk,
                    chazuoDataDao.loadAll().get(chazuoSelectId).openTime,
                    chazuoDataDao.loadAll().get(chazuoSelectId).closedTime));
        }

        computerDao.insert(new Computer(et_computer_ip.getText().toString(), et_computer_port.getText().toString(),
                et_computer_name.getText().toString(), et_password.getText().toString(),
                chazuoSelectName, chazuoSelectId));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        chazuoAdapter.setDatas(chazuoDataDao.loadAll());

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
