package com.onetechsol.ipayment.ui.screen.change_tpass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ChangeIpinSheetBinding;
import com.onetechsol.ipayment.databinding.ChangeTpinClickListener;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class ChangeTpinBottomSheet extends CurvedBottomSheetDialogFragment<ChangeIpinSheetBinding, ChangeTpinViewModel> implements ChangeTpinClickListener {


    boolean otpReceived = false;

    @Override
    public int getLayoutRes() {
        return R.layout.change_ipin_sheet;
    }

    @Override
    public ChangeTpinViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ChangeTpinViewModel.class);
    }


    @Override
    public ChangeIpinSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setChangeTpinClickListener(this);

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
    public void submit(String otp, String newPin, String confirmNewPin) {

        String security = "0";

        if (StringUtils.isEmpty(newPin)) {
            Toast.makeText(getContext(), "New IPIN cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isEmpty(confirmNewPin)) {
            Toast.makeText(getContext(), "Confirmed IPIN cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!confirmNewPin.equals(newPin)) {
            Toast.makeText(getContext(), "New Tpin must match with confirmed Tpin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (otpReceived) {

            security = "1";

            if (StringUtils.isEmpty(otp)) {
                Toast.makeText(getContext(), "Please enter the otp received", Toast.LENGTH_SHORT).show();
                return;
            }

            changeIpin(otp, newPin, confirmNewPin, security);
        } else
            changeIpin("", newPin, confirmNewPin, security);

    }

    private void changeIpin(String otp, String newPin, String confirmNewPin, String security) {
        onShowLoading();
        compositeDisposable().add(viewModel().changeTpin(newPin, confirmNewPin, security, "", otp)
                .subscribe(changeTpinResponse -> {
                    onHideLoading();
                    showAlertDialog("Change Tpin", changeTpinResponse.message(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {

                                dialogInterface.dismiss();
                                if (StringUtils.isEmpty(otp)) {
                                    otpReceived = true;
                                    viewBinding().mcvOtp.setVisibility(View.VISIBLE);
                                    viewBinding().mcvConfirmTpin.setEnabled(false);
                                    viewBinding().mcvNewIpin.setEnabled(false);
                                } else if (changeTpinResponse.status().equals("1")){
                                    dismiss();
                                }

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
