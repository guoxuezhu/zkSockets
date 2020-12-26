package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserDialog extends Dialog {

    @BindView(R.id.et_userName)
    EditText et_userName;
    @BindView(R.id.et_userPaw)
    EditText et_userPaw;
    @BindView(R.id.et_userPaw2)
    EditText et_userPaw2;

    private Context mContext;
    private UserDialogCallBack mUserDialogCallBack;

    public AddUserDialog(Context context, UserDialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mUserDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_dialog_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    private void initView() {
        et_userName.setText("");
        et_userName.setText("");
        et_userName.setText("");
    }

    public interface UserDialogCallBack {
        void addUserInfo(String userName, String userPaw, int userType);
    }

    @OnClick(R.id.dialog_user_btn_no)
    public void dialog_user_btn_no() {
        dismiss();
    }

    @OnClick(R.id.dialog_user_btn_ok)
    public void dialog_user_btn_ok() {
        if (et_userName.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_userPaw.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_userPaw2.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!et_userPaw.getText().toString().trim().equals(et_userPaw2.getText().toString().trim())) {
            Toast.makeText(mContext, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        mUserDialogCallBack.addUserInfo(et_userName.getText().toString(), et_userPaw.getText().toString(), 1);
    }

}
