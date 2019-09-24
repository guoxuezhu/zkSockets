package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.SerialCommand;
import com.lh.zksockets.data.model.ZkInfo;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;

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

public class BaseSetingActivity extends BaseActivity {

    @BindView(R.id.et_classRoom)
    EditText et_classRoom;
    @BindView(R.id.tv_IP)
    TextView tv_IP;
    @BindView(R.id.tv_zkVersionName)
    TextView tv_zkVersionName;
    @BindView(R.id.tv_zkDeviceName)
    TextView tv_zkDeviceName;
    @BindView(R.id.et_vid_num)
    EditText et_vid_num;

    @BindView(R.id.rbtn_mqtt_ok)
    RadioButton rbtn_mqtt_ok;
    @BindView(R.id.rbtn_mqtt_no)
    RadioButton rbtn_mqtt_no;

    private ZkInfoDao zkInfoDao;
    private String uuid;


    private Handler baseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 21:
                    ELog.e("======baseHandler=====21====" + msg.obj.toString());
                    Toast.makeText(BaseSetingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 22:
                    ELog.e("======baseHandler=====22====" + msg.obj.toString());
                    initView();
                    Toast.makeText(BaseSetingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_seting);
        ButterKnife.bind(this);

        tv_IP.setText(DisplayTools.getIPAddress(this));
        tv_zkVersionName.setText(DisplayTools.getVersionName(this));
        tv_zkDeviceName.setText("" + MyApplication.geendaoVersion());

        zkInfoDao = MyApplication.getDaoSession().getZkInfoDao();

        initView();

    }

    private void initView() {
        if (zkInfoDao.loadAll().size() == 0) {
            et_classRoom.setText("");
            et_vid_num.setText("0");
            uuid = java.util.UUID.randomUUID().toString();
            rbtn_mqtt_no.setChecked(true);
        } else {
            et_classRoom.setText(zkInfoDao.loadAll().get(0).zkname);
            et_vid_num.setText(zkInfoDao.loadAll().get(0).hudongVIDnum + "");
            uuid = zkInfoDao.loadAll().get(0).uuid;
            if (zkInfoDao.loadAll().get(0).ismqttStart == 1) {
                rbtn_mqtt_ok.setChecked(true);
            } else {
                rbtn_mqtt_no.setChecked(true);
            }
            ELog.e("=======zkInfoDao====" + zkInfoDao.loadAll().get(0).toString());
        }
    }

    @OnClick(R.id.btn_baseset_ok)
    public void btn_baseset_ok() {
        if (et_classRoom.getText().toString().equals("")) {
            Toast.makeText(this, "请输入设备名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_vid_num.getText().toString().equals("")) {
            Toast.makeText(this, "请输入显示屏个数", Toast.LENGTH_SHORT).show();
            return;
        }
        zkInfoDao.deleteAll();
        if (rbtn_mqtt_ok.isChecked()) {
            zkInfoDao.insert(new ZkInfo(et_classRoom.getText().toString(), tv_IP.getText().toString(), tv_zkVersionName.getText().toString(),
                    tv_zkDeviceName.getText().toString(), Integer.valueOf(et_vid_num.getText().toString()), uuid, 1));
        } else {
            zkInfoDao.insert(new ZkInfo(et_classRoom.getText().toString(), tv_IP.getText().toString(), tv_zkVersionName.getText().toString(),
                    tv_zkDeviceName.getText().toString(), Integer.valueOf(et_vid_num.getText().toString()), uuid, 0));
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_baseip_name)
    public void btn_baseip_name() {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("name", et_classRoom.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/exist_center_set")
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
                ELog.e("==========数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========数据==11=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 21;
                    baseHandler.sendMessage(message);
                }
            }
        });


    }

    @OnClick(R.id.btn_baseset_tongbu)
    public void btn_baseset_tongbu() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_center_list?ip=" + DisplayTools.getIPAddress(this))
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
                ELog.e("=======中控===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========中控==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<ZkInfo>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<ZkInfo>>>>() {
                    }.getType());
                    ELog.e("========中控=httpRow=数据=======" + httpRowHttpData);
                    zkInfoDao.deleteAll();
                    zkInfoDao.insert(new ZkInfo(httpRowHttpData.getData().getRows().get(0).zkname,
                            httpRowHttpData.getData().getRows().get(0).zkip,
                            httpRowHttpData.getData().getRows().get(0).zkVersion,
                            httpRowHttpData.getData().getRows().get(0).geendaoVersion,
                            httpRowHttpData.getData().getRows().get(0).hudongVIDnum,
                            uuid, httpRowHttpData.getData().getRows().get(0).ismqttStart));

                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 22;
                    baseHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 21;
                    baseHandler.sendMessage(message);
                }
            }
        });

    }


    @OnClick(R.id.btn_baseset_beifen)
    public void btn_baseset_beifen() {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("title", zkInfoDao.loadAll().get(0).zkname)
                .add("version", zkInfoDao.loadAll().get(0).zkVersion)
                .add("data_version", zkInfoDao.loadAll().get(0).geendaoVersion)
                .add("video_num", zkInfoDao.loadAll().get(0).hudongVIDnum + "")
                .add("show", zkInfoDao.loadAll().get(0).ismqttStart == 1 ? "on" : "off")
                .build();


        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_center_set")
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
                ELog.e("==========数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========数据==11=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 21;
                    baseHandler.sendMessage(message);
                }
            }
        });
    }

    @OnClick(R.id.baseSet_btn_back)
    public void baseSet_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    private void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
