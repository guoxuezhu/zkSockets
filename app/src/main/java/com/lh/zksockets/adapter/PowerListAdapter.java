package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.IcCard;
import com.lh.zksockets.data.model.PowerDevice;
import com.lh.zksockets.ui.PowerDeviceActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PowerListAdapter extends RecyclerView.Adapter<PowerListAdapter.PowerViewHolder> {

    private Context mContext;
    private List<PowerDevice> datas;
    private CallBack mCallBack;

    public PowerListAdapter(Context context, List<PowerDevice> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.power_device_item, parent, false);
        return new PowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PowerViewHolder holder, int position) {
        PowerDevice powerDevice = datas.get(position);
        holder.tv_powerdevice_serialNumber.setText(position + 1 + "");
        holder.tv_Powerdevice.setText(powerDevice.deviceName);
        holder.tv_openTime.setText(powerDevice.openTime);
        holder.tv_closedTime.setText(powerDevice.closedTime);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    public interface CallBack {
    }

    public class PowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_powerdevice_serialNumber)
        TextView tv_powerdevice_serialNumber;
        @BindView(R.id.tv_Powerdevice)
        TextView tv_Powerdevice;
        @BindView(R.id.tv_openTime)
        TextView tv_openTime;
        @BindView(R.id.tv_closedTime)
        TextView tv_closedTime;

        public PowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
