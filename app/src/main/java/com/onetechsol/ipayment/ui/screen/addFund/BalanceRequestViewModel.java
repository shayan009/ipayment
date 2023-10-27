package com.onetechsol.ipayment.ui.screen.addFund;

import com.onetechsol.ipayment.pojo.CheckFundReqDetailResponse;
import com.onetechsol.ipayment.pojo.CheckFundReqResponse;
import com.onetechsol.ipayment.pojo.FundRequest;
import com.onetechsol.ipayment.pojo.FundRequestCheckDetailRequest;
import com.onetechsol.ipayment.pojo.FundRequestCheckRequest;
import com.onetechsol.ipayment.pojo.FundRequestResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BalanceRequestViewModel extends BaseViewModel {


    public Observable<CheckFundReqResponse> fundRequestCheck() {

        FundRequestCheckRequest addCustomerRequest = new FundRequestCheckRequest(ApiConstant.BASIC_VERSION);
        return iModelRepository().fundRequestCheck(addCustomerRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<CheckFundReqDetailResponse> fundRequestCheckDetails(String accId) {

        FundRequestCheckDetailRequest addCustomerRequest = new FundRequestCheckDetailRequest(accId);
        return iModelRepository().fundRequestCheckDetails(addCustomerRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FundRequestResponse> fundRequest(String txnAmt, String step, String requestTo, String bankTxn, String securitys, String tpin, String otp) {

        FundRequest fundRequest = new FundRequest(txnAmt, step, requestTo, bankTxn, securitys, tpin, otp);
        return iModelRepository().fundRequest(fundRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
