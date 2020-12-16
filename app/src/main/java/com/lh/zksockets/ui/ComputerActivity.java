package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.data.DbDao.WenShiDuDao;
import com.lh.zksockets.data.model.WenShiDu;
import com.lh.zksockets.utils.TimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComputerActivity extends BaseActivity {

    @BindView(R.id.et_ws_wen)
    EditText et_ws_wen;
    @BindView(R.id.et_ws_shi)
    EditText et_ws_shi;
    @BindView(R.id.et_ws_ml)
    EditText et_ws_ml;
    @BindView(R.id.et_ws_time)
    EditText et_ws_time;
    private WenShiDuDao wenShiDuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);

        wenShiDuDao = MyApplication.getDaoSession().getWenShiDuDao();

        if (wenShiDuDao.loadAll().size() == 0) {
            wenShiDuDao.insert(new WenShiDu("", "", "", "", "", "", "", 1, ""));
        }

        initView();

    }

    private void initView() {
        et_ws_wen.setText(wenShiDuDao.loadAll().get(0).wenStr);
        et_ws_shi.setText(wenShiDuDao.loadAll().get(0).shiStr);
        et_ws_time.setText(wenShiDuDao.loadAll().get(0).timeStr + "");
        et_ws_ml.setText(wenShiDuDao.loadAll().get(0).serialportML);
    }

    @OnClick(R.id.btn_computer_ok)
    public void btn_computer_ok() {
        wenShiDuDao.deleteAll();
        wenShiDuDao.insert(new WenShiDu("", "", "", "", et_ws_wen.getText().toString(),
                et_ws_shi.getText().toString(), "", Integer.valueOf(et_ws_time.getText().toString()), et_ws_ml.getText().toString()));

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        TimerUtils.setWenshiduTimer();
    }

    @OnClick(R.id.computer_btn_back)
    public void computer_btn_back() {
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
