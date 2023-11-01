package com.onetechsol.ipayment.data.remote.network;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.utils.ApiConstant;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyOkHttpInterceptor implements Interceptor {


    @Inject
    public PrefManager prefManager;

    public MyOkHttpInterceptor(PrefManager prefManager) {
        this.prefManager = prefManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String basicLat = "22.554115";
        String basicLong = "88.338562";
        String basicDevice = Settings.Secure.getString(MainApp.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        Request.Builder builder = chain.request().newBuilder();

        String bearerAuth = "";
        String userName = "";

        if (prefManager != null && StringUtils.isNotBlank(prefManager.getLoginToken())) {
            bearerAuth = "Bearer " + prefManager.getLoginToken();
            userName = prefManager.getUsername();
            basicLat = prefManager.getCurrentLocation().latitude();
            basicLong = prefManager.getCurrentLocation().longitude();
        }


        String path = chain.request().url().url().getPath();
        Log.d("path : ", path);

        if (TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.LOGIN, path)) {
            Log.i("TAG ::: ", path);
            // loginHeader.put(ApiConstant.AUTHORIZATION, baererToken);
            builder.addHeader(ApiConstant.APP_ID, ApiConstant.BASIC_APP_ID);
            builder.addHeader(ApiConstant.LATITUDE, basicLat);
            builder.addHeader(ApiConstant.LONGITUDE, basicLong);
            builder.addHeader(ApiConstant.DEVICE, basicDevice);
            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);

        } else if (TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.LOGIN_CHECK, path)) {
            Log.i("TAG ::: ", path);
            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);
            builder.addHeader(ApiConstant.USERNAME, userName);
            builder.addHeader(ApiConstant.AUTHORIZATION, bearerAuth);
        } else if (TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.VERIFY_OTP, path)) {
            Log.i("TAG ::: ", path);
            //builder.addHeader(ApiConstant.AUTHORIZATION,baererToken);
            builder.addHeader(ApiConstant.APP_ID, ApiConstant.BASIC_APP_ID);
            builder.addHeader(ApiConstant.LATITUDE, basicLat);
            builder.addHeader(ApiConstant.LONGITUDE, basicLong);
            builder.addHeader(ApiConstant.DEVICE, basicDevice);
            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);
        } else if (TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SIGNUP_CHECK, path)) {

            builder.addHeader(ApiConstant.APP_ID, ApiConstant.BASIC_APP_ID);
            builder.addHeader(ApiConstant.LATITUDE, basicLat);
            builder.addHeader(ApiConstant.LONGITUDE, basicLong);
            builder.addHeader(ApiConstant.DEVICE, basicDevice);
            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);

        } else if (
                TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SIGNUP, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.APP_SETUP, path)
        ) {
            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);
            builder.addHeader(ApiConstant.APP_ID, ApiConstant.BASIC_APP_ID);
        } else if (
                TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_33_ONBOARD_CHECK, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.AEPS1_BANK_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.MY_WALLET_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_25_ONBOARD_CHECK, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.KYC_API_18, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.UPLOAD_BANKING_KYC, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.KYC_API_12_REQUEST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.KYC_API_12_RESPONSE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_1, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_2, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_3, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_4, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_BALK_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_BENEFETCH, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_23_CHARGE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_OPERATOR, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CIRCLE_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_OPERATOR_INFO, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.MOBILIE_VPLAN, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_1_RECHARGE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_1_FETCH, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CUS_CAT_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CUS_EXT_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CUS_EXT_SINGLE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.GET_KIT_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.BUY_KIT, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.MY_PACKAGE_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.GET_REPORT_TYPE_LIST_URL, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.VIEW_ALL_TRANSACTION_REPORT, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CATEGORY, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_CATEGORY_WISE_LIST_URL, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.ADD_CUSTOMER_AFFILIATE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.AFFILIATE_CUSTOMER_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.TRANSFER_WALLET, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.FUND_REQUEST_CHECK, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.FUND_REQUEST_CHECK_DETAILS, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.FUND_REQUEST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.BUY_INSURANCE_DETAIL, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.INITIATE_PAYMENT, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.CUSTOMER_DEPARTMENT_LIST, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.CUSTOMER_TO_MERCHANT, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.CUSTOMER_UPGRADE_INFO, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.MATM_BALANCE_CHECK_WITHDRAW, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.MATM_MICRO_ATM_RESPONSE_BE, path) ||
                        TextUtils.equals(ApiConstant.CONTEXT_PATH + ApiConstant.SERVICE_33_MAIN, path)

        ) {

            builder.addHeader(ApiConstant.VERSION, ApiConstant.BASIC_VERSION);
            builder.addHeader(ApiConstant.USERNAME, userName);
            builder.addHeader(ApiConstant.AUTHORIZATION, bearerAuth);
            Log.d("Username", userName);
            Log.d("bearer", bearerAuth);
            Log.d("API PATH", ApiConstant.CONTEXT_PATH + ApiConstant.VIEW_ALL_TRANSACTION_REPORT);


        }


        return chain.proceed(builder.build());
    }
}
