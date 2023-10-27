package com.onetechsol.ipayment.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RewardItemBinding;
import com.onetechsol.ipayment.pojo.BenefitModel;

import java.util.List;

public class CCRewardListAdapter extends RecyclerView.Adapter<CCRewardListAdapter.RewardHolder> {

    private static String textColor;
    private List<BenefitModel> rewards;

    public void setRewards(List<BenefitModel> rewards, String textColor) {
        this.rewards = rewards;
        this.textColor = textColor;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RewardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RewardItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.reward_item, parent, false);
        return new RewardHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardHolder cardHolder, int position) {

        cardHolder.bind(rewards.get(position));

    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

    public static class RewardHolder extends RecyclerView.ViewHolder {

        private RewardItemBinding rewardItemBinding;

        public RewardHolder(RewardItemBinding rewardItemBinding) {
            super(rewardItemBinding.getRoot());
            this.rewardItemBinding = rewardItemBinding;
        }


        public void bind(BenefitModel benefit) {
            rewardItemBinding.tvRewardName.setTextColor(Color.parseColor(textColor));
            rewardItemBinding.setName(benefit.name());
            rewardItemBinding.executePendingBindings();
        }

    }
}
