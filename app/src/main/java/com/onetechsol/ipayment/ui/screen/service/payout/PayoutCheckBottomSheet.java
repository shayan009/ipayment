package com.onetechsol.ipayment.ui.screen.service.payout;

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
import com.onetechsol.ipayment.databinding.RemitterCheckClickListener;
import com.onetechsol.ipayment.databinding.RemitterCheckSheetBinding;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTActivity;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTViewModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class PayoutCheckBottomSheet extends CurvedBottomSheetDialogFragment<RemitterCheckSheetBinding, DMTViewModel> implements RemitterCheckClickListener {

    boolean otpRequired = false;
    private String remHelp = "";
    private String remMob = "";

    @Override
    public int getLayoutRes() {
        return R.layout.remitter_check_sheet;
    }

    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }


    @Override
    public RemitterCheckSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setRemitterCheckClickListener(this);


    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (otpRequired) {
            viewBinding().mcvRemitterName.setVisibility(View.VISIBLE);
            viewBinding().mcvRemitterOtp.setVisibility(View.VISIBLE);
        } else {
            viewBinding().mcvRemitterName.setVisibility(View.GONE);
            viewBinding().mcvRemitterOtp.setVisibility(View.GONE);
        }
    }

    @Override
    public void closeBottomSheet() {
        dismiss();

    }

    @Override
    public void submit(String number) {

        //Toast.makeText(getContext(), "number : "+number, //Toast.LENGTH_SHORT).show();

        if (!otpRequired) {

            compositeDisposable().add(viewModel().checkRemitter(number)
                    .subscribe(checkRemitterResponse ->
                            showAlertDialog("DMT Details", checkRemitterResponse.message(), true)
                                    .setPositiveButton("OK", (dialogInterface, i) -> {
                                        dialogInterface.dismiss();

                                        remMob = checkRemitterResponse.data().mobile();

                                        if (checkRemitterResponse.status().equals("1") && checkRemitterResponse.txnStatus().equals("3")) {

                                            viewBinding().mcvRemitterName.setVisibility(View.VISIBLE);
                                            viewBinding().mcvRemitterOtp.setVisibility(View.VISIBLE);
                                            otpRequired = true;
                                            remHelp = checkRemitterResponse.data().remHelp();

                                        } else if (checkRemitterResponse.status().equals("1") && checkRemitterResponse.txnStatus().equals("1")) {
                                            Intent intent = new Intent(getContext(), DMTActivity.class);
                                            intent.putExtra("remMobile", remMob);
                                            startActivity(intent);
                                            requireActivity().overridePendingTransition(0, 0);
                                            dismiss();
                                        }

                                    }).show(), th ->
                    {

                    }));

        } else {

            if (StringUtils.isNoneEmpty(remHelp) && StringUtils.isNoneEmpty(remMob)) {

                compositeDisposable().add(viewModel().submitOtpRemitter(remMob, viewBinding().etName.getText().toString(), viewBinding().etOtp.getText().toString(), remHelp)
                        .subscribe(submitRemitterOtpResponse -> {
                            showAlertDialog("DMT Details", submitRemitterOtpResponse.message(), true)
                                    .setPositiveButton("OK", (dialogInterface, i) -> {
                                        dialogInterface.dismiss();

                                        if (submitRemitterOtpResponse.status().equals("1") && submitRemitterOtpResponse.txnStatus().equals("1")) {

                                            Intent intent = new Intent(getContext(), DMTActivity.class);
                                            intent.putExtra("remMobile", remMob);
                                            startActivity(intent);
                                            requireActivity().overridePendingTransition(0, 0);
                                            dismiss();

                                        }

                                    });

                        }, th -> {

                        }));
            }


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
