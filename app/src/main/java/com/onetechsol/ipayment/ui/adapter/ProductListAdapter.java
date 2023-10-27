package com.onetechsol.ipayment.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ProductClickListener;
import com.onetechsol.ipayment.databinding.ProductItemBinding;
import com.onetechsol.ipayment.databinding.ProductItemClickListener;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.ui.screen.customer.AddCustomerActivity;
import com.onetechsol.ipayment.ui.screen.share.ShareActivity;
import com.onetechsol.ipayment.utils.GlideImageLoader;

import java.util.List;

import kotlin.jvm.JvmStatic;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductHolder> {

    private List<ProductModel> productModelList;
    private ProductClickListener productClickListener;

    public void setCreditCardProductList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        notifyDataSetChanged();
    }

    public ProductClickListener creditCardClickListener() {
        return productClickListener;
    }

    public ProductListAdapter setCreditCardClickListener(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
        return this;
    }

    public List<ProductModel> creditCardProductList() {
        return productModelList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        com.onetechsol.ipayment.databinding.ProductItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item, parent, false);
        return new ProductHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder cardHolder, int position) {

        cardHolder.bind(productModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements ProductItemClickListener {

        private ProductItemBinding productItemBinding;

        public ProductHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        public void bind(ProductModel productModel) {
            productItemBinding.setProductModel(productModel);
            productItemBinding.setProductItemClickListener(this);
            setImageUrl(productItemBinding.ivCCIcon, productModel);
            productItemBinding.executePendingBindings();

            CCRewardListAdapter ccRewardListAdapter = new CCRewardListAdapter();
            productItemBinding.setCcRewardListAdapter(ccRewardListAdapter);

            GoalListAdapter goalListAdapter = new GoalListAdapter();
            productItemBinding.setGoalListAdapter(goalListAdapter);

            goalListAdapter.setGoalModels(productModel.goalModels());
            ccRewardListAdapter.setRewards(productModel.rewards(), productModel.contentColor());

        }

        @JvmStatic
        @BindingAdapter("android:src")
        public void setImageUrl(AppCompatImageView appCompatImageView, ProductModel productModel) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .priority(Priority.HIGH);

            new GlideImageLoader(appCompatImageView,
                    null).load(productModel.iconUrl(), options);

        }

        @Override
        public void onClickItem(ProductModel productModel) {

            if (productClickListener != null)
                productClickListener.onProductSelected(productModel);

        }

        @Override
        public void shareLink(ProductModel productModel) {

            Intent intent = new Intent(productItemBinding.getRoot().getContext(), ShareActivity.class);
            intent.putExtra("productId", productModel.id());
            productItemBinding.getRoot().getContext().startActivity(intent);

        }

        @Override
        public void addCustomer(ProductModel productModel) {

            Intent intent = new Intent(productItemBinding.getRoot().getContext(), AddCustomerActivity.class);
            intent.putExtra("productModel", productModel);
            productItemBinding.getRoot().getContext().startActivity(intent);

        }
    }
}
