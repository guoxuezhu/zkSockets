package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangDaoActivity extends BaseActivity {

    @BindView(R.id.tp_ck_1)
    TextView tp_ck_1;
    @BindView(R.id.tp_ck_2)
    TextView tp_ck_2;
    @BindView(R.id.tp_ck_3)
    TextView tp_ck_3;
    @BindView(R.id.tp_ck_4)
    TextView tp_ck_4;
    @BindView(R.id.tp_ck_5)
    TextView tp_ck_5;
    @BindView(R.id.tp_ck_6)
    TextView tp_ck_6;
    @BindView(R.id.tp_ck_7)
    TextView tp_ck_7;
    @BindView(R.id.tp_ck_8)
    TextView tp_ck_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_dao);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        if (serialPortDataDao.loadAll().size() != 0) {
            tp_ck_1.setText(serialPortDataDao.load((long) 1).deviceName.equals("") ? "串口1：无设备" : "串口1：" + serialPortDataDao.load((long) 1).deviceName);
            tp_ck_2.setText(serialPortDataDao.load((long) 2).deviceName.equals("") ? "串口2：无设备" : "串口2：" + serialPortDataDao.load((long) 2).deviceName);
            tp_ck_3.setText(serialPortDataDao.load((long) 3).deviceName.equals("") ? "串口3：无设备" : "串口3：" + serialPortDataDao.load((long) 3).deviceName);
            tp_ck_4.setText(serialPortDataDao.load((long) 4).deviceName.equals("") ? "串口4：无设备" : "串口4：" + serialPortDataDao.load((long) 4).deviceName);
            tp_ck_5.setText(serialPortDataDao.load((long) 5).deviceName.equals("") ? "串口5：无设备" : "串口5：" + serialPortDataDao.load((long) 5).deviceName);
            tp_ck_6.setText(serialPortDataDao.load((long) 6).deviceName.equals("") ? "串口6：无设备" : "串口6：" + serialPortDataDao.load((long) 6).deviceName);
            tp_ck_7.setText(serialPortDataDao.load((long) 7).deviceName.equals("") ? "串口7：无设备" : "串口7：" + serialPortDataDao.load((long) 7).deviceName);
            tp_ck_8.setText(serialPortDataDao.load((long) 8).deviceName.equals("") ? "串口8：无设备" : "串口8：" + serialPortDataDao.load((long) 8).deviceName);
        } else {
            tp_ck_1.setText("串口1：无设备");
            tp_ck_2.setText("串口2：无设备");
            tp_ck_3.setText("串口3：无设备");
            tp_ck_4.setText("串口4：无设备");
            tp_ck_5.setText("串口5：无设备");
            tp_ck_6.setText("串口6：无设备");
            tp_ck_7.setText("串口7：无设备");
            tp_ck_8.setText("串口8：无设备");
        }

    }

    @OnClick(R.id.xiangdao_btn_back)
    public void xiangdao_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
