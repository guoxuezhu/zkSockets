package com.lh.zksockets.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lh.zksockets.R;
import com.lh.zksockets.data.model.IcCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IcCardAdapter extends RecyclerView.Adapter<IcCardAdapter.IcCardViewHolder> {

    private Context mContext;
    private List<IcCard> datas;
    private CallBack mCallBack;

    public IcCardAdapter(Context context, List<IcCard> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public IcCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ic_card_item, parent, false);
        return new IcCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IcCardViewHolder holder, int position) {
        IcCard icCard = datas.get(position);
        holder.tv_serialNumber.setText(position + 1 + "");
        holder.tv_workNumber.setText(icCard.workNum);
        holder.tv_cardType.setText(icCard.role + "");
        holder.tv_updataTime.setText(icCard.updataTime);
        holder.tv_teacherName.setText(icCard.name.substring(0, 1) + "* *");
//        holder.tv_department.setText(icCard.department);
        holder.tv_cardNum.setText(icCard.card_no.substring(0, 5) + "* *");

        holder.setItem(icCard);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<IcCard> icCards) {
        datas = icCards;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItem(IcCard item);
    }

    public class IcCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_serialNumber)
        TextView tv_serialNumber;
        @BindView(R.id.tv_workNumber)
        TextView tv_workNumber;
        @BindView(R.id.tv_cardType)
        TextView tv_cardType;
        @BindView(R.id.tv_updataTime)
        TextView tv_updataTime;
        @BindView(R.id.tv_teacherName)
        TextView tv_teacherName;
        @BindView(R.id.tv_department)
        TextView tv_department;
        @BindView(R.id.tv_cardNum)
        TextView tv_cardNum;


        private IcCard item;


        public IcCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(IcCard icCard) {
            item = icCard;
        }

        @OnClick(R.id.btn_delect)
        public void btn_delect() {
            mCallBack.onClickItem(item);
        }

    }
}
