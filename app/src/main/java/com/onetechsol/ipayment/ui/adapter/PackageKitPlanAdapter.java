package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.KitItemBinding;
import com.onetechsol.ipayment.databinding.KitItemClickListener;
import com.onetechsol.ipayment.pojo.KitData;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class PackageKitPlanAdapter extends BaseRecyclerViewAdapter<KitItemBinding, KitData, PackageKitPlanAdapter.PackageKitHolder> {


    private AdapterCallback callback;

    public PackageKitPlanAdapter() {
        super();
    }

    public AdapterCallback callback() {
        return callback;
    }

    public PackageKitPlanAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public PackageKitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        KitItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.kit_item, parent, false);

        return new PackageKitHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(KitData kitData);

        void buyKit(KitData kitData);
    }

    public class PackageKitHolder extends BaseViewHolder<KitItemBinding, KitData> implements KitItemClickListener {


        public PackageKitHolder(KitItemBinding kitItemBinding) {
            super(kitItemBinding);
        }

        @Override
        public void onBind(KitData kitData) {
            binding().setKitData(kitData);
            binding().setKitItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(KitData kitData) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(kitData);
        }

        @Override
        public void buyKit(KitData kitData) {
            if (kitData.kit().equals("1")) {
                if (callback != null)
                    callback.buyKit(kitData);
            }
        }
    }

}
