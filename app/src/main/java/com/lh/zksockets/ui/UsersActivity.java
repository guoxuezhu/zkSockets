package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.UsersAdapter;
import com.lh.zksockets.data.DbDao.UsersDao;
import com.lh.zksockets.data.model.Users;
import com.lh.zksockets.utils.AddUserDialog;
import com.lh.zksockets.utils.DeleteDialog;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersActivity extends BaseActivity implements AddUserDialog.UserDialogCallBack, DeleteDialog.DialogCallBack, UsersAdapter.CallBack {


    @BindView(R.id.user_recyclerView)
    RecyclerView user_recyclerView;

    private AddUserDialog addUserDialog;
    private UsersDao usersDao;
    private UsersAdapter usersAdapter;
    private DeleteDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        usersDao = MyApplication.getDaoSession().getUsersDao();
        initRcyclerView();
        ELog.i("============usersDao========" + usersDao.loadAll().toString());
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        user_recyclerView.setLayoutManager(manager);
        usersAdapter = new UsersAdapter(this, usersDao.loadAll(), this);
        user_recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void onFixClickItem(Users item) {
        if (addUserDialog == null) {
            ELog.i("===========onFixClickItem====Users========" + item.toString());
            addUserDialog = new AddUserDialog(this, item, this);
        }
        if (addUserDialog != null) {
            addUserDialog.show();
            addUserDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onClickItem(Users item) {
        if (deleteDialog == null) {
            ELog.i("===========onClickItem====Users========" + item.toString());
            deleteDialog = new DeleteDialog(this, this, item.id);
        }
        if (deleteDialog != null) {
            deleteDialog.show();
            deleteDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void deleteInfo(Long mitemId) {
        usersDao.deleteByKey(mitemId);
        closeDialog();
    }

    @Override
    public void userDialogCancel() {
        closeDialog();
    }

    @OnClick(R.id.add_user)
    public void add_user() {
        if (addUserDialog == null) {
            addUserDialog = new AddUserDialog(this, null, this);
        }

        if (addUserDialog != null) {
            addUserDialog.show();
            addUserDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void addUserInfo(String userName, String userPaw, long userid) {
        if (userid == -1) {
            if (usersDao.loadAll().size() != 0) {
                List<Users> users = usersDao.queryBuilder()
                        .where(UsersDao.Properties.Username.eq(userName))
                        .list();
                if (users.size() != 0) {
                    Toast.makeText(this, "此用户已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    usersDao.insert(new Users(null, userName, userPaw, 1, 1, (long) 1, 3, 1));
                    closeDialog();
                }
            } else {
                usersDao.insert(new Users(null, userName, userPaw, 1, 1, (long) 1, 3, 1));
                closeDialog();
            }
        } else {
            usersDao.update(new Users(userid, userName, userPaw, 1, 1, (long) 1, 3, 1));
            closeDialog();
        }
    }

    private void closeDialog() {
        usersAdapter.setData(usersDao.loadAll());
        if (addUserDialog != null) {
            addUserDialog.dismiss();
            addUserDialog = null;
        }
        if (deleteDialog != null) {
            deleteDialog.dismiss();
            deleteDialog = null;
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
