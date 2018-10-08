package com.lh.zksockets.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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


    public interface EventBaseCallBack {
        void onEventBaseCheckItem(boolean isChecked, EventBase eventBase);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = View.inflate(mContext, R.layout.checked_item, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.powerName = (CheckBox) convertView.findViewById(R.id.check_box_power);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final EventBase eventBase = datas.get(position);
        viewHolder.powerName.setText(eventBase.name);

        viewHolder.powerName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCallBack.onEventBaseCheckItem(b, eventBase);
            }
        });

        return convertView;
    }

    class ViewHolder {
        CheckBox powerName;
    }
}
