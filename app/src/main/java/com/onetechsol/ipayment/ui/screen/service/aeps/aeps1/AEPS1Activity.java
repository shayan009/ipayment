package com.onetechsol.ipayment.ui.screen.service.aeps.aeps1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AEPS1ClickListener;
import com.onetechsol.ipayment.databinding.ActivityAeps1Binding;
import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.AEPS1TaskAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.fingerprint.FingerPrintBottomSheet;
import com.onetechsol.ipayment.ui.screen.kyc.Kyc18OtpBottomSheet;
import com.onetechsol.ipayment.ui.screen.report.ReportFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.AEPSViewModel;
import com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc.UploadKycActivity;

import org.apache.commons.lang3.StringUtils;

import io.reactivex.disposables.CompositeDisposable;

public class AEPS1Activity extends BaseActivity<AEPSViewModel, ActivityAeps1Binding> implements AEPS1TaskAdapter.AdapterCallback, AEPS1ClickListener, Kyc18OtpBottomSheet.OnClickListener, FingerPrintBottomSheet.OnClickListener {


    private CompositeDisposable mDisposable = new CompositeDisposable();


    private UsbManager mUsbManager;
    private UserLocation userLocation;
    private ServiceCategoryModel serviceCategoryModel;
    private FingerPrintBottomSheet fingerPrintBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceCategoryModel = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getParcelable(ServiceCategoryModel.class.getName());

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {


            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();
            userLocation = viewModel().prefManager().getCurrentLocation();

            onAttachFragment(viewBinding().flReport.getId(), ReportFragment.newInstance("AEPS 1"), ReportFragment.class.getName());

            mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

            startService33Onboarding();

        }

    }


    private void startService33Onboarding() {

        onShowLoading();
        mDisposable.add(viewModel().startServiceOnboarding(serviceCategoryModel.categoryId()).subscribe(onboardingCheckResponse -> {
            onHideLoading();
            if (onboardingCheckResponse.status().equals("1")) {
                String txnStatus = onboardingCheckResponse.txnStatus();
                if (txnStatus.equals("1")) {


                } else if (txnStatus.equals("3")) {
                    //Open kYC fragment

                    if (StringUtils.isNotBlank(userLocation.latitude()) && StringUtils.isNotBlank(userLocation.longitude())) {

                        onShowLoading();
                        mDisposable.add(viewModel().startKyc18("0", "").subscribe(startKyc18Response -> {
                            onHideLoading();

                            if (startKyc18Response.status().equals("1") && startKyc18Response.txnStatus().equals("2")) {
                                openKycDialog();
                            } else {
                                showTextDialog(startKyc18Response.message);
                            }


                        }, throwable -> {
                            onHideLoading();
                        }));
                    }

                } else if (txnStatus.equals("4")) {
                    //Finger print 2 factor authentication

                    fingerPrintBottomSheet = new FingerPrintBottomSheet();
                    fingerPrintBottomSheet.setStep("4");
                    fingerPrintBottomSheet.setCancelable(false);
                    fingerPrintBottomSheet.setOnClickListener(this);
                    fingerPrintBottomSheet.show(getSupportFragmentManager(), FingerPrintBottomSheet.class.getName());
                }

            } else {
                showAlertDialog("AEPS1 Details", onboardingCheckResponse.message(), true).setPositiveButton("OK", (dialogInterface, i) -> {

                    dialogInterface.dismiss();

                }).show();
            }


        }, throwable -> {
            showAlertDialog("AEPS1 Error", throwable.getMessage(), true).setPositiveButton("OK", (dialogInterface, i) -> {

                dialogInterface.dismiss();

            }).show();
        }));
    }

    private void openKycDialog() {

        Kyc18OtpBottomSheet kyc18OtpBottomSheet = new Kyc18OtpBottomSheet();
        kyc18OtpBottomSheet.show(getSupportFragmentManager(), Kyc18OtpBottomSheet.class.getName());
        kyc18OtpBottomSheet.setOnClickListener(this);
    }


    private void showTextDialog(String message) {
        new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3).setTitle("Alert!").setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName())));

            }
        }).setNegativeButton("Cancel", null).show();
    }

    private void getBankItems() {


    }

    @Override
    public ActivityAeps1Binding setupViewBinding(LayoutInflater inflater) {
        return ActivityAeps1Binding.inflate(inflater);
    }

    @Override
    public AEPSViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AEPSViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_aeps1;
    }

    @Override
    public void onRefresh() {

    }

    private void showKycPromptDialog() {
        new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation).setTitle("Kyc Details").setMessage("Banking Kfc details not uploaded.").setCancelable(false).setPositiveButton("Ok", (dialogInterface, i) -> {
            startActivity(new Intent(AEPS1Activity.this, UploadKycActivity.class));
        }).setNegativeButton("Cancel", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            showKycPromptDialog();
        }).show();
    }


    @Override
    public void onItemClicked(AEPS1TaskModel aeps1TaskModel) {
        //Toast.makeText(this, aeps1TaskModel.name(), //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goBack() {

        getOnBackPressedDispatcher().onBackPressed();

    }

    @Override
    public void recheckOnBoardingCK() {
        startService33Onboarding();
    }

    @Override
    public void hitOnBoardingCk() {
        startService33Onboarding();
    }

    @Override
    public void dismiss() {
        showToastAlertDialog("Finger Print Capture", "Please complete your two factor authentication", false)
                .setOnClickListener(() -> {
                    fingerPrintBottomSheet = new FingerPrintBottomSheet();
                    fingerPrintBottomSheet.setStep("4");
                    fingerPrintBottomSheet.setCancelable(false);
                    fingerPrintBottomSheet.setOnClickListener(this);
                    fingerPrintBottomSheet.show(getSupportFragmentManager(), FingerPrintBottomSheet.class.getName());
                }).show(getSupportFragmentManager(), "showToastAlertDialog");
    }
}