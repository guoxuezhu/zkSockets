package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.utils.ELog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends BaseActivity {

    @BindView(R.id.event_et_1)
    EditText event_et_1;
    @BindView(R.id.event_et_2)
    EditText event_et_2;
    @BindView(R.id.event_et_3)
    EditText event_et_3;
    @BindView(R.id.event_et_4)
    EditText event_et_4;
    @BindView(R.id.event_et_5)
    EditText event_et_5;
    @BindView(R.id.event_et_6)
    EditText event_et_6;
    @BindView(R.id.event_et_7)
    EditText event_et_7;
    @BindView(R.id.event_et_8)
    EditText event_et_8;
    @BindView(R.id.event_et_9)
    EditText event_et_9;
    @BindView(R.id.event_et_10)
    EditText event_et_10;
    @BindView(R.id.event_et_11)
    EditText event_et_11;
    @BindView(R.id.event_et_12)
    EditText event_et_12;
    @BindView(R.id.event_et_13)
    EditText event_et_13;
    @BindView(R.id.event_et_14)
    EditText event_et_14;
    @BindView(R.id.event_et_15)
    EditText event_et_15;
    @BindView(R.id.event_et_16)
    EditText event_et_16;
    @BindView(R.id.event_et_17)
    EditText event_et_17;
    @BindView(R.id.event_et_18)
    EditText event_et_18;
    @BindView(R.id.event_et_19)
    EditText event_et_19;
    @BindView(R.id.event_et_20)
    EditText event_et_20;
    @BindView(R.id.event_et_21)
    EditText event_et_21;
    @BindView(R.id.event_et_22)
    EditText event_et_22;
    @BindView(R.id.event_et_23)
    EditText event_et_23;
    @BindView(R.id.event_et_24)
    EditText event_et_24;
    @BindView(R.id.event_et_25)
    EditText event_et_25;
    @BindView(R.id.event_et_26)
    EditText event_et_26;
    @BindView(R.id.event_et_27)
    EditText event_et_27;
    @BindView(R.id.event_et_28)
    EditText event_et_28;
    @BindView(R.id.event_et_29)
    EditText event_et_29;
    @BindView(R.id.event_et_30)
    EditText event_et_30;
    @BindView(R.id.event_et_31)
    EditText event_et_31;
    @BindView(R.id.event_et_32)
    EditText event_et_32;
    @BindView(R.id.event_et_33)
    EditText event_et_33;
    @BindView(R.id.event_et_34)
    EditText event_et_34;
    @BindView(R.id.event_et_35)
    EditText event_et_35;
    @BindView(R.id.event_et_36)
    EditText event_et_36;
    @BindView(R.id.event_et_37)
    EditText event_et_37;
    @BindView(R.id.event_et_38)
    EditText event_et_38;
    @BindView(R.id.event_et_39)
    EditText event_et_39;
    @BindView(R.id.event_et_40)
    EditText event_et_40;
    @BindView(R.id.event_et_41)
    EditText event_et_41;
    @BindView(R.id.event_et_42)
    EditText event_et_42;
    @BindView(R.id.event_et_43)
    EditText event_et_43;
    @BindView(R.id.event_et_44)
    EditText event_et_44;
    @BindView(R.id.event_et_45)
    EditText event_et_45;

    @BindView(R.id.event_et_46)
    EditText event_et_46;
    @BindView(R.id.event_et_47)
    EditText event_et_47;

    @BindView(R.id.event_et_48)
    EditText event_et_48;
    @BindView(R.id.event_et_49)
    EditText event_et_49;

    @BindView(R.id.event_et_50)
    EditText event_et_50;
    @BindView(R.id.event_et_51)
    EditText event_et_51;


    @BindView(R.id.event_et_52)
    EditText event_et_52;
    @BindView(R.id.event_et_53)
    EditText event_et_53;
    @BindView(R.id.event_et_54)
    EditText event_et_54;
    @BindView(R.id.event_et_55)
    EditText event_et_55;
    @BindView(R.id.event_et_56)
    EditText event_et_56;
    @BindView(R.id.event_et_57)
    EditText event_et_57;
    @BindView(R.id.event_et_58)
    EditText event_et_58;
    @BindView(R.id.event_et_59)
    EditText event_et_59;
//    @BindView(R.id.event_et_60)
//    EditText event_et_60;

    @BindView(R.id.event_tv_time_1)
    TextView event_tv_time_1;
    @BindView(R.id.event_tv_time_2)
    TextView event_tv_time_2;
    @BindView(R.id.event_tv_time_3)
    TextView event_tv_time_3;
    @BindView(R.id.event_tv_time_4)
    TextView event_tv_time_4;
    @BindView(R.id.event_tv_time_5)
    TextView event_tv_time_5;
    @BindView(R.id.event_tv_time_6)
    TextView event_tv_time_6;
    @BindView(R.id.event_tv_time_7)
    TextView event_tv_time_7;
    @BindView(R.id.event_tv_time_8)
    TextView event_tv_time_8;
    @BindView(R.id.event_tv_time_9)
    TextView event_tv_time_9;

    @BindView(R.id.event_tv_time_10)
    TextView event_tv_time_10;
    @BindView(R.id.event_tv_time_11)
    TextView event_tv_time_11;
    @BindView(R.id.event_tv_time_12)
    TextView event_tv_time_12;
    @BindView(R.id.event_tv_time_13)
    TextView event_tv_time_13;
    @BindView(R.id.event_tv_time_14)
    TextView event_tv_time_14;
    @BindView(R.id.event_tv_time_15)
    TextView event_tv_time_15;
    @BindView(R.id.event_tv_time_16)
    TextView event_tv_time_16;
    @BindView(R.id.event_tv_time_17)
    TextView event_tv_time_17;
    @BindView(R.id.event_tv_time_18)
    TextView event_tv_time_18;
    @BindView(R.id.event_tv_time_19)
    TextView event_tv_time_19;
    @BindView(R.id.event_tv_time_20)
    TextView event_tv_time_20;
    @BindView(R.id.event_tv_time_21)
    TextView event_tv_time_21;
    @BindView(R.id.event_tv_time_22)
    TextView event_tv_time_22;
    @BindView(R.id.event_tv_time_23)
    TextView event_tv_time_23;
    @BindView(R.id.event_tv_time_24)
    TextView event_tv_time_24;
    @BindView(R.id.event_tv_time_25)
    TextView event_tv_time_25;
    @BindView(R.id.event_tv_time_26)
    TextView event_tv_time_26;
    @BindView(R.id.event_tv_time_27)
    TextView event_tv_time_27;
    @BindView(R.id.event_tv_time_28)
    TextView event_tv_time_28;
    @BindView(R.id.event_tv_time_29)
    TextView event_tv_time_29;
    @BindView(R.id.event_tv_time_30)
    TextView event_tv_time_30;
    @BindView(R.id.event_tv_time_31)
    TextView event_tv_time_31;
    @BindView(R.id.event_tv_time_32)
    TextView event_tv_time_32;
    @BindView(R.id.event_tv_time_33)
    TextView event_tv_time_33;
    @BindView(R.id.event_tv_time_34)
    TextView event_tv_time_34;
    @BindView(R.id.event_tv_time_35)
    TextView event_tv_time_35;
    @BindView(R.id.event_tv_time_36)
    TextView event_tv_time_36;
    @BindView(R.id.event_tv_time_37)
    TextView event_tv_time_37;
    @BindView(R.id.event_tv_time_38)
    TextView event_tv_time_38;
    @BindView(R.id.event_tv_time_39)
    TextView event_tv_time_39;
    @BindView(R.id.event_tv_time_40)
    TextView event_tv_time_40;
    @BindView(R.id.event_tv_time_41)
    TextView event_tv_time_41;
    @BindView(R.id.event_tv_time_42)
    TextView event_tv_time_42;
    @BindView(R.id.event_tv_time_43)
    TextView event_tv_time_43;
    @BindView(R.id.event_tv_time_44)
    TextView event_tv_time_44;
    @BindView(R.id.event_tv_time_45)
    TextView event_tv_time_45;
    @BindView(R.id.event_tv_time_46)
    TextView event_tv_time_46;
    @BindView(R.id.event_tv_time_47)
    TextView event_tv_time_47;
    @BindView(R.id.event_tv_time_48)
    TextView event_tv_time_48;
    @BindView(R.id.event_tv_time_49)
    TextView event_tv_time_49;
    @BindView(R.id.event_tv_time_50)
    TextView event_tv_time_50;
    @BindView(R.id.event_tv_time_51)
    TextView event_tv_time_51;
    @BindView(R.id.event_tv_time_52)
    TextView event_tv_time_52;
    @BindView(R.id.event_tv_time_53)
    TextView event_tv_time_53;
    @BindView(R.id.event_tv_time_54)
    TextView event_tv_time_54;
    @BindView(R.id.event_tv_time_55)
    TextView event_tv_time_55;
    @BindView(R.id.event_tv_time_56)
    TextView event_tv_time_56;
    @BindView(R.id.event_tv_time_57)
    TextView event_tv_time_57;
    @BindView(R.id.event_tv_time_58)
    TextView event_tv_time_58;
    @BindView(R.id.event_tv_time_59)
    TextView event_tv_time_59;
//    @BindView(R.id.event_tv_time_60)
//    TextView event_tv_time_60;

    private MLsListsDao mLsListsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3, "自习", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "休息", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6, "窗帘1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7, "窗帘2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8, "窗帘2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-1", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-1", ""));
            mLsListsDao.insert(new MLsLists((long) 13, "灯光1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 15, "灯光2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 16, "灯光2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 17, "灯光3开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 18, "灯光3关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 19, "灯光4开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 20, "灯光4关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 21, "总音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 22, "总音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 23, "总音量静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 24, "总音量静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 25, "音响音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 26, "音响音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 27, "音响静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 28, "音响静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 29, "麦克风音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 30, "麦克风音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 31, "麦克风静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 32, "麦克风静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 33, "录播-录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 34, "录播-暂停", "", ""));
            mLsListsDao.insert(new MLsLists((long) 35, "录播-停止", "", ""));
            mLsListsDao.insert(new MLsLists((long) 36, "录播-直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 41, "空调-风速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 42, "空调-风向", "", ""));
            mLsListsDao.insert(new MLsLists((long) 43, "空调-温度+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 44, "空调-温度-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "开机", "", ""));
            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 48, "空调-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 49, "空调-摆风", "", ""));
            mLsListsDao.insert(new MLsLists((long) 50, "一体机-内置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 51, "一体机-外置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 52, "电视机1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 53, "电视机2", "", ""));
            mLsListsDao.insert(new MLsLists((long) 54, "电视机3", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "电视机4", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "电视机5", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "电视机6", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "新风开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "新风关", "", ""));
//            mLsListsDao.insert(new MLsLists((long) 60, "其它", "", ""));
        }

        DataInit();

    }

    private void DataInit() {
        event_et_1.setText(mLsListsDao.load((long) 1).strMLs);
        event_et_2.setText(mLsListsDao.load((long) 2).strMLs);
        event_et_3.setText(mLsListsDao.load((long) 3).strMLs);
        event_et_4.setText(mLsListsDao.load((long) 4).strMLs);
        event_et_5.setText(mLsListsDao.load((long) 5).strMLs);
        event_et_6.setText(mLsListsDao.load((long) 6).strMLs);
        event_et_7.setText(mLsListsDao.load((long) 7).strMLs);
        event_et_8.setText(mLsListsDao.load((long) 8).strMLs);
        event_et_9.setText(mLsListsDao.load((long) 9).strMLs);
        event_et_10.setText(mLsListsDao.load((long) 10).strMLs);
        event_et_11.setText(mLsListsDao.load((long) 11).strMLs);
        event_et_12.setText(mLsListsDao.load((long) 12).strMLs);
        event_et_13.setText(mLsListsDao.load((long) 13).strMLs);
        event_et_14.setText(mLsListsDao.load((long) 14).strMLs);
        event_et_15.setText(mLsListsDao.load((long) 15).strMLs);
        event_et_16.setText(mLsListsDao.load((long) 16).strMLs);
        event_et_17.setText(mLsListsDao.load((long) 17).strMLs);
        event_et_18.setText(mLsListsDao.load((long) 18).strMLs);
        event_et_19.setText(mLsListsDao.load((long) 19).strMLs);
        event_et_20.setText(mLsListsDao.load((long) 20).strMLs);
        event_et_21.setText(mLsListsDao.load((long) 21).strMLs);
        event_et_22.setText(mLsListsDao.load((long) 22).strMLs);
        event_et_23.setText(mLsListsDao.load((long) 23).strMLs);
        event_et_24.setText(mLsListsDao.load((long) 24).strMLs);
        event_et_25.setText(mLsListsDao.load((long) 25).strMLs);
        event_et_26.setText(mLsListsDao.load((long) 26).strMLs);
        event_et_27.setText(mLsListsDao.load((long) 27).strMLs);
        event_et_28.setText(mLsListsDao.load((long) 28).strMLs);
        event_et_29.setText(mLsListsDao.load((long) 29).strMLs);
        event_et_30.setText(mLsListsDao.load((long) 30).strMLs);
        event_et_31.setText(mLsListsDao.load((long) 31).strMLs);
        event_et_32.setText(mLsListsDao.load((long) 32).strMLs);
        event_et_33.setText(mLsListsDao.load((long) 33).strMLs);
        event_et_34.setText(mLsListsDao.load((long) 34).strMLs);
        event_et_35.setText(mLsListsDao.load((long) 35).strMLs);
        event_et_36.setText(mLsListsDao.load((long) 36).strMLs);
        event_et_37.setText(mLsListsDao.load((long) 37).strMLs);
        event_et_38.setText(mLsListsDao.load((long) 38).strMLs);
        event_et_39.setText(mLsListsDao.load((long) 39).strMLs);
        event_et_40.setText(mLsListsDao.load((long) 40).strMLs);
        event_et_41.setText(mLsListsDao.load((long) 41).strMLs);
        event_et_42.setText(mLsListsDao.load((long) 42).strMLs);
        event_et_43.setText(mLsListsDao.load((long) 43).strMLs);
        event_et_44.setText(mLsListsDao.load((long) 44).strMLs);
        event_et_45.setText(mLsListsDao.load((long) 45).strMLs);
        event_et_46.setText(mLsListsDao.load((long) 46).strMLs);
        event_et_47.setText(mLsListsDao.load((long) 47).strMLs);
        event_et_48.setText(mLsListsDao.load((long) 48).strMLs);
        event_et_49.setText(mLsListsDao.load((long) 49).strMLs);
        event_et_50.setText(mLsListsDao.load((long) 50).strMLs);
        event_et_51.setText(mLsListsDao.load((long) 51).strMLs);
        event_et_52.setText(mLsListsDao.load((long) 52).strMLs);
        event_et_53.setText(mLsListsDao.load((long) 53).strMLs);
        event_et_54.setText(mLsListsDao.load((long) 54).strMLs);
        event_et_55.setText(mLsListsDao.load((long) 55).strMLs);
        event_et_56.setText(mLsListsDao.load((long) 56).strMLs);
        event_et_57.setText(mLsListsDao.load((long) 57).strMLs);
        event_et_58.setText(mLsListsDao.load((long) 58).strMLs);
        event_et_59.setText(mLsListsDao.load((long) 59).strMLs);
//        event_et_60.setText(mLsListsDao.load((long) 60).strMLs);


        event_tv_time_1.setText(mLsListsDao.load((long) 1).time);
        event_tv_time_2.setText(mLsListsDao.load((long) 2).time);
        event_tv_time_3.setText(mLsListsDao.load((long) 3).time);
        event_tv_time_4.setText(mLsListsDao.load((long) 4).time);
        event_tv_time_5.setText(mLsListsDao.load((long) 5).time);
        event_tv_time_6.setText(mLsListsDao.load((long) 6).time);
        event_tv_time_7.setText(mLsListsDao.load((long) 7).time);
        event_tv_time_8.setText(mLsListsDao.load((long) 8).time);
        event_tv_time_9.setText(mLsListsDao.load((long) 9).time);
        event_tv_time_10.setText(mLsListsDao.load((long) 10).time);
        event_tv_time_11.setText(mLsListsDao.load((long) 11).time);
        event_tv_time_12.setText(mLsListsDao.load((long) 12).time);
        event_tv_time_13.setText(mLsListsDao.load((long) 13).time);
        event_tv_time_14.setText(mLsListsDao.load((long) 14).time);
        event_tv_time_15.setText(mLsListsDao.load((long) 15).time);
        event_tv_time_16.setText(mLsListsDao.load((long) 16).time);
        event_tv_time_17.setText(mLsListsDao.load((long) 17).time);
        event_tv_time_18.setText(mLsListsDao.load((long) 18).time);
        event_tv_time_19.setText(mLsListsDao.load((long) 19).time);
        event_tv_time_20.setText(mLsListsDao.load((long) 20).time);
        event_tv_time_21.setText(mLsListsDao.load((long) 21).time);
        event_tv_time_22.setText(mLsListsDao.load((long) 22).time);
        event_tv_time_23.setText(mLsListsDao.load((long) 23).time);
        event_tv_time_24.setText(mLsListsDao.load((long) 24).time);
        event_tv_time_25.setText(mLsListsDao.load((long) 25).time);
        event_tv_time_26.setText(mLsListsDao.load((long) 26).time);
        event_tv_time_27.setText(mLsListsDao.load((long) 27).time);
        event_tv_time_28.setText(mLsListsDao.load((long) 28).time);
        event_tv_time_29.setText(mLsListsDao.load((long) 29).time);
        event_tv_time_30.setText(mLsListsDao.load((long) 30).time);
        event_tv_time_31.setText(mLsListsDao.load((long) 31).time);
        event_tv_time_32.setText(mLsListsDao.load((long) 32).time);
        event_tv_time_33.setText(mLsListsDao.load((long) 33).time);
        event_tv_time_34.setText(mLsListsDao.load((long) 34).time);
        event_tv_time_35.setText(mLsListsDao.load((long) 35).time);
        event_tv_time_36.setText(mLsListsDao.load((long) 36).time);
        event_tv_time_37.setText(mLsListsDao.load((long) 37).time);
        event_tv_time_38.setText(mLsListsDao.load((long) 38).time);
        event_tv_time_39.setText(mLsListsDao.load((long) 39).time);
        event_tv_time_40.setText(mLsListsDao.load((long) 40).time);
        event_tv_time_41.setText(mLsListsDao.load((long) 41).time);
        event_tv_time_42.setText(mLsListsDao.load((long) 42).time);
        event_tv_time_43.setText(mLsListsDao.load((long) 43).time);
        event_tv_time_44.setText(mLsListsDao.load((long) 44).time);
        event_tv_time_45.setText(mLsListsDao.load((long) 45).time);
        event_tv_time_46.setText(mLsListsDao.load((long) 46).time);
        event_tv_time_47.setText(mLsListsDao.load((long) 47).time);
        event_tv_time_48.setText(mLsListsDao.load((long) 48).time);
        event_tv_time_49.setText(mLsListsDao.load((long) 49).time);
        event_tv_time_50.setText(mLsListsDao.load((long) 50).time);
        event_tv_time_51.setText(mLsListsDao.load((long) 51).time);
        event_tv_time_52.setText(mLsListsDao.load((long) 52).time);
        event_tv_time_53.setText(mLsListsDao.load((long) 53).time);
        event_tv_time_54.setText(mLsListsDao.load((long) 54).time);
        event_tv_time_55.setText(mLsListsDao.load((long) 55).time);
        event_tv_time_56.setText(mLsListsDao.load((long) 56).time);
        event_tv_time_57.setText(mLsListsDao.load((long) 57).time);
        event_tv_time_58.setText(mLsListsDao.load((long) 58).time);
        event_tv_time_59.setText(mLsListsDao.load((long) 59).time);
//        event_tv_time_60.setText(mLsListsDao.load((long) 60).time);

        ELog.i("=======mLsListsDao=======" + mLsListsDao.loadAll().toString());

    }


    @OnClick(R.id.evbtn_ok)
    public void evbtn_ok() {
        mLsListsDao.update(new MLsLists((long) 1, "上课", event_et_1.getText().toString(), event_tv_time_1.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2, "下课", event_et_2.getText().toString(), event_tv_time_2.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3, "自习", event_et_3.getText().toString(), event_tv_time_3.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4, "休息", event_et_4.getText().toString(), event_tv_time_4.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5, "窗帘1开", event_et_5.getText().toString(), event_tv_time_5.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6, "窗帘1关", event_et_6.getText().toString(), event_tv_time_6.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7, "窗帘2开", event_et_7.getText().toString(), event_tv_time_7.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8, "窗帘2关", event_et_8.getText().toString(), event_tv_time_8.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 9, "投影机开", event_et_9.getText().toString(), event_tv_time_9.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 10, "投影机关", event_et_10.getText().toString(), event_tv_time_10.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 11, "幕布升", event_et_11.getText().toString(), event_tv_time_11.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 12, "幕布降", event_et_12.getText().toString(), event_tv_time_12.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 13, "灯光1开", event_et_13.getText().toString(), event_tv_time_13.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 14, "灯光1关", event_et_14.getText().toString(), event_tv_time_14.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 15, "灯光2开", event_et_15.getText().toString(), event_tv_time_15.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 16, "灯光2关", event_et_16.getText().toString(), event_tv_time_16.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 17, "灯光3开", event_et_17.getText().toString(), event_tv_time_17.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 18, "灯光3关", event_et_18.getText().toString(), event_tv_time_18.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 19, "灯光4开", event_et_19.getText().toString(), event_tv_time_19.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 20, "灯光4关", event_et_20.getText().toString(), event_tv_time_20.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 21, "总音量+", event_et_21.getText().toString(), event_tv_time_21.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 22, "总音量-", event_et_22.getText().toString(), event_tv_time_22.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 23, "总音量静音开", event_et_23.getText().toString(), event_tv_time_23.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 24, "总音量静音关", event_et_24.getText().toString(), event_tv_time_24.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 25, "音响音量+", event_et_25.getText().toString(), event_tv_time_25.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 26, "音响音量-", event_et_26.getText().toString(), event_tv_time_26.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 27, "音响静音开", event_et_27.getText().toString(), event_tv_time_27.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 28, "音响静音关", event_et_28.getText().toString(), event_tv_time_28.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 29, "麦克风音量+", event_et_29.getText().toString(), event_tv_time_29.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 30, "麦克风音量-", event_et_30.getText().toString(), event_tv_time_30.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 31, "麦克风静音开", event_et_31.getText().toString(), event_tv_time_31.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 32, "麦克风静音关", event_et_32.getText().toString(), event_tv_time_32.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 33, "录播-录制", event_et_33.getText().toString(), event_tv_time_33.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 34, "录播-暂停", event_et_34.getText().toString(), event_tv_time_34.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 35, "录播-停止", event_et_35.getText().toString(), event_tv_time_35.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 36, "录播-直播", event_et_36.getText().toString(), event_tv_time_36.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 37, "电源-全开", event_et_37.getText().toString(), event_tv_time_37.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 38, "电源-全关", event_et_38.getText().toString(), event_tv_time_38.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 39, "空调-开", event_et_39.getText().toString(), event_tv_time_39.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 40, "空调-模式", event_et_40.getText().toString(), event_tv_time_40.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 41, "空调-风速", event_et_41.getText().toString(), event_tv_time_41.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 42, "空调-风向", event_et_42.getText().toString(), event_tv_time_42.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 43, "空调-温度+", event_et_43.getText().toString(), event_tv_time_43.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 44, "空调-温度-", event_et_44.getText().toString(), event_tv_time_44.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 45, "开机", event_et_45.getText().toString(), event_tv_time_45.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 46, "门禁-前门", event_et_46.getText().toString(), event_tv_time_46.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 47, "门禁-后门", event_et_47.getText().toString(), event_tv_time_47.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 48, "空调-关", event_et_48.getText().toString(), event_tv_time_48.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 49, "空调-摆风", event_et_49.getText().toString(), event_tv_time_49.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 50, "一体机-内置HDMI", event_et_50.getText().toString(), event_tv_time_50.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 51, "一体机-外置HDMI", event_et_51.getText().toString(), event_tv_time_51.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 52, "电视机1", event_et_52.getText().toString(), event_tv_time_52.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 53, "电视机2", event_et_53.getText().toString(), event_tv_time_53.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 54, "电视机3", event_et_54.getText().toString(), event_tv_time_54.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 55, "电视机4", event_et_55.getText().toString(), event_tv_time_55.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 56, "电视机5", event_et_56.getText().toString(), event_tv_time_56.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 57, "电视机6", event_et_57.getText().toString(), event_tv_time_57.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 58, "新风开", event_et_58.getText().toString(), event_tv_time_58.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 59, "新风关", event_et_59.getText().toString(), event_tv_time_59.getText().toString()));
//        mLsListsDao.update(new MLsLists((long) 60, "其它", event_et_60.getText().toString(), event_tv_time_60.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.event_btn_back)
    public void event_btn_back() {
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
