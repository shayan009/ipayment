package com.onetechsol.ipayment.ui.screen.service.insurance;

import com.onetechsol.ipayment.pojo.BuyInsuranceDetailRequest;
import com.onetechsol.ipayment.pojo.BuyInsuranceDetailResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BuyInsuranceViewModel extends BaseViewModel {


    public Observable<BuyInsuranceDetailResponse> getInsuranceDetail() {

        BuyInsuranceDetailRequest buyInsuranceDetailRequest = new BuyInsuranceDetailRequest(ApiConstant.VERSION);

        return iModelRepository().getInsuranceDetail(buyInsuranceDetailRequest).observeOn(AndroidSchedulers.mainThread());
    }


}
