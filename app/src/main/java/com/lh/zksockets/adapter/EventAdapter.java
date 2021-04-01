package com.lh.zksockets.adapter;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.MLsLists;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mContext;
    private List<MLsLists> datas;
    private CallBack mCallBack;

    public EventAdapter(Context context, List<MLsLists> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_1, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        MLsLists mLsLists = datas.get(position);
        holder.setItem(mLsLists, position);
        holder.event_id.setText(mLsLists.id + "");
        holder.event_name.setText(mLsLists.name);
        holder.event_time.setText(mLsLists.time);
        holder.event_et_ml.setText(mLsLists.strMLs);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<MLsLists> dfsEventdatas) {
        datas = dfsEventdatas;
//        notifyDataSetChanged();
    }

    public interface CallBack {
        void onSetingMl(int mPosition, String etml);
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_id)
        TextView event_id;
        @BindView(R.id.event_name)
        TextView event_name;
        @BindView(R.id.event_time)
        TextView event_time;
        @BindView(R.id.event_et_ml)
        EditText event_et_ml;

        private MLsLists item;
        private int mPosition;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(MLsLists mLsLists, int position) {
            item = mLsLists;
            mPosition = position;
        }

        @OnTextChanged(value = R.id.event_et_ml, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void mlEtChanged(Editable editable) {
            if (!editable.toString().equals(item.strMLs)) {
                mCallBack.onSetingMl(mPosition, editable.toString());
            }
        }
    }
}
