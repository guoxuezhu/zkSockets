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
            mLsListsDao.insert(new MLsLists((long) 5, "窗帘1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 6, "窗帘1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 7, "窗帘2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 8, "窗帘2关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 9, "投影机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 10, "投影机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 11, "幕布升", "2-7-0", ""));
            mLsListsDao.insert(new MLsLists((long) 12, "幕布降", "2-8-0", ""));


            mLsListsDao.insert(new MLsLists((long) 13, "灯光开(全开)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 14, "灯光关(全关)", "", ""));
            mLsListsDao.insert(new MLsLists((long) 15, "灯光1开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 16, "灯光1关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 17, "灯光2开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 18, "灯光2关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 19, "灯光3开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 20, "灯光3关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 21, "总音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 22, "总音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 23, "总音量静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 24, "总音量静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 25, "音响音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 26, "音响音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 27, "音响静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 28, "音响静音关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 29, "麦克风音量+", "", ""));
            mLsListsDao.insert(new MLsLists((long) 30, "麦克风音量-", "", ""));
            mLsListsDao.insert(new MLsLists((long) 31, "麦克风静音开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 32, "麦克风静音关", "", ""));


            mLsListsDao.insert(new MLsLists((long) 39, "空调-开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 40, "空调-关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 41, "空调-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 42, "空调-制冷", "", ""));
            mLsListsDao.insert(new MLsLists((long) 43, "空调-制热", "", ""));
            mLsListsDao.insert(new MLsLists((long) 44, "空调-风速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 48, "空调-模式", "", ""));
            mLsListsDao.insert(new MLsLists((long) 49, "空调-摆风", "", ""));


            mLsListsDao.insert(new MLsLists((long) 46, "门禁-前门", "", ""));
            mLsListsDao.insert(new MLsLists((long) 47, "门禁-后门", "", ""));

            mLsListsDao.insert(new MLsLists((long) 54, "开始录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 55, "暂停录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 56, "继续录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 57, "停止录制", "", ""));
            mLsListsDao.insert(new MLsLists((long) 58, "开始直播", "", ""));
            mLsListsDao.insert(new MLsLists((long) 59, "停止直播", "", ""));

            mLsListsDao.insert(new MLsLists((long) 5001, "大屏一体机开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5002, "大屏一体机关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5003, "大屏一体机内置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5004, "大屏一体机外置HDMI", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5005, "电视机1", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5006, "电视机2", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5007, "电视机3", "", ""));
            mLsListsDao.insert(new MLsLists((long) 5008, "电视机4", "", ""));


            mLsListsDao.insert(new MLsLists((long) 60, "新风开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 61, "新风关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 33, "新风-自动", "", ""));
            mLsListsDao.insert(new MLsLists((long) 34, "新风-低速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 35, "新风-中速", "", ""));
            mLsListsDao.insert(new MLsLists((long) 36, "新风-高速", "", ""));


            mLsListsDao.insert(new MLsLists((long) 37, "电源-全开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 38, "电源-全关", "", ""));

            mLsListsDao.insert(new MLsLists((long) 62, "黑板灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 63, "黑板灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 64, "教室灯开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 65, "教室灯关", "", ""));
            mLsListsDao.insert(new MLsLists((long) 66, "场景开", "", ""));
            mLsListsDao.insert(new MLsLists((long) 67, "场景关", "", ""));
        }

    }

    @OnClick(R.id.btn_event_huifu)
    public void btn_event_huifu() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
        progressDialog.setMessage("正在恢复数据");
        progressDialog.setCanceledOnTouchOutside(false);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/get_event_list?ip=" + DisplayTools.getIPAddress(this))
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
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
                .add("event", gson.toJson(mLsListsDao.loadAll()))
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(mLsListsDao.loadAll()));
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_event_set")
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
