package com.onetechsol.ipayment.ui.screen.change_tpass;

import com.onetechsol.ipayment.pojo.ChangeTPinRequest;
import com.onetechsol.ipayment.pojo.ChangeTpinResponse;
import com.onetechsol.ipayment.pojo.InitiatePaymentRequest;
import com.onetechsol.ipayment.pojo.InitiatePaymentResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChangeTpinViewModel extends BaseViewModel {



    public Observable<ChangeTpinResponse> changeTpin(String newTpin, String confirmTpin, String security, String tpin, String otp) {

        ChangeTPinRequest changeTPinRequest = new ChangeTPinRequest(newTpin,confirmTpin,security,tpin,otp);
        return iModelRepository().changeTpin(changeTPinRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


}
