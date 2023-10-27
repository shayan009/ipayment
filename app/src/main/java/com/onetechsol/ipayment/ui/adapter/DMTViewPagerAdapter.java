package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary.AddBeneficaryFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary.BeneficiaryListFragment;

public class DMTViewPagerAdapter extends FragmentStateAdapter {

    private String remMobile;

    public DMTViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return AddBeneficaryFragment.newInstance(remMobile);
        } else {
            return BeneficiaryListFragment.newInstance(remMobile);
        }

    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public void setRemitterMobile(String remMobile) {
        this.remMobile = remMobile;
    }
}
