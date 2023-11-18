package com.onetechsol.ipayment.di.builder;


import com.onetechsol.ipayment.ui.screen.customer.AddCustomerActivity;
import com.onetechsol.ipayment.ui.screen.dashboard.DashboardActivity;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;
import com.onetechsol.ipayment.ui.screen.insurance.InsuranceActivity;
import com.onetechsol.ipayment.ui.screen.login_singup.LoginSignupActivity;
import com.onetechsol.ipayment.ui.screen.mydiary.GromoDiaryActivity;
import com.onetechsol.ipayment.ui.screen.packagekit.PackageKitActivity;
import com.onetechsol.ipayment.ui.screen.product.OtherProductActivity;
import com.onetechsol.ipayment.ui.screen.product.ProductActivity;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailActivity;
import com.onetechsol.ipayment.ui.screen.profile.ProfileActivity;
import com.onetechsol.ipayment.ui.screen.report.ReportActivity;
import com.onetechsol.ipayment.ui.screen.service.ServiceActivity;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps1.AEPS1Activity;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps1.Aeps1OperationActivity;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps2.AEPS2Activity;
import com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc.UploadKycActivity;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTActivity;
import com.onetechsol.ipayment.ui.screen.service.insurance.BuyInsuranceActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.MobileRechargeActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.electricity.BillInsurancePayActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection.RechargePlanSelectionActivity;
import com.onetechsol.ipayment.ui.screen.service.external_service.UpiPayNowActivity;
import com.onetechsol.ipayment.ui.screen.share.ShareActivity;
import com.onetechsol.ipayment.ui.screen.signup.RegisterActivity;
import com.onetechsol.ipayment.ui.screen.welcome.WelcomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract WelcomeActivity bindWelcomeActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract LoginSignupActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ProductActivity bindCreditCardActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract InsuranceActivity bindInsuranceActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ProfileActivity bindProfileActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract GromoDiaryActivity bindGromoDiaryActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract RegisterActivity bindSignupActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ServiceActivity bindServiceActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract AEPS1Activity bindAEPS1Activity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract UploadKycActivity bindUploadKycActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract UpiPayNowActivity bindUpiPayNowActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract AEPS2Activity bindAEPS2Activity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ShareActivity bindShareActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ProductDetailActivity bindProductDetailActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract DMTActivity bindDMTActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract MobileRechargeActivity bindMobileRechargeActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract RechargePlanSelectionActivity bindPlanSelectionActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract BillInsurancePayActivity bindElectricityBillPayActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract BuyInsuranceActivity bindPremiumInsuranceActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract PackageKitActivity bindPackageKitActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract ReportActivity bindReportActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract OtherProductActivity bindOtherProductActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract AddCustomerActivity bindAddCustomerActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract Aeps1OperationActivity bindAeps1OperationActivity();
}


