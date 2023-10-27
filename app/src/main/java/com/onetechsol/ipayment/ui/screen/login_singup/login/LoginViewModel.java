package com.onetechsol.ipayment.ui.screen.login_singup.login;

import com.onetechsol.ipayment.pojo.LoginRequest;
import com.onetechsol.ipayment.pojo.LoginResponse;
import com.onetechsol.ipayment.pojo.VerifyOtpRequest;
import com.onetechsol.ipayment.pojo.VerifyOtpResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginViewModel extends BaseViewModel {

    private String mobile = "";


    Observable<LoginResponse> sendMobileOtp(String mobile) {

        this.mobile = mobile;

        LoginRequest loginRequest = new LoginRequest(ApiConstant.BASIC_VERSION);


        return iModelRepository().sendMobileOtp(loginRequest, mobile)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VerifyOtpResponse> submitOtp(String otp) {

        VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest(otp, prefManager().getFirebaseToken());
        return iModelRepository().verifyOtp(verifyOtpRequest, mobile)
                .observeOn(AndroidSchedulers.mainThread());

    }

}
