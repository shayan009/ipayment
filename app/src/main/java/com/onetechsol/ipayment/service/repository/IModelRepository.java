package com.onetechsol.ipayment.service.repository;

import com.onetechsol.ipayment.pojo.AEPS1ReportRequest;
import com.onetechsol.ipayment.pojo.AEPS1ReportResponse;
import com.onetechsol.ipayment.pojo.AddBeneficiaryRequest;
import com.onetechsol.ipayment.pojo.AddBeneficiaryResponse;
import com.onetechsol.ipayment.pojo.AddCustomerRequest;
import com.onetechsol.ipayment.pojo.AddCustomerResponse;
import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.AppSetupRequest;
import com.onetechsol.ipayment.pojo.AppSetupResponse;
import com.onetechsol.ipayment.pojo.AuthAepsOpRequest;
import com.onetechsol.ipayment.pojo.AuthAepsOpResponse;
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
}
