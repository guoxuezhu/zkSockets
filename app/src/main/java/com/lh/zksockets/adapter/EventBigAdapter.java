package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lh.zksockets.R;
import com.lh.zksockets.data.model.EventBase;
import com.lh.zksockets.data.model.EventBig;
import com.lh.zksockets.data.model.IcCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EventBigAdapter extends RecyclerView.Adapter<EventBigAdapter.IcCardViewHolder> {

    private Context mContext;
    private List<EventBig> datas;
    private CallBack mCallBack;

    public EventBigAdapter(Context context, List<EventBig> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public IcCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_big_item, parent, false);
        return new IcCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IcCardViewHolder holder, int position) {
        EventBig eventBig = datas.get(position);

        holder.tv_event_name.setText(eventBig.name);


//
//        holder.tv_event_datas.setText(eventBig.eventBases + "");

        holder.setItem(eventBig);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<EventBig> eventBigs) {
        datas = eventBigs;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItem(EventBig item);
    }

    public class IcCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_event_name)
        TextView tv_event_name;
        @BindView(R.id.tv_event_datas)
        TextView tv_event_datas;

        private EventBig item;


        public IcCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(EventBig eventBig) {
            item = eventBig;
        }

        @OnClick(R.id.btn_fix_add_event)
        public void btn_fix_add_event() {
            mCallBack.onClickItem(item);
        }

    }
}
