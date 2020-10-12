package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.data.model.DoorInfo;
import com.lh.zksockets.data.model.EventKejianRest;
import com.lh.zksockets.service.MyMqttService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongKongActivity extends BaseActivity {

    @BindView(R.id.wg_ip)
    EditText wg_ip;

    @BindView(R.id.rbtn_wg_ok)
    RadioButton rbtn_wg_ok;
    @BindView(R.id.rbtn_wg_no)
    RadioButton rbtn_wg_no;

    private EventKejianRestDao wangguandata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_kong);
        ButterKnife.bind(this);

        wangguandata = MyApplication.getDaoSession().getEventKejianRestDao();
        if (wangguandata.loadAll().size() == 0) {
            wangguandata.insert(new EventKejianRest(1, (long) 1, "192.168.0.220",
                    0, false, 0));
        }
        wg_ip.setText(wangguandata.loadAll().get(0).name);
        if (wangguandata.loadAll().get(0).status == 1) {
            rbtn_wg_ok.setChecked(true);
        } else {
            rbtn_wg_no.setChecked(true);
        }
    }

    @OnClick(R.id.btn_wg_baocun)
    public void btn_wg_baocun() {
        wangguandata.deleteAll();
        if (rbtn_wg_ok.isChecked()) {
            wangguandata.insert(new EventKejianRest(1, (long) 1, wg_ip.getText().toString(),
                    1, false, 0));
        } else {
            wangguandata.insert(new EventKejianRest(1, (long) 1, wg_ip.getText().toString(),
                    0, false, 0));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.zk_back_btn)
    public void zk_back_btn() {
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
