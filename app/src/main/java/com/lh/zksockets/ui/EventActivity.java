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
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;

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
        if (mLsListsDao.loadAll().size() == 0) {
            mLsListsDao.insert(new MLsLists((long) 1, "上课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2, "下课", "", ""));
            mLsListsDao.insert(new MLsLists((long) 45, "开机", "", ""));


            mLsListsDao.insert(new MLsLists((long) 3, "窗帘开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4, "窗帘关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘暂停(全暂停)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1001, "窗帘1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1002, "窗帘1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1003, "窗帘1暂停", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1004, "窗帘2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1005, "窗帘2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 1006, "窗帘2暂停", "", ""));


            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-0", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-0", ""));


            mLsListsDao.insert(new MLsLists((long) 13, "灯光开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3001, "黑板灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3002, "黑板灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3003, "教室灯1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3004, "教室灯1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3005, "教室灯2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3006, "教室灯2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3007, "教室灯3开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3008, "教室灯3关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3009, "教室灯4开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 3010, "教室灯4关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 4101, "音量1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4102, "音量2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4103, "音量3级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4104, "音量4级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4105, "音量5级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4106, "音量6级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4107, "音量7级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4108, "音量8级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4109, "音量9级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4110, "音量10级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4111, "静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 4112, "静音关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2001, "空调-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2002, "空调-制冷", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2003, "空调-制热", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2004, "空调-风速低", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2005, "空调-风速中", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2006, "空调-风速高", "", ""));
            mLsListsDao.insert(new MLsLists((long) 2007, "空调-摆风", "", ""));


            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", "", ""));

            mLsListsDao.insert(new MLsLists((long) 54, "开始录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "暂停录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "继续录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "停止录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "开始直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "停止直播", "", ""));


            mLsListsDao.insert(new MLsLists((long) 5001, "一体机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5002, "一体机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5003, "一体机内置显示", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5004, "一体机外置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5005, "大屏1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5006, "大屏2", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5007, "大屏3", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5008, "大屏4", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5091, "老师大屏一体机信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5092, "其它设备信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5101, "HDMI信号输出", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5102, "OPS信号输出", "", ""));


            mLsListsDao.insert(new MLsLists((long) 6001, "新风开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6002, "新风关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6003, "新风-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6004, "新风-低速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6005, "新风-中速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6006, "新风-高速", "", ""));


            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 7001, "自习模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7002, "投影模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7003, "课件模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7004, "自动模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7005, "模式1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7006, "模式2", "", ""));

            mLsListsDao.insert(new MLsLists((long) 8001, "电风扇开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8002, "电风扇关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8003, "电风扇1级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8004, "电风扇2级", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8005, "电风扇3级", "", ""));

        }

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
