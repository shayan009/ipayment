package com.onetechsol.ipayment.ui.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.ServiceItemBinding;
import com.onetechsol.ipayment.databinding.ServiceItemClickListener;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class ServiceAdapter extends BaseRecyclerViewAdapter<ServiceItemBinding, ServiceModel, ServiceAdapter.ServiceViewHolder> {


    int[] arr = {R.drawable.icon_banking, R.drawable.icon_rechargebill, R.drawable.icon_insurance, R.drawable.icon_more};
    private AdapterCallback callback;

    public ServiceAdapter() {
        super();
    }

    public AdapterCallback callback() {
        return callback;
    }

    public ServiceAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ServiceItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.service_item, parent, false);

        return new ServiceViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(ServiceModel serviceModel);
    }

    public class ServiceViewHolder extends BaseViewHolder<ServiceItemBinding, ServiceModel> implements ServiceItemClickListener {

        public ServiceViewHolder(ServiceItemBinding serviceItemBinding) {
            super(serviceItemBinding);

        }

        @Override
        public void onBind(ServiceModel serviceModel) {
            Log.d("model", String.valueOf(serviceModel.id()));
            Log.d("getLayoutPosition()", String.valueOf(getLayoutPosition()));

            Uri uri = Uri.parse(serviceModel.imagePath());
            if (getLayoutPosition() >= 0 && getLayoutPosition() <= 3)
                uri = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + arr[getLayoutPosition()]);

            Glide.with(binding().getRoot()).load(uri).apply(RequestOptions.circleCropTransform()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding().ivServiceItemIcon);
            binding().setServiceModel(serviceModel);
            binding().setServiceItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(ServiceModel serviceModel) {
            //Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(serviceModel);
        }
    }

}
