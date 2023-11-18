package com.onetechsol.ipayment.ui.screen.change_pass;

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
import com.onetechsol.ipayment.databinding.ChangePassClickListener;
import com.onetechsol.ipayment.databinding.ChangePasswordSheetBinding;
import com.onetechsol.ipayment.databinding.ChangeTpinClickListener;
import com.onetechsol.ipayment.ui.screen.change_tpass.ChangeTpinViewModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

public class ChangePasswordBottomSheet extends CurvedBottomSheetDialogFragment<ChangePasswordSheetBinding, ChangePassViewModel> implements ChangePassClickListener {


    boolean otpReceived = false;

    @Override
    public int getLayoutRes() {
        return R.layout.change_password_sheet;
    }

    @Override
    public ChangePassViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ChangePassViewModel.class);
    }


    @Override
    public ChangePasswordSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setChangePassClickListener(this);

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
    public void submit(String currentPassword, String newPass, String confirmPass) {

        String security = "0";

        if (StringUtils.isEmpty(newPass)) {
            Toast.makeText(getContext(), "New Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isEmpty(confirmPass)) {
            Toast.makeText(getContext(), "Confirmed Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!confirmPass.equals(newPass)) {
            Toast.makeText(getContext(), "New Password must match with confirmed Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(currentPassword)) {
            Toast.makeText(getContext(), "Please enter your current password.", Toast.LENGTH_SHORT).show();
            return;
        }

        changePass(currentPassword,newPass,confirmPass,security);

    }

    private void changePass(String currentPass, String newPass, String confirmPass, String security) {
        onShowLoading();
        compositeDisposable().add(viewModel().changePass(newPass, confirmPass, security, "", currentPass)
                .subscribe(changePasswordResponse -> {
                    onHideLoading();
                    showAlertDialog("Change Password", changePasswordResponse.message(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {

                                dialogInterface.dismiss();
                              if(changePasswordResponse.status().equals("1")) {
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
