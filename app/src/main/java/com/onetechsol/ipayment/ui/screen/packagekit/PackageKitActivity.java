package com.onetechsol.ipayment.ui.screen.packagekit;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityPackageKitBinding;
import com.onetechsol.ipayment.databinding.PackageKitClickListener;
import com.onetechsol.ipayment.pojo.KitData;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.PackageKitPlanAdapter;
import com.onetechsol.ipayment.ui.adapter.SlabViewPagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PackageKitActivity extends BaseActivity<PackageKitViewModel, ActivityPackageKitBinding> implements PackageKitClickListener, PackageKitPlanAdapter.AdapterCallback {

    private List<KitData> kitDataList;
    private PackageKitPlanAdapter packageKitPlanAdapter;
    private String retailerStatus;
    private UserLoginSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userSession = viewModel().prefManager().getUserSession();


        packageKitPlanAdapter = new PackageKitPlanAdapter();
        packageKitPlanAdapter.setCallback(this);
        viewBinding().setPackageKitPlanAdapter(packageKitPlanAdapter);

        viewBinding().setPackageKitItemClickListener(this);


        onShowLoading();
        compositeDisposable().add(viewModel().getKitList().subscribe(getKitListResponse -> {

            onHideLoading();
            if (getKitListResponse.status().equals("0")) {
                showToastAlertDialog("Alert", getKitListResponse.message(), false)
                        .setOnClickListener(() -> {

                        }).show(getSupportFragmentManager(), PackageKitActivity.class.getName());
            } else if (getKitListResponse.status().equals("1")) {
                kitDataList = getKitListResponse.data().kitDataList();
                retailerStatus = getKitListResponse.data().retailerStatus();

                for (int i = 0; i < kitDataList.size(); i++) {
                    KitData kitData = kitDataList.get(0);
                    kitData.setIndex(i);
                    kitData.setSelected(kitData.kit().equals("0"));
                }

                kitDataList.forEach(kitData -> {
                    if (kitData.selected()) {
                        viewBinding().vpSlabs.setCurrentItem(kitData.index());
                    }
                });

                packageKitPlanAdapter.setItems(kitDataList);
                SlabViewPagerAdapter slabViewPagerAdapter = new SlabViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
                slabViewPagerAdapter.setKitDataList(kitDataList);
                viewBinding().setSlabViewPagerAdapter(slabViewPagerAdapter);
            }

        }, throwable -> {
            onHideLoading();
        }));

    }

    @Override
    public ActivityPackageKitBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityPackageKitBinding.inflate(inflater);
    }

    @Override
    public PackageKitViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PackageKitViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_package_kit;
    }

    @Override
    public void onRefresh() {

    }


    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void onItemClicked(KitData kitData) {
        kitDataList.forEach(kitData1 -> {
            kitData1.setSelected(kitData.id().equals(kitData1.id()));
        });
        packageKitPlanAdapter.setItems(kitDataList);
        viewBinding().vpSlabs.setCurrentItem(kitData.index());
    }

    @Override
    public void buyKit(KitData kitData) {
        if (StringUtils.isNotEmpty(retailerStatus)) {

            showAlertDialog("Buy Kit", "Are you sure ?", false)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        if (retailerStatus.equals("1")) {
                            buyKitApi(kitData);
                        }


                    }).setNegativeButton("Cancel", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    }).show();


        }


    }


    private void buyKitApi(KitData kitData) {
        onShowLoading();
        compositeDisposable().add(viewModel().buyKit(kitData.label(), "1", userSession.userName())
                .subscribe(buyKitResponse -> {
                    onHideLoading();
                    showAlertDialog("Purchase Kit", buyKitResponse.message(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {

                                dialogInterface.dismiss();
                                if (buyKitResponse.status().equals("1")) {

                                    getOnBackPressedDispatcher().onBackPressed();

                                }
                            }).show();


                }, throwable -> {

                }));
    }
}