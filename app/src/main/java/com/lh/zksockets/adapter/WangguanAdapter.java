package com.lh.zksockets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.WuangguanInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WangguanAdapter extends RecyclerView.Adapter<WangguanAdapter.WangguanViewHolder> {


    private Context mContext;
    private List<WuangguanInfo> datas;
    private CallBack mCallBack;

    public WangguanAdapter(Context context, List<WuangguanInfo> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }


    @NonNull
    @Override
    public WangguanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wang_guan_item, parent, false);
        return new WangguanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WangguanViewHolder holder, int position) {
        WuangguanInfo wangguandata = datas.get(position);
        holder.wang_guan_id.setText(wangguandata.wg_id + "");
        holder.wang_guan_ip.setText(wangguandata.wg_ip);
        holder.wang_guan_port.setText(wangguandata.wg_port + "");
        if (wangguandata.wg_status == 1) {
            holder.wang_guan_status.setText("启用");
            holder.wang_guan_status.setTextColor(mContext.getResources().getColor(R.color.profile_badge_3));
        } else {
            holder.wang_guan_status.setText("禁用");
            holder.wang_guan_status.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        holder.setItem(wangguandata);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<WuangguanInfo> wgDatas) {
        datas = wgDatas;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onDelectItem(WuangguanInfo item);

        void onFixItem(WuangguanInfo item);
    }

    public class WangguanViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.wang_guan_id)
        TextView wang_guan_id;
        @BindView(R.id.wang_guan_ip)
        TextView wang_guan_ip;
        @BindView(R.id.wang_guan_port)
        TextView wang_guan_port;
        @BindView(R.id.wang_guan_status)
        TextView wang_guan_status;

        private WuangguanInfo item;

        public WangguanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(WuangguanInfo wangguandata) {
            item = wangguandata;
        }

        @OnClick(R.id.btn_wangguan_fix)
        public void btn_wangguan_fix() {
            mCallBack.onFixItem(item);
        }

        @OnClick(R.id.btn_wangguan_delect)
        public void btn_wangguan_delect() {
            mCallBack.onDelectItem(item);
        }


    }
}
