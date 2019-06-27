package com.lh.zksockets.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.data.model.JDQstatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangDaoActivity extends BaseActivity {

    @BindView(R.id.tp_ck_1) TextView tp_ck_1;
    @BindView(R.id.tp_ck_2) TextView tp_ck_2;
    @BindView(R.id.tp_ck_3) TextView tp_ck_3;
    @BindView(R.id.tp_ck_4) TextView tp_ck_4;
    @BindView(R.id.tp_ck_5) TextView tp_ck_5;
    @BindView(R.id.tp_ck_6) TextView tp_ck_6;
    @BindView(R.id.tp_ck_7) TextView tp_ck_7;
    @BindView(R.id.tp_ck_8) TextView tp_ck_8;

    @BindView(R.id.jdq_tuopu_name)
    TextView jdq_tuopu_name;
    @BindView(R.id.danger_in_tuopu)
    TextView danger_in_tuopu;
    @BindView(R.id.tuopu_dangerOut)
    TextView tuopu_dangerOut;
    @BindView(R.id.tuopu_ioOut)
    TextView tuopu_ioOut;

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
            if (!serialPortDataDao.load((long) 1).deviceName.equals("")) {
                tp_ck_1.setText("串口1：" +serialPortDataDao.load((long) 1).deviceName);
                tp_ck_1.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_1.setText("串口1：无设备");
//                tp_ck_1.setTextColor(Color.parseColor("#ff9e9e9e"));
                tp_ck_1.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 2).deviceName.equals("")) {
                tp_ck_2.setText("串口2：" +serialPortDataDao.load((long) 2).deviceName);
                tp_ck_2.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_2.setText("串口2：无设备");
                tp_ck_2.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 3).deviceName.equals("")) {
                tp_ck_3.setText("串口3：" +serialPortDataDao.load((long)3).deviceName);
                tp_ck_3.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_3.setText("串口3：无设备");
                tp_ck_3.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 4).deviceName.equals("")) {
                tp_ck_4.setText("串口4：" +serialPortDataDao.load((long) 4).deviceName);
                tp_ck_4.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_4.setText("串口4：无设备");
                tp_ck_4.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 5).deviceName.equals("")) {
                tp_ck_5.setText("串口5：" +serialPortDataDao.load((long) 5).deviceName);
                tp_ck_5.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_5.setText("串口5：无设备");
                tp_ck_5.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 6).deviceName.equals("")) {
                tp_ck_6.setText("串口6：" +serialPortDataDao.load((long) 6).deviceName);
                tp_ck_6.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_6.setText("串口6：无设备");
                tp_ck_6.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 7).deviceName.equals("")) {
                tp_ck_7.setText("串口7：" +serialPortDataDao.load((long) 7).deviceName);
                tp_ck_7.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_7.setText("串口7：无设备");
                tp_ck_7.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

            if (!serialPortDataDao.load((long) 8).deviceName.equals("")) {
                tp_ck_8.setText("串口8：" +serialPortDataDao.load((long) 8).deviceName);
                tp_ck_8.setTextColor(Color.parseColor("#19F124"));
            } else {
                tp_ck_8.setText("串口8：无设备");
                tp_ck_8.setTextColor(getResources().getColor(R.color.user_icon_default_gray));
            }

        }

        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() != 0) {
            String serialmsg1 = "";
            for (int i = 1; i < 9; i++) {
                if (!jdqStatusDao.load((long) i).deviceName.equals("")) {
                    serialmsg1 = serialmsg1 + "继电器" + i + "：" + jdqStatusDao.load((long) i).deviceName + "\n";
                } else {
                    serialmsg1 = serialmsg1 + "继电器" + i + "：无设备" + "\n";
                }
            }
            jdq_tuopu_name.setText(serialmsg1);
        }

        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() != 0) {
            String serialmsg2 = "";
            for (int i = 1; i < 5; i++) {
                if (!ioYuanDao.load((long) i).deviceName.equals("")) {
                    serialmsg2 = serialmsg2 + "报警输入" + i + "：" + ioYuanDao.load((long) i).deviceName + "\n";
                } else {
                    serialmsg2 = serialmsg2 + "报警输入" + i + "：无设备" + "\n";
                }
            }
            danger_in_tuopu.setText(serialmsg2);
        }

        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() != 0) {
            String serialmsg3 = "";
            for (int i = 1; i < 5; i++) {
                if (!dangerOutDao.load((long) i).deviceName.equals("")) {
                    serialmsg3 = serialmsg3 + "报警输出" + i + "：" + dangerOutDao.load((long) i).deviceName + "\n";
                } else {
                    serialmsg3 = serialmsg3 + "报警输出" + i + "：无设备" + "\n";
                }
            }
            tuopu_dangerOut.setText(serialmsg3);
        }

        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() != 0) {
            String serialmsg4 = "";
            for (int i = 1; i < 5; i++) {
                if (!ioPortDataDao.load((long) i).deviceName.equals("")) {
                    serialmsg4 = serialmsg4 + "io输出" + i + "：" + ioPortDataDao.load((long) i).deviceName + "\n";
                } else {
                    serialmsg4 = serialmsg4 + "io输出" + i + "：无设备" + "\n";
                }
            }
            tuopu_ioOut.setText(serialmsg4);
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
