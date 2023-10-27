package com.onetechsol.ipayment.ui.screen.login_singup.login;

import android.view.View;

public interface LoginClickEvent {

    void clearMobileField(View view);

    void requestOtp();

    void reSendOtp();


}
