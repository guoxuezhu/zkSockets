package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventBigAdapter;
import com.lh.zksockets.adapter.IoYuanAdapter;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.model.EventBase;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.utils.ELog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends Activity implements EventBigAdapter.CallBack {

    @BindView(R.id.event_recyclerView)
    RecyclerView event_recyclerView;

    private List<EventBase> eventBases = new ArrayList<EventBase>();
    private List<EventBig> eventBigs = new ArrayList<EventBig>();
    private EventBigAdapter eventBigAdapter;
    private EventBigDao eventBigDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        eventBigDao = MyApplication.getDaoSession().getEventBigDao();
        DataInit();
        initRcyclerView();

    }

    private void DataInit() {
        if (eventBigDao.loadAll().size() == 0) {
            Gson gson = new Gson();
            eventBases.add(new EventBase(1, "开空调", false, "0"));
            eventBases.add(new EventBase(2, "关空调", false, "0"));
            eventBases.add(new EventBase(3, "开投影机", false, "0"));
            eventBases.add(new EventBase(4, "关投影机", false, "0"));
            eventBases.add(new EventBase(5, "开灯", false, "0"));
            eventBases.add(new EventBase(6, "关灯", false, "0"));
            eventBases.add(new EventBase(7, "开窗帘1", false, "0"));
            eventBases.add(new EventBase(8, "关窗帘1", false, "0"));
            eventBases.add(new EventBase(9, "开窗帘2", false, "0"));
            eventBases.add(new EventBase(10, "关窗帘2", false, "0"));
            eventBases.add(new EventBase(11, "开窗帘3", false, "0"));
            eventBases.add(new EventBase(12, "关窗帘3", false, "0"));
            eventBases.add(new EventBase(13, "开窗帘4", false, "0"));
            eventBases.add(new EventBase(14, "关窗帘4", false, "0"));

            String eventBasesJsonStr = gson.toJson(eventBases);

            eventBigDao.insert(new EventBig((long) 1, "上课", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 2, "课间休息", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 3, "下课", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 4, "锁定", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 5, "开灯", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 6, "关灯", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 7, "开窗帘", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 8, "关窗帘", "", "", eventBasesJsonStr));
        }

        ELog.i("=======eventBigDao=======" + eventBigDao.loadAll().toString());

    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        event_recyclerView.setLayoutManager(manager);
        eventBigAdapter = new EventBigAdapter(this, eventBigDao.loadAll(), this);
        event_recyclerView.setAdapter(eventBigAdapter);
    }

    @Override
    public void onClickItem(EventBig item) {
        Intent intent = new Intent(this, EventSelectActivity.class);
        intent.putExtra("eventBigId", item.id);
        intent.putExtra("eventBases", item.eventBaseString);
        startActivity(intent);
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
