package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.FundReqAccount;
import com.onetechsol.ipayment.ui.screen.addFund.QRPayModeFragment;
import com.onetechsol.ipayment.ui.screen.addFund.UpiPayModeFragment;

public class AddFundPayModeViewPagerAdapter extends FragmentStateAdapter {

    private FundReqAccount fundReqAccount;

    public AddFundPayModeViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, FundReqAccount fundReqAccount) {
        super(fragmentManager, lifecycle);
        this.fundReqAccount = fundReqAccount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return QRPayModeFragment.newInstance(fundReqAccount);
            case 1:
                return UpiPayModeFragment.newInstance(fundReqAccount);
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 2;
    }

}
