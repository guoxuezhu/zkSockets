package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.LampAdapter;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.DbDao.LuboInfoDao;
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.data.model.LuboInfo;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.utils.AddLampDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
