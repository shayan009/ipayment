package com.onetechsol.ipayment.ui.screen.dashboard;

import com.onetechsol.ipayment.pojo.CustomerUpgradeInfoResponse;
import com.onetechsol.ipayment.pojo.CustomerUpgradeRequest;
import com.onetechsol.ipayment.pojo.CustomerUpgradeResponse;
import com.onetechsol.ipayment.pojo.InitiatePaymentRequest;
import com.onetechsol.ipayment.pojo.InitiatePaymentResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DashboardViewModel extends BaseViewModel {


    public Observable<InitiatePaymentResponse> initiatePayment(String amount) {

        InitiatePaymentRequest initiatePaymentRequest = new InitiatePaymentRequest(amount);
        return iModelRepository().initiatePayment(initiatePaymentRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CustomerUpgradeResponse> customerToMerchantUpgrade(String amount, String txnId) {

        CustomerUpgradeRequest customerUpgradeRequest = new CustomerUpgradeRequest(amount, txnId);
        return iModelRepository().customerToMerchantUpgrade(customerUpgradeRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CustomerUpgradeInfoResponse> customerToMerchantUpgradeInfo() {
        return iModelRepository().customerToMerchantUpgradeInfo()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
