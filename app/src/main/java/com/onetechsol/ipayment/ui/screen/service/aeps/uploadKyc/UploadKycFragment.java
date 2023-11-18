package com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.UploadKycClickListener;
import com.onetechsol.ipayment.databinding.UploadKycFragmentBinding;
import com.onetechsol.ipayment.pojo.CheckLoginRequest;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.service.external_service.UpiPayNowActivity;
import com.onetechsol.ipayment.utils.ApiConstant;

public class UploadKycFragment extends BaseFragment<UploadKycViewModel, UploadKycFragmentBinding> implements UploadKycClickListener {


    @Override
    public int getLayoutRes() {
        return R.layout.upload_kyc_fragment;
    }

    @Override
    public UploadKycViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(UploadKycViewModel.class);
    }


    @Override
    public UploadKycFragmentBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        char space = '-';
        checkKycStatus();
        viewBinding().setUploadKycClickListener(this);
        viewBinding().etKycAdharNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }
        });

    }

    @Override
    public void hideKeyboard(View view) {

    }

    private void checkKycStatus() {
        compositeDisposable().add(viewModel().checkLogin(new CheckLoginRequest(ApiConstant.BASIC_VERSION))
                .subscribe(checkLoginResponse -> {

                    Log.d("checkLoginResponse l", checkLoginResponse.toString());
                    String status = checkLoginResponse.status();
                    if (status.equals("1")) {

                        String kycCk = checkLoginResponse.kycBankingStatus();
                        String kycStatusMsg = checkLoginResponse.kycBankingStatusMsg();
                        int kycBankPage = checkLoginResponse.kycBankingPage();
                      /*
                        kyc_msg : 4 -> Ipayment
                       */
                        if (kycBankPage == 4) {
                            // viewBinding().tvKycOtpTitle.setText(kyc_msg);

                        }
                    }
                }, throwable -> {

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

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void proceed(String adhar, String businessName) {

        //Toast.makeText(getActivity(), adhar+businessName, //Toast.LENGTH_SHORT).show();

        compositeDisposable().add(viewModel().uploadBankingKyc(adhar, businessName)
                .subscribe(uploadKycResponse -> {

                    if (uploadKycResponse.status().equals("1") && uploadKycResponse.txnStatus().equals("2")) {
                        String reloadUrl = uploadKycResponse.reloadUrl();

                        showAlertDialog("Kyc Details", uploadKycResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getActivity(), UpiPayNowActivity.class);
                                    intent.putExtra("serviceUrl", reloadUrl);
                                    startActivity(intent);

                                }).show();
                    } else {
                        showAlertDialog("Kyc Details", uploadKycResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                                }).show();
                    }
                }, throwable -> {

                }));


    }

}
