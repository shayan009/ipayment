package com.onetechsol.ipayment.ui.screen.report;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityReportBinding;
import com.onetechsol.ipayment.databinding.TransactionsOnClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class ReportActivity extends BaseActivity<ReportViewModel, ActivityReportBinding> implements TransactionsOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReportFragment reportFragment = ReportFragment.newInstance(getIntent().getStringExtra("categoryId"));
        onAttachFragment(viewBinding().reportFrame.getId(), reportFragment, ReportFragment.class.getName());
        viewBinding().setTransactionsOnClickListener(this);
    }

    @Override
    public ActivityReportBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityReportBinding.inflate(inflater);
    }

    @Override
    public ReportViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ReportViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_report;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }
}