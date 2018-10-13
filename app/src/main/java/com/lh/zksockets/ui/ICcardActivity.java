package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.IcCardAdapter;
import com.lh.zksockets.data.DbDao.IcCardDao;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.utils.AddCardDialog;
import com.lh.zksockets.utils.DateUtil;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ICcardActivity extends Activity implements AddCardDialog.DialogCallBack, IcCardAdapter.CallBack {

    @BindView(R.id.ic_recyclerView)
    RecyclerView ic_recyclerView;

    private AddCardDialog addCardDialog;
    private IcCardDao icCardDao;
    private IcCardAdapter icCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iccard);
        ButterKnife.bind(this);

        icCardDao = MyApplication.getDaoSession().getIcCardDao();
        initRcyclerView();

    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ic_recyclerView.setLayoutManager(manager);
        icCardAdapter = new IcCardAdapter(this, icCardDao.loadAll(), this);
        ic_recyclerView.setAdapter(icCardAdapter);
    }

    @OnClick(R.id.add_card)
    public void add_card() {

        if (addCardDialog == null) {
            addCardDialog = new AddCardDialog(this, this);
        }

        if (addCardDialog != null) {
            addCardDialog.show();
            addCardDialog.setCanceledOnTouchOutside(false);
        }

    }

    @Override
    public void addCradInfo(String workNumber, int icType, String teacherName, String department, String cardNum) {
        if (icCardDao.loadAll().size() != 0) {
            List<IcCard> icCards = icCardDao.queryBuilder()
                    .where(IcCardDao.Properties.CardNum.eq(cardNum))
                    .orderAsc(IcCardDao.Properties.TerName)
                    .list();
            if (icCards.size() != 0) {
                Toast.makeText(this, "卡号已经存在", Toast.LENGTH_SHORT).show();
            } else {
                icCardDao.insert(new IcCard(workNumber, icType, teacherName, department, Long.parseLong(cardNum), DateUtil.getNow()));
                closeDialog();
            }
        } else {
            icCardDao.insert(new IcCard(workNumber, icType, teacherName, department, Long.parseLong(cardNum), DateUtil.getNow()));
            closeDialog();
        }
    }

    private void closeDialog() {
        icCardAdapter.setData(icCardDao.loadAll());
        if (addCardDialog != null) {
            addCardDialog.dismiss();
            addCardDialog = null;
        }
    }

    @Override
    public void onClickItem(IcCard item) {
        ELog.i("===========item============" + item.toString());
        icCardDao.deleteByKey(item.cardNum);
        closeDialog();
    }

    @OnClick(R.id.ic_btn_back)
    public void ic_btn_back() {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
