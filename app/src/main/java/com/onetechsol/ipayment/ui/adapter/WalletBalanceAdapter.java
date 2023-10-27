package com.onetechsol.ipayment.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.WalletBalanceItemBinding;
import com.onetechsol.ipayment.databinding.WalletBalanceItemClickListener;
import com.onetechsol.ipayment.pojo.WalletBalanceModel;
import com.onetechsol.ipayment.pojo.WalletType;

import java.util.List;


public class WalletBalanceAdapter extends RecyclerView.Adapter<WalletBalanceAdapter.WalletBalanceModelViewHolder> {


    private List<WalletBalanceModel> walletBalanceModels;

    public WalletBalanceAdapter() {
    }

    public void setSellEarnModelList(List<WalletBalanceModel> walletBalanceModels) {
        this.walletBalanceModels = walletBalanceModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletBalanceModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WalletBalanceItemBinding walletBalanceItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.wallet_balance_item, parent, false);


        return new WalletBalanceModelViewHolder(walletBalanceItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletBalanceModelViewHolder holder, int position) {

        holder.bind(walletBalanceModels.get(position));
    }

    @Override
    public int getItemCount() {
        return walletBalanceModels.size();
    }

    public static class WalletBalanceModelViewHolder extends RecyclerView.ViewHolder implements WalletBalanceItemClickListener {

        WalletBalanceItemBinding walletBalanceItemBinding;

        public WalletBalanceModelViewHolder(WalletBalanceItemBinding walletBalanceItemBinding) {
            super(walletBalanceItemBinding.getRoot());
            this.walletBalanceItemBinding = walletBalanceItemBinding;
        }

        public void bind(WalletBalanceModel walletBalanceModel) {

            if (walletBalanceModel.walletType() == WalletType.COMMISSION) {
                walletBalanceItemBinding.mcvItem.setCardBackgroundColor(Color.parseColor("#EDECFF"));
                walletBalanceItemBinding.mcvInnerIcon.setCardBackgroundColor(Color.parseColor("#D9D7FE"));
            } else if (walletBalanceModel.walletType() == WalletType.MAIN) {
                walletBalanceItemBinding.mcvItem.setCardBackgroundColor(Color.parseColor("#FFEEDB"));
                walletBalanceItemBinding.mcvInnerIcon.setCardBackgroundColor(Color.parseColor("#FCE1C3"));
            } else if (walletBalanceModel.walletType() == WalletType.SETTLEMENT) {
                walletBalanceItemBinding.mcvItem.setCardBackgroundColor(Color.parseColor("#EADDFF"));
                walletBalanceItemBinding.mcvInnerIcon.setCardBackgroundColor(Color.parseColor("#D0BCFF"));
            }
            walletBalanceItemBinding.setWalletBalanceModel(walletBalanceModel);
            walletBalanceItemBinding.setWalletBalanceItemClickListener(this);
            Glide.with(walletBalanceItemBinding.getRoot()).load(walletBalanceModel.image()).apply(RequestOptions.circleCropTransform()).into(walletBalanceItemBinding.ivWalletIcon);

            walletBalanceItemBinding.executePendingBindings();


        }


        @Override
        public void onClickItem(View view, WalletBalanceModel walletBalanceModel) {
            //Toast.makeText(view.getContext(), walletBalanceModel.walletType().getWalletType(), //Toast.LENGTH_SHORT).show();
            if (walletBalanceModel.walletType().equals(WalletType.COMMISSION)) {
                //view.getContext().startActivity(new Intent(view.getContext(), InsuranceActivity.class));
            } else if (walletBalanceModel.walletType().equals(WalletType.MAIN)) {
                //view.getContext().startActivity(new Intent(view.getContext(), CreditCardActivity.class));
            } else if (walletBalanceModel.walletType().equals(WalletType.SETTLEMENT)) {
                //view.getContext().startActivity(new Intent(view.getContext(), CreditCardActivity.class));
            }
        }
    }

}
