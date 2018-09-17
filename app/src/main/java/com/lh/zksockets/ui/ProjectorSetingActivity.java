package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectorSetingActivity extends Activity {


    @BindView(R.id.radio_btn_1)
    RadioButton radio_btn_1;
    @BindView(R.id.radio_btn_2)
    RadioButton radio_btn_2;

    @BindView(R.id.spinnerBaudRate)
    Spinner spinnerBaudRate;
    @BindView(R.id.spinnerCheckoutBit)
    Spinner spinnerCheckoutBit;
    @BindView(R.id.spinnerDataBit)
    Spinner spinnerDataBit;
    @BindView(R.id.spinnerStopBit)
    Spinner spinnerStopBit;
    @BindView(R.id.spinnerTyep)
    Spinner spinnerTyep;

    private List<String> baudRateList;
    private List<String> checkoutBitList;
    private List<String> dataBitList;
    private List<String> stopBitList;
    private List<String> typeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projector_seting);
        ButterKnife.bind(this);
        radio_btn_1.setChecked(true);
        radio_btn_2.setChecked(false);

        baudRateInitView();
        checkoutBitInitView();
        dataBitInitView();
        stopBitInitView();
        typeInitView();

    }

    private void typeInitView() {
        typeList = new ArrayList<>();
        typeList.add("投影机类型0");
        typeList.add("投影机类型1");
        typeList.add("投影机类型2");


        spinnerTyep.setAdapter(new SelectAdapter(this, typeList));
        spinnerTyep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lhlog", "=====投影机类型============" + typeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void stopBitInitView() {
        stopBitList = new ArrayList<>();
        stopBitList.add("0");
        stopBitList.add("1");
        stopBitList.add("2");


        spinnerStopBit.setAdapter(new SelectAdapter(this, stopBitList));
        spinnerStopBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lhlog", "=====停止位============" + stopBitList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void dataBitInitView() {
        dataBitList = new ArrayList<>();
        dataBitList.add("5");
        dataBitList.add("6");
        dataBitList.add("7");
        dataBitList.add("8");


        spinnerDataBit.setAdapter(new SelectAdapter(this, dataBitList));
        spinnerDataBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lhlog", "=====数据位============" + dataBitList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void checkoutBitInitView() {
        // 偶校验位与奇校验位
        checkoutBitList = new ArrayList<>();
        checkoutBitList.add("无");
        checkoutBitList.add("奇");
        checkoutBitList.add("偶");


        spinnerCheckoutBit.setAdapter(new SelectAdapter(this, checkoutBitList));
        spinnerCheckoutBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lhlog", "=====校验位============" + checkoutBitList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void baudRateInitView() {
        baudRateList = new ArrayList<>();
        baudRateList.add("9600");
        baudRateList.add("14400");
        baudRateList.add("19200");
        baudRateList.add("38400");
        baudRateList.add("57600");
        baudRateList.add("115200");


        spinnerBaudRate.setAdapter(new SelectAdapter(this, baudRateList));
        spinnerBaudRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lhlog", "=====波特率============" + baudRateList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @OnClick(R.id.radio_btn_1)
    public void radio_btn_1() {

    }

    @OnClick(R.id.radio_btn_2)
    public void radio_btn_2() {

    }

    @OnClick(R.id.btn_projector_ok)
    public void btn_projector_ok() {
        back();
    }

    @OnClick(R.id.projector_btn_back)
    public void projector_btn_back() {
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
