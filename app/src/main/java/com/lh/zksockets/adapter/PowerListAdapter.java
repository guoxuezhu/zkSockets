package com.lh.zksockets.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.ChazuoData;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnTextChanged;


public class PowerListAdapter extends RecyclerView.Adapter<PowerListAdapter.PowerViewHolder> {

    private Context mContext;
    private List<ChazuoData> datas;
    private CallBack mCallBack;

    public PowerListAdapter(Context context, List<ChazuoData> data, CallBack callBack) {
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
        ChazuoData chazuoData = datas.get(position);
        holder.tv_powerdevice_serialNumber.setText(position + 1 + "");

        holder.tv_chazuo.setText(chazuoData.name);

        if (chazuoData.bindName != null) {
            holder.tv_bindName.setText(chazuoData.bindName);
        } else {
            holder.tv_bindName.setText("");
        }

        if (chazuoData.isOk) {
            holder.check_box_enable.setChecked(true);
        } else {
            holder.check_box_enable.setChecked(false);
        }

        holder.et_openTime.setText(chazuoData.openTime + "");
        holder.et_closedTime.setText(chazuoData.closedTime + "");
        holder.setItem(chazuoData, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<ChazuoData> chazuoDataList) {
        this.datas = chazuoDataList;
        notifyDataSetChanged();
    }


    public interface CallBack {
        void setOpenEditTextChanged(ChazuoData item, int position, String time);

        void setClosedEditTextChanged(ChazuoData item, int position, String time);

        void onCheckBoxClickItem(boolean isChecked, int position);
    }

    public class PowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_powerdevice_serialNumber)
        TextView tv_powerdevice_serialNumber;
        @BindView(R.id.tv_chazuo)
        TextView tv_chazuo;
        @BindView(R.id.tv_bindName)
        TextView tv_bindName;
        @BindView(R.id.check_box_enable)
        CheckBox check_box_enable;
        @BindView(R.id.et_openTime)
        EditText et_openTime;
        @BindView(R.id.et_closedTime)
        EditText et_closedTime;

        private ChazuoData item;
        private int position;

        public PowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(ChazuoData chazuoData, int mposition) {
            this.item = chazuoData;
            this.position = mposition;
        }

        @OnCheckedChanged(R.id.check_box_enable)
        public void OnCheckedChanged(CompoundButton compoundButton, boolean b) {
            ELog.i("=========check_box_enable========" + b);
            mCallBack.onCheckBoxClickItem(b, position);
        }


        @OnTextChanged(value = R.id.et_openTime, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void openTimeTextChanged(Editable editable) {
//            ELog.i("=========et_openTime========" + editable.toString());
            mCallBack.setOpenEditTextChanged(item, position, editable.toString());
        }

        @OnTextChanged(value = R.id.et_closedTime, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void closedTimeTextChanged(Editable editable) {
//            ELog.i("=========et_closedTime========" + editable.toString());
            mCallBack.setClosedEditTextChanged(item, position, editable.toString());
        }
    }
}
