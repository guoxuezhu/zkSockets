package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.lh.zksockets.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeleteDialog extends Dialog {


    private Context mContext;
    private DialogCallBack mDialogCallBack;
    private Long mitemId;

    public DeleteDialog(Context context, DialogCallBack dialogCallBack, Long id) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mDialogCallBack = dialogCallBack;
        mitemId = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_dialog_view);
        ButterKnife.bind(this);
    }

    public interface DialogCallBack {
        void deleteInfo(Long mitemId);
    }

    @OnClick(R.id.delete_dialog_btn_no)
    public void delete_dialog_btn_no() {
        dismiss();
    }

    @OnClick(R.id.delete_dialog_btn_ok)
    public void delete_dialog_btn_ok() {
        mDialogCallBack.deleteInfo(mitemId);
    }

}
