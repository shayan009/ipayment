package com.onetechsol.ipayment.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onetechsol.ipayment.pojo.ImageSliderItem;
import com.onetechsol.ipayment.ui.screen.home.SliderFragment;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {


    private List<ImageSliderItem> imageSliderItems;

    public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void setImageSliderItems(List<ImageSliderItem> imageSliderItems) {
        this.imageSliderItems = imageSliderItems;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new SliderFragment(imageSliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return imageSliderItems.size();
    }
}
