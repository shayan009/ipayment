package com.onetechsol.ipayment.ui.screen.login_singup.signup;

import com.onetechsol.ipayment.pojo.CheckSignupRequest;
import com.onetechsol.ipayment.pojo.CheckSignupResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SignupViewModel extends BaseViewModel {


    Observable<CheckSignupResponse> checkSignUp(String mobile) {
        CheckSignupRequest checkSignupRequest = new CheckSignupRequest(mobile);


        return iModelRepository().checkSignup(checkSignupRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


}