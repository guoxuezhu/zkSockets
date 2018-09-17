package com.lh.zksockets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lh.zksockets.R;

import java.util.List;


public class BaudRateAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;

    public BaudRateAdapter(Context pContext, List<String> pList) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
        convertView = _LayoutInflater.inflate(R.layout.selsect_item, null);
        if (convertView != null) {
            TextView _TextView1 = (TextView) convertView.findViewById(R.id.textView1);
            _TextView1.setText(mList.get(position));
        }
        return convertView;
    }
}