package com.lh.zksockets.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.Users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private Context mContext;
    private List<Users> datas;
    private CallBack mCallBack;

    public UsersAdapter(Context context, List<Users> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = datas.get(position);
        holder.tv_user_serialNumber.setText(position + 1 + "");
        holder.tv_user_name.setText(user.username);
        holder.tv_user_paw.setText("******");
        if (user.user_status == 1) {
            holder.tv_user_status.setText("正常");
            holder.tv_user_status.setTextColor(mContext.getResources().getColor(R.color.black));
        } else {
            holder.tv_user_status.setText("锁定");
            holder.tv_user_status.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        holder.setItem(user);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<Users> users) {
        datas = users;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItem(Users item);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_serialNumber)
        TextView tv_user_serialNumber;
        @BindView(R.id.tv_user_name)
        TextView tv_user_name;
        @BindView(R.id.tv_user_paw)
        TextView tv_user_paw;
        @BindView(R.id.tv_user_status)
        TextView tv_user_status;

        private Users item;


        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(Users users) {
            item = users;
        }

        @OnClick(R.id.user_btn_delect)
        public void user_btn_delect() {
            mCallBack.onClickItem(item);
        }

    }
}
