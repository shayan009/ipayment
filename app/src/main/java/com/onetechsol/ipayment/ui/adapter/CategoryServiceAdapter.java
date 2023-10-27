package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ServiceCategoryItemBinding;
import com.onetechsol.ipayment.databinding.ServiceCategoryItemClickListener;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class CategoryServiceAdapter extends BaseRecyclerViewAdapter<ServiceCategoryItemBinding, ServiceCategoryModel, CategoryServiceAdapter.ServiceCategoryViewHolder> {


    private static AdapterCallback callback;
    private List<ServiceCategoryModel> serviceModelList;

    public CategoryServiceAdapter() {
    }

    public AdapterCallback callback() {
        return callback;
    }

    public CategoryServiceAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    public void setServiceCategoryModelList(List<ServiceCategoryModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
    }

    @NonNull
    @Override
    public ServiceCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceCategoryItemBinding serviceItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.service_category_item, parent, false);


        return new ServiceCategoryViewHolder(serviceItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceCategoryViewHolder holder, int position) {

        holder.onBind(serviceModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return serviceModelList.size();
    }

    public interface AdapterCallback {
        void onItemClicked(ServiceCategoryModel serviceCategoryModel);
    }

    public static class ServiceCategoryViewHolder extends BaseViewHolder<ServiceCategoryItemBinding, ServiceCategoryModel> implements ServiceCategoryItemClickListener {


        public ServiceCategoryViewHolder(ServiceCategoryItemBinding serviceCategoryItemBinding) {
            super(serviceCategoryItemBinding);

        }

        @Override
        public void onBind(ServiceCategoryModel serviceCategoryModel) {

            Glide.with(binding().getRoot()).load(serviceCategoryModel.imagePath()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(binding().ivServiceItemIcon);
            binding().setServiceModel(serviceCategoryModel);
            binding().setServiceItemClickListener(this);
            binding().executePendingBindings();

        }

        @Override
        public void onClickItem(ServiceCategoryModel categoryModel) {

            if (callback != null) {
                callback.onItemClicked(categoryModel);
            }
        }
    }

}
