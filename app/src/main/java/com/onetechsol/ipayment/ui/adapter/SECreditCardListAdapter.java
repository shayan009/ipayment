package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ProductItemClickListener;
import com.onetechsol.ipayment.databinding.SeCreditCardItemBinding;
import com.onetechsol.ipayment.pojo.ProductModel;

import java.util.List;

public class SECreditCardListAdapter extends RecyclerView.Adapter<SECreditCardListAdapter.CreditCardHolder> {

    private List<ProductModel> productModelList;

    public void setCreditCardProductList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CreditCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeCreditCardItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.se_credit_card_item, parent, false);
        return new CreditCardHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditCardHolder cardHolder, int position) {

        cardHolder.bind(productModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class CreditCardHolder extends RecyclerView.ViewHolder implements ProductItemClickListener {

        private SeCreditCardItemBinding creditCardItemBinding;

        public CreditCardHolder(SeCreditCardItemBinding creditCardItemBinding) {
            super(creditCardItemBinding.getRoot());
            this.creditCardItemBinding = creditCardItemBinding;
        }

        public void bind(ProductModel productModel) {
            //creditCardItemBinding.mbAddCustomer.setPaintFlags( Paint.UNDERLINE_TEXT_FLAG);
            creditCardItemBinding.setProductModel(productModel);
            creditCardItemBinding.setCreditCardItemClickListener(this);
            //setImageUrl(creditCardItemBinding.ivCCIcon,creditCardProduct);
            creditCardItemBinding.executePendingBindings();

            CCRewardListAdapter ccRewardListAdapter = new CCRewardListAdapter();
            creditCardItemBinding.setCcRewardListAdapter(ccRewardListAdapter);

            ccRewardListAdapter.setRewards(productModel.rewards(), productModel.contentColor());

        }
     /*   @JvmStatic
        @BindingAdapter("android:src")
        public  void setImageUrl(ShapeableImageView appCompatImageView, CreditCardProduct creditCardProduct){

            RequestOptions options = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .priority(Priority.HIGH);

            new GlideImageLoader(appCompatImageView,
                    null).load(creditCardProduct.iconUrl(),options);

        }*/

        @Override
        public void onClickItem(ProductModel productModel) {

        }

        @Override
        public void shareLink(ProductModel productModel) {

        }

        @Override
        public void addCustomer(ProductModel productModel) {

        }
    }
}
