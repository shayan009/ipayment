package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.CCFilterItemClickListener;
import com.onetechsol.ipayment.databinding.FilterItemBinding;
import com.onetechsol.ipayment.databinding.ProductClickListener;
import com.onetechsol.ipayment.pojo.ProductModel;

import java.util.List;

public class ProductFilterListAdapter extends RecyclerView.Adapter<ProductFilterListAdapter.CardHolder> {

    private List<ProductModel> productFilterItems;

    private ProductClickListener productClickListener;

    public void setProductNames(List<ProductModel> productFilterItems) {
        this.productFilterItems = productFilterItems;
        notifyDataSetChanged();
    }

    public ProductClickListener productClickListener() {
        return productClickListener;
    }

    public ProductFilterListAdapter setProductClickListener(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
        return this;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilterItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.filter_item, parent, false);
        return new CardHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder cardHolder, int position) {

        cardHolder.bind(productFilterItems.get(position));

    }

    @Override
    public int getItemCount() {
        return productFilterItems != null ? productFilterItems.size() : 0;
    }

    public class CardHolder extends RecyclerView.ViewHolder implements CCFilterItemClickListener {

        private FilterItemBinding filterItemBinding;

        public CardHolder(FilterItemBinding filterItemBinding) {
            super(filterItemBinding.getRoot());
            this.filterItemBinding = filterItemBinding;
        }


        public void bind(ProductModel productFilterItem) {
            ((TextView) filterItemBinding.getRoot().findViewById(R.id.chipCCName)).setText(productFilterItem.name());
            filterItemBinding.setProductModel(productFilterItem);
            filterItemBinding.setCcFilterItemClickListener(this);
            filterItemBinding.executePendingBindings();


            filterItemBinding.chipCCName.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    productClickListener.onProductSelected(filterItemBinding.chipCCName.getText().toString());
                } else
                    productClickListener.onProductSelected("");

            });
        }

        @Override
        public void onClickItem(View view, ProductModel name) {


        }
    }
}
