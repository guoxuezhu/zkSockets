package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.ChazuoData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class PowerDeviceOpenAdapter extends RecyclerView.Adapter<PowerDeviceOpenAdapter.PowerViewHolder> {

    private Context mContext;
    private List<ChazuoData> datas;
    private OpenCallBack mCallBack;

    public PowerDeviceOpenAdapter(Context context, List<ChazuoData> data, OpenCallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.power_item, parent, false);
        return new PowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PowerViewHolder holder, int position) {

        ChazuoData chazuo = datas.get(position);
        holder.tv_power_serialNumber.setText(position + 1 + "");
        if (chazuo.bindName == null) {
            holder.tv_device.setText(chazuo.name);
        } else {
            holder.tv_device.setText(chazuo.name + "(" + chazuo.bindName + ")");
        }
        holder.et_time.setText(holder.et_time.getText().toString());
        holder.setItem(chazuo);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OpenCallBack {
        void setOpenEditTextChanged(ChazuoData item, String time);
    }

    public class PowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_power_serialNumber)
        TextView tv_power_serialNumber;
        @BindView(R.id.tv_device)
        TextView tv_device;
        @BindView(R.id.et_time)
        EditText et_time;


        private ChazuoData item;


        public PowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(ChazuoData item) {
            this.item = item;
        }

        @OnTextChanged(value = R.id.et_time, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void afterTextChanged(Editable editable) {
            mCallBack.setOpenEditTextChanged(item, et_time.getText().toString());
        }


    }
}
