package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.VidstatusAdapter;
import com.lh.zksockets.data.DbDao.VidStatusDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventSelectActivity extends BaseActivity {

    @BindView(R.id.vid_status_gridView)
    GridView vid_status_gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);
        ButterKnife.bind(this);

        gridViewInit();

    }

    private void gridViewInit() {
        VidStatusDao vidStatusDao = MyApplication.getDaoSession().getVidStatusDao();
        VidstatusAdapter vidstatusAdapter = new VidstatusAdapter(this, vidStatusDao.loadAll());
        vid_status_gridView.setAdapter(vidstatusAdapter);
    }

    @OnClick(R.id.event_select_btn_back)
    public void event_select_btn_back() {
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
