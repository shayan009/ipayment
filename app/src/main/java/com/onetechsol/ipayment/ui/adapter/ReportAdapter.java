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
import com.onetechsol.ipayment.databinding.ReportItemClickListener;
import com.onetechsol.ipayment.databinding.ReportListItemBinding;
import com.onetechsol.ipayment.pojo.ReportItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.List;


public class ReportAdapter extends BaseRecyclerViewAdapter<ReportListItemBinding, ReportItem, ReportAdapter.ReportViewHolder> {


    private List<ReportItem> reportItemList;


    private AdapterCallback callback;


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setReportItemList(List<ReportItem> reportItemList) {
        this.reportItemList = reportItemList;
        notifyDataSetChanged();
    }

    public List<ReportItem> reportItemList() {
        return reportItemList;
    }

    public AdapterCallback callback() {
        return callback;
    }

    public ReportAdapter setCallback(AdapterCallback callback) {
        this.callback = callback;
        return this;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ReportListItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.report_list_item, parent, false);

        return new ReportViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {

        holder.onBind(reportItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return reportItemList != null && reportItemList.size() > 0 ? reportItemList.size() : 0;
    }


    public interface AdapterCallback {
        void onItemClicked(ReportItem reportItem);
    }

    public class ReportViewHolder extends BaseViewHolder<ReportListItemBinding, ReportItem> implements ReportItemClickListener {


        public ReportViewHolder(ReportListItemBinding aeps1TransactionItemBinding) {
            super(aeps1TransactionItemBinding);

        }

        @Override
        public void onBind(ReportItem reportItem) {

            Uri uri = null;
            if (reportItem.bank().equalsIgnoreCase("State Bank of India")) {
                uri = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.icon_sbi);
            }

            Uri status = null;
            if (reportItem.txnStatus().equalsIgnoreCase("Failed")) {
                status = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_failed);
            } else {
                status = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_success);
            }

            Glide.with(binding().getRoot()).load(status).apply(RequestOptions.circleCropTransform()).error(R.drawable.error_image).into(binding().ivAepsStausIcon);
            Glide.with(binding().getRoot()).load(uri).apply(RequestOptions.circleCropTransform()).error(R.drawable.error_image).into(binding().ivAepsStausIcon);
            binding().setReportItem(reportItem);
            binding().setReportItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(ReportItem serviceModel) {
            ////Toast.makeText(binding().getRoot().getContext(), serviceModel.serviceType().getServiceType(), //Toast.LENGTH_SHORT).show();
            if (callback != null)
                callback.onItemClicked(serviceModel);
        }
    }

}
