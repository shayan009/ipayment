package com.onetechsol.ipayment.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.OtherProductItemBinding;
import com.onetechsol.ipayment.databinding.ProductClickListener;
import com.onetechsol.ipayment.databinding.ProductItemClickListener;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;
import com.onetechsol.ipayment.ui.screen.customer.AddCustomerActivity;
import com.onetechsol.ipayment.ui.screen.share.ShareActivity;

public class OtherProductListAdapter extends BaseRecyclerViewAdapter<OtherProductItemBinding, ProductModel, OtherProductListAdapter.OtherProductHolder> {


    private ProductClickListener productClickListener;


    public ProductClickListener creditCardClickListener() {
        return productClickListener;
    }

    public OtherProductListAdapter setCreditCardClickListener(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
        return this;
    }

    @NonNull
    @Override
    public OtherProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OtherProductItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.other_product_item, parent, false);
        return new OtherProductHolder(inflate);
    }

    public class OtherProductHolder extends BaseViewHolder<OtherProductItemBinding, ProductModel> implements ProductItemClickListener {


        public OtherProductHolder(OtherProductItemBinding otherProductItemBinding) {
            super(otherProductItemBinding);
        }

        @Override
        public void onBind(ProductModel productModel) {
            binding().setProductModel(productModel);

            Glide.with(binding().getRoot()).load(productModel.iconUrl()).apply(RequestOptions.circleCropTransform()).into(binding().tvProductImage);


            binding().setProductItemClickListener(this);
            binding().executePendingBindings();
        }


        @Override
        public void onClickItem(ProductModel productModel) {

            if (productClickListener != null)
                productClickListener.onProductSelected(productModel);

        }

        @Override
        public void shareLink(ProductModel productModel) {

            Intent intent = new Intent(binding().getRoot().getContext(), ShareActivity.class);
            intent.putExtra("productId", productModel.id());
            binding().getRoot().getContext().startActivity(intent);

        }

        @Override
        public void addCustomer(ProductModel productModel) {

            Intent intent = new Intent(binding().getRoot().getContext(), AddCustomerActivity.class);
            intent.putExtra("productModel", productModel);
            binding().getRoot().getContext().startActivity(intent);

        }
    }
}
