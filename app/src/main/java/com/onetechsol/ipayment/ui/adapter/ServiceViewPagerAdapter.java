package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.screen.service.subservice.ServiceCategoryFragment;

import java.util.ArrayList;

public class ServiceViewPagerAdapter extends FragmentStateAdapter {


    private ArrayList<ServiceModel> serviceModelArrayList;

    public ServiceViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public ArrayList<ServiceModel> serviceModelArrayList() {
        return serviceModelArrayList;
    }

    public ServiceViewPagerAdapter setServiceModelArrayList(ArrayList<ServiceModel> serviceModelArrayList) {
        this.serviceModelArrayList = serviceModelArrayList;
        return this;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ServiceCategoryFragment.newInstance(serviceModelArrayList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return serviceModelArrayList != null && serviceModelArrayList.size() > 0 ? serviceModelArrayList.size() : 0;
    }
}
