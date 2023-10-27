package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RecentRemitterItemBinding;
import com.onetechsol.ipayment.databinding.RecentRemitterItemClickListener;
import com.onetechsol.ipayment.pojo.RecentRemitterItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class RecentRemitterAdapter extends BaseRecyclerViewAdapter<RecentRemitterItemBinding, RecentRemitterItem, RecentRemitterAdapter.RecentRemitterViewHolder> {


    private List<RecentRemitterItem> recentRemitterItems;


    private AdapterCallback callback;


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public RecentRemitterAdapter setRecentRemitterItems(List<RecentRemitterItem> recentRemitterItems) {
        this.recentRemitterItems = recentRemitterItems;
        notifyDataSetChanged();
        return this;
    }

    public List<RecentRemitterItem> recentRemitterItems() {
        return recentRemitterItems;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public RecentRemitterAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public RecentRemitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecentRemitterItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recent_remitter_item, parent, false);

        return new RecentRemitterViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(RecentRemitterViewHolder holder, int position) {

        holder.onBind(recentRemitterItems.get(position));
    }

    @Override
    public int getItemCount() {
        return recentRemitterItems != null && recentRemitterItems.size() > 0 ? recentRemitterItems.size() : 0;
    }


    public interface AdapterCallback {
        void onItemClicked(RecentRemitterItem reportItem);
    }

    public class RecentRemitterViewHolder extends BaseViewHolder<RecentRemitterItemBinding, RecentRemitterItem> implements RecentRemitterItemClickListener {


        public RecentRemitterViewHolder(RecentRemitterItemBinding recentRemitterItemBinding) {
            super(recentRemitterItemBinding);

        }

        @Override
        public void onBind(RecentRemitterItem recentRemitterItem) {

            binding().setRecentRemitterItem(recentRemitterItem);
            binding().setRecentRemitterItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(RecentRemitterItem serviceModel) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(serviceModel);
        }
    }

}
