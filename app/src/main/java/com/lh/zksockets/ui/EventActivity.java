package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventBigAdapter;
import com.lh.zksockets.adapter.IoYuanAdapter;
import com.lh.zksockets.data.model.EventBig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends Activity implements EventBigAdapter.CallBack {

    @BindView(R.id.event_recyclerView)
    RecyclerView event_recyclerView;

    private List<EventBig> eventBigs = new ArrayList<EventBig>();
    private EventBigAdapter eventBigAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        DataInit();
        initRcyclerView();

    }

    private void DataInit() {
        eventBigs.add(new EventBig(1, "上课", null));
        eventBigs.add(new EventBig(2, "课间休息", null));
        eventBigs.add(new EventBig(3, "下课", null));
        eventBigs.add(new EventBig(4, "锁定", null));
        eventBigs.add(new EventBig(5, "开灯", null));
        eventBigs.add(new EventBig(6, "关灯", null));
        eventBigs.add(new EventBig(7, "开窗帘", null));
        eventBigs.add(new EventBig(8, "关窗帘", null));
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        event_recyclerView.setLayoutManager(manager);
        eventBigAdapter = new EventBigAdapter(this, eventBigs, this);
        event_recyclerView.setAdapter(eventBigAdapter);
    }

    @Override
    public void onClickItem(EventBig item) {
        startActivity(new Intent(this, EventSelectActivity.class));
        finish();
    }


    @OnClick(R.id.event_btn_back)
    public void event_btn_back() {
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
