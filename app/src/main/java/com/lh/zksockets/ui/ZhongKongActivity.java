package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.WangguanAdapter;
import com.lh.zksockets.data.DbDao.WuangguanInfoDao;
import com.lh.zksockets.data.model.WuangguanInfo;
import com.lh.zksockets.utils.AddWangguanDialog;
import com.lh.zksockets.utils.DeleteDialog;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongKongActivity extends BaseActivity implements WangguanAdapter.CallBack, AddWangguanDialog.WuangguanDialogCallBack, DeleteDialog.DialogCallBack {

    @BindView(R.id.wangguan_recyclerView)
    RecyclerView wangguan_recyclerView;

    private WangguanAdapter wangguanAdapter;
    private AddWangguanDialog addWangguanDialog;
    private DeleteDialog deleteDialog;
    private WuangguanInfoDao wangguandata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_kong);
        ButterKnife.bind(this);

        wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
        if (wangguandata.loadAll().size() == 0) {
            wangguandata.insert(new WuangguanInfo((long) 1, "192.168.0.220", 10101, 0));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        wangguan_recyclerView.setLayoutManager(manager);
        wangguanAdapter = new WangguanAdapter(this, wangguandata.loadAll(), this);
        wangguan_recyclerView.setAdapter(wangguanAdapter);
        ELog.i("===========wangguandata.loadAll()============" + wangguandata.loadAll().toString());
    }

    @OnClick(R.id.add_wangguan)
    public void add_wangguan() {
        if (addWangguanDialog == null) {
            addWangguanDialog = new AddWangguanDialog(this, null, this);
        }
        if (addWangguanDialog != null) {
            addWangguanDialog.show();
            addWangguanDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void addWangguanInfo(String wgId, String wgIp, String wgPort, int status) {
        if (wangguandata.loadAll().size() != 0) {
            List<WuangguanInfo> wgData = wangguandata.queryBuilder()
                    .where(WuangguanInfoDao.Properties.Wg_id.eq(wgId))
                    .list();
            if (wgData.size() != 0) {
                wangguandata.deleteByKey(Long.valueOf(wgId));
            }
        }
        wangguandata.insert(new WuangguanInfo(Long.valueOf(wgId), wgIp, Integer.valueOf(wgPort), status));
        closeDialog();
    }

    @Override
    public void addWangguanDialogCancel() {
        closeDialog();
    }

    private void closeDialog() {
        wangguanAdapter.setData(wangguandata.loadAll());
        if (addWangguanDialog != null) {
            addWangguanDialog.dismiss();
            addWangguanDialog = null;
        }
        if (deleteDialog != null) {
            deleteDialog.dismiss();
            deleteDialog = null;
        }
    }

    @OnClick(R.id.zk_back_btn)
    public void zk_back_btn() {
        back();
    }

    @Override
    public void onDelectItem(WuangguanInfo item) {
        if (deleteDialog == null) {
            deleteDialog = new DeleteDialog(this, this, item.wg_id);
        }
        if (deleteDialog != null) {
            deleteDialog.show();
            deleteDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onFixItem(WuangguanInfo item) {
        if (addWangguanDialog == null) {
            addWangguanDialog = new AddWangguanDialog(this, item, this);
        }
        if (addWangguanDialog != null) {
            addWangguanDialog.show();
            addWangguanDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void deleteInfo(Long mitemId) {
        wangguandata.deleteByKey(mitemId);
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
