package com.onetechsol.ipayment.ui.screen.service.aeps.aeps2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AEPS1ClickListener;
import com.onetechsol.ipayment.databinding.ActivityAeps2Binding;
import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.pojo.StartKyc12ResponseData;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.AEPS1TaskAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.fingerprint.FingerPrintBottomSheet;
import com.onetechsol.ipayment.ui.screen.report.ReportFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.AEPSViewModel;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps1.Aeps1OperationActivity;
import com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc.UploadKycActivity;
import com.paysprint.onboardinglib.activities.HostActivity;

import org.apache.commons.lang3.StringUtils;

public class AEPS2Activity extends BaseActivity<AEPSViewModel, ActivityAeps2Binding> implements AEPS1TaskAdapter.AdapterCallback, AEPS1ClickListener, FingerPrintBottomSheet.OnClickListener {


    private ServiceCategoryModel serviceCategoryModel;
    private UserLocation userLocation;
    private UsbManager mUsbManager;
    private StartKyc12ResponseData startKyc12ResponseData;
    private ActivityResultLauncher<Intent> startActivityIntent;

    private boolean twoFactorDone = false;
    private FingerPrintBottomSheet fingerPrintBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceCategoryModel = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getParcelable(ServiceCategoryModel.class.getName());
        Log.d("serviceCatModel.id ->", String.valueOf(serviceCategoryModel.id()));
        Log.d("servCatModel.catId ->", String.valueOf(serviceCategoryModel.categoryId()));

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {


            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();
            userLocation = viewModel().prefManager().getCurrentLocation();

            mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);


            startService25Onboarding();


            onAttachFragment(viewBinding().flReport.getId(), ReportFragment.newInstance("AEPS 2"), ReportFragment.class.getName());


            startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        Intent data = result.getData();

                        if (data != null) {

                            final boolean status = data.getBooleanExtra("status", false);
                            final int response = data.getIntExtra("response", 0);
                            final String message = data.getStringExtra("message");

                           //call aeps_map_user_12_response

                            onShowLoading();
                             compositeDisposable().add(viewModel().startKyc12CallbackResponse(status,response,message,startKyc12ResponseData.merchant())
                                    .subscribe(startKyc12CallbackResponse -> {
                                      onHideLoading();

                                      if(startKyc12CallbackResponse.status().equals("1")) {
                                          startService25Onboarding();
                                      }
                                    },throwable -> {
                                        onHideLoading();
                                    }));

                        }
                    } catch (Exception e) {
                        Log.e("Error", "Error while deserialize pid data", e);
                    }
                }

            });

        }

    }


    @Override
    public ActivityAeps2Binding setupViewBinding(LayoutInflater inflater) {
        return ActivityAeps2Binding.inflate(inflater);
    }

    @Override
    public AEPSViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AEPSViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_aeps2;
    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openBalEnquiry(int type) {

        if(twoFactorDone) {
            Intent intent = new Intent(this, Aeps1OperationActivity.class);
            intent.putExtra("type",type);
            startActivity(intent);
        } else {
            showToastAlertDialog("Alert","Please complete your two factor authentication.",false)
                    .setOnClickListener(this::startService25Onboarding)
                    .show(getSupportFragmentManager(),"showToastAlertDialog");

        }


    }


    @Override
    public void onRefresh() {

    }

    private void startService25Onboarding() {

        compositeDisposable().add(viewModel().startServiceOnboarding("25").subscribe(
                onboardingCheckResponse ->
                {
                    if (onboardingCheckResponse.status().equals("1")) {
                        String txnStatus = onboardingCheckResponse.txnStatus();
                        if (txnStatus.equals("1")) {
                            twoFactorDone = true;
                        } else if (txnStatus.equals("3")) {


                            //auth+getUrl api

                            if (StringUtils.isNotBlank(userLocation.latitude()) && StringUtils.isNotBlank(userLocation.longitude())) {


                                compositeDisposable().add(viewModel().startKyc12()
                                        .subscribe(startKyc12Response -> {

                                            if (startKyc12Response.status().equals("1")) {


                                                startKyc12ResponseData = startKyc12Response.data();
                                                Intent intent = new Intent(AEPS2Activity.this, HostActivity.class);
                                                intent.putExtra("pId", startKyc12ResponseData.partnerId());
                                                intent.putExtra("pApiKey", startKyc12ResponseData.partnerKey());
                                                intent.putExtra("mCode", startKyc12ResponseData.merchant());
                                                intent.putExtra("mobile", startKyc12ResponseData.mobile());
                                                intent.putExtra("lat", userLocation.latitude());
                                                intent.putExtra("lng", userLocation.longitude());
                                                intent.putExtra("firm", startKyc12ResponseData.shopName());
                                                intent.putExtra("email", startKyc12ResponseData.email());
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivityIntent.launch(intent);

                                            } else {
                                                showTextDialog(startKyc12Response.message());
                                            }
                                        }, throwable -> {

                                        }));

                            }

                        } else if (txnStatus.equals("4")) {

                            //Finger print 2 factor authentication
                            fingerPrintBottomSheet = new FingerPrintBottomSheet();
                            fingerPrintBottomSheet.setStep("4");
                            fingerPrintBottomSheet.setCategoryId(serviceCategoryModel.categoryId());
                            fingerPrintBottomSheet.setCancelable(false);
                            fingerPrintBottomSheet.setOnClickListener(this);
                            fingerPrintBottomSheet.show(getSupportFragmentManager(), FingerPrintBottomSheet.class.getName());


                        }
                    }

                }, throwable -> {

                }));
    }

    private void showTextDialog(String message) {
        new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getOnBackPressedDispatcher().onBackPressed();
                        //startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName())));

                    }
                }).setNegativeButton("Cancel", null).show();
    }

    private void showKycPromptDialog() {
        new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation)
                .setTitle("Kyc Details")
                .setMessage("Banking Kfc details not uploaded.")
                .setCancelable(false)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    startActivity(new Intent(AEPS2Activity.this, UploadKycActivity.class));
                }).setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    showKycPromptDialog();
                }).show();
    }

    @Override
    public void onItemClicked(AEPS1TaskModel serviceModel) {

    }

    @Override
    public void hitOnBoardingCk() {
        startService25Onboarding();
    }

    @Override
    public void dismiss() {

    }
}