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
import com.lh.zksockets.data.DbDao.JDQstatusDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.JDQstatus;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.ZkInfo;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;

import org.json.JSONArray;
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

public class EnvironmentalActivity extends BaseActivity {

    @BindView(R.id.jdq_1_gl)
    Switch jdq_1_gl;
    @BindView(R.id.jdq_2_gl)
    Switch jdq_2_gl;
    @BindView(R.id.jdq_3_gl)
    Switch jdq_3_gl;
    @BindView(R.id.jdq_4_gl)
    Switch jdq_4_gl;
    @BindView(R.id.jdq_5_gl)
    Switch jdq_5_gl;
    @BindView(R.id.jdq_6_gl)
    Switch jdq_6_gl;
    @BindView(R.id.jdq_7_gl)
    Switch jdq_7_gl;
    @BindView(R.id.jdq_8_gl)
    Switch jdq_8_gl;

    @BindView(R.id.jdq_et_time_1)
    EditText jdq_et_time_1;
    @BindView(R.id.jdq_et_time_2)
    EditText jdq_et_time_2;
    @BindView(R.id.jdq_et_time_3)
    EditText jdq_et_time_3;
    @BindView(R.id.jdq_et_time_4)
    EditText jdq_et_time_4;
    @BindView(R.id.jdq_et_time_5)
    EditText jdq_et_time_5;
    @BindView(R.id.jdq_et_time_6)
    EditText jdq_et_time_6;
    @BindView(R.id.jdq_et_time_7)
    EditText jdq_et_time_7;
    @BindView(R.id.jdq_et_time_8)
    EditText jdq_et_time_8;

    @BindView(R.id.jdq_et_devicename_1)
    EditText jdq_et_devicename_1;
    @BindView(R.id.jdq_et_devicename_2)
    EditText jdq_et_devicename_2;
    @BindView(R.id.jdq_et_devicename_3)
    EditText jdq_et_devicename_3;
    @BindView(R.id.jdq_et_devicename_4)
    EditText jdq_et_devicename_4;
    @BindView(R.id.jdq_et_devicename_5)
    EditText jdq_et_devicename_5;
    @BindView(R.id.jdq_et_devicename_6)
    EditText jdq_et_devicename_6;
    @BindView(R.id.jdq_et_devicename_7)
    EditText jdq_et_devicename_7;
    @BindView(R.id.jdq_et_devicename_8)
    EditText jdq_et_devicename_8;

    private JDQstatusDao jdqStatusDao;

    private Handler jdqHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 41:
                    ELog.e("======baseHandler=====1====" + msg.obj.toString());
                    Toast.makeText(EnvironmentalActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 42:
                    ELog.e("======baseHandler=====2====" + msg.obj.toString());
                    initView();
                    Toast.makeText(EnvironmentalActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        ButterKnife.bind(this);

        jdqStatusDao = MyApplication.getDaoSession().getJDQstatusDao();
        if (jdqStatusDao.loadAll().size() == 0) {
            for (int i = 1; i < 9; i++) {
                jdqStatusDao.insert(new JDQstatus((long) i, "继电器" + i, "", 1, 1));
            }
        }

        initView();
        ELog.d("=========jdqStatusDao==========" + jdqStatusDao.loadAll().toString());
    }

    private void initView() {
        if (jdqStatusDao.load((long) 1).jdqStatus == 0) {
            jdq_1_gl.setChecked(true);
        } else {
            jdq_1_gl.setChecked(false);
        }
        jdq_et_time_1.setText(jdqStatusDao.load((long) 1).time + "");
        jdq_et_devicename_1.setText(jdqStatusDao.load((long) 1).deviceName);

        if (jdqStatusDao.load((long) 2).jdqStatus == 0) {
            jdq_2_gl.setChecked(true);
        } else {
            jdq_2_gl.setChecked(false);
        }
        jdq_et_time_2.setText(jdqStatusDao.load((long) 2).time + "");
        jdq_et_devicename_2.setText(jdqStatusDao.load((long) 2).deviceName);

        if (jdqStatusDao.load((long) 3).jdqStatus == 0) {
            jdq_3_gl.setChecked(true);
        } else {
            jdq_3_gl.setChecked(false);
        }
        jdq_et_time_3.setText(jdqStatusDao.load((long) 3).time + "");
        jdq_et_devicename_3.setText(jdqStatusDao.load((long) 3).deviceName);

        if (jdqStatusDao.load((long) 4).jdqStatus == 0) {
            jdq_4_gl.setChecked(true);
        } else {
            jdq_4_gl.setChecked(false);
        }
        jdq_et_time_4.setText(jdqStatusDao.load((long) 4).time + "");
        jdq_et_devicename_4.setText(jdqStatusDao.load((long) 4).deviceName);

        if (jdqStatusDao.load((long) 5).jdqStatus == 0) {
            jdq_5_gl.setChecked(true);
        } else {
            jdq_5_gl.setChecked(false);
        }
        jdq_et_time_5.setText(jdqStatusDao.load((long) 5).time + "");
        jdq_et_devicename_5.setText(jdqStatusDao.load((long) 5).deviceName);

        if (jdqStatusDao.load((long) 6).jdqStatus == 0) {
            jdq_6_gl.setChecked(true);
        } else {
            jdq_6_gl.setChecked(false);
        }
        jdq_et_time_6.setText(jdqStatusDao.load((long) 6).time + "");
        jdq_et_devicename_6.setText(jdqStatusDao.load((long) 6).deviceName);

        if (jdqStatusDao.load((long) 7).jdqStatus == 0) {
            jdq_7_gl.setChecked(true);
        } else {
            jdq_7_gl.setChecked(false);
        }
        jdq_et_time_7.setText(jdqStatusDao.load((long) 7).time + "");
        jdq_et_devicename_7.setText(jdqStatusDao.load((long) 7).deviceName);

        if (jdqStatusDao.load((long) 8).jdqStatus == 0) {
            jdq_8_gl.setChecked(true);
        } else {
            jdq_8_gl.setChecked(false);
        }
        jdq_et_time_8.setText(jdqStatusDao.load((long) 8).time + "");
        jdq_et_devicename_8.setText(jdqStatusDao.load((long) 8).deviceName);

    }


    @OnClick(R.id.btn_jdqIoPort_ok)
    public void btn_jdqIoPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;
        int io5;
        int io6;
        int io7;
        int io8;

        if (jdq_1_gl.isChecked()) {
            io1 = 0;
        } else {
            io1 = 1;
        }

        if (jdq_2_gl.isChecked()) {
            io2 = 0;
        } else {
            io2 = 1;
        }

        if (jdq_3_gl.isChecked()) {
            io3 = 0;
        } else {
            io3 = 1;
        }

        if (jdq_4_gl.isChecked()) {
            io4 = 0;
        } else {
            io4 = 1;
        }

        if (jdq_5_gl.isChecked()) {
            io5 = 0;
        } else {
            io5 = 1;
        }

        if (jdq_6_gl.isChecked()) {
            io6 = 0;
        } else {
            io6 = 1;
        }

        if (jdq_7_gl.isChecked()) {
            io7 = 1;
        } else {
            io7 = 0;
        }
        if (jdq_8_gl.isChecked()) {
            io8 = 0;
        } else {
            io8 = 1;
        }

        jdqStatusDao.update(new JDQstatus((long) 1, "继电器1", jdq_et_devicename_1.getText().toString(), io1, Integer.valueOf(jdq_et_time_1.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 2, "继电器2", jdq_et_devicename_2.getText().toString(), io2, Integer.valueOf(jdq_et_time_2.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 3, "继电器3", jdq_et_devicename_3.getText().toString(), io3, Integer.valueOf(jdq_et_time_3.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 4, "继电器4", jdq_et_devicename_4.getText().toString(), io4, Integer.valueOf(jdq_et_time_4.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 5, "继电器5", jdq_et_devicename_5.getText().toString(), io5, Integer.valueOf(jdq_et_time_5.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 6, "继电器6", jdq_et_devicename_6.getText().toString(), io6, Integer.valueOf(jdq_et_time_6.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 7, "继电器7", jdq_et_devicename_7.getText().toString(), io7, Integer.valueOf(jdq_et_time_7.getText().toString())));
        jdqStatusDao.update(new JDQstatus((long) 8, "继电器8", jdq_et_devicename_8.getText().toString(), io8, Integer.valueOf(jdq_et_time_8.getText().toString())));


        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_jdqPort_huifu)
    public void btn_jdqPort_huifu() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_relay_list?ip=" + DisplayTools.getIPAddress(this))
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
                ELog.e("==========数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========继电器==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<JDQstatus>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<JDQstatus>>>>() {
                    }.getType());
                    ELog.e("=========继电器=数据=======" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        jdqStatusDao.update(httpRowHttpData.getData().getRows().get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 42;
                    jdqHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 41;
                    jdqHandler.sendMessage(message);
                }
            }
        });


    }

    @OnClick(R.id.btn_jdqPort_beifen)
    public void btn_jdqPort_beifen() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("relay", gson.toJson(jdqStatusDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(jdqStatusDao.loadAll()));
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_relay_set")
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
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========继电器==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 41;
                    jdqHandler.sendMessage(message);
                }
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
