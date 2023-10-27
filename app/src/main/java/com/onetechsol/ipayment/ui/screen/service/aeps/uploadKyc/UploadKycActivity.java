package com.onetechsol.ipayment.ui.screen.service.aeps.uploadKyc;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityUploadKycBinding;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class UploadKycActivity extends BaseActivity<UploadKycViewModel, ActivityUploadKycBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAttachFragment(viewBinding().flUploadKyc.getId(), new UploadKycFragment(), UploadKycFragment.class.getName());

    }

    @Override
    public ActivityUploadKycBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityUploadKycBinding.inflate(inflater);
    }

    @Override
    public UploadKycViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(UploadKycViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_upload_kyc;
    }

    @Override
    public void onRefresh() {

    }
}