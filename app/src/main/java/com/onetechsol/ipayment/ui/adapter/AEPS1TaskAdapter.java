package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AEPS1ItemClickListener;
import com.onetechsol.ipayment.databinding.Aeps1ItemBinding;
import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class AEPS1TaskAdapter extends BaseRecyclerViewAdapter<Aeps1ItemBinding, AEPS1TaskModel, AEPS1TaskAdapter.AEPS1TaskViewHolder> {


    private List<AEPS1TaskModel> aeps1TaskModelList;


    private AdapterCallback callback;

    public AEPS1TaskAdapter() {
        super();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setAEPS1TaskModelList(List<AEPS1TaskModel> aeps1TaskModelList) {
        this.aeps1TaskModelList = aeps1TaskModelList;
    }

    public List<AEPS1TaskModel> aeps1TaskModelList() {
        return aeps1TaskModelList;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public AEPS1TaskAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public AEPS1TaskAdapter.AEPS1TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Aeps1ItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.aeps1_item, parent, false);

        return new AEPS1TaskViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(AEPS1TaskViewHolder holder, int position) {

        holder.onBind(aeps1TaskModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return aeps1TaskModelList.size();
    }


    public interface AdapterCallback {
        void onItemClicked(AEPS1TaskModel serviceModel);
    }

    public class AEPS1TaskViewHolder extends BaseViewHolder<Aeps1ItemBinding, AEPS1TaskModel> implements AEPS1ItemClickListener {


        public AEPS1TaskViewHolder(Aeps1ItemBinding serviceItemBinding) {
            super(serviceItemBinding);

        }

        @Override
        public void onBind(AEPS1TaskModel serviceModel) {
            Glide.with(binding().getRoot()).load(serviceModel.img()).apply(RequestOptions.circleCropTransform()).into(binding().ivAepstaskIcon);
            binding().setAeps1TaskModel(serviceModel);
            binding().setAeps1ItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(AEPS1TaskModel aeps1TaskModel) {
            // //Toast.makeText(binding().getRoot().getContext(),aeps1TaskModel.name(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(aeps1TaskModel);
        }
    }

}
