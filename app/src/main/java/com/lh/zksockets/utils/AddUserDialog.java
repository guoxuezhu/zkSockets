package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddUserDialog extends Dialog {


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
        getView();
    }

    private void getView() {

    }

    public interface UserDialogCallBack {
        void addUserInfo();
    }

    @OnClick(R.id.dialog_user_btn_no)
    public void dialog_user_btn_no() {
        dismiss();
    }

    @OnClick(R.id.dialog_user_btn_ok)
    public void dialog_user_btn_ok() {
        mUserDialogCallBack.addUserInfo();
    }

}
