package com.onetechsol.ipayment.ui.screen.report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ReportCategoryBottomsheetBinding;
import com.onetechsol.ipayment.databinding.ReportTypeOnClickListener;
import com.onetechsol.ipayment.pojo.ReportTypeItem;
import com.onetechsol.ipayment.ui.adapter.ReportCategoryAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.util.List;

public class ReportCategoryBottomSheet extends CurvedBottomSheetDialogFragment<ReportCategoryBottomsheetBinding, ReportViewModel> implements ReportCategoryAdapter.AdapterCallback, ReportTypeOnClickListener {


    private AdapterCallback adapterCallback;

    public AdapterCallback adapterCallback() {
        return adapterCallback;
    }

    public ReportCategoryBottomSheet setAdapterCallback(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.report_category_bottomsheet;
    }

    @Override
    public ReportViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ReportViewModel.class);
    }

    @Override
    public ReportCategoryBottomsheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ReportCategoryAdapter reportCategoryAdapter = new ReportCategoryAdapter();
        viewBinding().setReportCategoryAdapter(reportCategoryAdapter);
        viewBinding().setReportTypeOnClickListener(this);
        reportCategoryAdapter.setCallback(this);
        viewBinding().executePendingBindings();

        onShowLoading();
        compositeDisposable().add(viewModel().getReportCategoryList().subscribe(getReportTypeResponse -> {

            List<ReportTypeItem> reportTypeItems = getReportTypeResponse.data().reportTypeList();
            reportCategoryAdapter.setItems(reportTypeItems);
            onHideLoading();

        }, throwable -> {
            onHideLoading();
        }));
    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }

    @Override
    public void search(String text) {

    }

    @Override
    public void closeSheet() {
        dismiss();
    }

    @Override
    public void onItemClicked(ReportTypeItem reportTypeItem) {
        if (adapterCallback != null)
            adapterCallback.selectReportCategory(reportTypeItem);
    }

    public interface AdapterCallback {
        void selectReportCategory(ReportTypeItem reportTypeItem);
    }
}
