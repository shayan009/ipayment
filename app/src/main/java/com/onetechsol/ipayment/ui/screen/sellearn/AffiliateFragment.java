package com.onetechsol.ipayment.ui.screen.sellearn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentAffiliateBinding;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.adapter.HomeSellEarnAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;

public class AffiliateFragment extends BaseFragment<AffiliateViewModel, FragmentAffiliateBinding> {


    private static final String ARG_PARAM1 = "param1";

    private ServiceModel serviceModel;


    public AffiliateFragment() {
        // Required empty public constructor
    }

    public static AffiliateFragment newInstance(ServiceModel serviceModel) {
        AffiliateFragment fragment = new AffiliateFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, serviceModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serviceModel = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeSellEarnAdapter homeSellEarnAdapter = new HomeSellEarnAdapter();
        homeSellEarnAdapter.setItems(serviceModel.getAffiliateModels());
        viewBinding().rvAffiliateService.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        viewBinding().rvAffiliateService.setAdapter(homeSellEarnAdapter);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_affiliate;
    }


    @Override
    public AffiliateViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AffiliateViewModel.class);
    }

    @Override
    public FragmentAffiliateBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }
}