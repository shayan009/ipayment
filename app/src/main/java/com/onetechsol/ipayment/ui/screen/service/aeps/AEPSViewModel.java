package com.onetechsol.ipayment.ui.screen.service.aeps;

import android.net.Uri;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.pojo.AEPS1ReportRequest;
import com.onetechsol.ipayment.pojo.AEPS1ReportResponse;
import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.pojo.OnboardingCheckRequest;
import com.onetechsol.ipayment.pojo.OnboardingCheckResponse;
import com.onetechsol.ipayment.pojo.StartKyc12Request;
import com.onetechsol.ipayment.pojo.StartKyc12Response;
import com.onetechsol.ipayment.pojo.StartKyc18Request;
import com.onetechsol.ipayment.pojo.StartKyc18Response;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AEPSViewModel extends BaseViewModel {


    public Observable<List<AEPS1TaskModel>> getAeps1Features() {
        return Observable.just(getAEPS1Features()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private List<AEPS1TaskModel> getAEPS1Features() {

        Uri inquiry = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.cash);
        Uri withdraw = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.money_withdraw);
        Uri evaluation = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.evaluation);


        return Arrays.asList(new AEPS1TaskModel(0, inquiry.toString(), "Balance Inquiry"),
                new AEPS1TaskModel(1, withdraw.toString(), "Balance Withdraw"),
                new AEPS1TaskModel(2, evaluation.toString(), "Mini Statement")
        );
    }

    public Observable<AEPS1ReportResponse> getAEPS1Report(String categoryId, String fromDate, String toDate, String txnType, String txnSubType, String walletType, String txnStatus, String page) {

        AEPS1ReportRequest aeps1ReportRequest = new AEPS1ReportRequest(categoryId, fromDate, toDate, txnType, txnSubType, walletType, txnStatus, page);


        return iModelRepository().getAEPS1Report(aeps1ReportRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OnboardingCheckResponse> startServiceOnboarding(String categoryId) {

        OnboardingCheckRequest onboardingCheckRequest = new OnboardingCheckRequest(ApiConstant.BASIC_VERSION);

        return iModelRepository().startServiceOnboarding(onboardingCheckRequest, categoryId)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StartKyc18Response> startKyc18(String step, String otp) {

        StartKyc18Request startKyc18Request = new StartKyc18Request(ApiConstant.BASIC_VERSION, step, otp);


        return iModelRepository().startKyc18(startKyc18Request)
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<StartKyc12Response> startKyc12() {

        StartKyc12Request startKyc12Request = new StartKyc12Request(ApiConstant.BASIC_VERSION);


        return iModelRepository().startKyc12(startKyc12Request)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
