package com.onetechsol.ipayment.ui.screen.service.payout;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.PayoutCheckClickListener;
import com.onetechsol.ipayment.databinding.PayoutCheckSheetBinding;
import com.onetechsol.ipayment.pojo.CircleBankModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PayoutCheckBottomSheet extends CurvedBottomSheetDialogFragment<PayoutCheckSheetBinding, PayoutViewModel> implements PayoutCheckClickListener {


    private PayoutAddBankSheet payoutAddBankSheet;
    private int accountId = -1;
    private int modeId = -1;
    private String totalAmount;

    @Override
    public int getLayoutRes() {
        return R.layout.payout_check_sheet;
    }

    @Override
    public PayoutViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PayoutViewModel.class);
    }


    @Override
    public PayoutCheckSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setPayoutCheckClickListener(this);

        viewBinding().fcAccountNo.initialize(30, 1000, Color.WHITE, 0);
        viewBinding().fcMode.initialize(30, 1000, Color.WHITE, 0);

        checkPayoutStatus();

        viewBinding().chipGroupAcNo.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.size() > 0) {

                Chip chip = viewBinding().chipGroupAcNo.findViewById(checkedIds.get(0));

                accountId = chip.getId();
                viewBinding().tvAccountText.setText(chip.getText());

                viewBinding().fcAccountNo.toggle(false);
            }

        });

        viewBinding().chipGroupMode.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.size() > 0) {

                Chip chip = viewBinding().chipGroupMode.findViewById(checkedIds.get(0));
                chip.setWidth(120);
                modeId = chip.getId();
                viewBinding().tvModeText.setText(chip.getText());
                viewBinding().fcMode.toggle(false);
            }

        });

        viewBinding().etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                if (StringUtils.isNoneEmpty(charSequence) && charSequence.length() > 2) {

                    compositeDisposable().add(viewModel().getPayoutCharge(String.valueOf(charSequence), String.valueOf(modeId)).subscribe(payoutChargeResponse -> {

                        if (payoutChargeResponse.status().equals("1")) {

                            viewBinding().group2.setVisibility(View.VISIBLE);
                            viewBinding().tvPayoutChargeText.setText(payoutChargeResponse.charge());
                            viewBinding().tvTotalAmountText.setText(payoutChargeResponse.totalAmt().trim());
                            viewBinding().btSubmitPayout.setEnabled(true);
                        } else {
                            viewBinding().btSubmitPayout.setEnabled(false);
                            viewBinding().group2.setVisibility(View.GONE);
                        }


                    }, throwable -> {

                    }));
                } else {

                    viewBinding().tvPayoutChargeText.setText("");
                    viewBinding().tvTotalAmountText.setText("");
                    viewBinding().btSubmitPayout.setEnabled(false);
                    viewBinding().group2.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void checkPayoutStatus() {
        onShowLoading();
        compositeDisposable().add(viewModel().payoutServiceCheck().subscribe(checkPayoutServiceResponse -> {
            onHideLoading();

            if (checkPayoutServiceResponse.status().equals("1")) {


                double eg = StringUtils.isNoneEmpty(checkPayoutServiceResponse.getEligibleAmt()) ? Double.parseDouble(checkPayoutServiceResponse.getEligibleAmt()) : 0.0;
                double wallet = StringUtils.isNoneEmpty(checkPayoutServiceResponse.getWallet()) ? Double.parseDouble(checkPayoutServiceResponse.getWallet()) : 0.0;

                if (eg > wallet) {
                    wallet = 0.0;
                    checkPayoutServiceResponse.setWallet(String.valueOf(wallet));
                }
                viewBinding().setCheckPayoutServiceResponse(checkPayoutServiceResponse);

                if (checkPayoutServiceResponse.getBankStatus().equals("1")) {

                    viewBinding().group1.setVisibility(View.GONE);
                    viewBinding().group.setVisibility(View.VISIBLE);
                    viewModel().setCircleBankModelList(checkPayoutServiceResponse.getCircleBankModelList());
                    viewModel().setPayoutModeList(checkPayoutServiceResponse.getPayoutModeList());

                    checkPayoutServiceResponse.getCircleBankModelList().forEach(circleBankModel -> {

                        if (StringUtils.isNotEmpty(circleBankModel.getId())) {
                            Chip chip = new Chip(getContext());
                            chip.setId(Integer.parseInt(circleBankModel.getId()));
                            chip.setText(circleBankModel.getLabel());
                            float density = getResources().getDisplayMetrics().density;
                            chip.setHeight((int) (60 * density));
                            viewBinding().chipGroupAcNo.addView(chip);
                        }

                    });


                    checkPayoutServiceResponse.getPayoutModeList().forEach(circleBankModel -> {

                        if (StringUtils.isNotEmpty(circleBankModel.getId())) {
                            Chip chip = new Chip(getContext());
                            chip.setId(Integer.parseInt(circleBankModel.getId()));
                            chip.setText(circleBankModel.getLabel());

                            float density = getResources().getDisplayMetrics().density;
                            chip.setHeight((int) (60 * density));
                            viewBinding().chipGroupMode.addView(chip);
                        }

                    });


                } else if (checkPayoutServiceResponse.getBankStatus().equals("0")) {
                    viewBinding().group1.setVisibility(View.VISIBLE);
                    viewBinding().group.setVisibility(View.GONE);
                }

            }


        }, throwable -> {
            onHideLoading();
            showToastAlertDialog("Error", throwable.getMessage(), false).show(getParentFragmentManager(), "showToastAlertDialog");
        }));
    }

    @Override
    public void hideKeyboard(View view) {

    }


    @Override
    public void closeBottomSheet() {
        dismiss();

    }

    @Override
    public void createBank() {

        payoutAddBankSheet = new PayoutAddBankSheet();
        payoutAddBankSheet.setPayoutCheckClickListener(this);
        payoutAddBankSheet.show(getParentFragmentManager(), PayoutAddBankSheet.class.getName());


    }

    @Override
    public void openAccountList() {
        viewBinding().fcAccountNo.toggle(false);
    }

    @Override
    public void openMode() {

        viewBinding().fcMode.toggle(false);
    }


    @Override
    public void submitPayout(String amount, String totalAmount, String tpin) {


        if (accountId == -1) {

            showAlertDialog("Invalid Account", "Please enter an Payout Account", true).show();
            return;
        }

        if (modeId == -1) {

            showAlertDialog("Invalid Mode", "Please enter an Payout mode", true).show();
            return;
        }


        if (StringUtils.isEmpty(amount)) {

            showAlertDialog("Invalid amount", "Please enter an amount", true).show();
            return;
        }

        if (Integer.parseInt(amount) < 100) {

            showAlertDialog("Invalid amount", "Minimum amount is 100", true).show();
            return;
        }


        if (StringUtils.isEmpty(tpin)) {

            showAlertDialog("Invalid Tpin", "Enter correct Tpin", true).setPositiveButton("OK",(dialog, which) -> dialog.dismiss()).show();
            return;
        }

        onShowLoading();
        compositeDisposable().add(viewModel().payoutSubmit(String.valueOf(modeId), totalAmount, String.valueOf(accountId), "1", tpin, "").subscribe(payoutSubmitResponse -> {
            onHideLoading();

            if ((payoutSubmitResponse.status().equals("1") && payoutSubmitResponse.getTxnStatus().equals("1")) || (payoutSubmitResponse.status().equals("1") && payoutSubmitResponse.getTxnStatus().equals("2"))) {
                showToastAlertDialog("Payout Request", payoutSubmitResponse.message(), false).setOnClickListener(this::checkPayoutStatus).show(getParentFragmentManager(), "showToastAlertDialog");
            } else {

                showToastAlertDialog("Want to retry ? ", payoutSubmitResponse.message(), true).show(getParentFragmentManager(), "showToastAlertDialog");

            }


        }, throwable -> {
            onHideLoading();
        }));

    }

    @Override
    public void addBank(String bankName, String bankAcNo, String bankAccHolder, String banIfsc, String bankBranch) {
        payoutAddBankSheet.dismiss();
        onShowLoading();
        compositeDisposable().add(viewModel().payoutAddBank(bankAcNo, bankAccHolder, banIfsc, bankBranch, bankName, "", "", "").subscribe(payoutCreateBankResponse -> {
            onHideLoading();

            if (payoutCreateBankResponse.status().equals("1")) {

                showAlertDialog("Bank added.", payoutCreateBankResponse.message(), true).setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    if (payoutAddBankSheet != null) payoutAddBankSheet.dismiss();
                    getBankDetails();
                    checkPayoutStatus();
                }).show();

            } else {

                showAlertDialog("Could not add bank", payoutCreateBankResponse.message(), true).setPositiveButton("OK", (dialog, which) -> {
                    dismiss();
                }).show();
            }


        }, throwable -> {
            onHideLoading();
        }));
    }

    private void getBankDetails() {


        compositeDisposable().add(viewModel().payoutBankDetails().subscribe(payoutBankDetailResponse -> {
            onHideLoading();

            if (payoutBankDetailResponse.status().equals("success")) {

                Log.d("payoutBankList", payoutBankDetailResponse.getBankList().toString());


            } else {
                showAlertDialog("Could not getBankDetail", payoutBankDetailResponse.message(), true).show();
            }


        }, throwable -> {
            onHideLoading();
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
