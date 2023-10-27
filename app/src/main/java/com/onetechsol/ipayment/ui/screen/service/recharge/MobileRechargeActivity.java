package com.onetechsol.ipayment.ui.screen.service.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityMobileRechargeBinding;
import com.onetechsol.ipayment.databinding.MobileRechargeClickListener;
import com.onetechsol.ipayment.pojo.MobileRechargeDto;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection.RechargePlanSelectionActivity;

public class MobileRechargeActivity extends BaseActivity<RechargeViewModel, ActivityMobileRechargeBinding> implements MobileRechargeClickListener {

    private ServiceCategoryModel serviceCategoryModel;
    private UserLocation userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceCategoryModel = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getParcelable(ServiceCategoryModel.class.getName());
        Log.d("serviceCategoryModel.id ->", String.valueOf(serviceCategoryModel.id()));
        Log.d("serviceCategoryModel.categoryId ->", String.valueOf(serviceCategoryModel.categoryId()));

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {


            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();
            userLocation = viewModel().prefManager().getCurrentLocation();
            viewBinding().setMobileRechargeClickListener(this);

            MobileRechargeDto mobileRechargeDto = null;

            if (serviceCategoryModel.categoryId().equals("1")) {
                mobileRechargeDto = new MobileRechargeDto("Prepaid Recharge", "Enter Mobile Number", 10);
            } else if (serviceCategoryModel.categoryId().equals("2")) {
                mobileRechargeDto = new MobileRechargeDto("DTH Recharge", "Enter Subscriber ID", 30);
            }

            if (mobileRechargeDto != null) {
                viewBinding().setMobileRechargeDto(mobileRechargeDto);
            }

        }


    }

    @Override
    public ActivityMobileRechargeBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityMobileRechargeBinding.inflate(inflater);
    }

    @Override
    public RechargeViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(RechargeViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_mobile_recharge;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void startRecharge(String mobile) {


        Intent intent = new Intent(this, RechargePlanSelectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ServiceCategoryModel.class.getName(), serviceCategoryModel);
        bundle.putString("mobile", mobile);
        intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
        startActivity(intent);

    }
}