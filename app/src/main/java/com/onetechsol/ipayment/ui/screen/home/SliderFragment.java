package com.onetechsol.ipayment.ui.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentSliderBinding;
import com.onetechsol.ipayment.pojo.ImageSliderItem;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;

public class SliderFragment extends BaseFragment<FragmentSliderViewModel, FragmentSliderBinding> {

    private ImageSliderItem imageSliderItem;

    public SliderFragment(ImageSliderItem imageSliderItem) {
        this.imageSliderItem = imageSliderItem;
    }

    public SliderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinding().setHomeSliderItem(imageSliderItem);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .priority(Priority.HIGH);

//      new GlideImageLoader(viewBinding().ivSliderImage,viewBinding().progressSliderLoader).load(imageSliderItem.imageUrl(),options);

        //Glide.with(view.getContext()).load(homeSliderItem.imageUrl()).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivSliderImage);

    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_slider;
    }

    @Override
    public FragmentSliderViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(FragmentSliderViewModel.class);
    }

    @Override
    public FragmentSliderBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onRefresh() {

    }
}
