package com.onetechsol.ipayment.service.repository;

import android.util.Base64;

import com.google.gson.JsonObject;
import com.onetechsol.ipayment.data.remote.network.retrofit.RetrofitService;
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
import com.onetechsol.ipayment.ui.basefiles.BaseRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ModelRepositoryImpl extends BaseRepository
        implements Consumer<Throwable>
        , IModelRepository {


    public RetrofitService retrofitService;

    public ModelRepositoryImpl(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public void accept(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 401:

                    //
                    break;
            }
        }
    }

    @Override
    public Observable<CheckLoginResponse> checkLogin(CheckLoginRequest checkLoginRequest) {

        return Single.fromCallable(() -> checkLoginRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(checkLoginReq -> retrofitService.checkLogin(checkLoginReq));
    }

    @Override
    public Observable<LoginResponse> sendMobileOtp(LoginRequest loginRequest, String mobile) {

        String baererToken = "Basic " + Base64.encodeToString((mobile + ":" + "").getBytes(), Base64.NO_WRAP);


        return Single.fromCallable(() -> loginRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(loginReq -> retrofitService.sendOtp(loginReq, baererToken));

    }


    @Override
    public Observable<VerifyOtpResponse> verifyOtp(VerifyOtpRequest verifyOtpRequest, String mobile) {

        //String mobile = "8902416510"; //TODO from shared preference
        String pass = ""; //TODO blank for finpay
        String baererToken = "Basic " + Base64.encodeToString((mobile + ":" + "").getBytes(), Base64.NO_WRAP);

        return Single.fromCallable(() -> verifyOtpRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(verifyOtp -> retrofitService.verifyOtp(verifyOtpRequest, baererToken));

    }

    @Override
    public Observable<CheckSignupResponse> checkSignup(CheckSignupRequest checkSignupRequest) {
        return Single.fromCallable(() -> checkSignupRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(checkSignupReq -> retrofitService.checkSignup(checkSignupReq));

    }

    @Override
    public Observable<AppSetupResponse> appSetup(AppSetupRequest appSetupRequest) {
        return Single.fromCallable(() -> appSetupRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(appSetup -> retrofitService.appSetup(appSetup));

    }


    @Override
    public Observable<ServiceListResponse> getServiceList(ServiceListRequest serviceListRequest) {
        return Single.fromCallable(() -> serviceListRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(serviceListRequest2 -> retrofitService.getServiceList(serviceListRequest2));

    }

    @Override
    public Observable<WalletListResponse> getWalletList(WalletListRequest walletListRequest) {
        return Single.fromCallable(() -> walletListRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(walletListRequest2 -> retrofitService.getWalletList(walletListRequest2));
    }

    @Override
    public Observable<CategoryWiseServiceResponse> getCategoryWiseServiceList(CategoryWiseServiceRequest categoryWiseServiceRequest) {
        return Single.fromCallable(() -> categoryWiseServiceRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(categoryWiseService -> retrofitService.getCategoryWiseServiceList(categoryWiseService));
    }

    @Override
    public Observable<RegisterUserResponse> register(RegisterUserRequest registerUserRequest) {
        return Single.fromCallable(() -> registerUserRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(register -> retrofitService.register(register));

    }

    @Override
    public Observable<AEPS1ReportResponse> getAEPS1Report(AEPS1ReportRequest aeps1ReportRequest) {
        return Single.fromCallable(() -> aeps1ReportRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(aeps1ReportRequest2 -> retrofitService.getAEPS1Report(aeps1ReportRequest2));
    }


    @Override
    public Observable<OnboardingCheckResponse> startServiceOnboarding(OnboardingCheckRequest onboardingCheckRequest, String categoryId) {
        return Single.fromCallable(() -> onboardingCheckRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(onboardRequest -> {

                    if (categoryId.equals("33"))
                        return retrofitService.startService33Onboarding(onboardRequest);
                    else if (categoryId.equals("25")) {
                        return retrofitService.startService25Onboarding(onboardRequest);
                    }

                    return null;
                });
    }


    @Override
    public Observable<StartKyc18Response> startKyc18(StartKyc18Request startKyc18Request) {
        return Single.fromCallable(() -> startKyc18Request)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(startKyc18Req -> retrofitService.startKyc18(startKyc18Req));
    }

    @Override
    public Observable<StartKyc12Response> startKyc12(StartKyc12Request startKyc12Request) {
        return Single.fromCallable(() -> startKyc12Request)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(startKyc12Req -> retrofitService.startKyc12(startKyc12Req));
    }


    @Override
    public Observable<UploadKycResponse> uploadBankingKyc(UploadKycRequest uploadKycRequest) {
        return Single.fromCallable(() -> uploadKycRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(uploadKycRequest2 -> retrofitService.uploadBankingKyc(uploadKycRequest2));
    }

    @Override
    public Observable<CheckRemitterResponse> checkRemitter(CheckRemitterRequest checkRemitterRequest) {
        return Single.fromCallable(() -> checkRemitterRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(checkRemitterRequest2 -> retrofitService.checkRemitter(checkRemitterRequest2));
    }

    @Override
    public Observable<SubmitRemitterOtpResponse> submitRemitterOtp(SubmitRemitterOtpRequest submitRemitterOtpRequest) {
        return Single.fromCallable(() -> submitRemitterOtpRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(submitRemitterOtpRequest2 -> retrofitService.submitRemitterOtp(submitRemitterOtpRequest2));
    }


    @Override
    public Observable<AddBeneficiaryResponse> registerBeneficiary(AddBeneficiaryRequest addBeneficiaryRequest) {
        return Single.fromCallable(() -> addBeneficiaryRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(addBeneficiaryRequest2 -> retrofitService.registerBeneficiary(addBeneficiaryRequest2));
    }

    @Override
    public Observable<MoneyTransferResponse> moneyTransfer(MoneyTransferRequest moneyTransferRequest) {
        return Single.fromCallable(() -> moneyTransferRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(moneyTransferRequest2 -> retrofitService.moneyTransfer(moneyTransferRequest2));
    }

    @Override
    public Observable<GetBeneficiaryResponse> getBeneficiaryList(GetBeneficiaryRequest getBeneficiaryRequest) {
        return Single.fromCallable(() -> getBeneficiaryRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getBeneficiaryRequest2 -> retrofitService.getBeneficiaryList(getBeneficiaryRequest2));
    }

    @Override
    public Observable<List<BeneficiaryBankModel>> getBeneficiaryBankList(String query) {
        return Single.fromCallable(() -> query)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(query2 -> retrofitService.getBeneficiaryBankList(query2));
    }

    @Override
    public Observable<DMTChargeResponse> getDMTCharge(GetDMTChargeRequest getDMTChargeRequest) {

        return Single.fromCallable(() -> getDMTChargeRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getDMTChargeRequest2 -> retrofitService.getCharge(getDMTChargeRequest2));
    }


    @Override
    public Observable<GetOperatorResponse> getOperator(GetOperatorRequest getOperatorRequest) {

        return Single.fromCallable(() -> getOperatorRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getOperatorRequest2 -> retrofitService.getOperator(getOperatorRequest2));
    }

    @Override
    public Observable<GetOperatorCircleResponse> getOperatorCircle(GetOperatorCircleRequest getOperatorCircleRequest) {

        return Single.fromCallable(() -> getOperatorCircleRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getOperatorCircleRequest2 -> retrofitService.getOperatorCircle(getOperatorCircleRequest2));
    }


    @Override
    public Observable<GetOperatorInfoResponse> getOperatorInfo(GetOperatorInfoRequest getOperatorInfoRequest) {

        return Single.fromCallable(() -> getOperatorInfoRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getOperatorInfoRequest2 -> retrofitService.getOperatorInfo(getOperatorInfoRequest2));
    }

    @Override
    public Observable<PrepaidMobilePlansResponse> prepaidPlans(MobilePrepaidPlansRequest mobilePrepaidPlansRequest) {

        return Single.fromCallable(() -> mobilePrepaidPlansRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(mobilePrepaidPlansRequest2 -> retrofitService.prepaidPlans(mobilePrepaidPlansRequest2));
    }

    @Override
    public Observable<PostRechargeResponse> rechargeMobile(PostRechargeRequest postRechargeRequest) {

        return Single.fromCallable(() -> postRechargeRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(postRechargeRequest2 -> retrofitService.rechargeMobile(postRechargeRequest2));
    }


    @Override
    public Observable<FetchBillResponse> fetchBill(FetchBillRequest fetchBillRequest) {

        return Single.fromCallable(() -> fetchBillRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchBillRequest2 -> retrofitService.fetchBill(fetchBillRequest2));
    }

    @Override
    public Observable<GetAffiliateServiceList> getAffiliateList() {

        return Single.fromCallable(JsonObject::new)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchBillRequest2 -> retrofitService.getAffiliateList());
    }


    @Override
    public Observable<GetAffiliateProductList> getAffiliateProductList(FetchProductListRequest fetchProductListRequest) {

        return Single.fromCallable(() -> fetchProductListRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchProductListRequest2 -> retrofitService.getAffiliateProductList(fetchProductListRequest2));
    }


    @Override
    public Observable<GetAffiliateProductDetail> getAffiliateProductDetail(FetchProductDetailRequest fetchProductDetailRequest) {

        return Single.fromCallable(() -> fetchProductDetailRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchProductDetailRequest2 -> retrofitService.getAffiliateProductDetail(fetchProductDetailRequest2));
    }

    @Override
    public Observable<GetKitListResponse> getKitList(GetKitRequest getKitRequest) {

        return Single.fromCallable(() -> getKitRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getKitRequest2 -> retrofitService.getKitList(getKitRequest2));
    }


    @Override
    public Observable<GetPackageSlabResponse> myPackageList(GetPackageSlabRequest getPackageSlabRequest) {

        return Single.fromCallable(() -> getPackageSlabRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getPackageSlabRequest2 -> retrofitService.myPackageList(getPackageSlabRequest2));
    }


    @Override
    public Observable<BuyKitResponse> buyKit(BuyKitRequestRequest buyKitRequestRequest) {

        return Single.fromCallable(() -> buyKitRequestRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(buyKitRequestRequest2 -> retrofitService.buyKit(buyKitRequestRequest2));
    }

    @Override
    public Observable<CheckFundReqResponse> fundRequestCheck(FundRequestCheckRequest fundRequestCheckRequest) {

        return Single.fromCallable(() -> fundRequestCheckRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fundRequestCheckRequest2 -> retrofitService.fundRequestCheck(fundRequestCheckRequest2));
    }

    @Override
    public Observable<CheckFundReqDetailResponse> fundRequestCheckDetails(FundRequestCheckDetailRequest fundRequestCheckDetailRequest) {

        return Single.fromCallable(() -> fundRequestCheckDetailRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fundRequestCheckDetailRequest2 -> retrofitService.fundRequestCheckDetails(fundRequestCheckDetailRequest2));
    }

    @Override
    public Observable<GetReportTypeResponse> getReportCategoryList(GetReportTypeRequest getReportTypeRequest) {

        return Single.fromCallable(() -> getReportTypeRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getReportTypeRequest2 -> retrofitService.getReportCategoryList(getReportTypeRequest2));
    }


    @Override
    public Observable<AddCustomerResponse> addCustomerAffiliate(AddCustomerRequest addCustomerRequest) {

        return Single.fromCallable(() -> addCustomerRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(addCustomerRequest2 -> retrofitService.addCustomerAffiliate(addCustomerRequest2));
    }

    @Override
    public Observable<GetCustomerResponse> customerListAffiliate(GetCustomerRequest getCustomerRequest) {

        return Single.fromCallable(() -> getCustomerRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getCustomerRequest2 -> retrofitService.customerListAffiliate(getCustomerRequest2));
    }

    @Override
    public Observable<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest) {

        return Single.fromCallable(() -> initiatePaymentRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(initiatePaymentRequest2 -> retrofitService.initiatePayment(initiatePaymentRequest));
    }

    @Override
    public Observable<CustomerUpgradeResponse> customerToMerchantUpgrade(CustomerUpgradeRequest customerUpgradeRequest) {

        return Single.fromCallable(() -> customerUpgradeRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(customerUpgradeRequest2 -> retrofitService.customerToMerchantUpgrade(customerUpgradeRequest2));
    }

    @Override
    public Observable<GetDepartmentListResponse> getDepartmentList() {
        return Single.fromCallable(JsonObject::new)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchBillRequest2 -> retrofitService.getDepartmentList());
    }

    @Override
    public Observable<CustomerUpgradeInfoResponse> customerToMerchantUpgradeInfo() {
        return Single.fromCallable(JsonObject::new)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fetchBillRequest2 -> retrofitService.customerToMerchantUpgradeInfo());
    }


    @Override
    public Observable<FundTransferResponse> fundTransferRequest(FundTransferRequest fundTransferRequest) {

        return Single.fromCallable(() -> fundTransferRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fundTransferRequest2 -> retrofitService.fundTransferRequest(fundTransferRequest2));
    }


    @Override
    public Observable<FundRequestResponse> fundRequest(FundRequest fundRequest) {

        return Single.fromCallable(() -> fundRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(fundRequest2 -> retrofitService.fundRequest(fundRequest2));
    }

    @Override
    public Observable<BuyInsuranceDetailResponse> getInsuranceDetail(BuyInsuranceDetailRequest buyInsuranceDetailRequest) {

        return Single.fromCallable(() -> buyInsuranceDetailRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(buyInsuranceDetailRequest2 -> retrofitService.buyInsuranceDetail(buyInsuranceDetailRequest2));
    }


    @Override
    public Observable<MatmServiceResponse> hitMatmApiForBalWithdrawal(MatmServiceRequest matmServiceRequest) {

        return Single.fromCallable(() -> matmServiceRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(matmServiceRequest2 -> retrofitService.hitMatmApiForBalWithdrawal(matmServiceRequest2));
    }


    @Override
    public Observable<MatmMicroAmtFeedBackResponse> passMicroAtmResponseBE(MatmMicroAmtFeedBackRequest matmMicroAmtFeedBackRequest) {

        return Single.fromCallable(() -> matmMicroAmtFeedBackRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(matmMicroAmtFeedBackRequest2 -> retrofitService.passMicroAtmResponseBE(matmMicroAmtFeedBackRequest));
    }


    @Override
    public Observable<List<AepsBankModel>> getAepsBankList(String query) {
        return Single.fromCallable(() -> query)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(query2 -> retrofitService.getBankList(query2));
    }

    @Override
    public Observable<AuthAepsOpResponse> authenticateAepsOperation(AuthAepsOpRequest authAepsOpRequest) {

        return Single.fromCallable(() -> authAepsOpRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(authAepsOpRequest2 -> retrofitService.authenticateAepsOperation(authAepsOpRequest2));
    }

}
