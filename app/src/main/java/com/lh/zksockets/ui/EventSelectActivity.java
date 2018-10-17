package com.lh.zksockets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.MyApplication;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventBaseAdapter;
import com.lh.zksockets.data.DbDao.EventBigDao;
import com.lh.zksockets.data.DbDao.EventKejianRestDao;
import com.lh.zksockets.data.DbDao.EventShangkeDao;
import com.lh.zksockets.data.DbDao.EventXiakeDao;
import com.lh.zksockets.data.model.EventBase;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.data.model.EventShangke;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventSelectActivity extends BaseActivity implements EventBaseAdapter.EventBaseCallBack {

    @BindView(R.id.event_base_gridView)
    GridView event_base_gridView;

    private List<EventBase> eventBaseList;
    private EventBaseAdapter eventBaseAdapter;
    private long eventBigId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);
        ButterKnife.bind(this);

        gridViewInit();

    }

    private void gridViewInit() {
        eventBigId = getIntent().getLongExtra("eventBigId", 0);
        Gson gson = new Gson();
        eventBaseList = gson.fromJson(getIntent().getStringExtra("eventBases"), new TypeToken<List<EventBase>>() {
        }.getType());

        ELog.i("========eventBaseList==========" + eventBaseList.toString());
        eventBaseAdapter = new EventBaseAdapter(this, eventBaseList, this);
        event_base_gridView.setAdapter(eventBaseAdapter);
    }


    @OnClick(R.id.event_select_ok)
    public void event_select_ok() {
        eventBaseList = eventBaseAdapter.getDatas();
        ELog.i("========eventBaseList=====ok======" + eventBaseList.toString());
        String checkedIds = "";
        String checkedNameStr = "";

        EventShangkeDao eventShangkeDao = MyApplication.getDaoSession().getEventShangkeDao();
        EventKejianRestDao eventKejianRestDao = MyApplication.getDaoSession().getEventKejianRestDao();
        EventXiakeDao eventXiakeDao = MyApplication.getDaoSession().getEventXiakeDao();

        if (eventBigId == 1) {
            eventShangkeDao.deleteAll();
        } else if (eventBigId == 2) {
            eventKejianRestDao.deleteAll();
        } else if (eventBigId == 3) {
            eventXiakeDao.deleteAll();
        } else if (eventBigId == 4) {
            //eventShangkeDao.deleteAll();
        }

        for (int i = 0; i < eventBaseList.size(); i++) {
            if (eventBaseList.get(i).isChecked) {
                if (checkedNameStr.equals("")) {
                    checkedNameStr = eventBaseList.get(i).name;
                } else {
                    checkedNameStr = checkedNameStr + "," + eventBaseList.get(i).name;
                }

                if (eventBigId == 1) {
                    eventShangkeDao.insert(new EventShangke(eventBaseList.get(i).eventType, eventBaseList.get(i).eventId,
                            eventBaseList.get(i).name, eventBaseList.get(i).status, eventBaseList.get(i).isChecked, eventBaseList.get(i).time));
                } else if (eventBigId == 2) {
                    eventShangkeDao.insert(new EventShangke(eventBaseList.get(i).eventType, eventBaseList.get(i).eventId,
                            eventBaseList.get(i).name, eventBaseList.get(i).status, eventBaseList.get(i).isChecked, eventBaseList.get(i).time));
                } else if (eventBigId == 3) {
                    eventShangkeDao.insert(new EventShangke(eventBaseList.get(i).eventType, eventBaseList.get(i).eventId,
                            eventBaseList.get(i).name, eventBaseList.get(i).status, eventBaseList.get(i).isChecked, eventBaseList.get(i).time));
                } else if (eventBigId == 4) {

                }


            }
        }


        EventBigDao eventBigDao = MyApplication.getDaoSession().getEventBigDao();
        Gson gson = new Gson();
        String eventBasesJsonStr = gson.toJson(eventBaseList);

        EventBig eventBig = eventBigDao.load(eventBigId);
        eventBigDao.update(new EventBig(eventBigId, eventBig.name, checkedIds, checkedNameStr, eventBasesJsonStr));

        back();

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
        startActivity(new Intent(this, EventActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
