package com.onetechsol.ipayment.ui.screen.productdetails.earning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentEarningBinding;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailViewModel;

public class EarningFragment extends BaseFragment<ProductDetailViewModel, FragmentEarningBinding> {


    public EarningFragment() {
        // Required empty public constructor
    }

    public static EarningFragment newInstance() {
        EarningFragment fragment = new EarningFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_earning;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public ProductDetailViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductDetailViewModel.class);
    }

    @Override
    public FragmentEarningBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }
}