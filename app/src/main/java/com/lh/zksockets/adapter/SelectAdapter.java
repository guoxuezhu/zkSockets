package com.lh.zksockets.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lh.zksockets.R;

import java.util.List;


public class SelectAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    private boolean isSelected = true;

    public SelectAdapter(Context pContext, List<String> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setEnabledStatus(boolean b) {
        this.isSelected = b;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        convertView = LayoutInflater.from(mContext).inflate(R.layout.selsect_item, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(mList.get(position));

        if (isSelected) {
            viewHolder.tv.setTextColor(Color.BLACK);
        } else {
            viewHolder.tv.setTextColor(Color.GRAY);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView tv;
    }

}