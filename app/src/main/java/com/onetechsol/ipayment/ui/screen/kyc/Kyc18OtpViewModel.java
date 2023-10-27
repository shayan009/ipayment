package com.onetechsol.ipayment.ui.screen.kyc;

import com.onetechsol.ipayment.pojo.StartKyc18Request;
import com.onetechsol.ipayment.pojo.StartKyc18Response;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Kyc18OtpViewModel extends BaseViewModel {

    public Observable<StartKyc18Response> startKyc18(String step, String otp) {

        StartKyc18Request startKyc18Request = new StartKyc18Request(ApiConstant.BASIC_VERSION, step, otp);


        return iModelRepository().startKyc18(startKyc18Request)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
