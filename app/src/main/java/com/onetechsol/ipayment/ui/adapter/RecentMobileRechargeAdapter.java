package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RecentMobileRechargeItemBinding;
import com.onetechsol.ipayment.databinding.RecentMobileRechargeItemClickListener;
import com.onetechsol.ipayment.pojo.RecentMobileRechargeItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class RecentMobileRechargeAdapter extends BaseRecyclerViewAdapter<RecentMobileRechargeItemBinding, RecentMobileRechargeItem, RecentMobileRechargeAdapter.RecentRemitterViewHolder> {


    private List<RecentMobileRechargeItem> recentMobileRechargeItem;


    private AdapterCallback callback;


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public RecentMobileRechargeAdapter setRecentMobileRechargeItem(List<RecentMobileRechargeItem> recentMobileRechargeItem) {
        this.recentMobileRechargeItem = recentMobileRechargeItem;
        notifyDataSetChanged();
        return this;
    }

    public List<RecentMobileRechargeItem> recentMobileRechargeItem() {
        return recentMobileRechargeItem;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public RecentMobileRechargeAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public RecentRemitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecentMobileRechargeItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recent_mobile_recharge_item, parent, false);

        return new RecentRemitterViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(RecentRemitterViewHolder holder, int position) {

        holder.onBind(recentMobileRechargeItem.get(position));
    }

    @Override
    public int getItemCount() {
        return recentMobileRechargeItem != null && recentMobileRechargeItem.size() > 0 ? recentMobileRechargeItem.size() : 0;
    }


    public interface AdapterCallback {
        void onItemClicked(RecentMobileRechargeItem reportItem);
    }

    public class RecentRemitterViewHolder extends BaseViewHolder<RecentMobileRechargeItemBinding, RecentMobileRechargeItem> implements RecentMobileRechargeItemClickListener {


        public RecentRemitterViewHolder(RecentMobileRechargeItemBinding recentRemitterItemBinding) {
            super(recentRemitterItemBinding);

        }

        @Override
        public void onBind(RecentMobileRechargeItem recentMobileRechargeItem) {

            binding().setRecentMobileRechargeItem(recentMobileRechargeItem);
            binding().setRecentMobileRechargeItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(RecentMobileRechargeItem recentMobileRechargeItem) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(recentMobileRechargeItem);
        }
    }

}
