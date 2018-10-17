package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventBigAdapter;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.DbDao.LampDao;
import com.lh.zksockets.data.model.EventBase;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.utils.ELog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends BaseActivity implements EventBigAdapter.CallBack {

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
            eventBases.add(new EventBase(1, (long) 1, "开投影机一", 1, false, 0));
            eventBases.add(new EventBase(1, (long) 2, "关投影机一", 0, false, 0));
            eventBases.add(new EventBase(1, (long) 3, "开投影机二", 1, false, 0));
            eventBases.add(new EventBase(1, (long) 4, "关投影机二", 0, false, 0));


            LampDao lampDao = MyApplication.getDaoSession().getLampDao();
            if (lampDao.loadAll().size() != 0) {
                for (int i = 0; i < lampDao.loadAll().size(); i++) {
                    eventBases.add(new EventBase(2, lampDao.loadAll().get(i).id, "开灯(" + lampDao.loadAll().get(i).name + ")", 1, false, 0));
                    eventBases.add(new EventBase(2, lampDao.loadAll().get(i).id, "关灯(" + lampDao.loadAll().get(i).name + ")", 0, false, 0));
                }
            }


            Gson gson = new Gson();
            String eventBasesJsonStr = gson.toJson(eventBases);

            eventBigDao.insert(new EventBig((long) 1, "上课", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 2, "课间休息", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 3, "下课", "", "", eventBasesJsonStr));
            eventBigDao.insert(new EventBig((long) 4, "锁定", "", "", eventBasesJsonStr));
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

    @OnClick(R.id.event_reset_btn)
    public void event_reset_btn() {
        eventBigDao.deleteAll();
        DataInit();
        eventBigAdapter.setData(eventBigDao.loadAll());
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
