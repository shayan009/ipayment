package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.SettingItemClickListener;
import com.onetechsol.ipayment.databinding.SettingsItemBinding;
import com.onetechsol.ipayment.pojo.SettingItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class SettingAdapter extends BaseRecyclerViewAdapter<SettingsItemBinding, SettingItem, SettingAdapter.SettingViewHolder> {


    private AdapterCallback callback;

    public SettingAdapter() {
        super();
    }

    public AdapterCallback callback() {
        return callback;
    }

    public SettingAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SettingsItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.settings_item, parent, false);

        return new SettingViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(SettingItem settingItem);
    }

    public class SettingViewHolder extends BaseViewHolder<SettingsItemBinding, SettingItem> implements SettingItemClickListener {


        public SettingViewHolder(SettingsItemBinding settingsItemBinding) {
            super(settingsItemBinding);
        }

        @Override
        public void onBind(SettingItem settingItem) {
            Glide.with(binding().getRoot()).load(settingItem.url()).into(binding().ivSettingIcon);
            binding().setSettingItem(settingItem);
            binding().setSettingItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(SettingItem settingItem) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(settingItem);
        }
    }

}
