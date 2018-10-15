package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectAdapter;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.ui.LampActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddLampDialog extends Dialog {

    @BindView(R.id.lamp_et_id)
    EditText lamp_et_id;
    @BindView(R.id.lamp_et_name)
    EditText lamp_et_name;

    @BindView(R.id.rbtn_1)
    RadioButton rbtn_1;
    @BindView(R.id.rbtn_2)
    RadioButton rbtn_2;
    @BindView(R.id.rbtn_3)
    RadioButton rbtn_3;

    @BindView(R.id.lamp_io_num)
    Spinner lamp_io_num;
    @BindView(R.id.lamp_erial_port_num)
    Spinner lamp_erial_port_num;
    @BindView(R.id.lamp_baud_rate)
    Spinner lamp_baud_rate;
    @BindView(R.id.lamp_checkout_bit)
    Spinner lamp_checkout_bit;
    @BindView(R.id.lamp_data_bit)
    Spinner lamp_data_bit;
    @BindView(R.id.lamp_stop_bit)
    Spinner lamp_stop_bit;

    @BindView(R.id.IO_LLayout)
    LinearLayout IO_LLayout;
    @BindView(R.id.serial_port_LLayout)
    LinearLayout serial_port_LLayout;

    private Context mContext;
    private Lamp data;
    private DialogCallBack mDialogCallBack;

    private List<String> baudRateList;
    private List<String> checkoutBitList;
    private List<String> dataBitList;
    private List<String> stopBitList;
    private List<String> ioNumList;
    private List<String> serialPortList;
    private String selectIoNum;
    private int selectIoNumId;
    private String selectSerialPort;
    private int selectSerialPortId;
    private String selectBaudRate;
    private int selectBaudRateId;
    private String selectCheckoutBit;
    private int selectCheckoutBitId;
    private String selectDataBit;
    private int selectDataBitId;
    private String selectStopBit;
    private int selectStopBitId;
    private LampDao lampDao;

    public AddLampDialog(Context context, Lamp item, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        data = item;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_lamp_dialog_view);
        ButterKnife.bind(this);

        lampDao = MyApplication.getDaoSession().getLampDao();
        lamp_io_numInitView();
        lamp_erial_port_numInitView();
        lamp_baud_rateInitView();
        lamp_checkout_bitInitView();
        lamp_data_bitInitView();
        stopBitInitView();

        initDataView();

    }

    private void initDataView() {
        if (data != null) {
            lamp_et_id.setText(data.id + "");
            lamp_et_id.setEnabled(false);
            lamp_et_name.setText(data.name + "");
            if (data.workType == 1) {
                rbtn_1.setChecked(true);
                IO_LLayout.setVisibility(View.VISIBLE);
                serial_port_LLayout.setVisibility(View.GONE);
                rbtn_2.setChecked(false);
                rbtn_3.setChecked(false);
            } else if (data.workType == 2) {
                rbtn_1.setChecked(false);
                rbtn_2.setChecked(true);
                IO_LLayout.setVisibility(View.GONE);
                serial_port_LLayout.setVisibility(View.GONE);
                rbtn_3.setChecked(false);
            } else if (data.workType == 3) {
                rbtn_1.setChecked(false);
                rbtn_2.setChecked(false);
                rbtn_3.setChecked(true);
                IO_LLayout.setVisibility(View.GONE);
                serial_port_LLayout.setVisibility(View.VISIBLE);
            }
            lamp_io_num.setSelection(data.ioSelectId);
            lamp_erial_port_num.setSelection(data.serialPortSelectId);
            lamp_baud_rate.setSelection(data.baudRateSelectId);
            lamp_checkout_bit.setSelection(data.checkoutBitSelectId);
            lamp_data_bit.setSelection(data.dataBitSelectId);
            lamp_stop_bit.setSelection(data.stopBitSelectId);
        } else {
            lamp_et_id.setText("");
            lamp_et_id.setEnabled(true);
            lamp_et_name.setText("");
            rbtn_1.setChecked(true);
            IO_LLayout.setVisibility(View.VISIBLE);
            serial_port_LLayout.setVisibility(View.GONE);
            rbtn_2.setChecked(false);
            rbtn_3.setChecked(false);
            lamp_io_num.setSelection(0);
            lamp_erial_port_num.setSelection(0);
            lamp_baud_rate.setSelection(0);
            lamp_checkout_bit.setSelection(0);
            lamp_data_bit.setSelection(0);
            lamp_stop_bit.setSelection(0);

        }
    }

    private void lamp_io_numInitView() {
        ioNumList = SerialPortUtil.getIOnumDatas();
        lamp_io_num.setAdapter(new SelectAdapter(mContext, ioNumList));
        lamp_io_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectIoNum = ioNumList.get(position);
                selectIoNumId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void lamp_erial_port_numInitView() {
        serialPortList = SerialPortUtil.getSerialPortNumDatas();
        lamp_erial_port_num.setAdapter(new SelectAdapter(mContext, serialPortList));
        lamp_erial_port_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void lamp_baud_rateInitView() {
        baudRateList = SerialPortUtil.getBaudRateDatas();
        lamp_baud_rate.setAdapter(new SelectAdapter(mContext, baudRateList));
        lamp_baud_rate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void lamp_checkout_bitInitView() {
        checkoutBitList = SerialPortUtil.getCheckoutBitDatas();
        lamp_checkout_bit.setAdapter(new SelectAdapter(mContext, checkoutBitList));
        lamp_checkout_bit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void lamp_data_bitInitView() {
        dataBitList = SerialPortUtil.getDataBitDatas();
        lamp_data_bit.setAdapter(new SelectAdapter(mContext, dataBitList));
        lamp_data_bit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void stopBitInitView() {
        stopBitList = SerialPortUtil.getStopBitDatas();
        lamp_stop_bit.setAdapter(new SelectAdapter(mContext, stopBitList));
        lamp_stop_bit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public interface DialogCallBack {
        void addLampOk(boolean isOk);
    }

    @OnClick(R.id.rbtn_1)
    public void rbtn_1() {
        IO_LLayout.setVisibility(View.VISIBLE);
        serial_port_LLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.rbtn_2)
    public void rbtn_2() {
        IO_LLayout.setVisibility(View.GONE);
        serial_port_LLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.rbtn_3)
    public void rbtn_3() {
        IO_LLayout.setVisibility(View.GONE);
        serial_port_LLayout.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.lamp_dialog_btn_no)
    public void lamp_dialog_btn_no() {
        mDialogCallBack.addLampOk(false);
    }

    @OnClick(R.id.lamp_dialog_btn_ok)
    public void lamp_dialog_btn_ok() {

        if (lamp_et_id.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入序号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (lampDao.loadAll().size() != 0) {
            List<Lamp> lamps = lampDao.queryBuilder()
                    .where(LampDao.Properties.Id.eq(lamp_et_id.getText().toString().trim()))
                    .orderAsc(LampDao.Properties.Id)
                    .list();
            if (lamps.size() != 0) {
                if (data == null) {
                    Toast.makeText(mContext, "序号已存在", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            List<Lamp> lampsName = lampDao.queryBuilder()
                    .where(LampDao.Properties.Name.eq(lamp_et_name.getText().toString().trim()))
                    .orderAsc(LampDao.Properties.Id)
                    .list();
            if (lampsName.size() != 0) {
                Toast.makeText(mContext, lamp_et_name.getText().toString().trim() + "已存在", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (rbtn_1.isChecked()) {
            if (data == null) {
                lampDao.insert(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 1,
                        selectIoNumId, selectIoNum, 0, "",
                        0, "", 0, "",
                        0, "", 0, ""));
            } else {
                lampDao.update(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 1,
                        selectIoNumId, selectIoNum, 0, "",
                        0, "", 0, "",
                        0, "", 0, ""));
            }

            mDialogCallBack.addLampOk(true);
        } else if (rbtn_2.isChecked()) {
            if (data == null) {
                lampDao.insert(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 2,
                        0, "", 0, "",
                        0, "", 0, "",
                        0, "", 0, ""));
            } else {
                lampDao.update(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 2,
                        0, "", 0, "",
                        0, "", 0, "",
                        0, "", 0, ""));
            }
            mDialogCallBack.addLampOk(true);
        } else if (rbtn_3.isChecked()) {
            if (data == null) {
                lampDao.insert(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 3,
                        0, "", selectSerialPortId, selectSerialPort,
                        selectBaudRateId, selectBaudRate, selectCheckoutBitId, selectCheckoutBit,
                        selectDataBitId, selectDataBit, selectStopBitId, selectStopBit));
            } else {
                lampDao.update(new Lamp(Long.parseLong(lamp_et_id.getText().toString().trim()),
                        lamp_et_name.getText().toString().trim(), 3,
                        0, "", selectSerialPortId, selectSerialPort,
                        selectBaudRateId, selectBaudRate, selectCheckoutBitId, selectCheckoutBit,
                        selectDataBitId, selectDataBit, selectStopBitId, selectStopBit));
            }
            mDialogCallBack.addLampOk(true);
        } else {
            Toast.makeText(mContext, "请选择控制方式", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mDialogCallBack.addLampOk(false);
    }
}
