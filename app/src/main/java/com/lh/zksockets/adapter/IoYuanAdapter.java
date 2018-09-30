package com.lh.zksockets.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.IOYuan;
import com.lh.zksockets.utils.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;


public class IoYuanAdapter extends RecyclerView.Adapter<IoYuanAdapter.IcCardViewHolder> {

    private Context mContext;
    private List<IOYuan> datas;
    private List<String> outDatas;
    private CallBack mCallBack;

    public IoYuanAdapter(Context context, List<IOYuan> data, List<String> outYuans, CallBack callBack) {
        this.datas = data;
        this.outDatas = outYuans;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public IcCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.io_yuan_item, parent, false);
        return new IcCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IcCardViewHolder holder, int position) {
        IOYuan ioYuan = datas.get(position);
        SelectAdapter selectAdapter = new SelectAdapter(mContext, outDatas);
        holder.setItem(ioYuan, selectAdapter, position);

        holder.spinner_out.setAdapter(selectAdapter);
        holder.spinner_out.setSelection(ioYuan.outId);
        holder.tv_io_name.setText(ioYuan.name);
        if (ioYuan.isOK) {
            holder.check_box_io.setChecked(true);
            holder.spinner_out.setEnabled(true);
            selectAdapter.setEnabledStatus(true);
            holder.et_io_time.setEnabled(true);
            holder.et_io_time.setTextColor(Color.parseColor("#ff000000"));
        } else {
            holder.check_box_io.setChecked(false);
            holder.spinner_out.setEnabled(false);
            selectAdapter.setEnabledStatus(false);
            holder.et_io_time.setEnabled(false);
            holder.et_io_time.setTextColor(Color.GRAY);
        }
        holder.et_io_time.setText(ioYuan.sendTime + "");
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<IOYuan> ioYuans) {
        datas = ioYuans;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onCheckBoxClickItem(boolean isChecked, int adapterPosition);

        void onSpinnerSelected(int adapterPosition, int spinnerPosition);

        void setIOSendTime(int adapterPosition, String time);
    }

    public class IcCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_io_name)
        TextView tv_io_name;
        @BindView(R.id.check_box_io)
        CheckBox check_box_io;
        @BindView(R.id.spinner_out)
        Spinner spinner_out;
        @BindView(R.id.et_io_time)
        EditText et_io_time;


        private IOYuan item;
        private int adapterPosition;
        private SelectAdapter spinnerAdapter;


        public IcCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(IOYuan ioYuan, SelectAdapter adapter, int position) {
            item = ioYuan;
            spinnerAdapter = adapter;
            adapterPosition = position;
        }

        @OnCheckedChanged(R.id.check_box_io)
        public void OnCheckedChanged(CompoundButton compoundButton, boolean b) {
            ELog.i("=========check_box_io========" + b);
            if (b) {
                spinner_out.setEnabled(true);
                spinnerAdapter.setEnabledStatus(true);
                et_io_time.setEnabled(true);
                et_io_time.setTextColor(Color.parseColor("#ff000000"));
            } else {
                spinner_out.setEnabled(false);
                spinnerAdapter.setEnabledStatus(false);
                et_io_time.setEnabled(false);
                et_io_time.setTextColor(Color.GRAY);
            }
            mCallBack.onCheckBoxClickItem(b, adapterPosition);
        }

        @OnItemSelected(R.id.spinner_out)
        public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
            ELog.i("=========spinner_out========" + adapterPosition + "==========" + spinnerPosition);
            mCallBack.onSpinnerSelected(adapterPosition, spinnerPosition);
        }

        @OnTextChanged(value = R.id.et_io_time, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void afterTextChanged(Editable editable) {
            ELog.i("=========et_io_time========" + et_io_time.getText().toString());
            mCallBack.setIOSendTime(adapterPosition, et_io_time.getText().toString());
        }

    }
}
