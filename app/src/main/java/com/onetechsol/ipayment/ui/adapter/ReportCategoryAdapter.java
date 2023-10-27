package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ReportCategoryItemBinding;
import com.onetechsol.ipayment.databinding.ReportTypeClickListener;
import com.onetechsol.ipayment.pojo.ReportTypeItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;


public class ReportCategoryAdapter extends BaseRecyclerViewAdapter<ReportCategoryItemBinding, ReportTypeItem, ReportCategoryAdapter.ReportTypeViewHolder> {


    private AdapterCallback callback;

    public AdapterCallback callback() {
        return callback;
    }

    public ReportCategoryAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public ReportTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ReportCategoryItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.report_category_item, parent, false);

        return new ReportTypeViewHolder(inflate);

    }

    public interface AdapterCallback {
        void onItemClicked(ReportTypeItem opCircleItemDto);
    }

    public class ReportTypeViewHolder extends BaseViewHolder<ReportCategoryItemBinding, ReportTypeItem> implements ReportTypeClickListener {


        public ReportTypeViewHolder(ReportCategoryItemBinding operatorCircleItemBinding) {
            super(operatorCircleItemBinding);

        }

        @Override
        public void onBind(ReportTypeItem opCircleItemDto) {


            //Glide.with(binding().getRoot()).load(Uri.parse(opCircleItemDto.image())).into(binding().ivOperatorCircleIcon);


            binding().setReportTypeItem(opCircleItemDto);
            binding().setReportTypeClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onOpItemClick(ReportTypeItem reportTypeItem) {
            if (callback != null)
                callback.onItemClicked(reportTypeItem);
        }
    }

}
