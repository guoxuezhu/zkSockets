package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.BaseInfoDao;
import com.lh.zksockets.data.model.BaseInfo;
import com.lh.zksockets.service.MyMqttService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongKongActivity extends BaseActivity {

    @BindView(R.id.et_yc_weizhi)
    EditText et_yc_weizhi;
    @BindView(R.id.et_mqtt_user)
    EditText et_mqtt_user;
    @BindView(R.id.et_mqtt_mima)
    EditText et_mqtt_mima;

    @BindView(R.id.et_yc_deviceId)
    TextView et_yc_deviceId;
    @BindView(R.id.et_yc_mqtt_status)
    TextView et_yc_mqtt_status;

    private BaseInfoDao baseInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_kong);
        ButterKnife.bind(this);

        baseInfoDao = MyApplication.getDaoSession().getBaseInfoDao();
        if (baseInfoDao.loadAll().size() == 0) {
            baseInfoDao.insert(new BaseInfo("", "",
                    "", java.util.UUID.randomUUID().toString(), 0));
        }
        et_yc_weizhi.setText(baseInfoDao.loadAll().get(0).classRoom);
        et_mqtt_user.setText(baseInfoDao.loadAll().get(0).mqttuser);
        et_mqtt_mima.setText(baseInfoDao.loadAll().get(0).mqttpassword);
        et_yc_deviceId.setText(baseInfoDao.loadAll().get(0).uuid);
        if (baseInfoDao.loadAll().get(0).status == 0) {
            et_yc_mqtt_status.setText("断开");
            et_yc_mqtt_status.setTextColor(getResources().getColor(R.color.user_icon_8));
        } else {
            et_yc_mqtt_status.setText("在线");
            et_yc_mqtt_status.setTextColor(getResources().getColor(R.color.profile_badge_3));
        }
    }


    @OnClick(R.id.btn_zkyc_clean)
    public void btn_zkyc_clean() {
        baseInfoDao.deleteAll();
        MyMqttService.stopMqtt(this);
        Toast.makeText(this, "清除成功,已取消远程连接", Toast.LENGTH_SHORT).show();
        et_yc_weizhi.setText("");
        et_mqtt_user.setText("");
        et_mqtt_mima.setText("");
    }

    @OnClick(R.id.btn_zkyc_ok)
    public void btn_zkyc_ok() {
        if (et_yc_weizhi.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "请输入中控位置", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_mqtt_user.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "请输入MQTT用户", Toast.LENGTH_SHORT).show();
            return;
        }

        if (et_mqtt_mima.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "请输入MQTT密码", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseInfo baseInfo = new BaseInfo(et_yc_weizhi.getText().toString().trim(), et_mqtt_user.getText().toString().trim(),
                et_mqtt_mima.getText().toString().trim(), et_yc_deviceId.getText().toString().trim(), baseInfoDao.loadAll().get(0).status);
        baseInfoDao.deleteAll();
        baseInfoDao.insert(baseInfo);
        MyMqttService.startService(this); //开启服务
        Toast.makeText(this, "保存成功，正在启动", Toast.LENGTH_SHORT).show();
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
