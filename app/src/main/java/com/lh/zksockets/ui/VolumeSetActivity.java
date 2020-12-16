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
import com.lh.zksockets.data.DbDao.DangerOutDao;
import com.lh.zksockets.data.model.DangerOut;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;

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

public class VolumeSetActivity extends BaseActivity {

    @BindView(R.id.bjout_1_gl)
    Switch bjout_1_gl;
    @BindView(R.id.bjout_2_gl)
    Switch bjout_2_gl;
    @BindView(R.id.bjout_3_gl)
    Switch bjout_3_gl;
    @BindView(R.id.bjout_4_gl)
    Switch bjout_4_gl;


    @BindView(R.id.danger_et_time_1)
    EditText danger_et_time_1;
    @BindView(R.id.danger_et_time_2)
    EditText danger_et_time_2;
    @BindView(R.id.danger_et_time_3)
    EditText danger_et_time_3;
    @BindView(R.id.danger_et_time_4)
    EditText danger_et_time_4;


    @BindView(R.id.danger_et_devicename_1)
    EditText danger_et_devicename_1;
    @BindView(R.id.danger_et_devicename_2)
    EditText danger_et_devicename_2;
    @BindView(R.id.danger_et_devicename_3)
    EditText danger_et_devicename_3;
    @BindView(R.id.danger_et_devicename_4)
    EditText danger_et_devicename_4;

    private DangerOutDao dangerOutDao;

    private Handler bjoutHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 71:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    Toast.makeText(VolumeSetActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 72:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    initView();
                    Toast.makeText(VolumeSetActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_set);
        ButterKnife.bind(this);


        dangerOutDao = MyApplication.getDaoSession().getDangerOutDao();
        if (dangerOutDao.loadAll().size() == 0) {
            for (int i = 1; i < 5; i++) {
                dangerOutDao.insert(new DangerOut((long) i, "报警输出" + i, "", 1, 10));
            }
        }
        initView();
        ELog.i("========dangerOutDao========" + dangerOutDao.loadAll().toString());

    }

    private void initView() {
        if (dangerOutDao.load((long) 1).dangerOutStatus == 0) {
            bjout_1_gl.setChecked(true);
        } else {
            bjout_1_gl.setChecked(false);
        }
        danger_et_time_1.setText(dangerOutDao.load((long) 1).time + "");
        danger_et_devicename_1.setText(dangerOutDao.load((long) 1).deviceName);

        if (dangerOutDao.load((long) 2).dangerOutStatus == 0) {
            bjout_2_gl.setChecked(true);
        } else {
            bjout_2_gl.setChecked(false);
        }
        danger_et_time_2.setText(dangerOutDao.load((long) 2).time + "");
        danger_et_devicename_2.setText(dangerOutDao.load((long) 2).deviceName);

        if (dangerOutDao.load((long) 3).dangerOutStatus == 0) {
            bjout_3_gl.setChecked(true);
        } else {
            bjout_3_gl.setChecked(false);
        }
        danger_et_time_3.setText(dangerOutDao.load((long) 3).time + "");
        danger_et_devicename_3.setText(dangerOutDao.load((long) 3).deviceName);

        if (dangerOutDao.load((long) 4).dangerOutStatus == 0) {
            bjout_4_gl.setChecked(true);
        } else {
            bjout_4_gl.setChecked(false);
        }
        danger_et_time_4.setText(dangerOutDao.load((long) 4).time + "");
        danger_et_devicename_4.setText(dangerOutDao.load((long) 4).deviceName);
    }


    @OnClick(R.id.btn_bjout_ok)
    public void btn_bjout_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (bjout_1_gl.isChecked()) {
            io1 = 0;
        } else {
            io1 = 1;
        }

        if (bjout_2_gl.isChecked()) {
            io2 = 0;
        } else {
            io2 = 1;
        }

        if (bjout_3_gl.isChecked()) {
            io3 = 0;
        } else {
            io3 = 1;
        }

        if (bjout_4_gl.isChecked()) {
            io4 = 0;
        } else {
            io4 = 1;
        }

        dangerOutDao.update(new DangerOut((long) 1, "报警输出" + 1, danger_et_devicename_1.getText().toString(), io1, Integer.valueOf(danger_et_time_1.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 2, "报警输出" + 2, danger_et_devicename_2.getText().toString(), io2, Integer.valueOf(danger_et_time_2.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 3, "报警输出" + 3, danger_et_devicename_3.getText().toString(), io3, Integer.valueOf(danger_et_time_3.getText().toString())));
        dangerOutDao.update(new DangerOut((long) 4, "报警输出" + 4, danger_et_devicename_4.getText().toString(), io4, Integer.valueOf(danger_et_time_4.getText().toString())));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_bjout_huifu)
    public void btn_bjout_huifu() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_alarm_out_list?ip=" + DisplayTools.getIPAddress(this))
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
                ELog.e("=======报警输出===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========报警输出==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<DangerOut>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<DangerOut>>>>() {
                    }.getType());
                    ELog.e("=========报警输出=数据=======" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        dangerOutDao.update(httpRowHttpData.getData().getRows().get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 72;
                    bjoutHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 71;
                    bjoutHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.btn_bjout_beifen)
    public void btn_bjout_beifen() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("alarm", gson.toJson(dangerOutDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(dangerOutDao.loadAll()));
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_alarm_out_set")
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
                ELog.e("=======报警输出===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========报警输出==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 71;
                    bjoutHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.volume_btn_back)
    public void volume_btn_back() {
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
