package com.onetechsol.ipayment.ui.screen.service.dmt;

import com.onetechsol.ipayment.pojo.AddBeneficiaryRequest;
import com.onetechsol.ipayment.pojo.AddBeneficiaryResponse;
import com.onetechsol.ipayment.pojo.BeneficiaryBankModel;
import com.onetechsol.ipayment.pojo.BeneficiaryModel;
import com.onetechsol.ipayment.pojo.CheckRemitterRequest;
import com.onetechsol.ipayment.pojo.CheckRemitterResponse;
import com.onetechsol.ipayment.pojo.DMTChargeResponse;
import com.onetechsol.ipayment.pojo.GetBeneficiaryRequest;
import com.onetechsol.ipayment.pojo.GetBeneficiaryResponse;
import com.onetechsol.ipayment.pojo.GetDMTChargeRequest;
import com.onetechsol.ipayment.pojo.MoneyTransferRequest;
import com.onetechsol.ipayment.pojo.MoneyTransferResponse;
import com.onetechsol.ipayment.pojo.SubmitRemitterOtpRequest;
import com.onetechsol.ipayment.pojo.SubmitRemitterOtpResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DMTViewModel extends BaseViewModel {


    public Observable<CheckRemitterResponse> checkRemitter(String mobile) {
        CheckRemitterRequest checkRemitterRequest = new CheckRemitterRequest(mobile);
        return iModelRepository().checkRemitter(checkRemitterRequest).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SubmitRemitterOtpResponse> submitOtpRemitter(String mobile, String name, String otp, String help) {
        SubmitRemitterOtpRequest submitRemitterOtpRequest = new SubmitRemitterOtpRequest(mobile, name, otp, help);
        return iModelRepository().submitRemitterOtp(submitRemitterOtpRequest).observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<AddBeneficiaryResponse> registerBeneficiary(String remMobile, String beneficiaryName, String bank, String beneficiaryIfsc, String beneAccount) {
        AddBeneficiaryRequest addBeneficiaryRequest = new AddBeneficiaryRequest(remMobile, beneficiaryName, bank, beneficiaryIfsc, beneAccount);
        return iModelRepository().registerBeneficiary(addBeneficiaryRequest).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GetBeneficiaryResponse> getBeneficiaryList(String remMobile) {
        GetBeneficiaryRequest addBeneficiaryRequest = new GetBeneficiaryRequest(remMobile);
        return iModelRepository().getBeneficiaryList(addBeneficiaryRequest).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<BeneficiaryBankModel>> getBeneficiaryBankList(String query) {
        return iModelRepository().getBeneficiaryBankList(query).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MoneyTransferResponse> moneyTransfer(BeneficiaryModel beneficiaryModel, String amount, String remMobile) {
        MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest(remMobile, beneficiaryModel.beneficiaryName(), beneficiaryModel.beneficiaryBank(), beneficiaryModel.beneIfsc(), beneficiaryModel.beneficiaryAccount(), beneficiaryModel.benficiaryId(), amount, "0", "", "");

        return iModelRepository().moneyTransfer(moneyTransferRequest).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DMTChargeResponse> getCharge(String amount) {

        GetDMTChargeRequest getDMTChargeRequest = new GetDMTChargeRequest(amount);

        return iModelRepository().getDMTCharge(getDMTChargeRequest).observeOn(AndroidSchedulers.mainThread());
    }

}
