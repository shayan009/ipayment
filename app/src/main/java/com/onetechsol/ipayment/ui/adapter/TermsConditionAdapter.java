package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.TermsConditionItemBinding;
import com.onetechsol.ipayment.pojo.TermsConditionsModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class TermsConditionAdapter extends BaseRecyclerViewAdapter<TermsConditionItemBinding, TermsConditionsModel, TermsConditionAdapter.TermsConditionViewHolder> {


    private List<TermsConditionsModel> termsConditionsModels;

    public TermsConditionAdapter() {
        super();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public List<TermsConditionsModel> termsConditionsModels() {
        return termsConditionsModels;
    }

    public TermsConditionAdapter setTermsConditionsModels(List<TermsConditionsModel> termsConditionsModels) {
        this.termsConditionsModels = termsConditionsModels;
        notifyDataSetChanged();
        return this;
    }

    @NonNull
    @Override
    public TermsConditionAdapter.TermsConditionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TermsConditionItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.terms_condition_item, parent, false);

        return new TermsConditionViewHolder(inflate);

    }


    @Override
    public void onBindViewHolder(TermsConditionViewHolder holder, int position) {

        holder.onBind(termsConditionsModels.get(position));
    }


    @Override
    public int getItemCount() {
        return termsConditionsModels != null && termsConditionsModels.size() > 0 ? termsConditionsModels.size() : 0;
    }


    public static class TermsConditionViewHolder extends BaseViewHolder<TermsConditionItemBinding, TermsConditionsModel> {

        public TermsConditionViewHolder(TermsConditionItemBinding instructionItemBinding) {
            super(instructionItemBinding);

        }

        @Override
        public void onBind(TermsConditionsModel termsConditionsModel) {
            Glide.with(binding().getRoot()).load(termsConditionsModel.image()).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivTocImg);
            binding().setTermsConditionsModel(termsConditionsModel);
            binding().executePendingBindings();
        }
    }

}
