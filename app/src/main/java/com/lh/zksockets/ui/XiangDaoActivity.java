package com.lh.zksockets.ui;

import android.content.Intent;
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

    @BindView(R.id.tp_ck_1)
    TextView tp_ck_1;
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
            String serialmsg = "";
            for (int i = 1; i < 9; i++) {
                if (!serialPortDataDao.load((long) i).deviceName.equals("")) {
                    serialmsg = serialmsg + "串口" + i + "：" + serialPortDataDao.load((long) i).deviceName + "\n";
                } else {
                    serialmsg = serialmsg + "串口" + i + "：无设备" + "\n";
                }
            }
            tp_ck_1.setText(serialmsg);
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
