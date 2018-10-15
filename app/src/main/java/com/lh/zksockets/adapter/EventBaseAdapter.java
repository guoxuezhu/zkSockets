package com.lh.zksockets.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.EventBase;

import java.util.List;

public class EventBaseAdapter extends BaseAdapter {


    private List<EventBase> datas;
    private Context mContext;
    private EventBaseCallBack mCallBack;

    public EventBaseAdapter(Context pContext, List<EventBase> datalist, EventBaseCallBack eventBaseCallBack) {
        this.mContext = pContext;
        this.datas = datalist;
        this.mCallBack = eventBaseCallBack;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDatas(List<EventBase> eventBaseList) {
        datas = eventBaseList;
        notifyDataSetChanged();
    }

    public List<EventBase> getDatas() {
        return datas;
    }


    public interface EventBaseCallBack {
//        void onEventBaseCheckItem(boolean isChecked, EventBase eventBase);
//
//        void onItemTextChanged(EventBase eventBase, String etTime);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = View.inflate(mContext, R.layout.checked_event_item, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.powerName = (CheckBox) convertView.findViewById(R.id.check_event);
            viewHolder.eventTime = (EditText) convertView.findViewById(R.id.et_event_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final EventBase eventBase = datas.get(position);
        viewHolder.powerName.setText(eventBase.name);

        viewHolder.powerName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updataChecked(b, eventBase);
            }
        });

        viewHolder.eventTime.setText(eventBase.time);
        viewHolder.eventTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updataTime(eventBase, editable.toString());
            }
        });

        if (eventBase.isChecked) {
            viewHolder.powerName.setChecked(true);
        } else {
            viewHolder.powerName.setChecked(false);
        }


        return convertView;
    }

    private void updataTime(EventBase eventBase, String etTime) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).name.equals(eventBase.name)) {
                if (etTime.equals("")) {
                    datas.get(i).setTime("0");
                } else {
                    datas.get(i).setTime(etTime);
                }
            }
        }
    }

    private void updataChecked(boolean isChecked, EventBase eventBase) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).name.equals(eventBase.name)) {
                if (isChecked) {
                    datas.get(i).setChecked(true);
                } else {
                    datas.get(i).setChecked(false);
                }
            }
        }
    }

    class ViewHolder {
        CheckBox powerName;
        EditText eventTime;


    }
}
