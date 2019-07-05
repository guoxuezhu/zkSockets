package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.lh.zksockets.R;
import com.lh.zksockets.adapter.VidstatusAdapter;
import com.lh.zksockets.data.model.Vidstatus;

import java.util.ArrayList;
import java.util.List;

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
        List<Vidstatus> vidstatuses=new ArrayList<>();
        vidstatuses.add(new Vidstatus("录播输入",1));
        vidstatuses.add(new Vidstatus("电脑输入",1));

        vidstatuses.add(new Vidstatus("投影输出",1));
        vidstatuses.add(new Vidstatus("电脑输出",0));
        vidstatuses.add(new Vidstatus("录播输出",0));
        vidstatuses.add(new Vidstatus("小组一输出",1));
        vidstatuses.add(new Vidstatus("小组二输出",1));
        vidstatuses.add(new Vidstatus("小组三输出",1));
        vidstatuses.add(new Vidstatus("小组四输出",1));
        vidstatuses.add(new Vidstatus("小组五输出",5));
        vidstatuses.add(new Vidstatus("小组六输出",1));

        VidstatusAdapter vidstatusAdapter = new VidstatusAdapter(this, vidstatuses);
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
        startActivity(new Intent(this, AdvancedSetingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
