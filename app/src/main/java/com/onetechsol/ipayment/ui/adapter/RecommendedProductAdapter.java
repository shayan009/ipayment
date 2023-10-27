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

import java.util.List;

public class RecommendedProductAdapter extends RecyclerView.Adapter<RecommendedProductAdapter.RecommendedProductViewHolder> {

    private List<RecommendedProductItem> recommendedProductItems;

    public void setRecommendedProductItems(List<RecommendedProductItem> recommendedProductItems) {
        this.recommendedProductItems = recommendedProductItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecommendedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecommendedProductItemBinding recommendedProductItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recommended_product_item, parent, false);
        return new RecommendedProductViewHolder(recommendedProductItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedProductViewHolder holder, int position) {
        holder.bind(recommendedProductItems.get(position));
    }

    @Override
    public int getItemCount() {
        return recommendedProductItems.size();
    }

    public static class RecommendedProductViewHolder extends RecyclerView.ViewHolder implements RecommentedProductClickListener {

        private RecommendedProductItemBinding recommendedProductItemBinding;

        public RecommendedProductViewHolder(RecommendedProductItemBinding recommendedProductItemBinding) {
            super(recommendedProductItemBinding.getRoot());
            this.recommendedProductItemBinding = recommendedProductItemBinding;
        }

        public void bind(RecommendedProductItem recommendedProductItem) {

            recommendedProductItemBinding.setRecommendedProductItem(recommendedProductItem);
            recommendedProductItemBinding.executePendingBindings();
            recommendedProductItemBinding.setRecommentedProductClickListener(this);
        }

        @Override
        public void selectProduct(View view, RecommendedProductItem recommendedProductItem) {
            ////Toast.makeText(view.getContext(), "Selected : "+recommendedProductItem.name(), //Toast.LENGTH_SHORT).show();
        }
    }
}
