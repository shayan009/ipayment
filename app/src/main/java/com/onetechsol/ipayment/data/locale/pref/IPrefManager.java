package com.onetechsol.ipayment.data.locale.pref;

import com.onetechsol.ipayment.pojo.CheckLoginResponse;
import com.onetechsol.ipayment.pojo.UserProfile;
import com.onetechsol.ipayment.pojo.UserProfileSetup;
import com.onetechsol.ipayment.session.UserConfigSession;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;

public interface IPrefManager {

    void setUserConfigPrefData(CheckLoginResponse checkLoginResponse);

    UserConfigSession getUserConfigData();

    void setLoginResponseData(UserProfile userProfile, UserProfileSetup userProfileSetup, String token, String masterVersion, String companyVersion, String deviceId);

    String getUpgradeAmount();

    void setUpgradeAmount(String amount);

    String getLoginToken();

    String getUsername();

    UserLoginSession getUserSession();

    void clearLoginData();

    void setCurrentLocation(double latitude, double longitude);

    UserLocation getCurrentLocation();

    String getFirebaseToken();

    void setFirebaseToken(String token);

}
