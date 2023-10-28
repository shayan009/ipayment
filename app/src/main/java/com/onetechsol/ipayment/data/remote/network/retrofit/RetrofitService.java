package com.onetechsol.ipayment.data.remote.network.retrofit;

import com.onetechsol.ipayment.pojo.AEPS1ReportRequest;
import com.onetechsol.ipayment.pojo.AEPS1ReportResponse;
import com.onetechsol.ipayment.pojo.AddBeneficiaryRequest;
import com.onetechsol.ipayment.pojo.AddBeneficiaryResponse;
import com.onetechsol.ipayment.pojo.AddCustomerRequest;
import com.onetechsol.ipayment.pojo.AddCustomerResponse;
import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.AppSetupRequest;
import com.onetechsol.ipayment.pojo.AppSetupResponse;
import com.onetechsol.ipayment.pojo.BeneficiaryBankModel;
import com.onetechsol.ipayment.pojo.BuyInsuranceDetailRequest;
import com.onetechsol.ipayment.pojo.BuyInsuranceDetailResponse;
import com.onetechsol.ipayment.pojo.BuyKitRequestRequest;
import com.onetechsol.ipayment.pojo.BuyKitResponse;
import com.onetechsol.ipayment.pojo.CategoryWiseServiceRequest;
import com.onetechsol.ipayment.pojo.CategoryWiseServiceResponse;
import com.onetechsol.ipayment.pojo.CheckFundReqDetailResponse;
import com.onetechsol.ipayment.pojo.CheckFundReqResponse;
import com.onetechsol.ipayment.pojo.CheckLoginRequest;
import com.onetechsol.ipayment.pojo.CheckLoginResponse;
import com.onetechsol.ipayment.pojo.CheckRemitterRequest;
import com.onetechsol.ipayment.pojo.CheckRemitterResponse;
import com.onetechsol.ipayment.pojo.CheckSignupRequest;
import com.onetechsol.ipayment.pojo.CheckSignupResponse;
import com.onetechsol.ipayment.pojo.CustomerUpgradeInfoResponse;
import com.onetechsol.ipayment.pojo.CustomerUpgradeRequest;
import com.onetechsol.ipayment.pojo.CustomerUpgradeResponse;
import com.onetechsol.ipayment.pojo.DMTChargeResponse;
import com.onetechsol.ipayment.pojo.FetchBillRequest;
import com.onetechsol.ipayment.pojo.FetchBillResponse;
import com.onetechsol.ipayment.pojo.FetchProductDetailRequest;
import com.onetechsol.ipayment.pojo.FetchProductListRequest;
import com.onetechsol.ipayment.pojo.FundRequest;
import com.onetechsol.ipayment.pojo.FundRequestCheckDetailRequest;
import com.onetechsol.ipayment.pojo.FundRequestCheckRequest;
import com.onetechsol.ipayment.pojo.FundRequestResponse;
import com.onetechsol.ipayment.pojo.FundTransferRequest;
import com.onetechsol.ipayment.pojo.FundTransferResponse;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetail;
import com.onetechsol.ipayment.pojo.GetAffiliateProductList;
import com.onetechsol.ipayment.pojo.GetAffiliateServiceList;
import com.onetechsol.ipayment.pojo.GetBeneficiaryRequest;
import com.onetechsol.ipayment.pojo.GetBeneficiaryResponse;
import com.onetechsol.ipayment.pojo.GetCustomerRequest;
import com.onetechsol.ipayment.pojo.GetCustomerResponse;
import com.onetechsol.ipayment.pojo.GetDMTChargeRequest;
import com.onetechsol.ipayment.pojo.GetDepartmentListResponse;
import com.onetechsol.ipayment.pojo.GetKitListResponse;
import com.onetechsol.ipayment.pojo.GetKitRequest;
import com.onetechsol.ipayment.pojo.GetOperatorCircleRequest;
import com.onetechsol.ipayment.pojo.GetOperatorCircleResponse;
import com.onetechsol.ipayment.pojo.GetOperatorInfoRequest;
import com.onetechsol.ipayment.pojo.GetOperatorInfoResponse;
import com.onetechsol.ipayment.pojo.GetOperatorRequest;
import com.onetechsol.ipayment.pojo.GetOperatorResponse;
import com.onetechsol.ipayment.pojo.GetPackageSlabRequest;
import com.onetechsol.ipayment.pojo.GetPackageSlabResponse;
import com.onetechsol.ipayment.pojo.GetReportTypeRequest;
import com.onetechsol.ipayment.pojo.GetReportTypeResponse;
import com.onetechsol.ipayment.pojo.InitiatePaymentRequest;
import com.onetechsol.ipayment.pojo.InitiatePaymentResponse;
import com.onetechsol.ipayment.pojo.LoginRequest;
import com.onetechsol.ipayment.pojo.LoginResponse;
import com.onetechsol.ipayment.pojo.MatmMicroAmtFeedBackRequest;
import com.onetechsol.ipayment.pojo.MatmMicroAmtFeedBackResponse;
import com.onetechsol.ipayment.pojo.MatmServiceRequest;
import com.onetechsol.ipayment.pojo.MatmServiceResponse;
import com.onetechsol.ipayment.pojo.MobilePrepaidPlansRequest;
import com.onetechsol.ipayment.pojo.MoneyTransferRequest;
import com.onetechsol.ipayment.pojo.MoneyTransferResponse;
import com.onetechsol.ipayment.pojo.OnboardingCheckRequest;
import com.onetechsol.ipayment.pojo.OnboardingCheckResponse;
import com.onetechsol.ipayment.pojo.PostRechargeRequest;
import com.onetechsol.ipayment.pojo.PostRechargeResponse;
import com.onetechsol.ipayment.pojo.PrepaidMobilePlansResponse;
import com.onetechsol.ipayment.pojo.RegisterUserRequest;
import com.onetechsol.ipayment.pojo.RegisterUserResponse;
import com.onetechsol.ipayment.pojo.ServiceListRequest;
import com.onetechsol.ipayment.pojo.ServiceListResponse;
import com.onetechsol.ipayment.pojo.StartKyc12Request;
import com.onetechsol.ipayment.pojo.StartKyc12Response;
import com.onetechsol.ipayment.pojo.StartKyc18Request;
import com.onetechsol.ipayment.pojo.StartKyc18Response;
import com.onetechsol.ipayment.pojo.SubmitRemitterOtpRequest;
import com.onetechsol.ipayment.pojo.SubmitRemitterOtpResponse;
import com.onetechsol.ipayment.pojo.UploadKycRequest;
import com.onetechsol.ipayment.pojo.UploadKycResponse;
import com.onetechsol.ipayment.pojo.VerifyOtpRequest;
import com.onetechsol.ipayment.pojo.VerifyOtpResponse;
import com.onetechsol.ipayment.pojo.WalletListRequest;
import com.onetechsol.ipayment.pojo.WalletListResponse;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
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


}


