package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.KitData;
import com.onetechsol.ipayment.ui.screen.packagekit.SlabFragment;

import java.util.List;

public class SlabViewPagerAdapter extends FragmentStateAdapter {


    private List<KitData> kitDataList;

    public SlabViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public List<KitData> kitDataList() {
        return kitDataList;
    }

    public SlabViewPagerAdapter setKitDataList(List<KitData> kitDataList) {
        this.kitDataList = kitDataList;
        return this;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return SlabFragment.newInstance(kitDataList.get(position).id());
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
