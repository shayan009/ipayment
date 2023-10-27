package com.onetechsol.ipayment.ui.screen.service.dmt;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityDmtactivityBinding;
import com.onetechsol.ipayment.databinding.DmtOnClickListener;
import com.onetechsol.ipayment.ui.adapter.DMTViewPagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class DMTActivity extends BaseActivity<DMTViewModel, ActivityDmtactivityBinding> implements DmtOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding().setDmtOnClickListener(this);

        DMTViewPagerAdapter dmtViewPagerAdapter = new DMTViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        dmtViewPagerAdapter.setRemitterMobile(getIntent().getStringExtra("remMobile"));
        viewBinding().setDmtViewPagerAdapter(dmtViewPagerAdapter);

        viewBinding().vpBeneficiaryItems.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                setButtonSelectedColor(position);
                super.onPageSelected(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        viewBinding().vpBeneficiaryItems.setCurrentItem(0);


    }

    void setButtonSelectedColor(int pos) {
        switch (pos) {
            case 0: {
                viewBinding().btBeneficiaryAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                viewBinding().btBeneficiary.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                break;
            }

            case 1: {
                viewBinding().btBeneficiaryAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btBeneficiary.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                break;
            }

        }
    }

    @Override
    public ActivityDmtactivityBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityDmtactivityBinding.inflate(inflater);
    }

    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_dmtactivity;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void showBeneficiaryList() {
        viewBinding().vpBeneficiaryItems.setCurrentItem(1);

    }

    @Override
    public void addBeneficiary() {
        viewBinding().vpBeneficiaryItems.setCurrentItem(0);

    }
}