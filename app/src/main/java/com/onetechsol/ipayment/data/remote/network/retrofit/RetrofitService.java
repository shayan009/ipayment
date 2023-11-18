package com.onetechsol.ipayment.data.remote.network.retrofit;

import com.onetechsol.ipayment.pojo.*;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {


    @POST(ApiConstant.LOGIN_CHECK)
    @Headers("Content-Type: application/json")
    Observable<CheckLoginResponse> checkLogin(@Body CheckLoginRequest checkLoginRequest);

    @POST(ApiConstant.LOGIN)
    @Headers("Content-Type: application/json")
    Observable<LoginResponse> sendOtp(@Body LoginRequest loginRequest, @Header("Authorization") String bearerToken);

    @POST(ApiConstant.VERIFY_OTP)
    @Headers("Content-Type: application/json")
    Observable<VerifyOtpResponse> verifyOtp(@Body VerifyOtpRequest verifyOtpRequest, @Header("Authorization") String bearerToken);

    @POST(ApiConstant.SIGNUP_CHECK)
    @Headers("Content-Type: application/json")
    Observable<CheckSignupResponse> checkSignup(@Body CheckSignupRequest checkSignup);


    @POST(ApiConstant.APP_SETUP)
    @Headers("Content-Type: application/json")
    Observable<AppSetupResponse> appSetup(@Body AppSetupRequest appSetupRequest);


    @POST(ApiConstant.SERVICE_CATEGORY)
    @Headers("Content-Type: application/json")
    Observable<ServiceListResponse> getServiceList(@Body ServiceListRequest serviceListRequest);


    @POST(ApiConstant.SIGNUP)
    @Headers("Content-Type: application/json")
    Observable<RegisterUserResponse> register(@Body RegisterUserRequest registerUserRequest);


    @POST(ApiConstant.MY_WALLET_LIST)
    @Headers("Content-Type: application/json")
    Observable<WalletListResponse> getWalletList(@Body WalletListRequest serviceListRequest);

    @POST(ApiConstant.SERVICE_CATEGORY_WISE_LIST_URL)
    @Headers("Content-Type: application/json")
    Observable<CategoryWiseServiceResponse> getCategoryWiseServiceList(@Body CategoryWiseServiceRequest categoryWiseServiceRequest);


    @POST(ApiConstant.VIEW_ALL_TRANSACTION_REPORT)
    @Headers("Content-Type: application/json")
    Observable<AEPS1ReportResponse> getAEPS1Report(@Body AEPS1ReportRequest aeps1Report);

    @POST(ApiConstant.SERVICE_33_ONBOARD_CHECK)
    @Headers("Content-Type: application/json")
    Observable<OnboardingCheckResponse> startService33Onboarding(@Body OnboardingCheckRequest aeps1Report);


    @POST(ApiConstant.SERVICE_25_ONBOARD_CHECK)
    @Headers("Content-Type: application/json")
    Observable<OnboardingCheckResponse> startService25Onboarding(@Body OnboardingCheckRequest aeps1Report);


    @POST(ApiConstant.KYC_API_18)
    @Headers("Content-Type: application/json")
    Observable<StartKyc18Response> startKyc18(@Body StartKyc18Request aeps1Report);

    @POST(ApiConstant.KYC_API_12_REQUEST)
    @Headers("Content-Type: application/json")
    Observable<StartKyc12Response> startKyc12(@Body StartKyc12Request aeps1Report);


    @POST(ApiConstant.KYC_API_12_RESPONSE)
    @Headers("Content-Type: application/json")
    Observable<StartKyc12CallbackResponse> startKyc12CallbackResponse(@Body StartKyc12CallbackRequest startKyc12CallbackRequest);


    @POST(ApiConstant.UPLOAD_BANKING_KYC)
    @Headers("Content-Type: application/json")
    Observable<UploadKycResponse> uploadBankingKyc(@Body UploadKycRequest uploadKycRequest);


    @GET(ApiConstant.AEPS1_BANK_LIST)
    @Headers("Content-Type: application/json")
    Observable<List<AepsBankModel>> getBankList(@Query("term") String term);


    @POST(ApiConstant.SERVICE_23_1)
    @Headers("Content-Type: application/json")
    Observable<CheckRemitterResponse> checkRemitter(@Body CheckRemitterRequest checkRemitterRequest);

    @POST(ApiConstant.SERVICE_23_2)
    @Headers("Content-Type: application/json")
    Observable<SubmitRemitterOtpResponse> submitRemitterOtp(@Body SubmitRemitterOtpRequest submitRemitterOtpRequest);

    @POST(ApiConstant.SERVICE_23_3)
    @Headers("Content-Type: application/json")
    Observable<AddBeneficiaryResponse> registerBeneficiary(@Body AddBeneficiaryRequest addBeneficiaryRequest);

    @POST(ApiConstant.SERVICE_23_4)
    @Headers("Content-Type: application/json")
    Observable<MoneyTransferResponse> moneyTransfer(@Body MoneyTransferRequest moneyTransferRequest);

    @POST(ApiConstant.SERVICE_23_CHARGE)
    @Headers("Content-Type: application/json")
    Observable<DMTChargeResponse> getCharge(@Body GetDMTChargeRequest getDMTChargeRequest);

    @POST(ApiConstant.SERVICE_23_BENEFETCH)
    @Headers("Content-Type: application/json")
    Observable<GetBeneficiaryResponse> getBeneficiaryList(@Body GetBeneficiaryRequest getBeneficiaryRequest);

    @GET(ApiConstant.SERVICE_23_BALK_LIST)
    @Headers("Content-Type: application/json")
    Observable<List<BeneficiaryBankModel>> getBeneficiaryBankList(@Query("term") String term);

    @POST(ApiConstant.SERVICE_OPERATOR)
    @Headers("Content-Type: application/json")
    Observable<GetOperatorResponse> getOperator(@Body GetOperatorRequest operatorCircleRequest);

    @POST(ApiConstant.SERVICE_CIRCLE_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetOperatorCircleResponse> getOperatorCircle(@Body GetOperatorCircleRequest operatorCircleRequest);

    @POST(ApiConstant.SERVICE_OPERATOR_INFO)
    @Headers("Content-Type: application/json")
    Observable<GetOperatorInfoResponse> getOperatorInfo(@Body GetOperatorInfoRequest getOperatorInfoRequest);

    @POST(ApiConstant.MOBILIE_VPLAN)
    @Headers("Content-Type: application/json")
    Observable<PrepaidMobilePlansResponse> prepaidPlans(@Body MobilePrepaidPlansRequest mobilePrepaidPlansRequest);

    @POST(ApiConstant.SERVICE_1_RECHARGE)
    @Headers("Content-Type: application/json")
    Observable<PostRechargeResponse> rechargeMobile(@Body PostRechargeRequest postRechargeRequest);

    @POST(ApiConstant.SERVICE_1_FETCH)
    @Headers("Content-Type: application/json")
    Observable<FetchBillResponse> fetchBill(@Body FetchBillRequest fetchBillRequest);

    @POST(ApiConstant.SERVICE_CUS_CAT_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetAffiliateServiceList> getAffiliateList();

    @POST(ApiConstant.SERVICE_CUS_EXT_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetAffiliateProductList> getAffiliateProductList(@Body FetchProductListRequest fetchProductListRequest);

    @POST(ApiConstant.SERVICE_CUS_EXT_SINGLE)
    @Headers("Content-Type: application/json")
    Observable<GetAffiliateProductDetail> getAffiliateProductDetail(@Body FetchProductDetailRequest fetchProductDetailRequest);

    @POST(ApiConstant.FORGOT_CREATE_OTP)
    @Headers("Content-Type: application/json")
    Observable<GetAffiliateProductDetail> resendOtp(@Body FetchProductDetailRequest fetchProductDetailRequest);


    @POST(ApiConstant.GET_KIT_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetKitListResponse> getKitList(@Body GetKitRequest getKitRequest);

    @POST(ApiConstant.MY_PACKAGE_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetPackageSlabResponse> myPackageList(@Body GetPackageSlabRequest getPackageSlabRequest);


    @POST(ApiConstant.BUY_KIT)
    @Headers("Content-Type: application/json")
    Observable<BuyKitResponse> buyKit(@Body BuyKitRequestRequest buyKitRequestRequest);

    @POST(ApiConstant.FUND_REQUEST_CHECK)
    @Headers("Content-Type: application/json")
    Observable<CheckFundReqResponse> fundRequestCheck(@Body FundRequestCheckRequest buyKitRequestRequest);


    @POST(ApiConstant.FUND_REQUEST_CHECK_DETAILS)
    @Headers("Content-Type: application/json")
    Observable<CheckFundReqDetailResponse> fundRequestCheckDetails(@Body FundRequestCheckDetailRequest buyKitRequestRequest);

    @POST(ApiConstant.TRANSFER_WALLET)
    @Headers("Content-Type: application/json")
    Observable<FundTransferResponse> fundTransferRequest(@Body FundTransferRequest fundTransferRequest);

    @POST(ApiConstant.BUY_INSURANCE_DETAIL)
    @Headers("Content-Type: application/json")
    Observable<BuyInsuranceDetailResponse> buyInsuranceDetail(@Body BuyInsuranceDetailRequest buyInsuranceDetailRequest);


    @POST(ApiConstant.FUND_REQUEST)
    @Headers("Content-Type: application/json")
    Observable<FundRequestResponse> fundRequest(@Body FundRequest fundRequest);

    @POST(ApiConstant.GET_REPORT_TYPE_LIST_URL)
    @Headers("Content-Type: application/json")
    Observable<GetReportTypeResponse> getReportCategoryList(@Body GetReportTypeRequest getReportTypeRequest);

    @POST(ApiConstant.ADD_CUSTOMER_AFFILIATE)
    @Headers("Content-Type: application/json")
    Observable<AddCustomerResponse> addCustomerAffiliate(@Body AddCustomerRequest getReportTypeRequest);


    @POST(ApiConstant.AFFILIATE_CUSTOMER_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetCustomerResponse> customerListAffiliate(@Body GetCustomerRequest getCustomerRequest);

    @POST(ApiConstant.INITIATE_PAYMENT)
    @Headers("Content-Type: application/json")
    Observable<InitiatePaymentResponse> initiatePayment(@Body InitiatePaymentRequest initiatePaymentRequest);

    @POST(ApiConstant.CUSTOMER_DEPARTMENT_LIST)
    @Headers("Content-Type: application/json")
    Observable<GetDepartmentListResponse> getDepartmentList();

    @POST(ApiConstant.CUSTOMER_TO_MERCHANT)
    @Headers("Content-Type: application/json")
    Observable<CustomerUpgradeResponse> customerToMerchantUpgrade(@Body CustomerUpgradeRequest customerUpgradeRequest);

    @POST(ApiConstant.CUSTOMER_UPGRADE_INFO)
    @Headers("Content-Type: application/json")
    Observable<CustomerUpgradeInfoResponse> customerToMerchantUpgradeInfo();


    @POST(ApiConstant.MATM_BALANCE_CHECK_WITHDRAW)
    @Headers("Content-Type: application/json")
    Observable<MatmServiceResponse> hitMatmApiForBalWithdrawal(@Body MatmServiceRequest matmServiceRequest);


    @POST(ApiConstant.MATM_MICRO_ATM_RESPONSE_BE)
    @Headers("Content-Type: application/json")
    Observable<MatmMicroAmtFeedBackResponse> passMicroAtmResponseBE(@Body MatmMicroAmtFeedBackRequest matmServiceRequest);


    @POST(ApiConstant.SERVICE_33_MAIN)
    @Headers("Content-Type: application/json")
    Observable<AuthAepsOpResponse> authenticateAepsOperation(@Body AuthAepsOpRequest authAepsOpRequest);

    @FormUrlEncoded
    @POST(ApiConstant.PAYOUT_VIEW_BANK_DETAIL)
    @Headers("Content-Type: application/json")
    Observable<PayoutBankDetailResponse> payoutBankDetails(@FieldMap HashMap<String,String> stringHashMap);

    @POST(ApiConstant.PAYOUT_SERVICE_CHECK)
    @Headers("Content-Type: application/json")
    Observable<CheckPayoutServiceResponse> payoutServiceCheck(@Body CheckPayoutServiceRequest checkPayoutServiceRequest);


    @GET(ApiConstant.PAYOUT_BANK_LIST)
    @Headers("Content-Type: application/json")
    Observable<List<AepsBankModel>> payoutBankList(@Query("term") String term);


    @POST(ApiConstant.PAYOUT_BANK_CREATE)
    @Headers("Content-Type: application/json")
    Observable<PayoutCreateBankResponse> createBank(@Body PayoutAddBankRequest payoutAddBankRequest);

    @POST(ApiConstant.PAYOUT_SUBMIT)
    @Headers("Content-Type: application/json")
    Observable<PayoutSubmitResponse> payoutSubmit(@Body PayoutSubmitRequest payoutSubmitRequest);


    @POST(ApiConstant.SERVICE_24_CHARGE)
    @Headers("Content-Type: application/json")
    Observable<PayoutChargeResponse> getPayoutCharge(@Body GetPayoutChargeRequest getDMTChargeRequest);

    @POST(ApiConstant.CHANGE_TPIN)
    @Headers("Content-Type: application/json")
    Observable<ChangeTpinResponse> changeTpin(@Body ChangeTPinRequest changeTPinRequest);

    @POST(ApiConstant.CHANGE_PASS)
    @Headers("Content-Type: application/json")
    Observable<ChangePasswordResponse> changePass(@Body ChangePasswordRequest changePasswordRequest);

    @POST(ApiConstant.SERVICE_EXTERNAL)
    @Headers("Content-Type: application/json")
    Observable<GetExternalServiceResponse> externalService(@Body GetExternalServiceRequest getExternalServiceRequest);
}


