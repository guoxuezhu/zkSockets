package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.R;
import com.lh.zksockets.utils.AddLampDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LampActivity extends Activity implements AddLampDialog.DialogCallBack{

    @BindView(R.id.lamp_recyclerView)
    RecyclerView lamp_recyclerView;

    private AddLampDialog addLampDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.add_lamp)
    public void add_lamp() {

        if (addLampDialog == null) {
            addLampDialog = new AddLampDialog(this, this);
        }

        if (addLampDialog != null) {
            addLampDialog.show();
            addLampDialog.setCanceledOnTouchOutside(false);
        }

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
