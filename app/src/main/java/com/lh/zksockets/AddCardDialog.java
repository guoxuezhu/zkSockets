package com.lh.zksockets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddCardDialog extends Dialog {


    @BindView(R.id.et_workNumber)
    EditText et_workNumber;

    private Context mContext;
    private DialogCallBack mDialogCallBack;

    public AddCardDialog(Context context, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_dialog_view);
        ButterKnife.bind(this);
        getView();
    }

    private void getView() {
        et_workNumber.setText("sdfdsfsd");
    }

    public interface DialogCallBack {
        void addCradInfo();
    }

    @OnClick(R.id.dialog_btn_no)
    public void dialog_btn_no() {
        dismiss();
    }

    @OnClick(R.id.dialog_btn_ok)
    public void dialog_btn_ok() {
        mDialogCallBack.addCradInfo();
    }

}
