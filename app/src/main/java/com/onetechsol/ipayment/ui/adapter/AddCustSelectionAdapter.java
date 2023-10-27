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
import com.onetechsol.ipayment.pojo.AddCustSelectionItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

public class AddCustSelectionAdapter extends BaseRecyclerViewAdapter<AddCustSelectionItemBinding, AddCustSelectionItem, AddCustSelectionAdapter.AddCustSelectionViewHolder> {


    private AdapterCallback callback;

    public AdapterCallback callback() {
        return callback;
    }

    public AddCustSelectionAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public AddCustSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AddCustSelectionItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.add_cust_selection_item, parent, false);

        return new AddCustSelectionViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(AddCustSelectionItem addCustSelectionItem);
    }

    public class AddCustSelectionViewHolder extends BaseViewHolder<AddCustSelectionItemBinding, AddCustSelectionItem> implements AddCustSelectionItemClickListener {


        public AddCustSelectionViewHolder(AddCustSelectionItemBinding addCustSelectionItemBinding) {
            super(addCustSelectionItemBinding);

        }

        @Override
        public void onBind(AddCustSelectionItem addCustSelectionItem) {


            Glide.with(binding().getRoot()).load(Uri.parse(addCustSelectionItem.image())).apply(RequestOptions.circleCropTransform().error(R.drawable.hdfc_content)).into(binding().ivOperatorCircleIcon);

            binding().setAddCustSelectionItem(addCustSelectionItem);
            binding().setAddCustSelectionItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onAddCustSelectionClick(AddCustSelectionItem addCustSelectionItem) {
            if (callback != null)
                callback.onItemClicked(addCustSelectionItem);
        }
    }

}
