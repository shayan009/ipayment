package com.onetechsol.ipayment.ui.screen.fingerprint;

import com.onetechsol.ipayment.pojo.AuthAepsOpRequest;
import com.onetechsol.ipayment.pojo.AuthAepsOpResponse;
import com.onetechsol.ipayment.pojo.StartKyc12Request;
import com.onetechsol.ipayment.pojo.StartKyc12Response;
import com.onetechsol.ipayment.pojo.StartKyc18Request;
import com.onetechsol.ipayment.pojo.StartKyc18Response;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FingerPrintVideModel extends BaseViewModel {


    public Observable<StartKyc18Response> startKyc18(String step, String urlenco) {

        String latitude = prefManager().getCurrentLocation().latitude();
        String longitude = prefManager().getCurrentLocation().longitude();
        StartKyc18Request startKyc18Request = new StartKyc18Request("", step, "",urlenco,latitude,longitude);

        return iModelRepository().startKyc18(startKyc18Request)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StartKyc12Response> startKyc12(String step, String urlenco) {

        String latitude = prefManager().getCurrentLocation().latitude();
        String longitude = prefManager().getCurrentLocation().longitude();

        StartKyc12Request startKyc12Request = new StartKyc12Request(urlenco,latitude,longitude,step);


        return iModelRepository().startKyc12(startKyc12Request)
                .observeOn(AndroidSchedulers.mainThread());
    }



    public Observable<AuthAepsOpResponse> authenticateAepsOperation(String capture, String amt, String mobile, String aadhar, String bank, int mode) {

        String longitude = prefManager().getCurrentLocation().longitude();
        String latitude = prefManager().getCurrentLocation().latitude();
        String device = "ANY";
        String operation = "";
        switch (mode) {
            case 1:
                operation = "BALANCEINFO";
                break;
            case 2:
                operation = "WITHDRAWAL";
                break;
            case 3:
                operation = "MINISTATEMENT";
                break;
        }
        AuthAepsOpRequest authAepsOpRequest = new AuthAepsOpRequest(longitude, latitude, capture, amt, mobile, aadhar, bank, operation, device);


        return iModelRepository().authenticateAepsOperation(authAepsOpRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
