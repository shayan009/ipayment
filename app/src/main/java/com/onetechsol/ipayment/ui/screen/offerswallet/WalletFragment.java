package com.onetechsol.ipayment.ui.screen.offerswallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentWalletBinding;
import com.onetechsol.ipayment.databinding.WalletDetailsClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;


public class WalletFragment extends BaseFragment<WalletViewModel, FragmentWalletBinding> implements WalletDetailsClickListener {


    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_wallet;
    }

    @Override
    public WalletViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(WalletViewModel.class);
    }


    @Override
    public FragmentWalletBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onRefresh() {

    }

    /*@Override
    public void goToBrandRewards(View view) {

        if(view.getId() == R.id.mcvBrand) {

        } else if (view.getId() == R.id.mcvReward) {

        } else if (view.getId() == R.id.mcvInsurance) {

        }
    }*/
}