package com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.RechargeIpinSheetBinding;
import com.onetechsol.ipayment.databinding.VerifyRechargeClickListener;
import com.onetechsol.ipayment.pojo.FetchBillDetails;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class RechargeBottomSheet extends CurvedBottomSheetDialogFragment<RechargeIpinSheetBinding, PlanSelectionViewModel> implements VerifyRechargeClickListener {


    private String amount;

    private FetchBillDetails fetchBillDetails;

    public FetchBillDetails fetchBillDetails() {
        return fetchBillDetails;
    }

    public RechargeBottomSheet setFetchBillDetails(FetchBillDetails fetchBillDetails) {
        this.fetchBillDetails = fetchBillDetails;
        return this;
    }

    public RechargeBottomSheet setAmount(String amount) {
        this.amount = amount;
        return this;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.recharge_ipin_sheet;
    }

    @Override
    public PlanSelectionViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PlanSelectionViewModel.class);
    }


    @Override
    public RechargeIpinSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setVerifyRechargeClickListener(this);

        if (StringUtils.isEmpty(amount) || StringUtils.equals("0", amount)) {
            viewBinding().etAmount.setEnabled(true);
        } else {
            viewBinding().etAmount.setText(amount);
        }

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
    public void submit(String tpin, String amount) {


        if (StringUtils.isEmpty(tpin)) {
            //Toast.makeText(getContext(), "IPIN cannot be empty", //Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isNotEmpty(amount) && Double.parseDouble(amount) < 10) {
            //Toast.makeText(getContext(), "Amount cannot be less than 100", //Toast.LENGTH_SHORT).show();
            return;
        }

        if (fetchBillDetails.opCircleItemDto() == null) {
            //Toast.makeText(getContext(), "Please select an operator", //Toast.LENGTH_SHORT).show();
            return;
        }

        String id = "";
        if (fetchBillDetails.circleItemDto() != null) {
            id = fetchBillDetails.circleItemDto().id();
        }

        String billId = "";

        if (fetchBillDetails.billList().size() > 0)
            billId = fetchBillDetails.billList().get(0).billNo();

        onShowLoading();
        compositeDisposable().add(viewModel().rechargeMobile(id, fetchBillDetails.serviceCategoryModel().categoryId(), fetchBillDetails.opCircleItemDto().id() + "|" + fetchBillDetails.opCircleItemDto().title(), fetchBillDetails.txnNumber(), fetchBillDetails.txnCustomerNo(), fetchBillDetails.txnAd1(), fetchBillDetails.txnAd2(), fetchBillDetails.txnAd3(), fetchBillDetails.txnAd4(), amount, billId, fetchBillDetails.customerName(), fetchBillDetails.txnFetch(), "1", tpin, "")
                .subscribe(postRechargeResponse -> {
                    onHideLoading();
                    showAlertDialog("RechargeDetails", postRechargeResponse.message(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {


                                if (postRechargeResponse.status().equals("1") && postRechargeResponse.txnStatus().equals("1")) {

                                    //TODO payment Success
                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }

                                dialogInterface.dismiss();

                            }).show();
                }, th ->
                {
                    onHideLoading();
                    showAlertDialog("Error", th.getMessage(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                            }).show();
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
