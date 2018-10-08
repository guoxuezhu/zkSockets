package com.lh.zksockets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.R;
import com.lh.zksockets.adapter.EventBaseAdapter;
import com.lh.zksockets.data.model.EventBase;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventSelectActivity extends Activity implements EventBaseAdapter.EventBaseCallBack {

    @BindView(R.id.event_base_gridView)
    GridView event_base_gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);
        ButterKnife.bind(this);

        gridViewInit();

    }

    private void gridViewInit() {
        Gson gson = new Gson();
        List<EventBase> eventBaseList = gson.fromJson(getIntent().getStringExtra("eventBases"), new TypeToken<List<EventBase>>() {
        }.getType());

        ELog.i("========eventBaseList==========" + eventBaseList.toString());
        event_base_gridView.setAdapter(new EventBaseAdapter(this, eventBaseList, this));
    }

    @Override
    public void onEventBaseCheckItem(boolean isChecked, EventBase eventBase) {
        if (isChecked) {

        } else {

        }
        ELog.i("========eventBase==========" + eventBase.toString());
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
