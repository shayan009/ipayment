package com.onetechsol.ipayment.ui.screen.fingerprint;

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

}
