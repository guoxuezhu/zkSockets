package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.UDPInfoDao;
import com.lh.zksockets.data.model.UDPInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UdptcpActivity extends BaseActivity {

    @BindView(R.id.et_wg_ip)
    EditText et_wg_ip;
    @BindView(R.id.et_diannao_ip)
    EditText et_diannao_ip;
    @BindView(R.id.et_diannao_port)
    EditText et_diannao_port;
    @BindView(R.id.et_diannao_ml)
    EditText et_diannao_ml;

    private UDPInfoDao udpInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udptcp);
        ButterKnife.bind(this);

        udpInfoDao = MyApplication.getDaoSession().getUDPInfoDao();

        if (udpInfoDao.loadAll().size() == 0) {
            udpInfoDao.insert(new UDPInfo("", "", 0, ""));
        }

        et_wg_ip.setText(udpInfoDao.loadAll().get(0).wlip);
        et_diannao_ip.setText(udpInfoDao.loadAll().get(0).diannaoIP);
        et_diannao_port.setText(udpInfoDao.loadAll().get(0).diannaoPort + "");
        et_diannao_ml.setText(udpInfoDao.loadAll().get(0).diannaoMl);

    }


    @OnClick(R.id.btn_udptcp_ok)
    public void btn_udptcp_ok() {
        udpInfoDao.deleteAll();
        udpInfoDao.insert(new UDPInfo(et_wg_ip.getText().toString(), et_diannao_ip.getText().toString(),
                Integer.valueOf(et_diannao_port.getText().toString()) , et_diannao_ml.getText().toString()));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.udptcp_back_btn)
    public void udptcp_back_btn() {
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
