package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.WuangguanInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWangguanDialog extends Dialog {

    @BindView(R.id.add_wg_id)
    EditText add_wg_id;
    @BindView(R.id.add_wg_ip)
    EditText add_wg_ip;
    @BindView(R.id.add_wg_port)
    EditText add_wg_port;

    @BindView(R.id.rbtn_wg_status_ok)
    RadioButton rbtn_wg_status_ok;
    @BindView(R.id.rbtn_wg_status_no)
    RadioButton rbtn_wg_status_no;


    private Context mContext;
    private WuangguanInfo mWgdata;
    private WuangguanDialogCallBack mWuangguanDialogCallBack;


    public AddWangguanDialog(Context context, WuangguanInfo item, WuangguanDialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mWgdata = item;
        mWuangguanDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_wg_dialog_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (mWgdata != null) {
            add_wg_id.setText(mWgdata.wg_id + "");
            add_wg_id.setEnabled(false);
            add_wg_ip.setText(mWgdata.wg_ip);
            add_wg_port.setText(mWgdata.wg_port + "");
            if (mWgdata.wg_status == 1) {
                rbtn_wg_status_ok.setChecked(true);
            } else {
                rbtn_wg_status_no.setChecked(true);
            }
        } else {
            add_wg_id.setText("");
            add_wg_id.setEnabled(true);
            add_wg_ip.setText("");
            add_wg_port.setText("");
            rbtn_wg_status_no.setChecked(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismissDialog();
    }


    public interface WuangguanDialogCallBack {
        void addWangguanInfo(String wgId, String wgIp, String wgPort, int status);

        void addWangguanDialogCancel();
    }

    @OnClick(R.id.dialog_wg_btn_no)
    public void dialog_wg_btn_no() {
        dismissDialog();
    }

    private void dismissDialog() {
        mWuangguanDialogCallBack.addWangguanDialogCancel();
    }

    @OnClick(R.id.dialog_wg_btn_ok)
    public void dialog_wg_btn_ok() {
        if (add_wg_id.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入网关ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (add_wg_ip.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入网关IP", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!DisplayTools.ipCheck(add_wg_ip.getText().toString().trim())) {
            Toast.makeText(mContext, R.string.ip_msg, Toast.LENGTH_SHORT).show();
            return;
        }
        if (add_wg_port.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入网关端口", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rbtn_wg_status_ok.isChecked()) {
            mWuangguanDialogCallBack.addWangguanInfo(add_wg_id.getText().toString(), add_wg_ip.getText().toString(), add_wg_port.getText().toString(), 1);
        } else {
            mWuangguanDialogCallBack.addWangguanInfo(add_wg_id.getText().toString(), add_wg_ip.getText().toString(), add_wg_port.getText().toString(), 0);
        }

    }

}
