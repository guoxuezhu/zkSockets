package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.lh.zksockets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddCardDialog extends Dialog {


    @BindView(R.id.et_workNumber)
    EditText et_workNumber;
    @BindView(R.id.et_teacherName)
    EditText et_teacherName;
    @BindView(R.id.et_department)
    EditText et_department;
    @BindView(R.id.et_cardNum)
    EditText et_cardNum;


    private Context mContext;
    private DialogCallBack mDialogCallBack;

    public AddCardDialog(Context context, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_dialog_view);
        ButterKnife.bind(this);
    }

    public interface DialogCallBack {
        void addCradInfo(String workNumber, int icType, String teacherName, String department, String cardNum);
    }

    @OnClick(R.id.dialog_btn_no)
    public void dialog_btn_no() {
        dismiss();
    }

    @OnClick(R.id.dialog_btn_ok)
    public void dialog_btn_ok() {
        mDialogCallBack.addCradInfo(et_workNumber.getText().toString(), 1,
                et_teacherName.getText().toString(), et_department.getText().toString(),
                et_cardNum.getText().toString());
    }

}
