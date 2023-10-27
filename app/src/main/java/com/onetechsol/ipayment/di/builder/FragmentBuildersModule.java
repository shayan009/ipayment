package com.onetechsol.ipayment.di.builder;

import com.onetechsol.ipayment.ui.screen.addFund.QRPayModeFragment;
import com.onetechsol.ipayment.ui.screen.addFund.UpiPayModeFragment;
import com.onetechsol.ipayment.ui.screen.customer.MyCustomerFragment;
import com.onetechsol.ipayment.ui.screen.home.HomeFragment;
import com.onetechsol.ipayment.ui.screen.home.SliderFragment;
import com.onetechsol.ipayment.ui.screen.login_singup.login.LoginFragment;
import com.onetechsol.ipayment.ui.screen.login_singup.signup.SignupFragment;
import com.onetechsol.ipayment.ui.screen.offerswallet.WalletFragment;
import com.onetechsol.ipayment.ui.screen.packagekit.SlabFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.content.ShareContentFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.detail.DetailsFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.earning.EarningFragment;
import com.onetechsol.ipayment.ui.screen.report.ReportFragment;
import com.onetechsol.ipayment.ui.screen.sellearn.SellEarnFragment;
import com.onetechsol.ipayment.ui.screen.service.ServiceFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.ReportFilterBottomSheet;
import com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc.UploadKycFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary.AddBeneficaryFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary.BeneficiaryListFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.money_transfer.DMTTransferMoneyBottomSheet;
import com.onetechsol.ipayment.ui.screen.service.subservice.ServiceCategoryFragment;
import com.onetechsol.ipayment.ui.screen.upgrade.UpgradePackageAlertDialog;
import com.onetechsol.ipayment.widgets.ToastAlertDialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract SliderFragment contributeSliderFragment();

    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract WalletFragment contributeWalletFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract SignupFragment contributeSignupFragment();

    @ContributesAndroidInjector
    abstract SellEarnFragment contributeSellEarnFragment();

    @ContributesAndroidInjector
    abstract ServiceFragment contributeServiceFragment();

    @ContributesAndroidInjector
    abstract ServiceCategoryFragment contributeServiceCategoryFragment();

    @ContributesAndroidInjector
    abstract ReportFilterBottomSheet contributeAEPS1FilterBottomSheet();

    @ContributesAndroidInjector
    abstract UploadKycFragment contributeUploadKycFragment();

    @ContributesAndroidInjector
    abstract ReportFragment contributeReportFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();

    @ContributesAndroidInjector
    abstract ShareContentFragment contributeShareContentFragment();

    @ContributesAndroidInjector
    abstract EarningFragment contributeEarningFragment();

    @ContributesAndroidInjector
    abstract AddBeneficaryFragment contributeAddBeneficaryFragment();

    @ContributesAndroidInjector
    abstract BeneficiaryListFragment contributeBeneficiaryListFragment();

    @ContributesAndroidInjector
    abstract DMTTransferMoneyBottomSheet contributeDMTTransferMoneyBottomSheet();

    @ContributesAndroidInjector
    abstract SlabFragment contributeSlabFragment();

    @ContributesAndroidInjector
    abstract MyCustomerFragment contributeMyCustomerFragment();

    @ContributesAndroidInjector
    abstract QRPayModeFragment contributeQRPayModeFragment();

    @ContributesAndroidInjector
    abstract UpiPayModeFragment contributeUpiPayModeFragment();

    @ContributesAndroidInjector
    abstract UpgradePackageAlertDialog contributeUpgradePackageAlertDialog();

    @ContributesAndroidInjector
    abstract ToastAlertDialog contributeToastAlertDialog();

}
