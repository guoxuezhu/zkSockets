package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.LampAdapter;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.utils.AddLampDialog;
import com.lh.zksockets.utils.DisplayTools;
import com.lh.zksockets.utils.ELog;
import com.lh.zksockets.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
    private LuboInfoDao luboInfoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        ButterKnife.bind(this);


        luboInfoDao = MyApplication.getDaoSession().getLuboInfoDao();

        if (luboInfoDao.loadAll().size() == 0) {
            luboInfoDao.insert(new LuboInfo("", "", "", ""));
        }

        initView();

    }

    private void initView() {
        et_lb_ip.setText(luboInfoDao.loadAll().get(0).IP);
        et_lb_user.setText(luboInfoDao.loadAll().get(0).userName);
        et_lb_mima.setText(luboInfoDao.loadAll().get(0).Password);
    }

    @OnClick(R.id.btn_lubo_ok)
    public void btn_lubo_ok() {
        luboInfoDao.deleteAll();
        luboInfoDao.insert(new LuboInfo(et_lb_ip.getText().toString(), et_lb_user.getText().toString(),
                et_lb_mima.getText().toString(), ""));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

        HttpUtil.setLuboTokenTimer();

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
                ELog.e("======录播====数据=======" + responseText);

            }
        });
    }

    @OnClick(R.id.btn_lubo_beifen)
    public void btn_lubo_beifen() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("ip", DisplayTools.getIPAddress(this))
//                .add("title", "录播")
                .add("record_ip", luboInfoDao.loadAll().get(0).IP)
                .add("record_user", luboInfoDao.loadAll().get(0).userName)
                .add("record_pass", luboInfoDao.loadAll().get(0).Password)
                .build();
        ELog.e("==========1111111=ss======" + gson.toJson(luboInfoDao.loadAll()));
        Request request = new Request.Builder()
                .url(MyApplication.BASEURL + "api/edit_record_set")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
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
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    ELog.e("==========数据=======" + jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
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
