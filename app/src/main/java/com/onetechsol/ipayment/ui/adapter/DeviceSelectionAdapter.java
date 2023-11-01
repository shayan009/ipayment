package com.onetechsol.ipayment.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AddCustSelectionItemBinding;
import com.onetechsol.ipayment.databinding.AddCustSelectionItemClickListener;
import com.onetechsol.ipayment.databinding.DeviceSelectionItemBinding;
import com.onetechsol.ipayment.databinding.DeviceSelectionItemClickListener;
import com.onetechsol.ipayment.pojo.AddCustSelectionItem;
import com.onetechsol.ipayment.pojo.DeviceSelectionItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

public class DeviceSelectionAdapter extends BaseRecyclerViewAdapter<DeviceSelectionItemBinding, DeviceSelectionItem, DeviceSelectionAdapter.DeviceSelectionViewHolder> {


    private AdapterCallback callback;

    public AdapterCallback callback() {
        return callback;
    }

    public DeviceSelectionAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public DeviceSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        DeviceSelectionItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.device_selection_item, parent, false);

        return new DeviceSelectionViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(DeviceSelectionItem deviceSelectionItem);
    }

    public class DeviceSelectionViewHolder extends BaseViewHolder<DeviceSelectionItemBinding, DeviceSelectionItem> implements DeviceSelectionItemClickListener {


        public DeviceSelectionViewHolder(DeviceSelectionItemBinding deviceSelectionItemBinding) {
            super(deviceSelectionItemBinding);

        }

        @Override
        public void onBind(DeviceSelectionItem deviceSelectionItem) {


            Glide.with(binding().getRoot()).load(Uri.parse(deviceSelectionItem.image())).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivOperatorCircleIcon);

            binding().setDeviceSelectionItem(deviceSelectionItem);
            binding().setDeviceSelectionItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onDeviceSelectionClick(DeviceSelectionItem deviceSelectionItem) {
            if (callback != null)
                callback.onItemClicked(deviceSelectionItem);
        }

    }

}
