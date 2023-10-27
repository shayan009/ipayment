package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DirectionItemBinding;
import com.onetechsol.ipayment.pojo.ShareLinkDirectionModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class ShareLinkCustomerAdapter extends BaseRecyclerViewAdapter<DirectionItemBinding, ShareLinkDirectionModel, ShareLinkCustomerAdapter.ShareLinkCustomerHolder> {


    public ShareLinkCustomerAdapter() {
        super();
    }


    @NonNull
    @Override
    public ShareLinkCustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        DirectionItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.direction_item, parent, false);

        return new ShareLinkCustomerHolder(inflate);

    }


    public class ShareLinkCustomerHolder extends BaseViewHolder<DirectionItemBinding, ShareLinkDirectionModel> {


        public ShareLinkCustomerHolder(DirectionItemBinding directionItemBinding) {
            super(directionItemBinding);
        }

        @Override
        public void onBind(ShareLinkDirectionModel shareLinkDirectionModel) {
            binding().setShareLinkDirectionModel(shareLinkDirectionModel);
            binding().executePendingBindings();
        }
    }

}
