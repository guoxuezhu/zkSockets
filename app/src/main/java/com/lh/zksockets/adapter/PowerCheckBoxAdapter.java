package com.lh.zksockets.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.lh.zksockets.R;

import java.util.List;

public class PowerCheckBoxAdapter extends BaseAdapter {


    private List<String> datas;
    private Context mContext;
    private PowerCallBack mCallBack;

    public PowerCheckBoxAdapter(Context pContext, List<String> datalist, PowerCallBack powerCallBack) {
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
        void onPowerCheckItem(boolean isChecked, String name);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.checked_item, parent, false);
        }
        CheckBox powerName = (CheckBox) convertView.findViewById(R.id.check_box_power);
        final String name = datas.get(position);
        powerName.setText(name);
        powerName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCallBack.onPowerCheckItem(b, name);
            }
        });
        return convertView;
    }
}
