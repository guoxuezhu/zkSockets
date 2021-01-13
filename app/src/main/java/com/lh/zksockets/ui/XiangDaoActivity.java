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

    @BindView(R.id.jdq_tuopu_name_1)
    TextView jdq_tuopu_name_1;
    @BindView(R.id.jdq_tuopu_name_2)
    TextView jdq_tuopu_name_2;
    @BindView(R.id.jdq_tuopu_name_3)
    TextView jdq_tuopu_name_3;
    @BindView(R.id.jdq_tuopu_name_4)
    TextView jdq_tuopu_name_4;
    @BindView(R.id.jdq_tuopu_name_5)
    TextView jdq_tuopu_name_5;
    @BindView(R.id.jdq_tuopu_name_6)
    TextView jdq_tuopu_name_6;
    @BindView(R.id.jdq_tuopu_name_7)
    TextView jdq_tuopu_name_7;
    @BindView(R.id.jdq_tuopu_name_8)
    TextView jdq_tuopu_name_8;

    @BindView(R.id.danger_in_tuopu_1)
    TextView danger_in_tuopu_1;
    @BindView(R.id.danger_in_tuopu_2)
    TextView danger_in_tuopu_2;
    @BindView(R.id.danger_in_tuopu_3)
    TextView danger_in_tuopu_3;
    @BindView(R.id.danger_in_tuopu_4)
    TextView danger_in_tuopu_4;


    @BindView(R.id.tuopu_dangerOut_1)
    TextView tuopu_dangerOut_1;
    @BindView(R.id.tuopu_dangerOut_2)
    TextView tuopu_dangerOut_2;
    @BindView(R.id.tuopu_dangerOut_3)
    TextView tuopu_dangerOut_3;
    @BindView(R.id.tuopu_dangerOut_4)
    TextView tuopu_dangerOut_4;


    @BindView(R.id.tuopu_ioOut_1)
    TextView tuopu_ioOut_1;
    @BindView(R.id.tuopu_ioOut_2)
    TextView tuopu_ioOut_2;
    @BindView(R.id.tuopu_ioOut_3)
    TextView tuopu_ioOut_3;
    @BindView(R.id.tuopu_ioOut_4)
    TextView tuopu_ioOut_4;

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


            tp_ck_1.setTextColor(serialPortDataDao.load((long) 1).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_2.setTextColor(serialPortDataDao.load((long) 2).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_3.setTextColor(serialPortDataDao.load((long) 3).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_4.setTextColor(serialPortDataDao.load((long) 4).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_5.setTextColor(serialPortDataDao.load((long) 5).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_6.setTextColor(serialPortDataDao.load((long) 6).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_7.setTextColor(serialPortDataDao.load((long) 7).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tp_ck_8.setTextColor(serialPortDataDao.load((long) 8).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));


        }

        JDQstatusDao jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() != 0) {

            jdq_tuopu_name_1.setText(jdqStatusDao.load((long) 1).deviceName.equals("") ? "继电器1：无设备" : "继电器1：" + jdqStatusDao.load((long) 1).deviceName);
            jdq_tuopu_name_2.setText(jdqStatusDao.load((long) 2).deviceName.equals("") ? "继电器2：无设备" : "继电器2：" + jdqStatusDao.load((long) 2).deviceName);
            jdq_tuopu_name_3.setText(jdqStatusDao.load((long) 3).deviceName.equals("") ? "继电器3：无设备" : "继电器3：" + jdqStatusDao.load((long) 3).deviceName);
            jdq_tuopu_name_4.setText(jdqStatusDao.load((long) 4).deviceName.equals("") ? "继电器4：无设备" : "继电器4：" + jdqStatusDao.load((long) 4).deviceName);
            jdq_tuopu_name_5.setText(jdqStatusDao.load((long) 5).deviceName.equals("") ? "继电器5：无设备" : "继电器5：" + jdqStatusDao.load((long) 5).deviceName);
            jdq_tuopu_name_6.setText(jdqStatusDao.load((long) 6).deviceName.equals("") ? "继电器6：无设备" : "继电器6：" + jdqStatusDao.load((long) 6).deviceName);
            jdq_tuopu_name_7.setText(jdqStatusDao.load((long) 7).deviceName.equals("") ? "继电器7：无设备" : "继电器7：" + jdqStatusDao.load((long) 7).deviceName);
            jdq_tuopu_name_8.setText(jdqStatusDao.load((long) 8).deviceName.equals("") ? "继电器8：无设备" : "继电器8：" + jdqStatusDao.load((long) 8).deviceName);

            jdq_tuopu_name_1.setTextColor(jdqStatusDao.load((long) 1).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_2.setTextColor(jdqStatusDao.load((long) 2).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_3.setTextColor(jdqStatusDao.load((long) 3).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_4.setTextColor(jdqStatusDao.load((long) 4).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_5.setTextColor(jdqStatusDao.load((long) 5).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_6.setTextColor(jdqStatusDao.load((long) 6).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_7.setTextColor(jdqStatusDao.load((long) 7).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            jdq_tuopu_name_8.setTextColor(jdqStatusDao.load((long) 8).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));


        }

        IOYuanDao ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() != 0) {
            danger_in_tuopu_1.setText(ioYuanDao.load((long) 1).deviceName.equals("") ? "报警输入1：无设备" : "报警输入1：" + ioYuanDao.load((long) 1).deviceName);
            danger_in_tuopu_2.setText(ioYuanDao.load((long) 2).deviceName.equals("") ? "报警输入2：无设备" : "报警输入2：" + ioYuanDao.load((long) 2).deviceName);
            danger_in_tuopu_3.setText(ioYuanDao.load((long) 3).deviceName.equals("") ? "报警输入3：无设备" : "报警输入3：" + ioYuanDao.load((long) 3).deviceName);
            danger_in_tuopu_4.setText(ioYuanDao.load((long) 4).deviceName.equals("") ? "报警输入4：无设备" : "报警输入4：" + ioYuanDao.load((long) 4).deviceName);

            danger_in_tuopu_1.setTextColor(ioYuanDao.load((long) 1).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            danger_in_tuopu_2.setTextColor(ioYuanDao.load((long) 2).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            danger_in_tuopu_3.setTextColor(ioYuanDao.load((long) 3).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            danger_in_tuopu_4.setTextColor(ioYuanDao.load((long) 4).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));

        }

        DangerOutDao dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() != 0) {
            tuopu_dangerOut_1.setText(dangerOutDao.load((long) 1).deviceName.equals("") ? "报警输出1：无设备" : "报警输出1：" + dangerOutDao.load((long) 1).deviceName);
            tuopu_dangerOut_2.setText(dangerOutDao.load((long) 2).deviceName.equals("") ? "报警输出2：无设备" : "报警输出2：" + dangerOutDao.load((long) 2).deviceName);
            tuopu_dangerOut_3.setText(dangerOutDao.load((long) 3).deviceName.equals("") ? "报警输出3：无设备" : "报警输出3：" + dangerOutDao.load((long) 3).deviceName);
            tuopu_dangerOut_4.setText(dangerOutDao.load((long) 4).deviceName.equals("") ? "报警输出4：无设备" : "报警输出4：" + dangerOutDao.load((long) 4).deviceName);

            tuopu_dangerOut_1.setTextColor(dangerOutDao.load((long) 1).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_dangerOut_2.setTextColor(dangerOutDao.load((long) 2).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_dangerOut_3.setTextColor(dangerOutDao.load((long) 3).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_dangerOut_4.setTextColor(dangerOutDao.load((long) 4).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
        }

        IoPortDataDao ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() != 0) {

            tuopu_ioOut_1.setText(ioPortDataDao.load((long) 1).deviceName.equals("") ? "io输出1：无设备" : "io输出1：" + ioPortDataDao.load((long) 1).deviceName);
            tuopu_ioOut_2.setText(ioPortDataDao.load((long) 2).deviceName.equals("") ? "io输出2：无设备" : "io输出2：" + ioPortDataDao.load((long) 2).deviceName);
            tuopu_ioOut_3.setText(ioPortDataDao.load((long) 3).deviceName.equals("") ? "io输出3：无设备" : "io输出3：" + ioPortDataDao.load((long) 3).deviceName);
            tuopu_ioOut_4.setText(ioPortDataDao.load((long) 4).deviceName.equals("") ? "io输出4：无设备" : "io输出4：" + ioPortDataDao.load((long) 4).deviceName);

            tuopu_ioOut_1.setTextColor(ioPortDataDao.load((long) 1).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_ioOut_2.setTextColor(ioPortDataDao.load((long) 2).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_ioOut_3.setTextColor(ioPortDataDao.load((long) 3).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));
            tuopu_ioOut_4.setTextColor(ioPortDataDao.load((long) 4).deviceName.equals("") ? getResources().getColor(R.color.user_icon_default_gray) : getResources().getColor(R.color.profile_badge_3));

        }
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

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
