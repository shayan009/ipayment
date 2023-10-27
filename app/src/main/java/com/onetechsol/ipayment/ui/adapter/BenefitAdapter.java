package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.BenefitItemBinding;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class BenefitAdapter extends BaseRecyclerViewAdapter<BenefitItemBinding, BenefitModel, BenefitAdapter.BenefitViewHolder> {


    private List<BenefitModel> benefitList;

    public BenefitAdapter() {
        super();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void setBenefitList(List<BenefitModel> benefitList) {
        this.benefitList = benefitList;
        notifyDataSetChanged();
    }

    public List<BenefitModel> benefitList() {
        return benefitList;
    }


    @NonNull
    @Override
    public BenefitAdapter.BenefitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BenefitItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.benefit_item, parent, false);

        return new BenefitViewHolder(inflate);

    }


    @Override
    public void onBindViewHolder(BenefitViewHolder holder, int position) {

        holder.onBind(benefitList.get(position));
    }


    @Override
    public int getItemCount() {
        return benefitList != null && benefitList.size() > 0 ? benefitList.size() : 0;
    }


    public static class BenefitViewHolder extends BaseViewHolder<BenefitItemBinding, BenefitModel> {

        public BenefitViewHolder(BenefitItemBinding benefitItemBinding) {
            super(benefitItemBinding);

        }

        @Override
        public void onBind(BenefitModel benefit) {
            Glide.with(binding().getRoot()).load(benefit.image()).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivRewardImg);
            binding().setBenefit(benefit);
            binding().executePendingBindings();
        }
    }

}
