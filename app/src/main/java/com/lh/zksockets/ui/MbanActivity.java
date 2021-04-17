package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.UIsetDataDao;
import com.lh.zksockets.data.model.UIsetData;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.ZksDatasUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MbanActivity extends BaseActivity {


    @BindView(R.id.btn_ui_1)
    Switch btn_ui_1;
    @BindView(R.id.btn_ui_2)
    Switch btn_ui_2;
    @BindView(R.id.btn_ui_3)
    Switch btn_ui_3;
    @BindView(R.id.btn_ui_4)
    Switch btn_ui_4;
    @BindView(R.id.btn_ui_5)
    Switch btn_ui_5;
    @BindView(R.id.btn_ui_6)
    Switch btn_ui_6;
    @BindView(R.id.btn_ui_7)
    Switch btn_ui_7;
    @BindView(R.id.btn_ui_8)
    Switch btn_ui_8;
    @BindView(R.id.btn_ui_9)
    Switch btn_ui_9;
    @BindView(R.id.btn_ui_10)
    Switch btn_ui_10;

    private UIsetDataDao uIsetDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mban);
        ButterKnife.bind(this);
        uIsetDataDao = MyApplication.getDaoSession().getUIsetDataDao();
        ZksDatasUtil.getUIstatusDatas(uIsetDataDao);
        if (uIsetDataDao.load((long) 1).btn_status.equals("0")) {
            btn_ui_1.setChecked(true);
        } else {
            btn_ui_1.setChecked(false);
        }
        if (uIsetDataDao.load((long) 2).btn_status.equals("0")) {
            btn_ui_2.setChecked(true);
        } else {
            btn_ui_2.setChecked(false);
        }
        if (uIsetDataDao.load((long) 3).btn_status.equals("0")) {
            btn_ui_3.setChecked(true);
        } else {
            btn_ui_3.setChecked(false);
        }
        if (uIsetDataDao.load((long) 4).btn_status.equals("0")) {
            btn_ui_4.setChecked(true);
        } else {
            btn_ui_4.setChecked(false);
        }
        if (uIsetDataDao.load((long) 5).btn_status.equals("0")) {
            btn_ui_5.setChecked(true);
        } else {
            btn_ui_5.setChecked(false);
        }
        if (uIsetDataDao.load((long) 6).btn_status.equals("0")) {
            btn_ui_6.setChecked(true);
        } else {
            btn_ui_6.setChecked(false);
        }
        if (uIsetDataDao.load((long) 7).btn_status.equals("0")) {
            btn_ui_7.setChecked(true);
        } else {
            btn_ui_7.setChecked(false);
        }
        if (uIsetDataDao.load((long) 8).btn_status.equals("0")) {
            btn_ui_8.setChecked(true);
        } else {
            btn_ui_8.setChecked(false);
        }
        if (uIsetDataDao.load((long) 9).btn_status.equals("0")) {
            btn_ui_9.setChecked(true);
        } else {
            btn_ui_9.setChecked(false);
        }
        if (uIsetDataDao.load((long) 10).btn_status.equals("0")) {
            btn_ui_10.setChecked(true);
        } else {
            btn_ui_10.setChecked(false);
        }
        ELog.d("=========uIsetDataDao==========" + uIsetDataDao.loadAll().toString());

    }

    @OnClick(R.id.btn_mban_ok)
    public void btn_mban_ok() {
        if (btn_ui_1.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 1, "一键互动", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 1, "一键互动", "1"));
        }
        if (btn_ui_2.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 2, "自由互动", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 2, "自由互动", "1"));
        }
        if (btn_ui_3.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 3, "录播", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 3, "录播", "1"));
        }
        if (btn_ui_4.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 4, "多媒体", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 4, "多媒体", "1"));
        }
        if (btn_ui_5.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 5, "窗帘", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 5, "窗帘", "1"));
        }
        if (btn_ui_6.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 6, "灯光", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 6, "灯光", "1"));
        }
        if (btn_ui_7.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 7, "空调", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 7, "空调", "1"));
        }
        if (btn_ui_8.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 8, "门禁", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 8, "门禁", "1"));
        }
        if (btn_ui_9.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 9, "音频", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 9, "音频", "1"));
        }
        if (btn_ui_10.isChecked()) {
            uIsetDataDao.update(new UIsetData((long) 10, "电风扇", "0"));
        } else {
            uIsetDataDao.update(new UIsetData((long) 10, "电风扇", "1"));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mb_btn_back)
    public void mb_btn_back() {
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