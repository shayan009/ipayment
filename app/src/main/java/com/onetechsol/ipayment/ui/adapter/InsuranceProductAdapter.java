package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.InsuranceProductItemBinding;
import com.onetechsol.ipayment.databinding.ProductItemClickListener;
import com.onetechsol.ipayment.pojo.InsuranceProduct;
import com.onetechsol.ipayment.pojo.ProductModel;

import java.util.List;

public class InsuranceProductAdapter extends RecyclerView.Adapter<InsuranceProductAdapter.ProductViewHolder> {

    private List<InsuranceProduct> insuranceProductList;

    public void setInsuranceProductList(List<InsuranceProduct> insuranceProductList) {
        this.insuranceProductList = insuranceProductList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InsuranceProductItemBinding insuranceProductItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.insurance_product_item, parent, false);
        return new ProductViewHolder(insuranceProductItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(insuranceProductList.get(position));
    }

    @Override
    public int getItemCount() {
        return insuranceProductList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements ProductItemClickListener {


        private InsuranceProductItemBinding insuranceProductItemBinding;

        public ProductViewHolder(InsuranceProductItemBinding insuranceProductItemBinding) {
            super(insuranceProductItemBinding.getRoot());
            this.insuranceProductItemBinding = insuranceProductItemBinding;
        }

        public void bind(InsuranceProduct insuranceProduct) {
            insuranceProductItemBinding.setInsuranceProduct(insuranceProduct);
            insuranceProductItemBinding.executePendingBindings();
            insuranceProductItemBinding.setProductItemClickListener(this);
        }

        @Override
        public void onClickItem(ProductModel productModel) {
            ////Toast.makeText(insuranceProductItemBinding.getRoot().getContext(), "Selected : "+productModel.name(), //Toast.LENGTH_SHORT).show();

        }

        @Override
        public void shareLink(ProductModel productModel) {

        }

        @Override
        public void addCustomer(ProductModel productModel) {

        }
    }
}
