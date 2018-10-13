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
import com.lh.zksockets.data.model.Lamp;
import com.lh.zksockets.ui.LampActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> {

    private Context mContext;
    private List<Lamp> datas;
    private CallBack mCallBack;

    public LampAdapter(Context context, List<Lamp> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }


    @NonNull
    @Override
    public LampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lamp_item, parent, false);
        return new LampViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LampViewHolder holder, int position) {
        Lamp lamp = datas.get(position);


        holder.tv_lamp_id.setText("序号: " + lamp.id);
        holder.tv_lamp_name.setText("名称: " + lamp.name);
        if (lamp.workType == 1) {
            holder.tv_lamp_type.setText("操作类型: " + "IO      IO口: " + lamp.ioSelectName);
        } else if (lamp.workType == 2) {
            holder.tv_lamp_type.setText("操作类型: " + "总线");
        } else if (lamp.workType == 3) {
            holder.tv_lamp_type.setText("操作类型: " + "串口      串口: " + lamp.serialPortSelectName
                    + "      波特率: " + lamp.baudRateSelectName + "      校验位: " + lamp.checkoutBitSelectName
                    + "      数据位: " + lamp.dataBitSelectName + "      停止位: " + lamp.stopBitSelectName);
        }
        holder.setItem(lamp);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<Lamp> lamps) {
        datas = lamps;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItemFix(Lamp item);

        void onClickItemDelect(Lamp item);
    }

    public class LampViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_lamp_id)
        TextView tv_lamp_id;
        @BindView(R.id.tv_lamp_name)
        TextView tv_lamp_name;
        @BindView(R.id.tv_lamp_type)
        TextView tv_lamp_type;


        private Lamp item;


        public LampViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(Lamp lamp) {
            item = lamp;
        }

        @OnClick(R.id.lamp_fix)
        public void lamp_fix() {
            mCallBack.onClickItemFix(item);
        }

        @OnClick(R.id.lamp_delect)
        public void lamp_delect() {
            mCallBack.onClickItemDelect(item);
        }
    }
}
