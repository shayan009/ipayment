package com.onetechsol.ipayment.ui.screen.welcome;

import com.onetechsol.ipayment.pojo.CheckLoginRequest;
import com.onetechsol.ipayment.pojo.CheckLoginResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WelcomeViewModel extends BaseViewModel {


    Observable<CheckLoginResponse> checkLogin(CheckLoginRequest checkLoginRequest) {
        return iModelRepository().checkLogin(checkLoginRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


}
