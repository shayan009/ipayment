package com.onetechsol.ipayment.ui.screen.offerswallet;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityOfferWalletBinding;
import com.onetechsol.ipayment.ui.adapter.OfferWalletViewPagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class OfferWalletActivity extends BaseActivity<OfferWalletViewModel, ActivityOfferWalletBinding> implements TabLayout.OnTabSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OfferWalletViewPagerAdapter offerWalletViewPagerAdapter = new OfferWalletViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewBinding().vpOfferWallet.setAdapter(offerWalletViewPagerAdapter);
        viewBinding().tabLayoutOfferWallet.addOnTabSelectedListener(this);
        new TabLayoutMediator(viewBinding().tabLayoutOfferWallet, viewBinding().vpOfferWallet,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Offers");
                        tab.setIcon(R.drawable.drawable_offer);
                    } else if (position == 1) {
                        tab.setText("Wallet");
                        tab.setIcon(R.drawable.drawable_wallet);
                    }

                    viewBinding().vpOfferWallet.setCurrentItem(position);

                }
        ).attach();

    }


    @Override
    public ActivityOfferWalletBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityOfferWalletBinding.inflate(inflater);
    }

    @Override
    public OfferWalletViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(OfferWalletViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_offer_wallet;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        /*if(tab.getPosition() == 0) {
            tab.setIcon(R.drawable.offer_selected);
            viewBinding().tabLayoutOfferWallet.getTabAt(1).setIcon(R.drawable.wallet_unselected);
        } else  if (tab.getPosition() == 1) {
            tab.setIcon(R.drawable.wallet_selected);
            viewBinding().tabLayoutOfferWallet.getTabAt(1).setIcon(R.drawable.offer_unselected);
        }*/
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}