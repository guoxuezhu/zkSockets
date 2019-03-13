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
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.data.model.Projector;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.SerialPortUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectorSetingActivity extends BaseActivity {


    @BindView(R.id.spinnerBaudRate)
    Spinner spinnerBaudRate;
    @BindView(R.id.spinnerCheckoutBit)
    Spinner spinnerCheckoutBit;
    @BindView(R.id.spinnerDataBit)
    Spinner spinnerDataBit;
    @BindView(R.id.spinnerStopBit)
    Spinner spinnerStopBit;

    @BindView(R.id.tyj_binary_1)
    RadioButton tyj_binary_1;
    @BindView(R.id.tyj_binary_2)
    RadioButton tyj_binary_2;


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
    private SerialPortDataDao serialPortDataDao;
    private SerialCommandDao serialCommandDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projector_seting);
        ButterKnife.bind(this);

        baudRateInitView();
        checkoutBitInitView();
        dataBitInitView();
        stopBitInitView();


//        projectorDao = MyApplication.getDaoSession().getProjectorDao();
//        if (projectorDao.loadAll().size() != 0) {
//            if (projectorDao.load((long) 1) != null) {
//                setViewInit(projectorDao.load((long) 1));
//            } else if (projectorDao.load((long) 2) != null) {
//                radio_btn_1.setChecked(false);
//                radio_btn_2.setChecked(true);
//                setViewInit(projectorDao.load((long) 2));
//            }
//        }


        serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();

        List<SerialPortData> serialPortDatas = serialPortDataDao.queryBuilder()
                .where(SerialPortDataDao.Properties.Id.eq(1))
                .list();


        if (serialPortDatas.size() == 0) {
            serialPortDataDao.insert(new SerialPortData((long) 1, "串口1", "投影机", 2,
                    "9600", 0, "无", 0, "8", 0, "1", 10));


            for (int j = 1; j < 5; j++) {
                serialCommandDao.insert(new SerialCommand(Long.valueOf(1 + "" + j), 1, j, "1-" + 1 + "-" + j, "", ""));
            }

        }
        setInitView(serialPortDataDao.load((long) 1));

        ELog.i("=========serialPortDataDao========" + serialPortDataDao.loadAll().toString());
        ELog.i("=========serialCommandDao========" + serialCommandDao.loadAll().toString());

    }

    private void setInitView(SerialPortData serialPortData) {
        spinnerBaudRate.setSelection(serialPortData.baudRateId);
        spinnerCheckoutBit.setSelection(serialPortData.checkoutBitId);
        spinnerDataBit.setSelection(serialPortData.dataBitId);
        spinnerStopBit.setSelection(serialPortData.stopBitId);


        if (serialPortData.jinZhi == 10) {
            tyj_binary_1.setChecked(true);
        } else {
            tyj_binary_2.setChecked(true);
        }

//
        et_open_command.setText(serialCommandDao.load((long) 11).commandStr);
        et_closed_command.setText(serialCommandDao.load((long) 12).commandStr);
        et_VGA_command.setText(serialCommandDao.load((long) 13).commandStr);
        et_HDMI_command.setText(serialCommandDao.load((long) 14).commandStr);
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


    @OnClick(R.id.btn_projector_ok)
    public void btn_projector_ok() {

        if (tyj_binary_1.isChecked()) {
            serialPortDataDao.update(new SerialPortData((long) 1, "串口1", "投影机",
                    selectBaudRateId, selectBaudRate, selectCheckoutBitId, selectCheckoutBit, selectDataBitId,
                    selectDataBit, selectStopBitId, selectStopBit, 10));
        } else {
            serialPortDataDao.update(new SerialPortData((long) 1, "串口1", "投影机",
                    selectBaudRateId, selectBaudRate, selectCheckoutBitId, selectCheckoutBit, selectDataBitId,
                    selectDataBit, selectStopBitId, selectStopBit, 16));
        }

        serialCommandDao.update(new SerialCommand(Long.valueOf(11), 1, 1, "1-1-1",
                "开投影机命令", et_open_command.getText().toString()));

        serialCommandDao.update(new SerialCommand(Long.valueOf(12), 1, 2, "1-1-2",
                "关投影机命令", et_closed_command.getText().toString()));

        serialCommandDao.update(new SerialCommand(Long.valueOf(13), 1, 3, "1-1-3",
                "切换到VGA", et_VGA_command.getText().toString()));

        serialCommandDao.update(new SerialCommand(Long.valueOf(14), 1, 4, "1-1-4",
                "切换到HDMI", et_HDMI_command.getText().toString()));

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
