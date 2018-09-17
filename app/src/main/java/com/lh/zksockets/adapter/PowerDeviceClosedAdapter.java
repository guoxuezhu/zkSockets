package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lh.zksockets.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class PowerDeviceClosedAdapter extends RecyclerView.Adapter<PowerDeviceClosedAdapter.PowerViewHolder> {

    private Context mContext;
    private List<String> datas;
    private ClosedCallBack mCallBack;

    public PowerDeviceClosedAdapter(Context context, List<String> data, ClosedCallBack callBack) {
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

        String name = datas.get(position);
        holder.tv_power_serialNumber.setText(position + 1 + "");
        holder.tv_device.setText(datas.get(position));
        holder.et_time.setText(holder.et_time.getText().toString());
        holder.setItem(name);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    public interface ClosedCallBack {
        void setClosedEditTextChanged(String item, String time);
    }

    public class PowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_power_serialNumber)
        TextView tv_power_serialNumber;
        @BindView(R.id.tv_device)
        TextView tv_device;
        @BindView(R.id.et_time)
        EditText et_time;


        private String item;


        public PowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(String item) {
            this.item = item;
        }

        @OnTextChanged(value = R.id.et_time, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void afterTextChanged(Editable editable) {
            mCallBack.setClosedEditTextChanged(item, et_time.getText().toString());
        }
    }
}
