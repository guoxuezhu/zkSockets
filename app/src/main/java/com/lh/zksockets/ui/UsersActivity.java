package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IcCardAdapter;
import com.lh.zksockets.adapter.UsersAdapter;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.utils.AddUserDialog;
import com.lh.zksockets.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersActivity extends Activity implements AddUserDialog.UserDialogCallBack, UsersAdapter.CallBack {


    @BindView(R.id.user_recyclerView)
    RecyclerView user_recyclerView;

    private AddUserDialog addUserDialog;
    private UsersDao usersDao;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        usersDao = MyApplication.getDaoSession().getUsersDao();
        initRcyclerView();
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        user_recyclerView.setLayoutManager(manager);
        usersAdapter = new UsersAdapter(this, usersDao.loadAll(), this);
        user_recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void onClickItem(Users item) {
        Log.i("lhlog", "=====item====Users========" + item.toString());
        usersDao.deleteByKey(item.id);
        closeDialog();
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

    @Override
    public void addUserInfo(String userName, String userPaw, int userType) {
        if (usersDao.loadAll().size() != 0) {
            List<Users> users = usersDao.queryBuilder()
                    .where(UsersDao.Properties.Username.eq(userName))
                    .list();
            if (users.size() != 0) {
                Toast.makeText(this, "此用户已经存在", Toast.LENGTH_SHORT).show();
            } else {
                usersDao.insert(new Users(null, userName, userPaw, userType));
                closeDialog();
            }
        } else {
            usersDao.insert(new Users(null, userName, userPaw, userType));
            closeDialog();
        }
    }

    private void closeDialog() {
        usersAdapter.setData(usersDao.loadAll());
        if (addUserDialog != null) {
            addUserDialog.dismiss();
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


}
