package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RecommendedProductItemBinding;
import com.onetechsol.ipayment.databinding.RecommentedProductClickListener;
import com.onetechsol.ipayment.pojo.RecommendedProductItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;

public class RecommendedProductAdapter extends BaseRecyclerViewAdapter<RecommendedProductItemBinding, RecommendedProductItem, RecommendedProductAdapter.RecommendedProductViewHolder> {


    @NonNull
    @Override
    public RecommendedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecommendedProductItemBinding recommendedProductItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recommended_product_item, parent, false);
        return new RecommendedProductViewHolder(recommendedProductItemBinding);
    }


    public static class RecommendedProductViewHolder extends BaseViewHolder<RecommendedProductItemBinding, RecommendedProductItem> implements RecommentedProductClickListener {


        public RecommendedProductViewHolder(RecommendedProductItemBinding recommendedProductItemBinding) {
            super(recommendedProductItemBinding);
        }


        @Override
        public void onBind(RecommendedProductItem recommendedProductItem) {
            binding().setRecommendedProductItem(recommendedProductItem);
            binding().executePendingBindings();
            binding().setRecommentedProductClickListener(this);
        }



        @Override
        public void selectProduct(View view, RecommendedProductItem recommendedProductItem) {
            ////Toast.makeText(view.getContext(), "Selected : "+recommendedProductItem.name(), //Toast.LENGTH_SHORT).show();
        }
    }
}
