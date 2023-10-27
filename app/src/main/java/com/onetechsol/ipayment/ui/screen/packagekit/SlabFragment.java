package com.onetechsol.ipayment.ui.screen.packagekit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentSlabBinding;
import com.onetechsol.ipayment.pojo.PackageSlabItem;
import com.onetechsol.ipayment.ui.adapter.PackageSlabAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;

import java.util.List;

public class SlabFragment extends BaseFragment<PackageKitViewModel, FragmentSlabBinding> {

    public SlabFragment() {
        // Required empty public constructor
    }

    public static SlabFragment newInstance(String packageId) {

        Bundle args = new Bundle();
        args.putString("packageId", packageId);
        SlabFragment fragment = new SlabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_slab;
    }


    @Override
    public PackageKitViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PackageKitViewModel.class);
    }

    @Override
    public FragmentSlabBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PackageSlabAdapter packageSlabAdapter = new PackageSlabAdapter();
        viewBinding().setPackageSlabAdapter(packageSlabAdapter);
        onShowLoading();
        if (getArguments().getString("packageId") != null) {
            String packageId = getArguments().getString("packageId");
            compositeDisposable().add(viewModel().myPackageList(packageId)
                    .subscribe(getPackageSlabResponse -> {

                        onHideLoading();

                        if (getPackageSlabResponse.status().equals("0")) {
                            showToastAlertDialog("Alert", getPackageSlabResponse.message(), false)
                                    .setOnClickListener(() -> {

                                    }).show(requireActivity().getSupportFragmentManager(), PackageKitActivity.class.getName());
                        } else if (getPackageSlabResponse.status().equals("1")) {
                            List<PackageSlabItem> packageSlabItems = getPackageSlabResponse.data().packageList();
                            packageSlabAdapter.setItems(packageSlabItems);
                        }


                    }, throwable -> {
                        onHideLoading();
                    }));
        }


    }

    @Override
    public void onRefresh() {

    }
}