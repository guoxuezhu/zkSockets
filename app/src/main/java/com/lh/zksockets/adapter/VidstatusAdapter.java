package com.lh.zksockets.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.VidStatus;

import java.util.List;

public class VidstatusAdapter extends BaseAdapter {


    private List<VidStatus> datas;
    private Context mContext;

    public VidstatusAdapter(Context pContext, List<VidStatus> datalist) {
        this.mContext = pContext;
        this.datas = datalist;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = View.inflate(mContext, R.layout.vid_status_item, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.vid_name_tv = (TextView) convertView.findViewById(R.id.vid_name_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VidStatus vidstatus = datas.get(position);
        if (vidstatus.vidinStatus.equals("1")) {
            viewHolder.vid_name_tv.setText(vidstatus.vidinName + "\n" + "使用中");
            viewHolder.vid_name_tv.setBackgroundResource(R.color.profile_badge_3);
        } else {
            viewHolder.vid_name_tv.setText(vidstatus.vidinName + "\n" + "断开");
            viewHolder.vid_name_tv.setBackgroundResource(R.color.user_icon_8);
        }

        return convertView;
    }


    class ViewHolder {
        TextView vid_name_tv;
    }
}
