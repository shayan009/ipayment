package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ServiceFragClickListener;
import com.onetechsol.ipayment.databinding.SideMenuItemBinding;
import com.onetechsol.ipayment.databinding.SideMenuItemClickListener;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.Objects;

public class SideMenuListAdapter extends BaseRecyclerViewAdapter<SideMenuItemBinding, ServiceModel, SideMenuListAdapter.SideMenuHolder> {

    private int currentPosition;

    private ServiceFragClickListener serviceFragClickListener;


    public ServiceFragClickListener serviceFragClickListener() {
        return serviceFragClickListener;
    }

    public SideMenuListAdapter setServiceFragClickListener(ServiceFragClickListener serviceFragClickListener) {
        this.serviceFragClickListener = serviceFragClickListener;
        return this;
    }

    public SideMenuListAdapter setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        return this;
    }

    public int currentPosition() {
        return currentPosition;
    }


    @NonNull
    @Override
    public SideMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SideMenuItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.side_menu_item, parent, false);
        return new SideMenuHolder(inflate);
    }


    public class SideMenuHolder extends BaseViewHolder<SideMenuItemBinding, ServiceModel> implements SideMenuItemClickListener {


        public SideMenuHolder(SideMenuItemBinding sideMenuItemBinding) {
            super(sideMenuItemBinding);
        }

        @Override
        public void onBind(ServiceModel serviceModel) {
            binding().setServiceModel(serviceModel);
            binding().setSideMenuItemClickListener(this);
            binding().executePendingBindings();
        }


        @Override
        public void onClickItem(ServiceModel sideMenuItem) {


            for (int i = 0; i < items().size(); i++) {
                if (Objects.equals(items().get(i).title(), sideMenuItem.title())) {
                    items().get(i).setSelected(true);
                    items().get(i).setColor("#292929");
                } else {
                    items().get(i).setSelected(false);
                    items().get(i).setColor("#BAC0C6");
                }
            }
            getBindingAdapter().notifyDataSetChanged();

            serviceFragClickListener.openCategory(getLayoutPosition());

        }

    }
}
