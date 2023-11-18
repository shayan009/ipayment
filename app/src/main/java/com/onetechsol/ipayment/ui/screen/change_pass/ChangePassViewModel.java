package com.onetechsol.ipayment.ui.screen.change_pass;

import com.onetechsol.ipayment.pojo.ChangePasswordRequest;
import com.onetechsol.ipayment.pojo.ChangePasswordResponse;
import com.onetechsol.ipayment.pojo.ChangeTPinRequest;
import com.onetechsol.ipayment.pojo.ChangeTpinResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChangePassViewModel extends BaseViewModel {


    public Observable<ChangePasswordResponse> changePass(String newPass, String confirmPass, String security, String tpin, String oldPass) {

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(oldPass, newPass, confirmPass, security, tpin, "");
        return iModelRepository().changePass(changePasswordRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
