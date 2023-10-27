package com.onetechsol.ipayment.ui.screen.dashboard;

import static com.onetechsol.ipayment.utils.Utilities.getDrawableUrl;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.easebuzz.payment.kit.PWECouponsActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityDashboardBinding;
import com.onetechsol.ipayment.databinding.DashboardItemClickListener;
import com.onetechsol.ipayment.pojo.PaymentStatusType;
import com.onetechsol.ipayment.pojo.SettingItem;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.SettingAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.login_singup.LoginSignupActivity;
import com.onetechsol.ipayment.ui.screen.mydiary.GromoDiaryActivity;
import com.onetechsol.ipayment.ui.screen.packagekit.PackageKitActivity;
import com.onetechsol.ipayment.ui.screen.profile.ProfileActivity;
import com.onetechsol.ipayment.ui.screen.upgrade.UpgradePackageAlertDialog;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends BaseActivity<DashboardViewModel, ActivityDashboardBinding> implements DashboardItemClickListener, SettingAdapter.AdapterCallback {

    private ActivityResultLauncher<Intent> pweActivityResultLauncher;
    private UpgradePackageAlertDialog upgradePackageAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {

            viewBinding().setDashBoardClickListener(this);

            SettingAdapter settingBusinessAdapter = new SettingAdapter();
            SettingAdapter settingMoreAdapter = new SettingAdapter();

            viewBinding().tvProfileName.setText(userSession.loginName());
            viewBinding().tvProfileNumber.setText(userSession.mobile());

            viewBinding().setBusinessSettingAdapter(settingBusinessAdapter);
            viewBinding().setMoreSettingAdapter(settingMoreAdapter);

            setCustomerUpgrade();

            List<SettingItem> settingItemList = new ArrayList<>();
            settingItemList.add(new SettingItem(0, "Change Password", "Secure your account using a unique password", getDrawableUrl(R.drawable.change_password)));
            settingItemList.add(new SettingItem(1, "Change TPin", "Secure access to your Transactions using a unique pin", getDrawableUrl(R.drawable.pin_code)));
            settingItemList.add(new SettingItem(2, "Package Kits", "Buy or upgrade your current package kits and receive more commission", getDrawableUrl(R.drawable.package_kits)));
            settingBusinessAdapter.setItems(settingItemList);
            settingBusinessAdapter.setCallback(this);

            List<SettingItem> settingMoreList = new ArrayList<>();
            settingMoreList.add(new SettingItem(3, "Change Language", "Change the language you use the app in", getDrawableUrl(R.drawable.change_password)));
            settingMoreList.add(new SettingItem(4, "Logout", "Logout from your account on this device", getDrawableUrl(R.drawable.my_slab)));
            settingMoreAdapter.setItems(settingMoreList);
            settingMoreAdapter.setCallback(this);

            pweActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (data != null) {
                        String payment_result = data.getStringExtra("result");
                        String payment_response = data.getStringExtra("payment_response");
                        try {
                            // Handle response here
                            Log.d("result", payment_result);
                            Log.d("payment_response", payment_response);

                            JsonObject parse = JsonParser.parseString(payment_response).getAsJsonObject();
                            String error_message = parse.get("error_Message").getAsString();

                            PaymentStatusType paymentStatusType = PaymentStatusType.get(payment_result);

                            if (paymentStatusType == PaymentStatusType.SUCCESS) {


                                String txnId = parse.get("txnid").getAsString();
                                String amount = parse.get("amount").getAsString();

                                onShowLoading();

                                compositeDisposable().add(viewModel().customerToMerchantUpgrade(amount, txnId)
                                        .subscribe(customerUpgradeResponse -> {
                                            onHideLoading();

                                            if (customerUpgradeResponse.txnStatus().equalsIgnoreCase("1")) {

                                                prefManager.setRoleId(customerUpgradeResponse.roleId());
                                                setCustomerUpgrade();
                                                showAlertDialog(payment_result, "You have been upgraded to Business", true)
                                                        .setPositiveButton("OK", (dialogInterface, i) -> {
                                                            dialogInterface.dismiss();
                                                        }).show();


                                            } else {
                                                showAlertDialog("Payment Error", "Failed to upgrade to Business. Please contact Admin.", true)
                                                        .setPositiveButton("OK", (dialogInterface, i) -> {
                                                            dialogInterface.dismiss();
                                                        }).show();
                                            }

                                        }, throwable -> {
                                            onHideLoading();
                                            showAlertDialog("Payment Error", "Failed to upgrade to Business. Please contact Admin.", true)
                                                    .setPositiveButton("OK", (dialogInterface, i) -> {
                                                        dialogInterface.dismiss();
                                                    }).show();

                                        }));

                            } else {

                                showAlertDialog(payment_result, error_message, true)
                                        .setPositiveButton("Retry", (dialogInterface, i) -> {
                                            dialogInterface.dismiss();
                                            upgrade();
                                        }).setNegativeButton("Cancel", (dialogInterface, i) -> {
                                            dialogInterface.dismiss();
                                        }).show();
                            }


                        } catch (Exception e) {
                            showAlertDialog("Payment Error", "Failed to upgrade to Business. Please contact Admin.", true)
                                    .setPositiveButton("OK", (dialogInterface, i) -> {
                                        dialogInterface.dismiss();
                                    }).show();
                        }
                    }
                }
            });

        }


    }

    private void setCustomerUpgrade() {

        boolean isCustomer = StringUtils.equals(prefManager.getUserSession().roleId(), "200");

        viewBinding().mbUpgradeBiz.setEnabled(isCustomer);

        if (!isCustomer) {
            viewBinding().mbUpgradeBiz.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
            viewBinding().mbPersonal.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
        } else {
            viewBinding().mbUpgradeBiz.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
            viewBinding().mbPersonal.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
        }
    }

    @Override
    public ActivityDashboardBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityDashboardBinding.inflate(inflater);
    }

    @Override
    public DashboardViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DashboardViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void openDiary(View view) {
        startActivity(new Intent(this, GromoDiaryActivity.class));
    }

    @Override
    public void openReferEarn(View view) {
        //Toast.makeText(this, "clicked", //Toast.LENGTH_SHORT).show();

    }

    @Override
    public void openSupport(View view) {
        //Toast.makeText(this, "clicked", //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openLeaderBoard(View view) {
        //Toast.makeText(this, "clicked", //Toast.LENGTH_SHORT).show();

    }

    @Override
    public void openCreditScore(View view) {
        //Toast.makeText(this, "clicked", //Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openProfile() {
        startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
    }

    @Override
    public void upgrade() {

        // onShowLoading();
        compositeDisposable().add(
                viewModel().customerToMerchantUpgradeInfo()
                        .subscribe(customerUpgradeInfoResponse -> {
                            //onHideLoading();

                            upgradePackageAlertDialog = new UpgradePackageAlertDialog();
                            upgradePackageAlertDialog.setData(customerUpgradeInfoResponse.data());
                            upgradePackageAlertDialog.setDashboardItemClickListener(this);
                            upgradePackageAlertDialog.show(getSupportFragmentManager(), UpgradePackageAlertDialog.class.getName());

                        }, throwable -> {
                            //onHideLoading();
                        })

        );


    }

    @Override
    public void onItemClicked(SettingItem settingItem) {

        if (settingItem.id() == 4) {
            logout("Are you sure want to logout from the account ?");
        } else if (settingItem.id() == 2) {
            startActivity(new Intent(this, PackageKitActivity.class));
        }
    }

    private void logout(String message) {

        showToastAlertDialog("Logout", message, true)
                .setOnClickListener(() -> {
                    viewModel().prefManager().clearLoginData();
                    Intent intent = new Intent(DashboardActivity.this, LoginSignupActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }).show(getSupportFragmentManager(), DashboardActivity.class.getName());

       /* showAlertDialog("Logout", message, true)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    viewModel().prefManager().clearLoginData();
                    Intent intent = new Intent(DashboardActivity.this, LoginSignupActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    dialogInterface.dismiss();
                    finish();
                }).setNegativeButton("NO",(dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).show();*/
    }

    @Override
    public void onCancelClick() {

        if (upgradePackageAlertDialog != null) {
            upgradePackageAlertDialog.dismiss();
        }
    }

    @Override
    public void upgradeNow(String amount) {


        compositeDisposable().add(viewModel().initiatePayment(amount)
                .subscribe(initiatePaymentResponse -> {
                    onHideLoading();

                    if (StringUtils.isNotEmpty(initiatePaymentResponse.data())) {
                        Intent intentProceed = new Intent(this, PWECouponsActivity.class);
                        intentProceed.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // This is mandatory flag
                        intentProceed.putExtra("access_key", initiatePaymentResponse.data());
                        intentProceed.putExtra("pay_mode", "production");
                        pweActivityResultLauncher.launch(intentProceed);
                    }


                }, throwable -> {
                    onHideLoading();
                }));


    }
}