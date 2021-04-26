package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.utils.ZksDatasUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangDaoActivity extends BaseActivity {

    @BindView(R.id.LL_sk_layout)
    LinearLayout LL_sk_layout;
    @BindView(R.id.LL_jdq_layout)
    LinearLayout LL_jdq_layout;
    @BindView(R.id.LL_danger_out_layout)
    LinearLayout LL_danger_out_layout;
    @BindView(R.id.LL_danger_in_layout)
    LinearLayout LL_danger_in_layout;
    @BindView(R.id.LL_io_out_layout)
    LinearLayout LL_io_out_layout;
    @BindView(R.id.LL_vid_layout)
    LinearLayout LL_vid_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_dao);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SerialPortDataDao serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        if (serialPortDataDao.loadAll().size() == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_sk_layout.addView(textView);
        } else {
            for (int i = 1; i < ZksDatasUtil.COMCOUNT; i++) {
                TextView textView = new TextView(this);
                String skmsg = "串口" + i + ":";
                textView.setText(serialPortDataDao.load((long) i).deviceName.equals("") ? skmsg + "未知设备" : skmsg + serialPortDataDao.load((long) i).deviceName);
                textView.setTextSize(16);
                textView.setTextColor(serialPortDataDao.load((long) i).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_sk_layout.addView(textView);
            }
        }

        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_jdq_layout.addView(textView);
        } else {
            for (int i = 1; i < ZksDatasUtil.JDQ_COUNT; i++) {
                TextView textView = new TextView(this);
                String jdqmsg = "继电器" + i + ":";
                textView.setText(jdqStatusDao.load((long) i).deviceName.equals("") ? jdqmsg + "未知设备" : jdqmsg + jdqStatusDao.load((long) i).deviceName);
                textView.setTextSize(16);
                textView.setTextColor(jdqStatusDao.load((long) i).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_jdq_layout.addView(textView);
            }
        }

        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_danger_in_layout.addView(textView);
        } else {
            for (int i = 1; i < ZksDatasUtil.DANFER_IN_COUNT; i++) {
                TextView textView = new TextView(this);
                textView.setText(ioYuanDao.load((long) i).deviceName.equals("") ? "报警输入" + i + "：未知设备" : "报警输入" + i + ":" + ioYuanDao.load((long) i).deviceName);
                textView.setTextSize(16);
                textView.setTextColor(ioYuanDao.load((long) i).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_danger_in_layout.addView(textView);
            }
        }

        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_danger_out_layout.addView(textView);
        } else {
            for (int i = 1; i < ZksDatasUtil.DANFER_OUT_COUNT; i++) {
                TextView textView = new TextView(this);
                textView.setText(dangerOutDao.load((long) i).deviceName.equals("") ? "报警输出" + i + "：未知设备" : "报警输出" + i + ":" + dangerOutDao.load((long) i).deviceName);
                textView.setTextColor(dangerOutDao.load((long) i).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
                textView.setTextSize(16);
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_danger_out_layout.addView(textView);
            }
        }

        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_io_out_layout.addView(textView);
        } else {
            for (int i = 1; i < ZksDatasUtil.IO_OUT_COUNT; i++) {
                TextView textView = new TextView(this);
                textView.setText(ioPortDataDao.load((long) i).deviceName.equals("") ? "io输出" + i + "：未知设备" : "io输出" + i + ":" + ioPortDataDao.load((long) i).deviceName);
                textView.setTextColor(ioPortDataDao.load((long) i).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
                textView.setTextSize(16);
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_io_out_layout.addView(textView);
            }
        }

        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0 || zkInfoDao.loadAll().get(0).hudongVIDnum == 0) {
            TextView textView = new TextView(this);
            textView.setText("无设备");
            textView.setTextSize(16);
            textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
            textView.setPadding(6, 6, 6, 6);
            layoutParams.setMargins(0, 6, 0, 0);
            textView.setLayoutParams(layoutParams);
            LL_vid_layout.addView(textView);
        } else {
            for (int i = 1; i < zkInfoDao.loadAll().get(0).hudongVIDnum + 1; i++) {
                TextView textView = new TextView(this);
                textView.setText("互动大屏" + i);
                textView.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.profile_badge_3));
                textView.setBackgroundResource(R.drawable.layout_tuobu_bg); //设置背景
                textView.setPadding(6, 6, 6, 6);
                layoutParams.setMargins(0, 6, 0, 0);
                textView.setLayoutParams(layoutParams);
                LL_vid_layout.addView(textView);
            }
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
