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

    private UIsetDataDao uIsetDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mban);
        ButterKnife.bind(this);
        uIsetDataDao = MyApplication.getDaoSession().getUIsetDataDao();
        if (uIsetDataDao.loadAll().size() != 0) {
            if (uIsetDataDao.loadAll().get(0).btn_1_status.equals("0")) {
                btn_ui_1.setChecked(true);
            } else {
                btn_ui_1.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_2_status.equals("0")) {
                btn_ui_2.setChecked(true);
            } else {
                btn_ui_2.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_3_status.equals("0")) {
                btn_ui_3.setChecked(true);
            } else {
                btn_ui_3.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_4_status.equals("0")) {
                btn_ui_4.setChecked(true);
            } else {
                btn_ui_4.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_5_status.equals("0")) {
                btn_ui_5.setChecked(true);
            } else {
                btn_ui_5.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_6_status.equals("0")) {
                btn_ui_6.setChecked(true);
            } else {
                btn_ui_6.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_7_status.equals("0")) {
                btn_ui_7.setChecked(true);
            } else {
                btn_ui_7.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_8_status.equals("0")) {
                btn_ui_8.setChecked(true);
            } else {
                btn_ui_8.setChecked(false);
            }
            if (uIsetDataDao.loadAll().get(0).btn_9_status.equals("0")) {
                btn_ui_9.setChecked(true);
            } else {
                btn_ui_9.setChecked(false);
            }
            ELog.d("=========uIsetDataDao==========" + uIsetDataDao.loadAll().toString());
        }

    }

    @OnClick(R.id.btn_mban_ok)
    public void btn_mban_ok() {
        String ui1;
        String ui2;
        String ui3;
        String ui4;
        String ui5;
        String ui6;
        String ui7;
        String ui8;
        String ui9;
        if (btn_ui_1.isChecked()) {
            ui1 = "0";
        } else {
            ui1 = "1";
        }
        if (btn_ui_2.isChecked()) {
            ui2 = "0";
        } else {
            ui2 = "1";
        }
        if (btn_ui_3.isChecked()) {
            ui3 = "0";
        } else {
            ui3 = "1";
        }
        if (btn_ui_4.isChecked()) {
            ui4 = "0";
        } else {
            ui4 = "1";
        }
        if (btn_ui_5.isChecked()) {
            ui5 = "0";
        } else {
            ui5 = "1";
        }
        if (btn_ui_6.isChecked()) {
            ui6 = "0";
        } else {
            ui6 = "1";
        }
        if (btn_ui_7.isChecked()) {
            ui7 = "0";
        } else {
            ui7 = "1";
        }
        if (btn_ui_8.isChecked()) {
            ui8 = "0";
        } else {
            ui8 = "1";
        }
        if (btn_ui_9.isChecked()) {
            ui9 = "0";
        } else {
            ui9 = "1";
        }
        uIsetDataDao.deleteAll();
        uIsetDataDao.insert(new UIsetData(ui1, ui2, ui3, ui4, ui5, ui6, ui7, ui8, ui9));
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