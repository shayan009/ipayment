package com.onetechsol.ipayment.ui.screen.service.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.PayoutAddBankBinding;
import com.onetechsol.ipayment.databinding.PayoutAddBankClickListener;
import com.onetechsol.ipayment.databinding.PayoutCheckClickListener;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;
import com.onetechsol.ipayment.widgets.CustomSideSheetSpinner;

import java.util.ArrayList;
import java.util.List;


public class PayoutAddBankSheet extends CurvedBottomSheetDialogFragment<PayoutAddBankBinding, PayoutViewModel> implements PayoutAddBankClickListener, SideSheetDataOnClickListener, CustomSideSheetSpinner.SideSpinnerCallback {


    PayoutCheckClickListener payoutCheckClickListener;
    private CustomSideSheetSpinner sideSheetDialog;

    @Override
    public int getLayoutRes() {
        return R.layout.payout_add_bank;
    }

    public PayoutCheckClickListener getPayoutCheckClickListener() {
        return payoutCheckClickListener;
    }

    public void setPayoutCheckClickListener(PayoutCheckClickListener payoutCheckClickListener) {
        this.payoutCheckClickListener = payoutCheckClickListener;
    }

    @Override
    public PayoutViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PayoutViewModel.class);
    }


    @Override
    public PayoutAddBankBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setPayoutAddBankClickListener(this);

        initSideSheet();


    }

    @Override
    public void hideKeyboard(View view) {

    }


    @Override
    public void closeBottomSheet() {
        dismiss();

    }

    private void initSideSheet() {

        sideSheetDialog = new CustomSideSheetSpinner(requireContext());
        ((CustomSideSheetSpinner) sideSheetDialog).setSideSheetDataOnClickListener(this);
        sideSheetDialog.setSideSpinnerCallback(this);
        searchBank("sta");

    }


    private void searchBank(String query) {
        compositeDisposable().add(viewModel().payoutServiceCheck(query)
                .subscribe(aepsBankModels -> {
                    List<SideSheetItem> sideSheetItems = new ArrayList<>();

                    for (int i = 0; i < aepsBankModels.size(); i++) {
                        AepsBankModel aepsBankModel = aepsBankModels.get(i);
                        sideSheetItems.add(new SideSheetItem(aepsBankModel.name(), aepsBankModel.value(), true, (i - 1), false));
                    }

                    sideSheetDialog.setSideSheetItems(sideSheetItems);

                }, throwable -> {
                    onHideLoading();
                }));
    }

    @Override
    public void openBankList() {

        if (sideSheetDialog != null)
            sideSheetDialog.show();

    }

    @Override
    public void addBank(String bankName,String bankAcNo, String bankAccHolder, String banIfsc, String bankBranch) {

        if (null != payoutCheckClickListener)
            payoutCheckClickListener.addBank(bankName,bankAcNo, bankAccHolder, banIfsc, bankBranch);
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
    public void selectSideSheetItem(SideSheetItem stateList) {
        viewBinding().tvBankNameText.setText(stateList.value());
    }

    @Override
    public void onItemSearched(String search) {
        searchBank(search);
    }
}