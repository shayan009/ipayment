package com.onetechsol.ipayment.ui.screen.login_singup.login;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentLoginBinding;
import com.onetechsol.ipayment.di.binders.ImageViewBinder;
import com.onetechsol.ipayment.di.binders.TextViewBindingAdapter;
import com.onetechsol.ipayment.dto.LoginViewData;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;

import org.apache.commons.lang3.StringUtils;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> implements LoginClickEvent {


    private boolean otpRequired = false;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(LoginViewModel.class);
    }

    @Override
    public FragmentLoginBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewBinding().setLoginClickEvent(this);
        viewBinding().setLoginViewData(new LoginViewData("Request Otp", false));
        viewBinding().executePendingBindings();

        viewBinding().etLoginMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ImageViewBinder.setVisibility(viewBinding().ivClear, View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void clearMobileField(View view) {
        TextViewBindingAdapter.setText(viewBinding().etLoginMobile, "");
        ImageViewBinder.setVisibility((ImageView) view, View.INVISIBLE);
        resetLogin();
    }

    private void resetLogin() {
        otpRequired = false;
        viewBinding().mbRequestOtp.setText("Send Otp");
        viewBinding().setLoginViewData(new LoginViewData("Send Otp", false));
        viewBinding().executePendingBindings();
    }


    @Override
    public void requestOtp() {

        String mobile = TextViewBindingAdapter.getTextString(viewBinding().etLoginMobile);
        String otp = TextViewBindingAdapter.getTextString(viewBinding().etOtp);

        if (otpRequired) {

            onShowLoading();

            if (StringUtils.isEmpty(otp)) {
                //Toast.makeText(getActivity(), "Otp number cannot be empty.", //Toast.LENGTH_SHORT).show();
                return;
            } else if (StringUtils.length(otp) != 6) {
                //Toast.makeText(getActivity(), "Otp number must be of 10 digits.", //Toast.LENGTH_SHORT).show();
                return;
            }


            compositeDisposable().add(viewModel().submitOtp(viewBinding().etOtp.getText().toString())
                    .subscribe(verifyOtpResponse -> {

                                if (verifyOtpResponse.status.equals("1")) {

                                    String deviceId = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                                    viewModel().prefManager().setLoginResponseData(verifyOtpResponse.data().userProfile(), verifyOtpResponse.data().setup(), verifyOtpResponse.token(), verifyOtpResponse.data().masterVersion(), verifyOtpResponse.data().companyVersion(), deviceId);

                                    if (verifyOtpResponse.kycStatus().equals("1") || verifyOtpResponse.kycStatus().equals("3")) {

                                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                                Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        onHideLoading();
                                    }
                                } else {
                                    showAlertDialog("Login Alert", verifyOtpResponse.message(), true)
                                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                                dialogInterface.dismiss();
                                            }).show();
                                    onHideLoading();
                                }

                            },
                            throwable -> {
                                onHideLoading();
                                showAlertDialog("Error", throwable.getMessage(), true)
                                        .setPositiveButton("OK", (dialogInterface, i) -> {
                                            dialogInterface.dismiss();
                                        }).show();
                            }));
        } else {

            //api hit
            sendOtp(mobile);

        }

    }


    private void sendOtp(String mobile) {

        if (StringUtils.isEmpty(mobile)) {
            //Toast.makeText(getActivity(), "Mobile number cannot be empty.", //Toast.LENGTH_SHORT).show();
            return;
        }

        onShowLoading();

        compositeDisposable().add(viewModel().sendMobileOtp(viewBinding().etLoginMobile.getText().toString())
                .subscribe(loginResponse -> {

                    Log.d("loginResponse", loginResponse.toString());
                    //Toast.makeText(getActivity(), loginResponse.message(), //Toast.LENGTH_SHORT).show();

                    if (loginResponse.status().equals("1")) {

                        Intent goToHome = new Intent(getActivity(), HomeActivity.class);
                        startActivity(goToHome);
                        requireActivity().overridePendingTransition(0, 0);
                        getActivity().finish();

                    } else if (loginResponse.status().equals("2")) {

                        otpRequired = true;
                        viewBinding().mbRequestOtp.setText("Submit Otp");
                        viewBinding().setLoginViewData(new LoginViewData("Submit Otp", true));
                        viewBinding().executePendingBindings();
                    }

                    onHideLoading();
                }, throwable -> {
                    //Toast.makeText(getActivity(), throwable.getMessage(), //Toast.LENGTH_SHORT).show();
                    onHideLoading();
                }));
    }

    @Override
    public void reSendOtp() {
        String mobile = TextViewBindingAdapter.getTextString(viewBinding().etLoginMobile);
        sendOtp(mobile);
    }


}