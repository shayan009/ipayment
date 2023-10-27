package com.onetechsol.ipayment.ui.screen.fund_transfer;

import com.onetechsol.ipayment.pojo.FundTransferRequest;
import com.onetechsol.ipayment.pojo.FundTransferResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FundTransferViewModel extends BaseViewModel {


    public Observable<FundTransferResponse> fundTransferRequest(String amount) {

        FundTransferRequest addCustomerRequest = new FundTransferRequest("wlt0", "1", amount);
        return iModelRepository().fundTransferRequest(addCustomerRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


}
