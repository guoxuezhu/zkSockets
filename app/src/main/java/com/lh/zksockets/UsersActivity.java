package com.lh.zksockets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersActivity extends Activity implements AddUserDialog.UserDialogCallBack {

    private AddUserDialog addUserDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.add_user)
    public void add_user() {
        if (addUserDialog == null) {
            addUserDialog = new AddUserDialog(this, this);
        }

        if (addUserDialog != null) {
            addUserDialog.show();
            addUserDialog.setCanceledOnTouchOutside(false);
        }
    }


    @OnClick(R.id.user_btn_back)
    public void user_btn_back() {
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

    @Override
    public void addUserInfo() {

    }
}
