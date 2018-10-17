package com.lh.zksockets.ui;

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

public class EnvironmentalActivity extends BaseActivity {

    @BindView(R.id.environmental_radio_btn_1)
    RadioButton environmental_radio_btn_1;
    @BindView(R.id.environmental_radio_btn_2)
    RadioButton environmental_radio_btn_2;
    @BindView(R.id.environmental_radio_btn_3)
    RadioButton environmental_radio_btn_3;

    @BindView(R.id.environmental_chazuo)
    Spinner environmental_chazuo;

    private ChazuoDataDao chazuoDataDao;
    private SelectChazuoAdapter chazuoAdapter;
    private String chazuoSelectName;
    private int chazuoSelectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        ButterKnife.bind(this);
        environmental_radio_btn_1.setChecked(true);
        environmental_radio_btn_2.setChecked(false);
        environmental_radio_btn_3.setChecked(false);

        chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        chazuoAdapter = new SelectChazuoAdapter(this, chazuoDataDao.loadAll());

        environmental_chazuo.setAdapter(chazuoAdapter);
        environmental_chazuo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    @OnClick(R.id.environmental_btn_back)
    public void environmental_btn_back() {
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
