package com.lh.zksockets.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.Users;

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
    @BindView(R.id.passeord_ts_2)
    TextView passeord_ts_2;
    @BindView(R.id.passeord_ts_1)
    TextView passeord_ts_1;


    private Context mContext;
    private Users mUser;
    private UserDialogCallBack mUserDialogCallBack;
    private Handler addUserDialogHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 45:
                    ELog.e("======Handler=====45====" + msg.obj.toString());
                    passeord_ts_1.setText(msg.obj.toString());
                    break;
                case 46:
                    ELog.e("======Handler=====46====" + msg.obj.toString());
                    passeord_ts_2.setText(msg.obj.toString());
                    break;
            }

        }
    };


    public AddUserDialog(Context context, Users item, UserDialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mUser = item;
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
        dismissDialog();
    }

    private void initView() {
        passeord_ts_1.setText("");
        passeord_ts_2.setText("");
        if (mUser != null) {
            et_userName.setText(mUser.username + "");
            et_userPaw.setText(mUser.userPaw + "");
            et_userPaw2.setText(mUser.userPaw + "");
        } else {
            et_userName.setText("");
            et_userPaw.setText("");
            et_userPaw2.setText("");
        }
        et_userPaw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Message message = new Message();
                message.obj = "";
                if (charSequence.toString().matches("^[0-9]+$")) {
                    //输入的纯数字为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[a-z]+$")) {
                    //输入的纯小写字母为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z]+$")) {
                    //输入的纯大写字母为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z0-9]{1,5}")) {
                    //输入的大写字母和数字，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z0-9]{6,16}")) {
                    //输入的大写字母和数字，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[a-z0-9]{1,5}")) {
                    //输入的小写字母和数字，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[a-z0-9]{6,16}")) {
                    //输入的小写字母和数字，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z]{1,5}")) {
                    //输入的大写字母和小写字母，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Za-z]{6,16}")) {
                    //输入的大写字母和小写字母，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{1,5}")) {
                    //输入的大写字母和小写字母和数字，输入的字符小于5个个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{6,8}")) {
                    //输入的大写字母和小写字母和数字，输入的字符大于6个个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{9,16}")) {
                    //输入的大写字母和小写字母和数字，输入的字符大于8个密码为强
                    message.obj = "复杂";
                } else {
                    ELog.i("==========其它====45=====");
                }
                message.what = 45;
                addUserDialogHandler.sendMessage(message);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_userPaw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Message message = new Message();
                message.obj = "";
                if (charSequence.toString().matches("^[0-9]+$")) {
                    //输入的纯数字为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[a-z]+$")) {
                    //输入的纯小写字母为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z]+$")) {
                    //输入的纯大写字母为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z0-9]{1,5}")) {
                    //输入的大写字母和数字，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Z0-9]{6,16}")) {
                    //输入的大写字母和数字，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[a-z0-9]{1,5}")) {
                    //输入的小写字母和数字，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[a-z0-9]{6,16}")) {
                    //输入的小写字母和数字，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z]{1,5}")) {
                    //输入的大写字母和小写字母，输入的字符小于7个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Za-z]{6,16}")) {
                    //输入的大写字母和小写字母，输入的字符大于7个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{1,5}")) {
                    //输入的大写字母和小写字母和数字，输入的字符小于5个个密码为弱
                    message.obj = "简单";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{6,8}")) {
                    //输入的大写字母和小写字母和数字，输入的字符大于6个个密码为中
                    message.obj = "中级";
                } else if (charSequence.toString().matches("^[A-Za-z0-9]{9,16}")) {
                    //输入的大写字母和小写字母和数字，输入的字符大于8个密码为强
                    message.obj = "复杂";
                } else {
                    ELog.i("==========其它====46=====");
                }
                message.what = 46;
                addUserDialogHandler.sendMessage(message);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public interface UserDialogCallBack {
        void addUserInfo(String userName, String userPaw, Users user);

        void userDialogCancel();
    }

    @OnClick(R.id.dialog_user_btn_no)
    public void dialog_user_btn_no() {
        dismissDialog();
    }

    private void dismissDialog() {
        mUserDialogCallBack.userDialogCancel();
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
        mUserDialogCallBack.addUserInfo(et_userName.getText().toString(), et_userPaw.getText().toString(), mUser);
    }

}
