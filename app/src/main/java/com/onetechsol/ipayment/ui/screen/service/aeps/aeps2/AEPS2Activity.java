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
import com.onetechsol.ipayment.ui.screen.report.ReportFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.AEPSViewModel;
import com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc.UploadKycActivity;
import com.paysprint.onboardinglib.activities.HostActivity;

import org.apache.commons.lang3.StringUtils;

public class AEPS2Activity extends BaseActivity<AEPSViewModel, ActivityAeps2Binding> implements AEPS1TaskAdapter.AdapterCallback, AEPS1ClickListener {


    private ServiceCategoryModel serviceCategoryModel;
    private UserLocation userLocation;
    private UsbManager mUsbManager;
    private StartKyc12ResponseData startKyc12ResponseData;
    private ActivityResultLauncher<Intent> startActivityIntent;

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

            getBankItems();

            onAttachFragment(viewBinding().flReport.getId(), ReportFragment.newInstance("AEPS 2"), ReportFragment.class.getName());


            startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        Intent data = result.getData();

                        if (data != null) {

                            final boolean status = data.getBooleanExtra("status", false);
                            final int response = data.getIntExtra("response", 0);
                            final String message = data.getStringExtra("message");
                            // TODO show alery dialog followed by api call - > aeps_map_user_12_response

                        }
                    } catch (Exception e) {
                        Log.e("Error", "Error while deserialize pid data", e);
                    }
                }

            });

        }

    }

    private void getBankItems() {

        compositeDisposable().add(viewModel().getAeps1Features().subscribe(aeps1TaskModels -> {


            AEPS1TaskAdapter aeps1TaskAdapter = new AEPS1TaskAdapter();
            aeps1TaskAdapter.setAEPS1TaskModelList(aeps1TaskModels);
            aeps1TaskAdapter.setCallback(this);
            viewBinding().setAeps2TaskAdapter(aeps1TaskAdapter);
            viewBinding().setAeps2ClickListener(this);

        }, throwable -> {

        }));

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

    }


    @Override
    public void onRefresh() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (serviceCategoryModel.kycNeeded()) {
            showKycPromptDialog();
        } else {
            startService25Onboarding();
        }
    }

    private void startService25Onboarding() {

        compositeDisposable().add(viewModel().startServiceOnboarding("25").subscribe(
                onboardingCheckResponse ->
                {
                    if (onboardingCheckResponse.status().equals("1")) {
                        String txnStatus = onboardingCheckResponse.txnStatus();
                        if (txnStatus.equals("1")) {

                        } else if (txnStatus.equals("2")) {

                            showTextDialog(onboardingCheckResponse.message());
                        } else if (txnStatus.equals("3")) {
                            //Open kYC fragment

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
}