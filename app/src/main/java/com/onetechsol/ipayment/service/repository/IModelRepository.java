package com.onetechsol.ipayment.service.repository;

import com.onetechsol.ipayment.pojo.*;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface IModelRepository {


    Observable<CheckLoginResponse> checkLogin(CheckLoginRequest checkLoginRequest);

    Observable<LoginResponse> sendMobileOtp(LoginRequest loginRequest, String mobile);


    Observable<VerifyOtpResponse> verifyOtp(VerifyOtpRequest verifyOtpRequest, String mobile);

    Observable<CheckSignupResponse> checkSignup(CheckSignupRequest checkSignupRequest);

    Observable<AppSetupResponse> appSetup(AppSetupRequest appSetupRequest);

    Observable<ServiceListResponse> getServiceList(ServiceListRequest serviceListRequest);


    Observable<WalletListResponse> getWalletList(WalletListRequest walletListRequest);

    Observable<CategoryWiseServiceResponse> getCategoryWiseServiceList(CategoryWiseServiceRequest categoryWiseServiceRequest);

    Observable<RegisterUserResponse> register(RegisterUserRequest appSetupRequest);

    Observable<AEPS1ReportResponse> getAEPS1Report(AEPS1ReportRequest aeps1ReportRequest);


    Observable<OnboardingCheckResponse> startServiceOnboarding(OnboardingCheckRequest onboardingCheckRequest, String categoryId);


    Observable<StartKyc18Response> startKyc18(StartKyc18Request startKyc18Request);

    Observable<StartKyc12Response> startKyc12(StartKyc12Request startKyc12Request);

    Observable<StartKyc12CallbackResponse> startKyc12CallbackResponse(StartKyc12CallbackRequest startKyc12CallbackRequest);

    Observable<UploadKycResponse> uploadBankingKyc(UploadKycRequest uploadKycRequest);


    Observable<CheckRemitterResponse> checkRemitter(CheckRemitterRequest checkRemitterRequest);

    Observable<SubmitRemitterOtpResponse> submitRemitterOtp(SubmitRemitterOtpRequest submitRemitterOtpRequest);

    Observable<AddBeneficiaryResponse> registerBeneficiary(AddBeneficiaryRequest addBeneficiaryRequest);

    Observable<MoneyTransferResponse> moneyTransfer(MoneyTransferRequest moneyTransferRequest);

    Observable<GetBeneficiaryResponse> getBeneficiaryList(GetBeneficiaryRequest getBeneficiaryRequest);

    Observable<List<BeneficiaryBankModel>> getBeneficiaryBankList(String query);

    Observable<DMTChargeResponse> getDMTCharge(GetDMTChargeRequest getDMTChargeRequest);

    Observable<GetOperatorResponse> getOperator(GetOperatorRequest getOperatorRequest);

    Observable<GetOperatorCircleResponse> getOperatorCircle(GetOperatorCircleRequest getOperatorCircleRequest);

    Observable<GetOperatorInfoResponse> getOperatorInfo(GetOperatorInfoRequest getOperatorInfoRequest);

    Observable<PrepaidMobilePlansResponse> prepaidPlans(MobilePrepaidPlansRequest mobilePrepaidPlansRequest);

    Observable<PostRechargeResponse> rechargeMobile(PostRechargeRequest postRechargeRequest);

    Observable<FetchBillResponse> fetchBill(FetchBillRequest fetchBillRequest);

    Observable<GetAffiliateServiceList> getAffiliateList();

    Observable<GetAffiliateProductList> getAffiliateProductList(FetchProductListRequest fetchProductListRequest);

    Observable<GetAffiliateProductDetail> getAffiliateProductDetail(FetchProductDetailRequest fetchProductDetailRequest);

    Observable<GetKitListResponse> getKitList(GetKitRequest getKitRequest);

    Observable<GetPackageSlabResponse> myPackageList(GetPackageSlabRequest getPackageSlabRequest);

    Observable<BuyKitResponse> buyKit(BuyKitRequestRequest buyKitRequestRequest);

    Observable<CheckFundReqResponse> fundRequestCheck(FundRequestCheckRequest fundRequestCheckRequest);

    Observable<CheckFundReqDetailResponse> fundRequestCheckDetails(FundRequestCheckDetailRequest fundRequestCheckDetailRequest);

    Observable<GetReportTypeResponse> getReportCategoryList(GetReportTypeRequest getReportTypeRequest);

    Observable<AddCustomerResponse> addCustomerAffiliate(AddCustomerRequest addCustomerRequest);

    Observable<GetCustomerResponse> customerListAffiliate(GetCustomerRequest getCustomerRequest);

    Observable<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest);

    Observable<CustomerUpgradeResponse> customerToMerchantUpgrade(CustomerUpgradeRequest customerUpgradeRequest);

    Observable<GetDepartmentListResponse> getDepartmentList();

    Observable<CustomerUpgradeInfoResponse> customerToMerchantUpgradeInfo();

    Observable<FundTransferResponse> fundTransferRequest(FundTransferRequest fundTransferRequest);

    Observable<FundRequestResponse> fundRequest(FundRequest fundRequest);

    Observable<BuyInsuranceDetailResponse> getInsuranceDetail(BuyInsuranceDetailRequest buyInsuranceDetailRequest);

    Observable<MatmServiceResponse> hitMatmApiForBalWithdrawal(MatmServiceRequest matmServiceRequest);

    Observable<MatmMicroAmtFeedBackResponse> passMicroAtmResponseBE(MatmMicroAmtFeedBackRequest matmMicroAmtFeedBackRequest);

    Observable<List<AepsBankModel>> getAepsBankList(String query);

    Observable<AuthAepsOpResponse> authenticateAepsOperation(AuthAepsOpRequest authAepsOpRequest);

    Observable<PayoutBankDetailResponse> payoutBankDetails(HashMap<String, String> stringHashMap);

    Observable<CheckPayoutServiceResponse> payoutServiceCheck(CheckPayoutServiceRequest checkPayoutServiceRequest);

    Observable<List<AepsBankModel>> getPayoutBank(String query);

    Observable<PayoutCreateBankResponse> createPayoutBank(PayoutAddBankRequest payoutAddBankRequest);

    Observable<PayoutSubmitResponse> payoutSubmit(PayoutSubmitRequest payoutSubmitRequest);

    Observable<PayoutChargeResponse> getPayoutCharge(GetPayoutChargeRequest getPayoutChargeRequest);

    Observable<ChangePasswordResponse> changePass(ChangePasswordRequest changePasswordRequest);

    Observable<ChangeTpinResponse> changeTpin(ChangeTPinRequest changeTPinRequest);

    Observable<GetExternalServiceResponse> externalService(GetExternalServiceRequest getExternalServiceRequest);
}
