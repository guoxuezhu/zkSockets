package com.lh.zksockets.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.SerialCommand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class SerialportAdapter extends RecyclerView.Adapter<SerialportAdapter.SerialportViewHolder> {

    private Context mContext;
    private List<SerialCommand> datas;
    private CallBack mCallBack;

    public SerialportAdapter(Context context, List<SerialCommand> data, CallBack callBack) {
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
        SerialCommand serialCommand = datas.get(position);
        holder.et_command_id.setText(serialCommand.commandId);
        holder.et_command_name.setText(serialCommand.commandName);
        holder.et_command_str.setText(serialCommand.commandStr);

        holder.setItem(serialCommand, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<SerialCommand> serialCommands) {
        datas = serialCommands;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void setMlEditTextChanged(SerialCommand serialCommand, int position, String ml);

        void setNameEditTextChanged(SerialCommand serialCommand, int position, String name);
    }

    public class SerialportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.et_command_id)
        TextView et_command_id;
        @BindView(R.id.et_command_name)
        TextView et_command_name;
        @BindView(R.id.et_command_str)
        TextView et_command_str;


        private SerialCommand item;
        private int position;

        public SerialportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(SerialCommand serialCommand, int mposition) {
            item = serialCommand;
            this.position = mposition;
        }

        @OnTextChanged(value = R.id.et_command_name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void openTimeTextChanged(Editable editable) {
            mCallBack.setNameEditTextChanged(item, position, editable.toString());
        }

        @OnTextChanged(value = R.id.et_command_str, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        public void closedTimeTextChanged(Editable editable) {
            mCallBack.setMlEditTextChanged(item, position, editable.toString());
        }

    }
}
