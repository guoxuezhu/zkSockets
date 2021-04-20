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

public class OnekeyEventAdapter extends RecyclerView.Adapter<OnekeyEventAdapter.OnekeyEventViewHolder> {

    private Context mContext;
    private List<MLsLists> datas;
    private CallBack mCallBack;

    public OnekeyEventAdapter(Context context, List<MLsLists> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public OnekeyEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_2, parent, false);
        return new OnekeyEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnekeyEventViewHolder holder, int position) {
        MLsLists mLsLists = datas.get(position);
        holder.setItem(mLsLists, position);
        holder.onekey_event_id.setText(mLsLists.id + "");
        holder.onekey_event_name.setText(mLsLists.name);
        holder.onekey_event_time.setText(mLsLists.time);
        holder.onekey_event_et_ml.setText(mLsLists.strMLs);
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
        void onOnekeySetingMl(int mPosition, String etml);
    }

    public class OnekeyEventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.onekey_event_id)
        TextView onekey_event_id;
        @BindView(R.id.onekey_event_name)
        TextView onekey_event_name;
        @BindView(R.id.onekey_event_time)
        TextView onekey_event_time;
        @BindView(R.id.onekey_event_et_ml)
        EditText onekey_event_et_ml;

        private MLsLists item;
        private int mPosition;

        public OnekeyEventViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(MLsLists mLsLists, int position) {
            item = mLsLists;
            mPosition = position;
        }

        @OnTextChanged(value = R.id.onekey_event_et_ml, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void OnkeyMlEtChanged(Editable editable) {
            if (!editable.toString().equals(item.strMLs)) {
                mCallBack.onOnekeySetingMl(mPosition, editable.toString());
            }
        }
    }
}
