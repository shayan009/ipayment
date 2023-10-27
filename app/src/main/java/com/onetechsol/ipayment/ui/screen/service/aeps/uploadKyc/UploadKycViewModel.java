package com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc;

import com.onetechsol.ipayment.pojo.CheckLoginRequest;
import com.onetechsol.ipayment.pojo.CheckLoginResponse;
import com.onetechsol.ipayment.pojo.UploadKycRequest;
import com.onetechsol.ipayment.pojo.UploadKycResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UploadKycViewModel extends BaseViewModel {


    Observable<CheckLoginResponse> checkLogin(CheckLoginRequest checkLoginRequest) {
        return iModelRepository().checkLogin(checkLoginRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<UploadKycResponse> uploadBankingKyc(String adharNo, String business) {

        UploadKycRequest uploadKycRequest = new UploadKycRequest(adharNo, business, "APP");

        return iModelRepository().uploadBankingKyc(uploadKycRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
