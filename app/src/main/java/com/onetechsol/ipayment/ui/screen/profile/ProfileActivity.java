package com.onetechsol.ipayment.ui.screen.profile;


import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityProfileBinding;
import com.onetechsol.ipayment.databinding.ProfileItemClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class ProfileActivity extends BaseActivity<ProfileViewModel, ActivityProfileBinding> implements ProfileItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ActivityProfileBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityProfileBinding.inflate(inflater);
    }

    @Override
    public ProfileViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProfileViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_profile;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void goBack() {
        onBackPressed();
    }
}