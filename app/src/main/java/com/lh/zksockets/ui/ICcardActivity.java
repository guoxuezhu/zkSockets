package com.lh.zksockets.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IcCardAdapter;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.ZkInfoDao;
import com.lh.zksockets.data.model.HttpData;
import com.lh.zksockets.data.model.HttpRow;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.utils.AddCardDialog;
import com.lh.zksockets.utils.DateUtil;
import com.lh.zksockets.utils.DeleteDialog;
import com.lh.zksockets.utils.ELog;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ICcardActivity extends BaseActivity implements AddCardDialog.DialogCallBack, DeleteDialog.DialogCallBack, IcCardAdapter.CallBack {

    @BindView(R.id.ic_recyclerView)
    RecyclerView ic_recyclerView;

    @BindView(R.id.et_work_search)
    EditText et_work_search;

    private AddCardDialog addCardDialog;
    private DeleteDialog deleteDialog;
    private IcCardDao icCardDao;
    private IcCardAdapter icCardAdapter;

    private Handler cardHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 151:
                    ELog.e("======Handler=====1====" + msg.obj.toString());
                    icCardAdapter.setData(icCardDao.loadAll());
                    Toast.makeText(ICcardActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    stopDialog();
                    break;
                case 152:
                    ELog.e("======Handler=====2====" + msg.obj.toString());
                    Toast.makeText(ICcardActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_iccard);
        ButterKnife.bind(this);

        icCardDao = MyApplication.getDaoSession().getIcCardDao();
        initRcyclerView();


    }

    private void getIcdata() {
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
                .url(zkInfoDao.loadAll().get(0).ser_ip + "api/get_ic_card_list?zk_ip=" + zkInfoDao.loadAll().get(0).zkip)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                ELog.e("==========onFailure=======" + e.toString());
                if (cardHandler != null) {
                    Message message = new Message();
                    message.obj = "服务器连接失败,请检测网络";
                    message.what = 152;
                    cardHandler.sendMessage(message);
                }
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                ELog.e("=======getIcdata===数据=======" + responseText);
                Gson gson = new Gson();
                HttpData httpData = gson.fromJson(responseText, HttpData.class);
                ELog.e("==========getIcdata==response=====" + httpData.toString());
                if (httpData.flag == 1) {
                    HttpData<HttpRow<List<IcCard>>> httpRowHttpData = gson.fromJson(responseText, new TypeToken<HttpData<HttpRow<List<IcCard>>>>() {
                    }.getType());
                    ELog.i("=========ic=====数据=======" + httpRowHttpData.getData());
                    icCardDao.deleteAll();
                    List<IcCard> icCards = httpRowHttpData.getData().getRows();
                    for (int i = 0; i < icCards.size(); i++) {
                        icCardDao.insert(icCards.get(i));
                    }
                    Message message = new Message();
                    message.obj = "数据恢复成功";
                    message.what = 151;
                    cardHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.obj = "无数据/恢复失败";
                    message.what = 152;
                    cardHandler.sendMessage(message);
                }

            }
        });
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ic_recyclerView.setLayoutManager(manager);
        icCardAdapter = new IcCardAdapter(this, icCardDao.loadAll(), this);
        ic_recyclerView.setAdapter(icCardAdapter);
        ELog.i("===========icCardDao.loadAll()============" + icCardDao.loadAll().toString());
    }

    @OnClick(R.id.btn_search)
    public void btn_search() {
        List<IcCard> icCards = icCardDao.queryBuilder()
                .where(IcCardDao.Properties.WorkNum.eq(et_work_search.getText().toString().trim()))
                .orderAsc(IcCardDao.Properties.CardNumId)
                .list();
        icCardAdapter.setData(icCards);
    }

    @OnClick(R.id.get_card)
    public void get_card() {
        getIcdata();
    }

    @OnClick(R.id.add_card)
    public void add_card() {

        if (addCardDialog == null) {
            addCardDialog = new AddCardDialog(this, this);
        }

        if (addCardDialog != null) {
            addCardDialog.show();
            addCardDialog.setCanceledOnTouchOutside(false);
        }

    }

    @Override
    public void addCradInfo(String workNumber, int icType, String teacherName, String department, String cardNum) {
        if (icCardDao.loadAll().size() != 0) {
            List<IcCard> icCards = icCardDao.queryBuilder()
                    .where(IcCardDao.Properties.Card_no.eq(cardNum))
                    .orderAsc(IcCardDao.Properties.CardNumId)
                    .list();
            if (icCards.size() != 0) {
                Toast.makeText(this, "卡号已经存在", Toast.LENGTH_SHORT).show();
            } else {
                icCardDao.insert(new IcCard(workNumber, teacherName, cardNum, Long.parseLong(cardNum), 1, DateUtil.getNow(), "on"));
                closeDialog();
            }
        } else {
            icCardDao.insert(new IcCard(workNumber, teacherName, cardNum, Long.parseLong(cardNum), 1, DateUtil.getNow(), "on"));
            closeDialog();
        }
    }

    private void closeDialog() {
        icCardAdapter.setData(icCardDao.loadAll());
        if (addCardDialog != null) {
            addCardDialog.dismiss();
            addCardDialog = null;
        }
        if (deleteDialog != null) {
            deleteDialog.dismiss();
            deleteDialog = null;
        }
    }

    @Override
    public void onClickItem(IcCard item) {
        ELog.i("===========item============" + item.toString());
        if (deleteDialog == null) {
            deleteDialog = new DeleteDialog(this, this, item);
        }
        if (deleteDialog != null) {
            deleteDialog.show();
            deleteDialog.setCanceledOnTouchOutside(false);
        }

    }

    @Override
    public void deleteInfo(IcCard item) {
        icCardDao.deleteByKey(item.cardNumId);
        closeDialog();
    }

    @OnClick(R.id.ic_btn_back)
    public void ic_btn_back() {
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
