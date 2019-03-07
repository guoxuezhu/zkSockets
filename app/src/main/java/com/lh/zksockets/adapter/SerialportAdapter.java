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
import com.lh.zksockets.data.model.SerialPortData;
import com.lh.zksockets.ui.SerialportActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SerialportAdapter extends RecyclerView.Adapter<SerialportAdapter.SerialportViewHolder> {

    private Context mContext;
    private List<SerialPortData> datas;
    private CallBack mCallBack;

    public SerialportAdapter(Context context, List<SerialPortData> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public SerialportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serialport_item, parent, false);
        return new SerialportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerialportViewHolder holder, int position) {
        SerialPortData serialPortData = datas.get(position);
        holder.tv_s_name.setText(serialPortData.serialPortName);
        holder.tv_s_info.setText("波特率:" + serialPortData.baudRate + "       校验位:" + serialPortData.checkoutBit +
                "\n" + "数据位:" + serialPortData.dataBit + "        停止位:" + serialPortData.stopBit);

        holder.setItem(serialPortData);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<SerialPortData> serialPortDataList) {
        datas = serialPortDataList;
        notifyDataSetChanged();
    }

    public interface CallBack {
//        void onClickItem(SerialPortData item);
    }

    public class SerialportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_s_name)
        TextView tv_s_name;
        @BindView(R.id.tv_s_info)
        TextView tv_s_info;


        private SerialPortData item;


        public SerialportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(SerialPortData serialPortData) {
            item = serialPortData;
        }

        @OnClick(R.id.btn_setting)
        public void btn_setting() {
//            mCallBack.onClickItem(item);
        }

    }
}
