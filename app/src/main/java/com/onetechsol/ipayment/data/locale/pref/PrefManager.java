package com.onetechsol.ipayment.data.locale.pref;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.pojo.CheckLoginResponse;
import com.onetechsol.ipayment.pojo.PageList;
import com.onetechsol.ipayment.pojo.StatusList;
import com.onetechsol.ipayment.pojo.SupportList;
import com.onetechsol.ipayment.pojo.UserProfile;
import com.onetechsol.ipayment.pojo.UserProfileSetup;
import com.onetechsol.ipayment.session.UserConfigSession;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.utils.PrefConstants;

import java.util.List;
import java.util.Locale;

public class PrefManager implements IPrefManager {

    // Sharedpref file name

    public static SharedPreferences prefsWelcome;
    public static SharedPreferences prefsLogin;
    public static SharedPreferences prefsConfig;


    // Shared pref mode
    private int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        init(context);
    }

    public void init(Context context) {
        prefsWelcome = context.getSharedPreferences(PrefConstants.PREF_WELCOME, PRIVATE_MODE);
        prefsLogin = context.getSharedPreferences(PrefConstants.PREF_LOGIN, PRIVATE_MODE);
        prefsConfig = context.getSharedPreferences(PrefConstants.PREF_CONFIG, PRIVATE_MODE);

    }

    public String getAddress() {
        return getCompleteAddressString(getCurrentLocation().latitude(), getCurrentLocation().longitude());
    }

    @SuppressLint("LongLogTag")
    private String getCompleteAddressString(String LATITUDE, String LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(MainApp.getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(LATITUDE), Double.parseDouble(LONGITUDE), 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.d("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    public String getWelcomeDetail() {
        return prefsWelcome.getString(PrefConstants.WELCOME_DETAILS, "");
    }

    public void setWelcomeDetails(String value) {
        setPrefStringData(prefsWelcome, PrefConstants.WELCOME_DETAILS, value);
    }

    public boolean getIsCaptureRequired() {
        return getPrefBooleanData(prefsLogin, PrefConstants.FINGER_PRINT_CAPTURE);
    }

    public void setIsCaptureRequired(boolean captureRequired) {
        setPrefBooleanData(prefsWelcome, PrefConstants.WELCOME_DETAILS, captureRequired);
    }

    @Override
    public void setUserConfigPrefData(CheckLoginResponse checkLoginResponse) {

        setPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_SIGNUP, checkLoginResponse.statusList.signup());
        setPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_FORGOT, checkLoginResponse.statusList.forgot());
        setPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_PWD, checkLoginResponse.statusList.pwd());

        setPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_LOGIN, checkLoginResponse.pageList.login());
        setPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_SIGNUP, checkLoginResponse.pageList.signup());
        setPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_FORGOT, checkLoginResponse.pageList.forgot());
        setPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_HOME, checkLoginResponse.pageList.home());

        setPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_MOBILE, checkLoginResponse.supportList.mob());
        setPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_EMAIL, checkLoginResponse.supportList.eml());
        setPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_WHATSAPP, checkLoginResponse.supportList.whatsapp());
    }

    @Override
    public UserConfigSession getUserConfigData() {

        return new UserConfigSession(new StatusList(getPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_SIGNUP), getPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_FORGOT), getPrefIntData(prefsConfig, PrefConstants.STATUS_LIST_PWD)),
                new PageList(getPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_LOGIN), getPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_FORGOT), getPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_HOME), getPrefIntData(prefsConfig, PrefConstants.PAGE_LIST_SIGNUP)),
                new SupportList(getPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_MOBILE), getPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_EMAIL), getPrefStringData(prefsConfig, PrefConstants.SUPPORT_LIST_WHATSAPP)));

    }

    @Override
    public void setLoginResponseData(UserProfile userProfile, UserProfileSetup userProfileSetup, String token, String masterVersion, String companyVersion, String deviceId) {

        setPrefStringData(prefsLogin, PrefConstants.LOGIN_TOKEN, token);
        setPrefStringData(prefsLogin, PrefConstants.USERNAME, userProfile.userName());
        setPrefStringData(prefsLogin, PrefConstants.LOGIN_NAME, userProfile.name());
        setPrefStringData(prefsLogin, PrefConstants.ROLE_ID, userProfile.roleId());
        setPrefStringData(prefsLogin, PrefConstants.BUSINESS_NAME, userProfile.businessName());
        setPrefStringData(prefsLogin, PrefConstants.BUSINESS_ADDRESS, userProfile.businessAddress());
        setPrefStringData(prefsLogin, PrefConstants.MOBILE, userProfile.mobile());
        setPrefStringData(prefsLogin, PrefConstants.MOBILE_ALTER, userProfile.mobileAlter());
        setPrefStringData(prefsLogin, PrefConstants.EMAIL, userProfile.email());
        setPrefStringData(prefsLogin, PrefConstants.ADDRESS, userProfile.address());
        setPrefStringData(prefsLogin, PrefConstants.PIN_CODE, userProfile.pinCode());
        setPrefStringData(prefsLogin, PrefConstants.STATE, userProfile.state());
        setPrefStringData(prefsLogin, PrefConstants.CITY, userProfile.city());
        setPrefStringData(prefsLogin, PrefConstants.PAN, userProfile.pan());
        setPrefStringData(prefsLogin, PrefConstants.AADHAR, userProfile.aadhar());
        setPrefStringData(prefsLogin, PrefConstants.DOB, userProfile.dob());
        setPrefStringData(prefsLogin, PrefConstants.MASTER_VERSION, masterVersion);
        setPrefStringData(prefsLogin, PrefConstants.COMPANY_VERSION, companyVersion);
        setPrefStringData(prefsLogin, PrefConstants.ANDROID_ID, deviceId);
        setPrefStringData(prefsLogin, PrefConstants.VIDEO_LINK, userProfileSetup.videoLink());

    }

    public void setRoleId(String roleId) {
        setPrefStringData(prefsLogin, PrefConstants.ROLE_ID, roleId);
    }

    @Override
    public String getUpgradeAmount() {
        return getPrefStringData(prefsLogin, PrefConstants.UPGRADE_AMOUNT);
    }

    @Override
    public void setUpgradeAmount(String amount) {
        setPrefStringData(prefsLogin, PrefConstants.UPGRADE_AMOUNT, amount);
    }

    public String getPrefStringData(SharedPreferences pref, String key) {
        return pref.getString(key, "");
    }

    @Override
    public String getLoginToken() {
        return prefsLogin.getString(PrefConstants.LOGIN_TOKEN, "");
    }

    @Override
    public String getUsername() {

        return prefsLogin.getString(PrefConstants.USERNAME, "");
    }

    private int getPrefIntData(SharedPreferences pref, String key) {
        return pref.getInt(key, 0);
    }

    @Override
    public UserLoginSession getUserSession() {

        return new UserLoginSession(getPrefStringData(prefsLogin, PrefConstants.USERNAME), getPrefStringData(prefsLogin, PrefConstants.ROLE_ID),
                getPrefStringData(prefsLogin, PrefConstants.LOGIN_NAME), getPrefStringData(prefsLogin, PrefConstants.BUSINESS_NAME), getPrefStringData(prefsLogin, PrefConstants.BUSINESS_ADDRESS),
                getPrefStringData(prefsLogin, PrefConstants.MOBILE), getPrefStringData(prefsLogin, PrefConstants.MOBILE_ALTER), getPrefStringData(prefsLogin, PrefConstants.EMAIL),
                getPrefStringData(prefsLogin, PrefConstants.ADDRESS), getPrefStringData(prefsLogin, PrefConstants.PIN_CODE), getPrefStringData(prefsLogin, PrefConstants.STATE),
                getPrefStringData(prefsLogin, PrefConstants.CITY), getPrefStringData(prefsLogin, PrefConstants.PAN), getPrefStringData(prefsLogin, PrefConstants.AADHAR),
                getPrefStringData(prefsLogin, PrefConstants.DOB), getPrefStringData(prefsLogin, PrefConstants.LOGIN_TOKEN),
                getPrefStringData(prefsLogin, PrefConstants.MASTER_VERSION), getPrefStringData(prefsLogin, PrefConstants.COMPANY_VERSION),
                getPrefStringData(prefsLogin, PrefConstants.ANDROID_ID), getPrefStringData(prefsLogin, PrefConstants.VIDEO_LINK),
                "0"
        );

    }

    private void setPrefStringData(SharedPreferences sharedPreferences, String stringKey, String stringValue) {
        sharedPreferences.edit().putString(stringKey, stringValue).apply();
    }

    private void setPrefBooleanData(SharedPreferences sharedPreferences, String stringKey, boolean stringValue) {
        sharedPreferences.edit().putBoolean(stringKey, stringValue).apply();
    }

    private boolean getPrefBooleanData(SharedPreferences pref, String key) {
        return pref.getBoolean(key, false);
    }


    private void setPrefIntData(SharedPreferences sharedPreferences, String stringKey, int intValue) {
        sharedPreferences.edit().putInt(stringKey, intValue).apply();

    }

    @Override
    public void clearLoginData() {
        prefsLogin.edit().clear().apply();
    }

    @Override
    public void setCurrentLocation(double latitude, double longitude) {
        setPrefStringData(prefsLogin, PrefConstants.LAST_LOCATION_LATITUDE, String.valueOf(latitude));
        setPrefStringData(prefsLogin, PrefConstants.LAST_LOCATION_LONGITUDE, String.valueOf(longitude));
    }

    @Override
    public UserLocation getCurrentLocation() {
        return new UserLocation(getPrefStringData(prefsLogin, PrefConstants.LAST_LOCATION_LATITUDE), getPrefStringData(prefsLogin, PrefConstants.LAST_LOCATION_LONGITUDE));
    }

    @Override
    public String getFirebaseToken() {
        return getPrefStringData(prefsWelcome, PrefConstants.FIREBASE_TOKEN);
    }

    @Override
    public void setFirebaseToken(String token) {
        setPrefStringData(prefsWelcome, PrefConstants.FIREBASE_TOKEN, token);
    }
}