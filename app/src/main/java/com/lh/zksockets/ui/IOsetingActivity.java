package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.IoPortDataDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.data.model.IoPortData;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

public class IOsetingActivity extends BaseActivity {


    @BindView(R.id.switch_1_gl)
    Switch switch_1_gl;
    @BindView(R.id.switch_2_gl)
    Switch switch_2_gl;
    @BindView(R.id.switch_3_gl)
    Switch switch_3_gl;
    @BindView(R.id.switch_4_gl)
    Switch switch_4_gl;


    @BindView(R.id.io_et_time_1)
    EditText io_et_time_1;
    @BindView(R.id.io_et_time_2)
    EditText io_et_time_2;
    @BindView(R.id.io_et_time_3)
    EditText io_et_time_3;
    @BindView(R.id.io_et_time_4)
    EditText io_et_time_4;

    @BindView(R.id.io_et_devicename_1)
    EditText io_et_devicename_1;
    @BindView(R.id.io_et_devicename_2)
    EditText io_et_devicename_2;
    @BindView(R.id.io_et_devicename_3)
    EditText io_et_devicename_3;
    @BindView(R.id.io_et_devicename_4)
    EditText io_et_devicename_4;

    private IoPortDataDao ioPortDataDao;
    private Handler ioHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 61:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    Toast.makeText(IOsetingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 62:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    initView();
                    Toast.makeText(IOsetingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioseting);
        ButterKnife.bind(this);


        ioPortDataDao = MyApplication.getDaoSession().getIoPortDataDao();
        if (ioPortDataDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                ioPortDataDao.insert(new IoPortData((long) i, "io输出" + i, "", 0, 10));
            }
        }
        initView();
        ELog.i("========ioPortDataDao========" + ioPortDataDao.loadAll().toString());
    }

    private void initView() {
        if (ioPortDataDao.load((long) 1).ioOutStatus == 1) {
            switch_1_gl.setChecked(true);
        } else {
            switch_1_gl.setChecked(false);
        }
        io_et_time_1.setText(ioPortDataDao.load((long) 1).time + "");
        io_et_devicename_1.setText(ioPortDataDao.load((long) 1).deviceName);

        if (ioPortDataDao.load((long) 2).ioOutStatus == 1) {
            switch_2_gl.setChecked(true);
        } else {
            switch_2_gl.setChecked(false);
        }
        io_et_time_2.setText(ioPortDataDao.load((long) 2).time + "");
        io_et_devicename_2.setText(ioPortDataDao.load((long) 2).deviceName);

        if (ioPortDataDao.load((long) 3).ioOutStatus == 1) {
            switch_3_gl.setChecked(true);
        } else {
            switch_3_gl.setChecked(false);
        }
        io_et_time_3.setText(ioPortDataDao.load((long) 3).time + "");
        io_et_devicename_3.setText(ioPortDataDao.load((long) 3).deviceName);

        if (ioPortDataDao.load((long) 4).ioOutStatus == 1) {
            switch_4_gl.setChecked(true);
        } else {
            switch_4_gl.setChecked(false);
        }
        io_et_time_4.setText(ioPortDataDao.load((long) 4).time + "");
        io_et_devicename_4.setText(ioPortDataDao.load((long) 4).deviceName);
    }


    @OnClick(R.id.btn_ioPort_ok)
    public void btn_ioPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (switch_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (switch_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (switch_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (switch_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        ioPortDataDao.update(new IoPortData((long) 1, "io输出" + 1, io_et_devicename_1.getText().toString(), io1, Integer.valueOf(io_et_time_1.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 2, "io输出" + 2, io_et_devicename_2.getText().toString(), io2, Integer.valueOf(io_et_time_2.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 3, "io输出" + 3, io_et_devicename_3.getText().toString(), io3, Integer.valueOf(io_et_time_3.getText().toString())));
        ioPortDataDao.update(new IoPortData((long) 4, "io输出" + 4, io_et_devicename_4.getText().toString(), io4, Integer.valueOf(io_et_time_4.getText().toString())));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_ioPort_huifu)
    public void btn_ioPort_huifu() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_io_list?ip=" + DisplayTools.getIPAddress(this))
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
                ELog.e("======io输出====数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========io输出==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<IoPortData>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<IoPortData>>>>() {
                    }.getType());
                    ELog.e("=========io输出=数据=======" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        ioPortDataDao.update(httpRowHttpData.getData().getRows().get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 62;
                    ioHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 61;
                    ioHandler.sendMessage(message);
                }
            }
        });
    }

    @OnClick(R.id.btn_ioPort_beifen)
    public void btn_ioPort_beifen() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("io", gson.toJson(ioPortDataDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(ioPortDataDao.loadAll()));
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_io_set")
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
                ELog.e("=======io输出===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========io输出==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 61;
                    ioHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.ioport_back_btn)
    public void ioport_back_btn() {
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
