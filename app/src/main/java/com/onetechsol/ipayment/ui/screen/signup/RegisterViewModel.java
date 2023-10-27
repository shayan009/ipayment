package com.onetechsol.ipayment.ui.screen.signup;

import com.onetechsol.ipayment.pojo.AppSetupRequest;
import com.onetechsol.ipayment.pojo.AppSetupResponse;
import com.onetechsol.ipayment.pojo.RegisterUserRequest;
import com.onetechsol.ipayment.pojo.RegisterUserResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RegisterViewModel extends BaseViewModel {


    Observable<AppSetupResponse> appSetup() {
        AppSetupRequest appSetupRequest = new AppSetupRequest(ApiConstant.BASIC_VERSION);

        return iModelRepository().appSetup(appSetupRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


    Observable<RegisterUserResponse> register(String referral, String fullName, String email, String address, String pinCode, String district, String state) {
        RegisterUserRequest appSetupRequest = new RegisterUserRequest(referral, "", fullName, "", "", "", email, address, pinCode, "", "", "", district, state, "", "", "");

        return iModelRepository().register(appSetupRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
