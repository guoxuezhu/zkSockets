package com.lh.zksockets.ui;

import android.app.ProgressDialog;
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
import com.lh.zksockets.data.DbDao.IOYuanDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.IOYuan;
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

public class IOsetActivity extends BaseActivity {

    @BindView(R.id.danger_1_gl)
    Switch danger_1_gl;
    @BindView(R.id.danger_2_gl)
    Switch danger_2_gl;
    @BindView(R.id.danger_3_gl)
    Switch danger_3_gl;
    @BindView(R.id.danger_4_gl)
    Switch danger_4_gl;

    @BindView(R.id.bj_event_ok_1)
    EditText bj_event_ok_1;
    @BindView(R.id.bj_event_ok_2)
    EditText bj_event_ok_2;
    @BindView(R.id.bj_event_ok_3)
    EditText bj_event_ok_3;
    @BindView(R.id.bj_event_ok_4)
    EditText bj_event_ok_4;

    @BindView(R.id.bj_event_no_1)
    EditText bj_event_no_1;
    @BindView(R.id.bj_event_no_2)
    EditText bj_event_no_2;
    @BindView(R.id.bj_event_no_3)
    EditText bj_event_no_3;
    @BindView(R.id.bj_event_no_4)
    EditText bj_event_no_4;

    @BindView(R.id.bj_devicename_1)
    EditText bj_devicename_1;
    @BindView(R.id.bj_devicename_2)
    EditText bj_devicename_2;
    @BindView(R.id.bj_devicename_3)
    EditText bj_devicename_3;
    @BindView(R.id.bj_devicename_4)
    EditText bj_devicename_4;

    private IOYuanDao ioYuanDao;

    private Handler bjHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 51:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    Toast.makeText(IOsetActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
                case 52:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    initView();
                    Toast.makeText(IOsetActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
            }

        }
    };
    private ProgressDialog progressDialog;

    private void stopDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioset);
        ButterKnife.bind(this);

        ioYuanDao = MyApplication.getDaoSession().getIOYuanDao();
        if (ioYuanDao.loadAll().size() == 0) {
            ioYuanDao.insert(new IOYuan((long) 1, "报警1", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 2, "报警2", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 3, "报警3", "", 0, "", ""));
            ioYuanDao.insert(new IOYuan((long) 4, "报警4", "", 0, "", ""));
        }

        initView();
    }

    private void initView() {
        if (ioYuanDao.load((long) 1).dangerIoStatus == 1) {
            danger_1_gl.setChecked(true);
        } else {
            danger_1_gl.setChecked(false);
        }
        bj_event_ok_1.setText(ioYuanDao.load((long) 1).dangerMl);
        bj_event_no_1.setText(ioYuanDao.load((long) 1).noDangerMl);
        bj_devicename_1.setText(ioYuanDao.load((long) 1).deviceName);

        if (ioYuanDao.load((long) 2).dangerIoStatus == 1) {
            danger_2_gl.setChecked(true);
        } else {
            danger_2_gl.setChecked(false);
        }
        bj_event_ok_2.setText(ioYuanDao.load((long) 2).dangerMl);
        bj_event_no_2.setText(ioYuanDao.load((long) 2).noDangerMl);
        bj_devicename_2.setText(ioYuanDao.load((long) 2).deviceName);

        if (ioYuanDao.load((long) 3).dangerIoStatus == 1) {
            danger_3_gl.setChecked(true);
        } else {
            danger_3_gl.setChecked(false);
        }
        bj_event_ok_3.setText(ioYuanDao.load((long) 3).dangerMl);
        bj_event_no_3.setText(ioYuanDao.load((long) 3).noDangerMl);
        bj_devicename_3.setText(ioYuanDao.load((long) 3).deviceName);

        if (ioYuanDao.load((long) 4).dangerIoStatus == 1) {
            danger_4_gl.setChecked(true);
        } else {
            danger_4_gl.setChecked(false);
        }
        bj_event_ok_4.setText(ioYuanDao.load((long) 4).dangerMl);
        bj_event_no_4.setText(ioYuanDao.load((long) 4).noDangerMl);
        bj_devicename_4.setText(ioYuanDao.load((long) 4).deviceName);

    }


    @OnClick(R.id.btn_dangerIoPort_ok)
    public void btn_dangerIoPort_ok() {
        int io1;
        int io2;
        int io3;
        int io4;

        if (danger_1_gl.isChecked()) {
            io1 = 1;
        } else {
            io1 = 0;
        }

        if (danger_2_gl.isChecked()) {
            io2 = 1;
        } else {
            io2 = 0;
        }

        if (danger_3_gl.isChecked()) {
            io3 = 1;
        } else {
            io3 = 0;
        }

        if (danger_4_gl.isChecked()) {
            io4 = 1;
        } else {
            io4 = 0;
        }

        ioYuanDao.update(new IOYuan((long) 1, "报警1", bj_devicename_1.getText().toString(), io1, bj_event_ok_1.getText().toString(), bj_event_no_1.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 2, "报警2", bj_devicename_2.getText().toString(), io2, bj_event_ok_2.getText().toString(), bj_event_no_2.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 3, "报警3", bj_devicename_3.getText().toString(), io3, bj_event_ok_3.getText().toString(), bj_event_no_3.getText().toString()));
        ioYuanDao.update(new IOYuan((long) 4, "报警4", bj_devicename_4.getText().toString(), io4, bj_event_ok_4.getText().toString(), bj_event_no_4.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_dangerIoPort_huifu)
    public void btn_dangerIoPort_huifu() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            Toast.makeText(this, "请先设置基本信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
        progressDialog.setMessage("正在恢复数据");
        progressDialog.setCanceledOnTouchOutside(false);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/get_alarm_list?ip=" + zkInfoDao.loadAll().get(0).zkip)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
                if (bjHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 51;
                    bjHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("======报警====数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========报警==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<IOYuan>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<IOYuan>>>>() {
                    }.getType());
                    ELog.e("=========报警=数据=======" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        ioYuanDao.update(httpRowHttpData.getData().getRows().get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 52;
                    bjHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 51;
                    bjHandler.sendMessage(message);
                }
            }
        });
    }

    @OnClick(R.id.btn_dangerIoPort_beifen)
    public void btn_dangerIoPort_beifen() {
        ZkInfoDao zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();
        if (zkInfoDao.loadAll().size() == 0) {
            Toast.makeText(this, "请先设置基本信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
        progressDialog.setMessage("正在备份数据");
        progressDialog.setCanceledOnTouchOutside(false);
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", zkInfoDao.loadAll().get(0).zkip)
                .add("alarm", gson.toJson(ioYuanDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(ioYuanDao.loadAll()));
        //{'Content-Type': 'application/x-www-form-urlencoded'}
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/edit_alarm_set")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
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
                if (bjHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 51;
                    bjHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========报警==数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========报警==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 51;
                    bjHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.io_btn_back)
    public void io_btn_back() {
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
