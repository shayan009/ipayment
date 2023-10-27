package com.onetechsol.ipayment.ui.screen.share;


import com.onetechsol.ipayment.pojo.FetchProductDetailRequest;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetail;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ShareViewModel extends BaseViewModel {


    public io.reactivex.Observable<GetAffiliateProductDetail> getAffiliateProductDetail(String sellEarnProductId) {

        return iModelRepository().getAffiliateProductDetail(new FetchProductDetailRequest(sellEarnProductId))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
