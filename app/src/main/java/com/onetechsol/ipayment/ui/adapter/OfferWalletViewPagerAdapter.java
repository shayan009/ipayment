package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.ui.screen.offerswallet.OfferFragment;
import com.onetechsol.ipayment.ui.screen.offerswallet.WalletFragment;

public class OfferWalletViewPagerAdapter extends FragmentStateAdapter {


    public OfferWalletViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OfferFragment();
            case 1:
                return new WalletFragment();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
