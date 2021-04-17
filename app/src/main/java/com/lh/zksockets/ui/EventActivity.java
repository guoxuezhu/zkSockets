package com.lh.zksockets.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.MLsListsDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.MLsLists;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.ZksDatasUtil;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EventActivity extends BaseActivity {


    private MLsListsDao mLsListsDao;

    private Handler eventHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 81:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    Toast.makeText(EventActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
                case 82:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    Toast.makeText(EventActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        mLsListsDao = MyApplication.getDaoSession().getMLsListsDao();
        ZksDatasUtil.getEventDatas(mLsListsDao);
    }

    @OnClick(R.id.btn_event_huifu)
    public void btn_event_huifu() {
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
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/get_event_list?ip=" + zkInfoDao.loadAll().get(0).zkip)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
                if (eventHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 81;
                    eventHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========事件==数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========事件==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<MLsLists>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<MLsLists>>>>() {
                    }.getType());
                    ELog.e("=========事件=数据==get=====" + httpRowHttpData);
                    for (int i = 0; i < httpRowHttpData.getData().getRows().size(); i++) {
                        mLsListsDao.update(httpRowHttpData.getData().getRows().get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 82;
                    eventHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 81;
                    eventHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.btn_event_beifen)
    public void btn_event_beifen() {
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
                .add("event", gson.toJson(mLsListsDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(mLsListsDao.loadAll()));
        Request request = new Request.Builder()
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/edit_event_set")
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
                if (eventHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 81;
                    eventHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("========事件==数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========事件==post=====" + httpData.toString());

                if (httpData.flag == 1) {
                    Message message = new Message();
                    message.obj = httpData.msg;
                    message.what = 81;
                    eventHandler.sendMessage(message);
                }
            }
        });
    }


    @OnClick(R.id.button_event_tyj)
    public void button_event_tyj() {
        startActivity(new Intent(this, TyjEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_cl)
    public void button_event_cl() {
        startActivity(new Intent(this, ChuanglianActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_dg)
    public void button_event_dg() {
        startActivity(new Intent(this, DengguangActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_pc)
    public void button_event_pc() {
        startActivity(new Intent(this, PcEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_door)
    public void button_event_door() {
        startActivity(new Intent(this, DoorEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_kt)
    public void button_event_kt() {
        startActivity(new Intent(this, KongtiaoEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_lubo)
    public void button_event_lubo() {
        startActivity(new Intent(this, LuboEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_wgkzq)
    public void button_event_wgkzq() {
        startActivity(new Intent(this, WangguanEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_yp)
    public void button_event_yp() {
        startActivity(new Intent(this, YinpinEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_power)
    public void button_event_power() {
        startActivity(new Intent(this, PowerEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_xinfeng)
    public void button_event_xinfeng() {
        startActivity(new Intent(this, XinfengEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_onekey)
    public void button_event_onekey() {
        startActivity(new Intent(this, OnekeyEventActivity.class));
        finish();
    }

    @OnClick(R.id.button_event_dianfs)
    public void button_event_dianfs() {
        startActivity(new Intent(this, DianfsEventActivity.class));
        finish();
    }

//
//    @OnClick(R.id.event_btn_back)
//    public void event_btn_back() {
//        back();
//    }

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
