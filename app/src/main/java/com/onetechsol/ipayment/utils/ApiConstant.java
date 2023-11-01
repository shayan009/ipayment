package com.onetechsol.ipayment.utils;

public interface ApiConstant {


    String BASE_DOMAIN = "https://partner.ipayments.in";
    String BASIC_VERSION = "1.0.26";
    String BASE_PRINT_REPORT = BASE_DOMAIN + "print/report";
    String CONTEXT_PATH = "/api/";
    String BASE_URL = BASE_DOMAIN + CONTEXT_PATH;
    String BASE_IMG_DOMAIN = "https://images.incomeowl.in/";

    String BASE_URL_IMAGE_SERVICE = BASE_IMG_DOMAIN;
    String LOGIN_CHECK = "token";
    String SIGNUP_CHECK = "signup_check";

    String UPLOAD_BANKING_KYC = "kyc_banking";


    String MY_WALLET_LIST = "wallet_list";
    String APP_SETUP = "app_setup";
    String BASIC_APP_ID = "com.fintech.ipayments";
    String LOGIN = "login";
    String SIGNUP = "signup";
    String VERIFY_OTP = "login_otdevice";

    String AEPS1_BANK_LIST = "aeps_18_bank_autocomplete";
    // Header constants
    String AUTHORIZATION = "Authorization";
    String APP_ID = "Appid";
    String VERSION = "Version";
    String DEVICE = "Device";
    String LATITUDE = "Lat";
    String LONGITUDE = "Long";
    String SERVICE_CATEGORY = "service_category";
    String USERNAME = "Username";

    String SERVICE_CATEGORY_WISE_LIST_URL = "service";
    String VIEW_ALL_TRANSACTION_REPORT = "report";
    String SERVICE_33_ONBOARD_CHECK = "service_33_onboard_check";
    String SERVICE_25_ONBOARD_CHECK = "service_25_onboard_check";

    String KYC_API_18 = "aeps_map_user_18";
    String KYC_API_12_REQUEST = "aeps_map_user_12";
    String KYC_API_12_RESPONSE = "aeps_map_user_12_response";
    long TIMEOUT = 120; //seconds
    long TIMEOUT_READ = 120; //seconds
    long TIMEOUT_WRITE = 120; //seconds


    String SERVICE_23_1 = "service_23_1";

    String SERVICE_23_2 = "service_23_2";
    String SERVICE_23_3 = "service_23_3";
    String SERVICE_23_4 = "service_23_4";

    String SERVICE_23_CHARGE = "service_23_charge";
    String SERVICE_23_BENEFETCH = "service_23_benefetch";

    String SERVICE_23_BALK_LIST = "dmt_12_bank_autocomplete";
    String SERVICE_OPERATOR = "service_operator";
    String SERVICE_OPERATOR_INFO = "service_operator_info";
    String SERVICE_CIRCLE_LIST = "service_circle";

    String MOBILIE_VPLAN = "mobile_plan_viewplan";
    String SERVICE_1_RECHARGE = "service_1_app";
    String FORGOT_CREATE_OTP = "forgot_otp_app";
    String SERVICE_1_FETCH = "service_1_fetch_app";


    String GET_KIT_LIST = "kit_list";
    String BUY_KIT = "package_kit_add_retailer";

    String GET_REPORT_TYPE_LIST_URL = "app_report_type_list";
    String ADD_CUSTOMER_AFFILIATE = "customer_info_submit_retailer.php";
    String AFFILIATE_CUSTOMER_LIST = "customer_info_retailer.php";

    String MY_PACKAGE_LIST = "my_commission";
    String INITIATE_PAYMENT = "lerner_upline_initiate";
    String CUSTOMER_DEPARTMENT_LIST = "service_customer_department_list";
    String CUSTOMER_TO_MERCHANT = "learner_upline_confirmation";
    String CUSTOMER_UPGRADE_INFO = "larner_upgrade_package_info";
    String MATM_BALANCE_CHECK_WITHDRAW = "service_73";
    String MATM_MICRO_ATM_RESPONSE_BE = "service_73_response";

    String SERVICE_33_MAIN= "service_33";
    String TRANSFER_WALLET = "wallet_management";

    String FUND_REQUEST_CHECK = "fund_request_check";

    String FUND_REQUEST_CHECK_DETAILS = "fund_request_check_details";
    String FUND_REQUEST = "fund_request";

    public static String BUY_INSURANCE_DETAIL = "service_70";
    public static String SERVICE_71 = "service_71";
    public static String SERVICE_72 = "service_72";


    //affiliate
    String SERVICE_CUS_CAT_LIST = "service_customer_category_list";
    String SERVICE_CUS_EXT_LIST = "service_customer_external_list";
    String SERVICE_CUS_EXT_SINGLE = "service_customer_external_single";


}
