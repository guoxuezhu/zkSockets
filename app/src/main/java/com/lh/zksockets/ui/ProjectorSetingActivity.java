package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectAdapter;
import com.lh.zksockets.adapter.SelectChazuoAdapter;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.Projector;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SerialPortUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectorSetingActivity extends BaseActivity {


    @BindView(R.id.radio_btn_1)
    RadioButton radio_btn_1;
    @BindView(R.id.radio_btn_2)
    RadioButton radio_btn_2;

    @BindView(R.id.spinner_serial_port)
    Spinner spinner_serial_port;
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


    @BindView(R.id.et_open_command)
    EditText et_open_command;
    @BindView(R.id.et_closed_command)
    EditText et_closed_command;
    @BindView(R.id.et_VGA_command)
    EditText et_VGA_command;
    @BindView(R.id.et_HDMI_command)
    EditText et_HDMI_command;

    private List<String> serialPortList;
    private List<String> baudRateList;
    private List<String> checkoutBitList;
    private List<String> dataBitList;
    private List<String> stopBitList;
    private List<String> typeList;
    private ProjectorDao projectorDao;
    private String selectSerialPort;
    private String selectBaudRate;
    private String selectCheckoutBit;
    private String selectDataBit;
    private String selectStopBit;
    private String selectTyep;

    private int selectSerialPortId;
    private int selectBaudRateId;
    private int selectCheckoutBitId;
    private int selectDataBitId;
    private int selectStopBitId;
    private int selectTyepId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projector_seting);
        ButterKnife.bind(this);
        radio_btn_1.setChecked(true);
        radio_btn_2.setChecked(false);

        serialPortInitView();
        baudRateInitView();
        checkoutBitInitView();
        dataBitInitView();
        stopBitInitView();
        typeInitView();


        projectorDao = MyApplication.getDaoSession().getProjectorDao();
        if (projectorDao.loadAll().size() != 0) {
            if (projectorDao.load((long) 1) != null) {
                setViewInit(projectorDao.load((long) 1));
            } else if (projectorDao.load((long) 2) != null) {
                radio_btn_1.setChecked(false);
                radio_btn_2.setChecked(true);
                setViewInit(projectorDao.load((long) 2));
            }
        }

        ELog.i("=========vvvv========" + projectorDao.loadAll().toString());

    }




    private void setViewInit(Projector projector) {
        if (projector != null) {
            spinner_serial_port.setSelection(projector.serialPortId);
            spinnerBaudRate.setSelection(projector.baudRateId);
            spinnerCheckoutBit.setSelection(projector.checkoutBitId);
            spinnerDataBit.setSelection(projector.dataBitId);
            spinnerStopBit.setSelection(projector.stopBitId);
            spinnerTyep.setSelection(projector.typeId);

            et_open_command.setText(projector.openCommand);
            et_closed_command.setText(projector.closedCommand);
            et_VGA_command.setText(projector.VGACommand);
            et_HDMI_command.setText(projector.HDMICommand);
        } else {
            spinner_serial_port.setSelection(0);
            spinnerBaudRate.setSelection(0);
            spinnerCheckoutBit.setSelection(0);
            spinnerDataBit.setSelection(0);
            spinnerStopBit.setSelection(0);
            spinnerTyep.setSelection(0);

            et_open_command.setText("");
            et_closed_command.setText("");
            et_VGA_command.setText("");
            et_HDMI_command.setText("");
        }

    }

    private void typeInitView() {
        typeList = new ArrayList<>();
        typeList.add("其它");
        typeList.add("PLC-XU35");
        typeList.add("投影机类型1");
        typeList.add("投影机类型2");

        spinnerTyep.setAdapter(new SelectAdapter(this, typeList));
        spinnerTyep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTyep = typeList.get(position);
                selectTyepId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void stopBitInitView() {
        stopBitList = SerialPortUtil.getStopBitDatas();

        spinnerStopBit.setAdapter(new SelectAdapter(this, stopBitList));
        spinnerStopBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectStopBit = stopBitList.get(position);
                selectStopBitId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void dataBitInitView() {
        dataBitList = SerialPortUtil.getDataBitDatas();
        spinnerDataBit.setAdapter(new SelectAdapter(this, dataBitList));
        spinnerDataBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDataBit = dataBitList.get(position);
                selectDataBitId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void checkoutBitInitView() {
        // 偶校验位与奇校验位
        checkoutBitList = SerialPortUtil.getCheckoutBitDatas();
        spinnerCheckoutBit.setAdapter(new SelectAdapter(this, checkoutBitList));
        spinnerCheckoutBit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCheckoutBit = checkoutBitList.get(position);
                selectCheckoutBitId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void baudRateInitView() {
        baudRateList = SerialPortUtil.getBaudRateDatas();
        spinnerBaudRate.setAdapter(new SelectAdapter(this, baudRateList));
        spinnerBaudRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectBaudRate = baudRateList.get(position);
                selectBaudRateId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void serialPortInitView() {
        serialPortList = SerialPortUtil.getSerialPortNumDatas();
        spinner_serial_port.setAdapter(new SelectAdapter(this, serialPortList));
        spinner_serial_port.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSerialPort = serialPortList.get(position);
                selectSerialPortId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.radio_btn_1)
    public void radio_btn_1() {
        radio_btn_1.setChecked(true);
        radio_btn_2.setChecked(false);
        setViewInit(projectorDao.load((long) 1));
    }

    @OnClick(R.id.radio_btn_2)
    public void radio_btn_2() {
        radio_btn_1.setChecked(false);
        radio_btn_2.setChecked(true);
        setViewInit(projectorDao.load((long) 2));
    }

    @OnClick(R.id.btn_projector_ok)
    public void btn_projector_ok() {

        int deviceId = 0;
        String name = "";
        if (radio_btn_1.isChecked()) {
            deviceId = 1;
            name = "投影机一";
        }

        if (radio_btn_2.isChecked()) {
            deviceId = 2;
            name = "投影机二";
        }

        if (projectorDao.load((long) deviceId) != null) {
            projectorDao.deleteByKey((long) deviceId);
        }


        projectorDao.insert(new Projector((long) deviceId, name, selectSerialPort, selectSerialPortId, selectBaudRate,
                selectBaudRateId, selectCheckoutBit, selectCheckoutBitId, selectDataBit, selectDataBitId, selectStopBit,
                selectStopBitId, selectTyep, selectTyepId, et_open_command.getText().toString(),
                et_closed_command.getText().toString(), et_VGA_command.getText().toString(), et_HDMI_command.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();


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
