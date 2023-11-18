package com.onetechsol.ipayment.service.repository;

import android.util.Base64;

import com.google.gson.JsonObject;
import com.onetechsol.ipayment.data.remote.network.retrofit.RetrofitService;
import com.onetechsol.ipayment.pojo.*;
import com.onetechsol.ipayment.ui.basefiles.BaseRepository;

import java.util.HashMap;
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
    public Observable<StartKyc12CallbackResponse> startKyc12CallbackResponse(StartKyc12CallbackRequest startKyc12CallbackRequest) {
        return Single.fromCallable(() -> startKyc12CallbackRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(startKyc12CallbackRequest2 -> retrofitService.startKyc12CallbackResponse(startKyc12CallbackRequest2));
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

    @Override
    public Observable<PayoutBankDetailResponse> payoutBankDetails(HashMap<String,String> stringHashMap) {

        return Single.fromCallable(() -> stringHashMap)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(stringHashMap2 -> retrofitService.payoutBankDetails(stringHashMap2));
    }


    @Override
    public Observable<CheckPayoutServiceResponse> payoutServiceCheck(CheckPayoutServiceRequest checkPayoutServiceRequest) {

        return Single.fromCallable(() -> checkPayoutServiceRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(checkPayoutServiceRequest2 -> retrofitService.payoutServiceCheck(checkPayoutServiceRequest2));
    }


    @Override
    public Observable<List<AepsBankModel>> getPayoutBank(String query) {
        return Single.fromCallable(() -> query)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(query2 -> retrofitService.payoutBankList(query2));
    }

    @Override
    public Observable<PayoutCreateBankResponse> createPayoutBank(PayoutAddBankRequest  payoutAddBankRequest) {
        return Single.fromCallable(() -> payoutAddBankRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(payoutAddBankRequest2 -> retrofitService.createBank(payoutAddBankRequest2));
    }

    @Override
    public Observable<PayoutSubmitResponse> payoutSubmit(PayoutSubmitRequest  payoutSubmitRequest) {
        return Single.fromCallable(() -> payoutSubmitRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(payoutSubmitRequest2 -> retrofitService.payoutSubmit(payoutSubmitRequest2));
    }

    @Override
    public Observable<PayoutChargeResponse> getPayoutCharge(GetPayoutChargeRequest getPayoutChargeRequest) {
        return Single.fromCallable(() -> getPayoutChargeRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getPayoutChargeRequest2 -> retrofitService.getPayoutCharge(getPayoutChargeRequest2));
    }

    @Override
    public Observable<ChangePasswordResponse> changePass(ChangePasswordRequest changePasswordRequest) {
        return Single.fromCallable(() -> changePasswordRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(changePasswordRequest2 -> retrofitService.changePass(changePasswordRequest2));
    }

    @Override
    public Observable<ChangeTpinResponse> changeTpin(ChangeTPinRequest changeTPinRequest) {
        return Single.fromCallable(() -> changeTPinRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(changeTPinRequest2 -> retrofitService.changeTpin(changeTPinRequest2));
    }

    @Override
    public Observable<GetExternalServiceResponse> externalService(GetExternalServiceRequest getExternalServiceRequest) {
        return Single.fromCallable(() -> getExternalServiceRequest)
                .doOnError(this)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMap(getExternalServiceRequest2 -> retrofitService.externalService(getExternalServiceRequest2));
    }


}
