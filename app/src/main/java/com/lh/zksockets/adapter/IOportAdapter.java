package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.IoPortData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IOportAdapter extends RecyclerView.Adapter<IOportAdapter.IOportViewHolder> {

    private Context mContext;
    private List<IoPortData> datas;
    private CallBack mCallBack;

    public IOportAdapter(Context context, List<IoPortData> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public IOportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ioport_item, parent, false);
        return new IOportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IOportViewHolder holder, int position) {
        IoPortData ioPortData = datas.get(position);
        holder.tv_io_name.setText(ioPortData.ioName);
        if (ioPortData.Vgd) {
            holder.switch_Vgd.setChecked(true);
        } else {
            holder.switch_Vgd.setChecked(false);
        }
        holder.tv_io_strML.setText(ioPortData.strML);

        holder.setItem(ioPortData);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<IoPortData> ioPortDataList) {
        datas = ioPortDataList;
        notifyDataSetChanged();
    }

    public interface CallBack {
//        void onClickItem(IoPortData item);
    }

    public class IOportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_io_name)
        TextView tv_io_name;
        @BindView(R.id.switch_Vgd)
        Switch switch_Vgd;
        @BindView(R.id.tv_io_strML)
        TextView tv_io_strML;

        private IoPortData item;


        public IOportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(IoPortData ioPortData) {
            item = ioPortData;
        }

        @OnClick(R.id.btn_add_setting)
        public void btn_add_setting() {
//            mCallBack.onClickItem(item);
        }

    }
}
