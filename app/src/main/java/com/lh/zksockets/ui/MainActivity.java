package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
