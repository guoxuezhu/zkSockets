package com.lh.zksockets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.ChazuoData;

import java.util.List;


public class SelectChazuoAdapter extends BaseAdapter {
    private List<ChazuoData> mList;
    private Context mContext;

    public SelectChazuoAdapter(Context pContext, List<ChazuoData> pList) {
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
        ViewHolder viewHolder = null;
        convertView = View.inflate(mContext, R.layout.selsect_item, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        ChazuoData chazuoData = mList.get(position);
        if (chazuoData.bindName == null) {
            viewHolder.tv.setText(chazuoData.name);
        } else {
            viewHolder.tv.setText(chazuoData.name + "(" + chazuoData.bindName + ")");
        }
        return convertView;
    }

    public void setDatas(List<ChazuoData> chazuoData) {
        this.mList = chazuoData;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv;
    }
}