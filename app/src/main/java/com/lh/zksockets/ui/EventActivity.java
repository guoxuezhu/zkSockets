package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
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


    private MLsListsDao mLsListsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", ""));
            mLsListsDao.insert(new MLsLists((long) 3, "自习", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "休息", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘1开", ""));
            mLsListsDao.insert(new MLsLists((long) 6, "窗帘1关", ""));
            mLsListsDao.insert(new MLsLists((long) 7, "窗帘2开", ""));
            mLsListsDao.insert(new MLsLists((long) 8, "窗帘2关", ""));
            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", ""));
            mLsListsDao.insert(new MLsLists((long) 13, "灯光1开", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光1关", ""));
            mLsListsDao.insert(new MLsLists((long) 15, "灯光2开", ""));
            mLsListsDao.insert(new MLsLists((long) 16, "灯光2关", ""));
            mLsListsDao.insert(new MLsLists((long) 17, "灯光3开", ""));
            mLsListsDao.insert(new MLsLists((long) 18, "灯光3关", ""));
            mLsListsDao.insert(new MLsLists((long) 19, "灯光4开", ""));
            mLsListsDao.insert(new MLsLists((long) 20, "灯光4关", ""));
            mLsListsDao.insert(new MLsLists((long) 21, "总音量+", ""));
            mLsListsDao.insert(new MLsLists((long) 22, "总音量-", ""));
            mLsListsDao.insert(new MLsLists((long) 23, "总音量静音开", ""));
            mLsListsDao.insert(new MLsLists((long) 24, "总音量静音关", ""));
            mLsListsDao.insert(new MLsLists((long) 25, "音响音量+", ""));
            mLsListsDao.insert(new MLsLists((long) 26, "音响音量-", ""));
            mLsListsDao.insert(new MLsLists((long) 27, "音响静音开", ""));
            mLsListsDao.insert(new MLsLists((long) 28, "音响静音关", ""));
            mLsListsDao.insert(new MLsLists((long) 29, "麦克风音量+", ""));
            mLsListsDao.insert(new MLsLists((long) 30, "麦克风音量-", ""));
            mLsListsDao.insert(new MLsLists((long) 31, "麦克风静音开", ""));
            mLsListsDao.insert(new MLsLists((long) 32, "麦克风静音关", ""));
            mLsListsDao.insert(new MLsLists((long) 33, "录播-录制", ""));
            mLsListsDao.insert(new MLsLists((long) 34, "录播-暂停", ""));
            mLsListsDao.insert(new MLsLists((long) 35, "录播-停止", ""));
            mLsListsDao.insert(new MLsLists((long) 36, "录播-直播", ""));
            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", ""));
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
        ELog.i("=======mLsListsDao=======" + mLsListsDao.loadAll().toString());

    }


    @OnClick(R.id.evbtn_ok)
    public void evbtn_ok() {
        mLsListsDao.update(new MLsLists((long) 1, "上课", event_et_1.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 2, "下课", event_et_2.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 3, "自习", event_et_3.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 4, "休息", event_et_4.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 5, "窗帘1开", event_et_5.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 6, "窗帘1关", event_et_6.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 7, "窗帘2开", event_et_7.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 8, "窗帘2关", event_et_8.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 9, "投影机开", event_et_9.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 10, "投影机关", event_et_10.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 11, "幕布升", event_et_11.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 12, "幕布降", event_et_12.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 13, "灯光1开", event_et_13.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 14, "灯光1关", event_et_14.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 15, "灯光2开", event_et_15.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 16, "灯光2关", event_et_16.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 17, "灯光3开", event_et_17.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 18, "灯光3关", event_et_18.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 19, "灯光4开", event_et_19.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 20, "灯光4关", event_et_20.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 21, "总音量+", event_et_21.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 22, "总音量-", event_et_22.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 23, "总音量静音开", event_et_23.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 24, "总音量静音关", event_et_24.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 25, "音响音量+", event_et_25.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 26, "音响音量-", event_et_26.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 27, "音响静音开", event_et_27.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 28, "音响静音关", event_et_28.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 29, "麦克风音量+", event_et_29.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 30, "麦克风音量-", event_et_30.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 31, "麦克风静音开", event_et_31.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 32, "麦克风静音关", event_et_32.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 33, "录播-录制", event_et_33.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 34, "录播-暂停", event_et_34.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 35, "录播-停止", event_et_35.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 36, "录播-直播", event_et_36.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 37, "电源-全开", event_et_37.getText().toString()));
        mLsListsDao.update(new MLsLists((long) 38, "电源-全关", event_et_38.getText().toString()));
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
