package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.zksockets.AddCardDialog;
import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ICcardActivity extends Activity implements AddCardDialog.DialogCallBack {

    private AddCardDialog addCardDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iccard);
        ButterKnife.bind(this);
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
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addCradInfo() {

    }
}
