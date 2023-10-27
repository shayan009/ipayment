package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.PlanTitleClickListener;
import com.onetechsol.ipayment.databinding.PlanTitleItemBinding;
import com.onetechsol.ipayment.databinding.RechargeOnClickListener;
import com.onetechsol.ipayment.pojo.PlanItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class PlanTitleAdapter extends BaseRecyclerViewAdapter<PlanTitleItemBinding, PlanItem, PlanTitleAdapter.PlanTitleViewHolder> {


    private RechargeOnClickListener rechargeOnClickListener;

    public PlanTitleAdapter() {
        super();
    }


    public RechargeOnClickListener planOnClickListener() {
        return rechargeOnClickListener;
    }

    public PlanTitleAdapter setPlanOnClickListener(RechargeOnClickListener rechargeOnClickListener) {
        this.rechargeOnClickListener = rechargeOnClickListener;
        return this;
    }

    @NonNull
    @Override
    public PlanTitleAdapter.PlanTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlanTitleItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.plan_title_item, parent, false);
        return new PlanTitleViewHolder(inflate);

    }

    public class PlanTitleViewHolder extends BaseViewHolder<PlanTitleItemBinding, PlanItem> implements PlanTitleClickListener {


        public PlanTitleViewHolder(PlanTitleItemBinding planTitleItemBinding) {
            super(planTitleItemBinding);

        }

        @Override
        public void onBind(PlanItem planItem) {
            binding().setPlanItem(planItem);
            binding().setPlanTitleClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void selectedPlan(PlanItem planItem) {

            rechargeOnClickListener.selectedPlan(planItem);


        }
    }

}
