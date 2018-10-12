package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.ChazuoDataDao;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.utils.ELog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ChazuoDataDao chazuoDataDao = MyApplication.getDaoSession().getChazuoDataDao();
        if (chazuoDataDao.loadAll().size() == 0) {
            chazuoDataDao.insert(new ChazuoData((long) 0, "无", null, false, 0, 0));
            for (int i = 1; i < 21; i++) {
                chazuoDataDao.insert(new ChazuoData((long) i, "插座" + i, null, false, 0, 0));
            }
        }
        ELog.i("=========chazuoDataDao========" + chazuoDataDao.loadAll().toString());
    }

    @OnClick(R.id.net_btn)
    public void net_btn() {
        startActivity(new Intent(this, NetStatusActivity.class));
        finish();
    }

    @OnClick(R.id.base_set)
    public void base_set() {
        startActivity(new Intent(this, BaseSetingActivity.class));
        finish();
    }

    @OnClick(R.id.sets_btn)
    public void sets_btn() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @OnClick(R.id.user_btn)
    public void user_btn() {
        startActivity(new Intent(this, UsersActivity.class));
        finish();
    }

    @OnClick(R.id.xiangdao)
    public void xiangdao() {
        startActivity(new Intent(this, XiangDaoActivity.class));
        finish();
    }




    @OnClick(R.id.main_btn_back)
    public void main_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    private void back() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
