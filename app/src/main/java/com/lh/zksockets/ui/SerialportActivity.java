package com.lh.zksockets.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.SelectAdapter;
import com.lh.zksockets.adapter.SerialportAdapter;
import com.lh.zksockets.data.DbDao.ProjectorDao;
import com.lh.zksockets.data.DbDao.SerialCommandDao;
import com.lh.zksockets.data.DbDao.SerialPortDataDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpResult;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.SerialGetResult;
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.data.model.SerialResult;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;
import com.lh.zksockets.utils.SerialPortUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private int spt_btn_port = 1;

    private Handler skHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 91:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    Toast.makeText(SerialportActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 92:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    ViewInit(spt_btn_port);
                    Toast.makeText(SerialportActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
            }

        }
    };



    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialport);
        ButterKnife.bind(this);

        spt_btn_1.setChecked(true);

        baudRateInitView();
        checkoutBitInitView();
        dataBitInitView();
        stopBitInitView();


        serialPortDataDao = MyApplication.getDaoSession().getSerialPortDataDao();
        serialCommandDao = MyApplication.getDaoSession().getSerialCommandDao();
        if (serialPortDataDao.loadAll().size() < 4) {
            for (int i = 1; i < 9; i++) {
                serialPortDataDao.insert(new SerialPortData((long) i, "串口" + i, "", 3,
                        "9600", 0, "NONE", 0, "8", 0, "1", 10));
                for (int j = 1; j < 31; j++) {
                    if (j >= 10) {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "" + j), i, j, "1-" + i + "" + j, "", "", 10));
                    } else {
                        serialCommandDao.insert(new SerialCommand(Long.valueOf(i + "0" + j), i, j, "1-" + i + "0" + j, "", "", 10));
                    }
                }
            }
        }
        ELog.i("=========serialPortDataDao===11=====" + serialPortDataDao.loadAll().toString());
        ELog.i("=========serialCommandDao====11====" + serialCommandDao.loadAll().toString());

        setSelectBtn(1);


    }

    private void stopDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void ViewInit(long i) {
        et_device_name.setText(serialPortDataDao.load(i).deviceName);
        spinnerBaudRate.setSelection(serialPortDataDao.load(i).baudRateId);
        spinnerCheckoutBit.setSelection(serialPortDataDao.load(i).checkoutBitId);
        spinnerDataBit.setSelection(serialPortDataDao.load(i).dataBitId);
        spinnerStopBit.setSelection(serialPortDataDao.load(i).stopBitId);

        if (serialPortDataDao.load(i).jinZhi == 10) {
            radio_binary_1.setChecked(true);
        } else {
            radio_binary_2.setChecked(true);
        }


        serialCommands = serialCommandDao.queryBuilder()
                .where(SerialCommandDao.Properties.SId.eq(i))
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
        setSelectBtn(1);
    }

    @OnClick(R.id.spt_btn_2)
    public void spt_btn_2() {
        setSelectBtn(2);
    }

    @OnClick(R.id.spt_btn_3)
    public void spt_btn_3() {
        setSelectBtn(3);
    }

    @OnClick(R.id.spt_btn_4)
    public void spt_btn_4() {
        setSelectBtn(4);
    }

    @OnClick(R.id.spt_btn_5)
    public void spt_btn_5() {
        setSelectBtn(5);
    }

    @OnClick(R.id.spt_btn_6)
    public void spt_btn_6() {
        setSelectBtn(6);
    }

    @OnClick(R.id.spt_btn_7)
    public void spt_btn_7() {
        setSelectBtn(7);
    }

    @OnClick(R.id.spt_btn_8)
    public void spt_btn_8() {
        setSelectBtn(8);
    }

    private void setSelectBtn(int i) {
        spt_btn_port = i;
        ViewInit(i);

    }


    @OnClick(R.id.sport_btn_ok)
    public void sport_btn_ok() {
        int jinzhi = 0;
        if (radio_binary_1.isChecked()) {
            jinzhi = 10;
        } else {
            jinzhi = 16;
        }

        serialPortDataDao.update(new SerialPortData((long) spt_btn_port, "串口" + spt_btn_port,
                et_device_name.getText().toString(), selectBaudRateId, selectBaudRate,
                selectCheckoutBitId, selectCheckoutBit, selectDataBitId, selectDataBit,
                selectStopBitId, selectStopBit, jinzhi));

        for (int j = 0; j < serialCommands.size(); j++) {
            serialCommands.get(j).setJinZhi(jinzhi);
            serialCommandDao.update(serialCommands.get(j));
        }

        //String spStr = selectBaudRate + "," + selectCheckoutBit + "," + selectDataBit + "," + selectStopBit;
        String spStr = selectBaudRate + ",n,8,1";
        String msg = "{[COM" + (spt_btn_port - 1) + ":ST:A0" + spStr.length() + "]<" + spStr + ">}";
        ELog.i("========sport_btn_ok=====" + msg);
        byte[] data = msg.getBytes();
        SerialPortUtil.sendMsg(data);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_sport_huifu)
    public void btn_sport_huifu() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
        progressDialog.setMessage("正在恢复数据");
        progressDialog.setCanceledOnTouchOutside(false);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_serial_list?ip=" + DisplayTools.getIPAddress(this))
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======串口====数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========报警==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<SerialGetResult>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<SerialGetResult>>>>() {
                    }.getType());
                    ELog.e("=========串口=数据===get====" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        serialPortDataDao.update(new SerialPortData(httpRowHttpData.getData().getRows().get(i).id,
                                "串口" + i,
                                httpRowHttpData.getData().getRows().get(i).deviceName,
                                httpRowHttpData.getData().getRows().get(i).baudRateId,
                                httpRowHttpData.getData().getRows().get(i).baudRate,
                                httpRowHttpData.getData().getRows().get(i).checkoutBitId,
                                httpRowHttpData.getData().getRows().get(i).checkoutBit,
                                httpRowHttpData.getData().getRows().get(i).dataBitId,
                                httpRowHttpData.getData().getRows().get(i).dataBit,
                                httpRowHttpData.getData().getRows().get(i).stopBitId,
                                httpRowHttpData.getData().getRows().get(i).stopBit,
                                httpRowHttpData.getData().getRows().get(i).jinZhi));

                        for (int j = 0; j < 30; j++) {
                            try {
                                serialCommandDao.update(httpRowHttpData.getData().getRows().get(i).command.get(j));
                            } catch (Exception e) {
                                ELog.e("=======serialCommandDao==e====" + j + "=====" + httpRowHttpData.getData().getRows().get(i).command.get(j));
                            }
                        }
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 92;
                    skHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 91;
                    skHandler.sendMessage(message);
                }

            }
        });
    }

    @OnClick(R.id.btn_sport_beifen)
    public void btn_sport_beifen() {
        List<SerialResult> serialResults = new ArrayList<SerialResult>();

        for (int n = 1; n < 9; n++) {
            List<SerialCommand> serialCommands = serialCommandDao.queryBuilder()
                    .where(SerialCommandDao.Properties.SId.eq(n))
                    .orderAsc(SerialCommandDao.Properties.MlId)
                    .list();
            serialResults.add(new SerialResult(serialPortDataDao.load(Long.valueOf(n)), serialCommands));
        }
        Gson gson = new Gson();
        ELog.e("==========1111111=ss======" + gson.toJson(serialResults));

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("serial", gson.toJson(serialResults))
                .build();

        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_serial_set")
                .post(requestBody)
                .build();


        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======串口====数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========串口==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 91;
                    skHandler.sendMessage(message);
                }
            }
        });
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
