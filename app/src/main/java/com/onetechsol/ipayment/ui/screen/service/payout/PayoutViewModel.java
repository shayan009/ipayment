package com.onetechsol.ipayment.ui.screen.service.payout;

import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.CheckPayoutServiceRequest;
import com.onetechsol.ipayment.pojo.CheckPayoutServiceResponse;
import com.onetechsol.ipayment.pojo.CircleBankModel;
import com.onetechsol.ipayment.pojo.GetPayoutChargeRequest;
import com.onetechsol.ipayment.pojo.PayoutAddBankRequest;
import com.onetechsol.ipayment.pojo.PayoutBankDetailResponse;
import com.onetechsol.ipayment.pojo.PayoutChargeResponse;
import com.onetechsol.ipayment.pojo.PayoutCreateBankResponse;
import com.onetechsol.ipayment.pojo.PayoutMode;
import com.onetechsol.ipayment.pojo.PayoutSubmitRequest;
import com.onetechsol.ipayment.pojo.PayoutSubmitResponse;
import com.onetechsol.ipayment.pojo.StartKyc12Request;
import com.onetechsol.ipayment.pojo.StartKyc12Response;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PayoutViewModel extends BaseViewModel {

    private List<CircleBankModel> circleBankModelList;
    private List<PayoutMode> payoutModeList;

    public Observable<PayoutBankDetailResponse> payoutBankDetails() {


        HashMap<String,String> stringHashMap = new HashMap<>();
        stringHashMap.put("UserId","");
        stringHashMap.put("LoginCode","");

        return iModelRepository().payoutBankDetails(stringHashMap)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CheckPayoutServiceResponse> payoutServiceCheck() {

        CheckPayoutServiceRequest checkPayoutServiceRequest = new CheckPayoutServiceRequest(ApiConstant.VERSION);

        return iModelRepository().payoutServiceCheck(checkPayoutServiceRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<AepsBankModel>> payoutServiceCheck(String query) {

        return iModelRepository().getPayoutBank(query)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PayoutCreateBankResponse> payoutAddBank(String accountNo, String accountHolder, String ifsc, String branch, String bank, String docUploadId, String panUp, String passbookUp) {

        PayoutAddBankRequest payoutAddBankRequest = new PayoutAddBankRequest(accountNo,accountHolder,ifsc,branch,bank,docUploadId,panUp,passbookUp);

        return iModelRepository().createPayoutBank(payoutAddBankRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PayoutSubmitResponse> payoutSubmit(String mode, String amount, String accountId, String security, String tpin, String otp) {

        PayoutSubmitRequest payoutSubmitRequest = new PayoutSubmitRequest(mode,String.valueOf(Math.round(Float.parseFloat(amount))),accountId,security,tpin,otp);

        return iModelRepository().payoutSubmit(payoutSubmitRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void setCircleBankModelList(List<CircleBankModel> circleBankModelList) {
        this.circleBankModelList = circleBankModelList;
    }

    public List<PayoutMode> getPayoutModeList() {
        return null != payoutModeList ? payoutModeList : new ArrayList<>();
    }

    public void setPayoutModeList(List<PayoutMode> payoutModeList) {
        this.payoutModeList = payoutModeList;
    }


    public List<CircleBankModel> getCircleBankModelList() {
        return null != circleBankModelList ? circleBankModelList : new ArrayList<>();
    }

    public Observable<PayoutChargeResponse> getPayoutCharge(String amount, String mode) {

        GetPayoutChargeRequest getPayoutChargeRequest = new GetPayoutChargeRequest(amount,mode);

        return iModelRepository().getPayoutCharge(getPayoutChargeRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
