package com.onetechsol.ipayment.ui.screen.service.matm;

import com.onetechsol.ipayment.pojo.BuyInsuranceDetailRequest;
import com.onetechsol.ipayment.pojo.BuyInsuranceDetailResponse;
import com.onetechsol.ipayment.pojo.MatmMicroAmtFeedBackRequest;
import com.onetechsol.ipayment.pojo.MatmMicroAmtFeedBackResponse;
import com.onetechsol.ipayment.pojo.MatmServiceRequest;
import com.onetechsol.ipayment.pojo.MatmServiceResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MatmViewModel extends BaseViewModel {


    public Observable<MatmServiceResponse> getMatmDetails(String amount, String mode) {

        MatmServiceRequest matmServiceRequest = new MatmServiceRequest(mode,amount);

        return iModelRepository().hitMatmApiForBalWithdrawal(matmServiceRequest).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<MatmMicroAmtFeedBackResponse> passMicroAtmResponseBE(boolean status, String message, String response, double transAmount, double balAmount, String bankRrn, String transType, int type, String cardNum, String rrn, String tType, String bankName, String cardType, String terminalId, String fpId, String transId, String txn) {

        MatmMicroAmtFeedBackRequest matmServiceRequest = new MatmMicroAmtFeedBackRequest()
                .setBalAmount(balAmount).setBankName(bankName).setBankRrn(bankRrn).setRrn(rrn)
                .setTxn(txn).setCardNum(cardNum).setCardType(cardType).setMessage(message)
                .setFpId(fpId).setType(type).setResponse(response).settType(tType).setTransType(transType)
                .setStatus(status).setTerminalId(terminalId).setTransId(transId).setTransAmount(transAmount);

        return iModelRepository().passMicroAtmResponseBE(matmServiceRequest).observeOn(AndroidSchedulers.mainThread());
    }

}
