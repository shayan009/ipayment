package com.onetechsol.ipayment.ui.screen.fund_transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FundTransferOnClickListener;
import com.onetechsol.ipayment.databinding.FundTransferSheetBinding;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class FundTransferBottomSheet extends CurvedBottomSheetDialogFragment<FundTransferSheetBinding, FundTransferViewModel> implements FundTransferOnClickListener {


    private String retailerStatus;

    @Override
    public int getLayoutRes() {
        return R.layout.fund_transfer_sheet;
    }

    @Override
    public FundTransferViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(FundTransferViewModel.class);
    }

    @Override
    public FundTransferSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    public String retailerStatus() {
        return retailerStatus;
    }

    public FundTransferBottomSheet setRetailerStatus(String retailerStatus) {
        this.retailerStatus = retailerStatus;
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setFundTransferOnClickListener(this);


    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void closeBottomSheet() {
        dismiss();
    }

    @Override
    public void submit(String amount) {


        if (StringUtils.isEmpty(amount)) {
            //Toast.makeText(getContext(), "Amount cannot be empty", //Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isNotEmpty(amount) && Double.parseDouble(amount) < 10) {
            //Toast.makeText(getContext(), "Amount cannot be less than 10", //Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable().add(viewModel().fundTransferRequest(amount)
                .subscribe(fundTransferResponse ->
                        showAlertDialog("DMT Details", fundTransferResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();


                                    if (fundTransferResponse.status().equals("1") && fundTransferResponse.message().equals("1")) {

                                        dismiss();

                                    }

                                }).show(), th ->
                {

                }));

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
}
