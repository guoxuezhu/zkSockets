package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectAdapter;
import com.lh.zksockets.adapter.SerialportAdapter;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SerialPortUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerialportActivity extends BaseActivity implements SerialportAdapter.CallBack {


    @BindView(R.id.spt_btn_1)
    RadioButton spt_btn_1;
    @BindView(R.id.radio_binary_1)
    RadioButton radio_binary_1;
    @BindView(R.id.radio_binary_2)
    RadioButton radio_binary_2;

    @BindView(R.id.et_device_name)
    EditText et_device_name;

    @BindView(R.id.spinnerBaudRate)
    Spinner spinnerBaudRate;
    @BindView(R.id.spinnerCheckoutBit)
    Spinner spinnerCheckoutBit;
    @BindView(R.id.spinnerDataBit)
    Spinner spinnerDataBit;
    @BindView(R.id.spinnerStopBit)
    Spinner spinnerStopBit;

    @BindView(R.id.serial_recyclerView)
    RecyclerView serial_recyclerView;


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


    private SerialPortDataDao serialPortDataDao;
    private SerialCommandDao serialCommandDao;
    private SerialportAdapter serialportAdapter;
    private List<SerialCommand> serialCommands;
    private int spt_btn_port;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialport);
        ButterKnife.bind(this);

        spt_btn_1.setChecked(true);
//        spt_btn_2.setChecked(false);

        baudRateInitView();
        checkoutBitInitView();
        dataBitInitView();
        stopBitInitView();


        serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();

        if (serialPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 9; i++) {
                serialPortDataDao.insert(new SerialPortData((long) i, "串口" + i, "", 2,
                        "9600", 0, "无", 0, "8", 0, "1", 10));
                for (int j = 1; j < 11; j++) {
                    serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "" + j), i, j, "1-" + i + "-" + j, "", ""));
                }
            }
        }
        ELog.i("=========serialPortDataDao========" + serialPortDataDao.loadAll().toString());
        ELog.i("=========serialCommandDao========" + serialCommandDao.loadAll().toString());

        ViewInit(0);


    }

    private void ViewInit(int i) {

        et_device_name.setText(serialPortDataDao.loadAll().get(i).deviceName);
        spinnerBaudRate.setSelection(serialPortDataDao.loadAll().get(i).baudRateId);
        spinnerCheckoutBit.setSelection(serialPortDataDao.loadAll().get(i).checkoutBitId);
        spinnerDataBit.setSelection(serialPortDataDao.loadAll().get(i).dataBitId);
        spinnerStopBit.setSelection(serialPortDataDao.loadAll().get(i).stopBitId);

        if (serialPortDataDao.loadAll().get(i).jinZhi == 10) {
            radio_binary_1.setChecked(true);
        } else {
            radio_binary_2.setChecked(true);
        }

        ELog.i("=========serialCommands===00000=====" + serialPortDataDao.loadAll().get(i).toString());

        serialCommands = serialCommandDao.queryBuilder()
                .where(SerialCommandDao.Properties.SId.eq(i + 1))
                .orderAsc(SerialCommandDao.Properties.MlId)
                .list();

        ELog.i("=========serialCommands===1111=====" + serialCommands.toString());

        serial_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        serialportAdapter = new SerialportAdapter(this, serialCommands, this);
        serial_recyclerView.setAdapter(serialportAdapter);


    }


    @Override
    public void setNameEditTextChanged(SerialCommand serialCommand, int position, String name) {
        if (serialCommand != null && name != null) {
            serialCommands.get(position).setCommandName(name);
        }
    }


    @Override
    public void setMlEditTextChanged(SerialCommand serialCommand, int position, String ml) {
        if (serialCommand != null && ml != null) {
            serialCommands.get(position).setCommandStr(ml);
        }
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


    @OnClick(R.id.spt_btn_1)
    public void spt_btn_1() {
        setSelectBtn(0);
    }

    @OnClick(R.id.spt_btn_2)
    public void spt_btn_2() {
        setSelectBtn(1);
    }

    @OnClick(R.id.spt_btn_3)
    public void spt_btn_3() {
        setSelectBtn(2);
    }

    @OnClick(R.id.spt_btn_4)
    public void spt_btn_4() {
        setSelectBtn(3);
    }

    @OnClick(R.id.spt_btn_5)
    public void spt_btn_5() {
        setSelectBtn(4);
    }

    @OnClick(R.id.spt_btn_6)
    public void spt_btn_6() {
        setSelectBtn(5);
    }

    @OnClick(R.id.spt_btn_7)
    public void spt_btn_7() {
        setSelectBtn(6);
    }

    @OnClick(R.id.spt_btn_8)
    public void spt_btn_8() {
        setSelectBtn(7);
    }

    private void setSelectBtn(int i) {
        spt_btn_port = i + 1;
        ViewInit(i);

    }


    @OnClick(R.id.sport_btn_ok)
    public void sport_btn_ok() {

        if (radio_binary_1.isChecked()) {
            serialPortDataDao.update(new SerialPortData((long) spt_btn_port, "串口" + spt_btn_port,
                    et_device_name.getText().toString(), selectBaudRateId, selectBaudRate,
                    selectCheckoutBitId, selectCheckoutBit, selectDataBitId, selectDataBit,
                    selectStopBitId, selectStopBit, 10));
        } else {
            serialPortDataDao.update(new SerialPortData((long) spt_btn_port, "串口" + spt_btn_port,
                    et_device_name.getText().toString(), selectBaudRateId, selectBaudRate,
                    selectCheckoutBitId, selectCheckoutBit, selectDataBitId, selectDataBit,
                    selectStopBitId, selectStopBit, 16));
        }

        for (int j = 0; j < serialCommands.size(); j++) {
            serialCommandDao.update(serialCommands.get(j));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.serialport_back_btn)
    public void serialport_back_btn() {
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


}
