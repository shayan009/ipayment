package com.onetechsol.ipayment.ui.screen.service.recharge.electricity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.BillPaymentSheetBinding;
import com.onetechsol.ipayment.databinding.BillPaymentSheetClickListener;
import com.onetechsol.ipayment.pojo.FetchBillDetails;
import com.onetechsol.ipayment.ui.adapter.BillDetailsAdapter;
import com.onetechsol.ipayment.ui.screen.service.recharge.RechargeViewModel;
import com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection.RechargeBottomSheet;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

public class BillPaymentBottomSheet extends CurvedBottomSheetDialogFragment<BillPaymentSheetBinding, RechargeViewModel> implements BillPaymentSheetClickListener {


    private FetchBillDetails fetchBillDetails;


    public FetchBillDetails fetchBillDetails() {
        return fetchBillDetails;
    }

    public BillPaymentBottomSheet setFetchBillDetails(FetchBillDetails fetchBillDetails) {
        this.fetchBillDetails = fetchBillDetails;
        return this;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.bill_payment_sheet;
    }

    @Override
    public RechargeViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(RechargeViewModel.class);
    }


    @Override
    public BillPaymentSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        BillDetailsAdapter billDetailsAdapter = new BillDetailsAdapter();
        billDetailsAdapter.setItems(fetchBillDetails.billList());
        billDetailsAdapter.setBillPaymentSheetClickListener(this);
        viewBinding().setBillDetailsAdapter(billDetailsAdapter);
        viewBinding().setFetchBillDetails(fetchBillDetails);

        viewBinding().setBillPaymentSheetClickListener(this);

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

        if (null != fetchBillDetails) {

            RechargeBottomSheet rechargeBottomSheet = new RechargeBottomSheet();
            rechargeBottomSheet.setAmount(amount);
            rechargeBottomSheet.setFetchBillDetails(fetchBillDetails);
            rechargeBottomSheet.show(requireActivity().getSupportFragmentManager(), RechargeBottomSheet.class.getName());

        }

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
