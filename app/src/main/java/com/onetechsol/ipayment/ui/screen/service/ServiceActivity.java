package com.onetechsol.ipayment.ui.screen.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityServiceBinding;
import com.onetechsol.ipayment.databinding.ServiceActivityClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.home.HomeFragment;
import com.onetechsol.ipayment.ui.screen.sellearn.SellEarnFragment;

public class ServiceActivity extends BaseActivity<ServiceViewModel, ActivityServiceBinding> implements ServiceActivityClickListener {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getIntent().getIntExtra("position", 0);
        //ServiceFragment serviceFragment = new ServiceFragment();
        //serviceFragment.setPosition(position);
        //   onAttachFragment(viewBinding().framelayoutService.getId(), serviceFragment, ServiceFragment.class.getName());
        viewBinding().setServiceActivityClickListener(this);
        loadServices();
    }

    @Override
    public void loadHome() {

        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.VISIBLE, R.color.colorPrimary);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
        viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

        onAttachFragment(viewBinding().framelayoutService.getId(), new HomeFragment(), HomeFragment.class.getName());


    }

    @Override
    public void loadCustomer() {

        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.VISIBLE, R.color.colorPrimary);
        viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

    }

    @Override
    public void loadSellEarn() {
        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
        viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.colorPrimary));
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

        onAttachFragment(viewBinding().framelayoutService.getId(), new SellEarnFragment(), SellEarnFragment.class.getName());

    }

    @Override
    public void loadServices() {

        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
        viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.VISIBLE, R.color.colorPrimary);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

       /* ServiceFragment serviceFragment = new ServiceFragment();
        serviceFragment.setPosition(position);*/

        // onAttachFragment(viewBinding().framelayoutService.getId(), serviceFragment, ServiceFragment.class.getName());

    }

    @Override
    public void loadContent() {

        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
        viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.VISIBLE, R.color.colorPrimary);

    }


    private void setUINavColors(AppCompatImageView ivNavBg, MaterialCheckBox mbNavIcon, AppCompatTextView tvNavText, int invisible, int light_grey) {
        ivNavBg.setVisibility(invisible);
        mbNavIcon.setCheckedState(invisible == 0 ? MaterialCheckBox.STATE_CHECKED : MaterialCheckBox.STATE_UNCHECKED);
        tvNavText.setTextColor(getResources().getColor(light_grey));
    }


    @Override
    public ActivityServiceBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityServiceBinding.inflate(inflater);
    }

    @Override
    public ServiceViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ServiceViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_service;
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onRefresh() {

    }
}