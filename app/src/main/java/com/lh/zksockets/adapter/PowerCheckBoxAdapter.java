package com.lh.zksockets.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.ChazuoData;

import java.util.List;

public class PowerCheckBoxAdapter extends BaseAdapter {


    private List<ChazuoData> datas;
    private Context mContext;
    private PowerCallBack mCallBack;

    public PowerCheckBoxAdapter(Context pContext, List<ChazuoData> datalist, PowerCallBack powerCallBack) {
        this.mContext = pContext;
        this.datas = datalist;
        this.mCallBack = powerCallBack;
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


    public interface PowerCallBack {
        void onPowerCheckItem(boolean isChecked, ChazuoData chazuoData);
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

        final ChazuoData chazuoData = datas.get(position);
        if (chazuoData.bindName == null) {
            viewHolder.powerName.setText(chazuoData.name);
        } else {
            viewHolder.powerName.setText(chazuoData.name + "(" + chazuoData.bindName + ")");
        }

        viewHolder.powerName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCallBack.onPowerCheckItem(b, chazuoData);
            }
        });

        return convertView;
    }

    class ViewHolder {
        CheckBox powerName;
    }
}
