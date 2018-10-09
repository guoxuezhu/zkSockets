package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectChazuoAdapter;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolumeSetActivity extends Activity {

    @BindView(R.id.volume_radio_btn_1)
    RadioButton volume_radio_btn_1;
    @BindView(R.id.volume_radio_btn_2)
    RadioButton volume_radio_btn_2;

    @BindView(R.id.volume_chazuo_spinner)
    Spinner volume_chazuo_spinner;
    private ChazuoDataDao chazuoDataDao;
    private SelectChazuoAdapter chazuoAdapter;
    private String chazuoSelectName;
    private int chazuoSelectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_set);
        ButterKnife.bind(this);
        volume_radio_btn_1.setChecked(true);
        volume_radio_btn_2.setChecked(false);

        chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        chazuoAdapter = new SelectChazuoAdapter(this, chazuoDataDao.loadAll());

        volume_chazuo_spinner.setAdapter(chazuoAdapter);
        volume_chazuo_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chazuoSelectName = chazuoDataDao.loadAll().get(position).name;
                chazuoSelectId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @OnClick(R.id.volume_btn_back)
    public void volume_btn_back() {
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
