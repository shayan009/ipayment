package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.screen.sellearn.AffiliateFragment;

import java.util.List;


public class SellEarnViewPagerAdapter extends FragmentStateAdapter {


    public SellEarnViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    private List<ServiceModel> serviceModelList;

    public List<ServiceModel> getServiceModelList() {
        return serviceModelList;
    }

    public void setServiceModelList(List<ServiceModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return AffiliateFragment.newInstance(serviceModelList.get(position));
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
