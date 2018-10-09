package com.lh.zksockets.ui;

import android.app.Activity;
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

    @BindView(R.id.chazuo_ji)
    Spinner chazuo_ji;
    @BindView(R.id.chazuo_bu)
    Spinner chazuo_bu;

    @BindView(R.id.et_open_command)
    EditText et_open_command;
    @BindView(R.id.et_closed_command)
    EditText et_closed_command;
    @BindView(R.id.et_VGA_command)
    EditText et_VGA_command;
    @BindView(R.id.et_HDMI_command)
    EditText et_HDMI_command;

    private List<String> baudRateList;
    private List<String> checkoutBitList;
    private List<String> dataBitList;
    private List<String> stopBitList;
    private List<String> typeList;
    private ProjectorDao projectorDao;
    private String selectBaudRate;
    private String selectCheckoutBit;
    private String selectDataBit;
    private String selectStopBit;
    private String selectTyep;

    private int selectBaudRateId;
    private int selectCheckoutBitId;
    private int selectDataBitId;
    private int selectStopBitId;
    private int selectTyepId;
    private String chazuojiselect;
    private int chazuojiselectId;
    private String chazuobuselect;
    private int chazuobuselectId;
    private ChazuoDataDao chazuoDataDao;
    private SelectChazuoAdapter chazuoAdapter;


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

        chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        chazuoAdapter = new SelectChazuoAdapter(this, chazuoDataDao.loadAll());
        chazuoProjectorView();
        chazuoBuView();

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


    private void chazuoProjectorView() {
        chazuo_ji.setAdapter(chazuoAdapter);
        chazuo_ji.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chazuojiselect = chazuoDataDao.loadAll().get(position).name;
                chazuojiselectId = position;
                ELog.i("=========jjjjjjj========" +position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void chazuoBuView() {
        chazuo_bu.setAdapter(chazuoAdapter);
        chazuo_bu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chazuobuselect = chazuoDataDao.loadAll().get(position).name;
                chazuobuselectId = position;
                ELog.i("=========bbbbbbbb========" +position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setViewInit(Projector projector) {
        if (projector != null) {
            spinnerBaudRate.setSelection(projector.baudRateId);
            spinnerCheckoutBit.setSelection(projector.checkoutBitId);
            spinnerDataBit.setSelection(projector.dataBitId);
            spinnerStopBit.setSelection(projector.stopBitId);
            spinnerTyep.setSelection(projector.typeId);
            chazuo_ji.setSelection(projector.jiChazuoId);
            chazuo_bu.setSelection(projector.buChazuoId);

            et_open_command.setText(projector.openCommand);
            et_closed_command.setText(projector.closedCommand);
            et_VGA_command.setText(projector.VGACommand);
            et_HDMI_command.setText(projector.HDMICommand);
        } else {
            spinnerBaudRate.setSelection(0);
            spinnerCheckoutBit.setSelection(0);
            spinnerDataBit.setSelection(0);
            spinnerStopBit.setSelection(0);
            spinnerTyep.setSelection(0);
            chazuo_ji.setSelection(0);
            chazuo_bu.setSelection(0);

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
        stopBitList = new ArrayList<>();
        stopBitList.add("1");
        stopBitList.add("1.5");
        stopBitList.add("2");


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
        dataBitList = new ArrayList<>();
        dataBitList.add("8");
        dataBitList.add("7");
        dataBitList.add("6");
        dataBitList.add("5");


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
        checkoutBitList = new ArrayList<>();
        checkoutBitList.add("无");
        checkoutBitList.add("奇");
        checkoutBitList.add("偶");


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
        baudRateList = new ArrayList<>();
        baudRateList.add("2400");
        baudRateList.add("4800");
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
                selectBaudRate = baudRateList.get(position);
                selectBaudRateId = position;
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
        if (chazuojiselectId == chazuobuselectId && chazuojiselectId != 0) {
            Toast.makeText(this, "投影机与幕布插座不能相同", Toast.LENGTH_SHORT).show();
            return;
        }


        int deviceId = 0;
        String name = "";
        String buname = "";
        if (radio_btn_1.isChecked()) {
            deviceId = 1;
            name = "投影机一";
            buname = "投影机一幕布";
        }

        if (radio_btn_2.isChecked()) {
            deviceId = 2;
            name = "投影机二";
            buname = "投影机二幕布";
        }

        if (chazuoDataDao.loadAll().get(chazuojiselectId).bindName != null && !chazuoDataDao.loadAll().get(chazuojiselectId).bindName.equals(name)
                && !chazuoDataDao.loadAll().get(chazuojiselectId).bindName.equals(buname)) {
            Toast.makeText(this, "投影机选择的插座已经被" + chazuoDataDao.loadAll().get(chazuojiselectId).bindName + "使用", Toast.LENGTH_SHORT).show();
            return;
        }

        if (chazuoDataDao.loadAll().get(chazuobuselectId).bindName != null && !chazuoDataDao.loadAll().get(chazuobuselectId).bindName.equals(buname)
                && !chazuoDataDao.loadAll().get(chazuobuselectId).bindName.equals(name)) {
            Toast.makeText(this, "幕布选择的插座已经被" + chazuoDataDao.loadAll().get(chazuobuselectId).bindName + "使用", Toast.LENGTH_SHORT).show();
            return;
        }

        if (projectorDao.load((long) deviceId) != null) {
            int jidaoId = projectorDao.load((long) deviceId).jiChazuoId;
            int budaoId = projectorDao.load((long) deviceId).buChazuoId;
            if (jidaoId != chazuojiselectId || budaoId != chazuobuselectId) {
                Toast.makeText(this, "投影机插座线路有变动，需要重新修改电源箱设置", Toast.LENGTH_SHORT).show();
            }
            if (jidaoId != 0) {
                chazuoDataDao.update(new ChazuoData((long) jidaoId, "插座" + jidaoId, null));
            }
            if (budaoId != 0) {
                chazuoDataDao.update(new ChazuoData((long) budaoId, "插座" + budaoId, null));
            }

            projectorDao.deleteByKey((long) deviceId);
        }

        if (chazuobuselectId != 0) {
            chazuoDataDao.update(new ChazuoData((long) chazuobuselectId, "插座" + chazuobuselectId, buname));
        }
        if (chazuojiselectId != 0) {
            chazuoDataDao.update(new ChazuoData((long) chazuojiselectId, "插座" + chazuojiselectId, name));
        }

        projectorDao.insert(new Projector((long) deviceId, name, selectBaudRate, selectBaudRateId, selectCheckoutBit,
                selectCheckoutBitId, selectDataBit, selectDataBitId, selectStopBit, selectStopBitId, selectTyep,
                selectTyepId, et_open_command.getText().toString(), et_closed_command.getText().toString(),
                et_VGA_command.getText().toString(), et_HDMI_command.getText().toString(),
                chazuojiselect, chazuojiselectId, chazuobuselect, chazuobuselectId));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

        chazuoAdapter.setDatas(chazuoDataDao.loadAll());

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
