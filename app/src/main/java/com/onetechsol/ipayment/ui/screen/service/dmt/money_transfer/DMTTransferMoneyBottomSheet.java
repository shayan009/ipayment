package com.onetechsol.ipayment.ui.screen.service.dmt.money_transfer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DMTMoneyTransferClickListener;
import com.onetechsol.ipayment.databinding.DmtTransferSheetBinding;
import com.onetechsol.ipayment.pojo.BeneficiaryModel;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTViewModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class DMTTransferMoneyBottomSheet extends CurvedBottomSheetDialogFragment<DmtTransferSheetBinding, DMTViewModel> implements DMTMoneyTransferClickListener {

    private String remMob = "";

    private BeneficiaryModel beneficiaryModel;
    private String totalAmount;


    public DMTTransferMoneyBottomSheet setBeneficiaryModel(BeneficiaryModel beneficiaryModel) {
        this.beneficiaryModel = beneficiaryModel;
        return this;
    }

    public DMTTransferMoneyBottomSheet setRemMob(String remMob) {
        this.remMob = remMob;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dmt_transfer_sheet;
    }

    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }


    @Override
    public DmtTransferSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setDmtMoneyTransferClickListener(this);
        viewBinding().setBeneficiaryModel(beneficiaryModel);

        viewBinding().etBenAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (StringUtils.isNoneEmpty(charSequence) && charSequence.length() > 2) {
                    compositeDisposable().add(viewModel().getCharge(String.valueOf(charSequence))
                            .subscribe(dmtChargeResponse -> {

                                viewBinding().mcvBenDMTCharge.setVisibility(View.VISIBLE);
                                viewBinding().mcvBenAmtTotal.setVisibility(View.VISIBLE);
                                viewBinding().tvBenDMTCharge.setText(dmtChargeResponse.charge());
                                viewBinding().tvBenAmtTotal.setText(dmtChargeResponse.totalAmt().trim());
                                totalAmount = dmtChargeResponse.totalAmt();

                            }, throwable -> {

                            }));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
        if (StringUtils.isNotEmpty(amount) && Double.parseDouble(amount) < 100) {
            //Toast.makeText(getContext(), "Amount cannot be less than 100", //Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable().add(viewModel().moneyTransfer(beneficiaryModel, amount, remMob)
                .subscribe(moneyTransferResponse ->
                        showAlertDialog("DMT Details", moneyTransferResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();


                                    if (moneyTransferResponse.status().equals("1") && moneyTransferResponse.txnStatus().equals("1")) {

                                        //TODO payment Success


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
