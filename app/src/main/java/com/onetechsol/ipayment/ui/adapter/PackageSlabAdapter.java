package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.SlabItemBinding;
import com.onetechsol.ipayment.pojo.PackageSlabItem;
import com.onetechsol.ipayment.pojo.SettingItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class PackageSlabAdapter extends BaseRecyclerViewAdapter<SlabItemBinding, PackageSlabItem, PackageSlabAdapter.PackageSlabViewHolder> {


    private AdapterCallback callback;

    public PackageSlabAdapter() {
        super();
    }

    public AdapterCallback callback() {
        return callback;
    }

    public PackageSlabAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public PackageSlabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SlabItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.slab_item, parent, false);

        return new PackageSlabViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(SettingItem settingItem);
    }

    public class PackageSlabViewHolder extends BaseViewHolder<SlabItemBinding, PackageSlabItem> {


        public PackageSlabViewHolder(SlabItemBinding slabItemBinding) {
            super(slabItemBinding);
        }

        @Override
        public void onBind(PackageSlabItem packageSlabItem) {
            binding().setPackageSlabItem(packageSlabItem);
            binding().executePendingBindings();
        }
    }

}
