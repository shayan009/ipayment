package com.onetechsol.ipayment.ui.screen.service.aeps.aeps1;

import android.net.Uri;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.pojo.AEPS1ReportRequest;
import com.onetechsol.ipayment.pojo.AEPS1ReportResponse;
import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.AuthAepsOpRequest;
import com.onetechsol.ipayment.pojo.AuthAepsOpResponse;
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

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AEPSOperationViewModel extends BaseViewModel {




    public Observable<List<AepsBankModel>> getAepsBankList(String s) {
        return iModelRepository().getAepsBankList(s)
                .observeOn(AndroidSchedulers.mainThread());
    }





}
