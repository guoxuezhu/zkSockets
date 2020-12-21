package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;

import java.io.IOException;

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

public class LampActivity extends BaseActivity {

    @BindView(R.id.et_lb_ip)
    EditText et_lb_ip;
    @BindView(R.id.et_lb_user)
    EditText et_lb_user;
    @BindView(R.id.et_lb_mima)
    EditText et_lb_mima;

    @BindView(R.id.rbtn_lb_ok)
    RadioButton rbtn_lb_ok;
    @BindView(R.id.rbtn_lb_no)
    RadioButton rbtn_lb_no;


    private LuboInfoDao luboInfoDao;

    private Handler luboHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 31:
                    ELog.e("======baseHandler=====21====" + msg.obj.toString());
                    Toast.makeText(LampActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 32:
                    ELog.e("======baseHandler=====22====" + msg.obj.toString());
                    initView();
                    Toast.makeText(LampActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    HttpUtil.setLuboTokenTimer();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        ButterKnife.bind(this);


        luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();

        if (luboInfoDao.loadAll().size() == 0) {
            luboInfoDao.insert(new LuboInfo("", "", "", "", 0));
        }

        initView();

    }

    private void initView() {
        et_lb_ip.setText(luboInfoDao.loadAll().get(0).IP);
        et_lb_user.setText(luboInfoDao.loadAll().get(0).userName);
        et_lb_mima.setText(luboInfoDao.loadAll().get(0).Password);
        if (luboInfoDao.loadAll().get(0).status == 1) {
            rbtn_lb_ok.setChecked(true);
        } else {
            rbtn_lb_no.setChecked(true);
        }
    }

    @OnClick(R.id.btn_lubo_ok)
    public void btn_lubo_ok() {
        luboInfoDao.deleteAll();
        if (rbtn_lb_ok.isChecked()) {
            luboInfoDao.insert(new LuboInfo(et_lb_ip.getText().toString(), et_lb_user.getText().toString(),
                    et_lb_mima.getText().toString(), "", 1));
            HttpUtil.setLuboTokenTimer();
        } else {
            luboInfoDao.insert(new LuboInfo(et_lb_ip.getText().toString(), et_lb_user.getText().toString(),
                    et_lb_mima.getText().toString(), "", 0));
        }

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btn_lubo_huifu)
    public void btn_lubo_huifu() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_record_list?ip=" + DisplayTools.getIPAddress(this))
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
                ELog.e("=======录播===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========录播==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    try {
                        HttpData<LuboInfo> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<LuboInfo>>() {
                        }.getType());
                        ELog.e("=========录播=数据=======" + httpRowHttpData);
                        luboInfoDao.deleteAll();
                        luboInfoDao.insert(httpRowHttpData.getData());
                        Message message = new Message();
                        message.obj = "数据恢复成功";
                        message.what = 32;
                        luboHandler.sendMessage(message);
                    } catch (Exception e) {
                        Message message = new Message();
                        message.obj = "数据解析错误";
                        message.what = 31;
                        luboHandler.sendMessage(message);
                    }
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 31;
                    luboHandler.sendMessage(message);
                }

            }
        });
    }

    @OnClick(R.id.btn_lubo_beifen)
    public void btn_lubo_beifen() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("record_ip", luboInfoDao.loadAll().get(0).IP)
                .add("record_user", luboInfoDao.loadAll().get(0).userName)
                .add("record_pass", luboInfoDao.loadAll().get(0).Password)
                .add("status", luboInfoDao.loadAll().get(0).status + "")
                .build();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_record_set")
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
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("=======录播===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("=========录播=数据==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 31;
                    luboHandler.sendMessage(message);
                }
            }
        });
    }

    @OnClick(R.id.lamp_btn_back)
    public void lamp_btn_back() {
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
