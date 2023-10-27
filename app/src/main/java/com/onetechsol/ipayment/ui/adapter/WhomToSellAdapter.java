package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.WhomToSellItemBinding;
import com.onetechsol.ipayment.pojo.WhomToSellModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class WhomToSellAdapter extends BaseRecyclerViewAdapter<WhomToSellItemBinding, WhomToSellModel, WhomToSellAdapter.WhomToSellViewHolder> {


    private List<WhomToSellModel> whomToSellModels;

    public WhomToSellAdapter() {
        super();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public List<WhomToSellModel> whomToSellModels() {
        return whomToSellModels;
    }

    public WhomToSellAdapter setWhomToSellModels(List<WhomToSellModel> whomToSellModels) {
        this.whomToSellModels = whomToSellModels;
        notifyDataSetChanged();
        return this;
    }

    @NonNull
    @Override
    public WhomToSellAdapter.WhomToSellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        WhomToSellItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.whom_to_sell_item, parent, false);

        return new WhomToSellViewHolder(inflate);

    }


    @Override
    public void onBindViewHolder(WhomToSellViewHolder holder, int position) {

        holder.onBind(whomToSellModels.get(position));
    }


    @Override
    public int getItemCount() {
        return whomToSellModels != null && whomToSellModels.size() > 0 ? whomToSellModels.size() : 0;
    }


    public static class WhomToSellViewHolder extends BaseViewHolder<WhomToSellItemBinding, WhomToSellModel> {

        public WhomToSellViewHolder(WhomToSellItemBinding whomToSellItemBinding) {
            super(whomToSellItemBinding);

        }

        @Override
        public void onBind(WhomToSellModel whomToSellModel) {
            Glide.with(binding().getRoot()).load(whomToSellModel.image()).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivAudienceImg);
            binding().setWhomToSellModel(whomToSellModel);
            binding().executePendingBindings();
        }
    }

}
