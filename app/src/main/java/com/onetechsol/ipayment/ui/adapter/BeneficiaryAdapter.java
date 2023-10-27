package com.onetechsol.ipayment.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.BeneficiaryItemBinding;
import com.onetechsol.ipayment.databinding.BeneficiaryItemClickListener;
import com.onetechsol.ipayment.pojo.BeneficiaryModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class BeneficiaryAdapter extends BaseRecyclerViewAdapter<BeneficiaryItemBinding, BeneficiaryModel, BeneficiaryAdapter.BeneficiaryViewHolder> {


    private List<BeneficiaryModel> beneficiaryModelList;


    private AdapterCallback callback;


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setBeneficiaryModelList(List<BeneficiaryModel> beneficiaryModelList) {
        this.beneficiaryModelList = beneficiaryModelList;
        notifyDataSetChanged();
    }

    public List<BeneficiaryModel> beneficiaryModelList() {
        return beneficiaryModelList;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public BeneficiaryAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public BeneficiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BeneficiaryItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.beneficiary_item, parent, false);

        return new BeneficiaryViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(BeneficiaryViewHolder holder, int position) {

        holder.onBind(beneficiaryModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return beneficiaryModelList != null && beneficiaryModelList.size() > 0 ? beneficiaryModelList.size() : 0;
    }


    public interface AdapterCallback {
        void onItemClicked(BeneficiaryModel beneficiaryModel);
    }

    public class BeneficiaryViewHolder extends BaseViewHolder<BeneficiaryItemBinding, BeneficiaryModel> implements BeneficiaryItemClickListener {


        public BeneficiaryViewHolder(BeneficiaryItemBinding beneficiaryItemBinding) {
            super(beneficiaryItemBinding);
        }

        @Override
        public void onBind(BeneficiaryModel beneficiaryModel) {

            Uri uri = null;
            if (beneficiaryModel.beneficiaryBank().equalsIgnoreCase("State Bank of India")) {
                uri = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_sbi);
            }

            Uri status = null;
            if (beneficiaryModel.beneVerify().equalsIgnoreCase("Not Verify")) {
                status = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_failed);
            } else {
                status = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_success);
            }

            Glide.with(binding().getRoot()).load(status).apply(RequestOptions.circleCropTransform()).error(R.drawable.error_image).into(binding().ivAepsStausIcon);
            Glide.with(binding().getRoot()).load(uri).apply(RequestOptions.circleCropTransform()).error(R.drawable.error_image).into(binding().ivAepsStausIcon);
            binding().setBeneficiaryModel(beneficiaryModel);
            binding().setBeneficiaryItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(BeneficiaryModel serviceModel) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(serviceModel);
        }
    }

}
