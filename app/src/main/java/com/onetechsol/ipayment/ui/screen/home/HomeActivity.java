package com.onetechsol.ipayment.ui.screen.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityHomeBinding;
import com.onetechsol.ipayment.databinding.CommonEventHandler;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.customer.MyCustomerFragment;
import com.onetechsol.ipayment.ui.screen.service.ServiceFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity<HomeActivityViewModel, ActivityHomeBinding> implements CommonEventHandler {


    private ArrayList<ServiceModel> serviceModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        onShowLoading();
        compositeDisposable().add(viewModel().getServiceList(getIntent().getIntExtra("pos", 0))
                .subscribe(serviceListResponse -> {
                    onHideLoading();
                    serviceModelArrayList = serviceListResponse.serviceModelList();


                    if (serviceListResponse.status().equals("1")) {

                        if (getIntent().getIntExtra("pos", 0) == 2)
                            openServiceFragment(serviceListResponse.serviceModelList());
                        else {
                            openHomeFragment();
                        }
                    } else if (serviceListResponse.status().equals("0")) {
                        showAlertDialog("Service alert!", serviceListResponse.message(), true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        }).show();
                    }


                }, throwable -> {
                    onHideLoading();
                    showAlertDialog("Error alert!", throwable.getMessage(), true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dialogInterface.dismiss();

                        }
                    }).show();
                }));


        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showAlertDialog("Exit Application", "Want to exit the application", true)
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            finish();

                        }).setNegativeButton("No", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        }).show();
            }
        });

        viewBinding().clNavHome.setOnClickListener(view -> {

            setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.VISIBLE, R.color.colorPrimary);
            setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);

            //viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
            //setUINavColors(viewBinding().ivNavSellEarnBg,viewBinding().cbNavSellEarnIcon,viewBinding().tvNavSellEarnText,  View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

            openHomeFragment();

        });

        viewBinding().clNavCustomer.setOnClickListener(view -> {

            setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.VISIBLE, R.color.colorPrimary);
            //viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
            //setUINavColors(viewBinding().ivNavSellEarnBg,viewBinding().cbNavSellEarnIcon,viewBinding().tvNavSellEarnText,View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);
            onAttachFragment(viewBinding().frameLayout.getId(), new MyCustomerFragment(), MyCustomerFragment.class.getName());


        });

        viewBinding().tvAddSellEarn.setOnClickListener(view -> {

            setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
            //viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.colorPrimary));
            //setUINavColors(viewBinding().ivNavSellEarnBg,viewBinding().cbNavSellEarnIcon,viewBinding().tvNavSellEarnText,View.VISIBLE, R.color.colorPrimary);
            setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

            // onAttachFragment(viewBinding().frameLayout.getId(), new SellEarnFragment(), SellEarnFragment.class.getName());


        });

        viewBinding().clNavService.setOnClickListener(view -> {

            if (prefManager.getUserSession().roleId().equals("6")) {
                openServiceFragment(serviceModelArrayList);
            } else {
                showAlertDialog("Invalid Selection", "Not available for you. Please upgrade to Merchant business to access it", true)
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        }).show();
            }


        });

        viewBinding().clNavContent.setOnClickListener(view -> {

            setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);
            viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
            //setUINavColors(viewBinding().ivNavSellEarnBg,viewBinding().cbNavSellEarnIcon,viewBinding().tvNavSellEarnText,View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.INVISIBLE, R.color.light_grey);
            setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.VISIBLE, R.color.colorPrimary);


        });

    }

    private void openHomeFragment() {
        HomeFragment homeFragment = HomeFragment.newInstance(serviceModelArrayList);
        onAttachFragment(viewBinding().frameLayout.getId(), homeFragment, HomeFragment.class.getName());
    }

    private void openServiceFragment(ArrayList<ServiceModel> serviceModels) {
        setUINavColors(viewBinding().ivNavHomeBg, viewBinding().cbNavHomeIcon, viewBinding().tvNavHomeText, View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavCustomerBg, viewBinding().cbNavCustomerIcon, viewBinding().tvNavCustomerText, View.INVISIBLE, R.color.light_grey);

        //viewBinding().tvNavSellEarnText.setTextColor(getResources().getColor(R.color.light_grey));
        //setUINavColors(viewBinding().ivNavSellEarnBg,viewBinding().cbNavSellEarnIcon,viewBinding().tvNavSellEarnText,View.INVISIBLE, R.color.light_grey);
        setUINavColors(viewBinding().ivNavServiceBg, viewBinding().cbNavServiceIcon, viewBinding().tvNavServiceText, View.VISIBLE, R.color.colorPrimary);
        setUINavColors(viewBinding().ivNavContentBg, viewBinding().cbNavContentIcon, viewBinding().tvNavContentText, View.INVISIBLE, R.color.light_grey);

        ServiceFragment serviceFragment = ServiceFragment.newInstance(serviceModels, 0);
        onAttachFragment(viewBinding().frameLayout.getId(), serviceFragment, ServiceFragment.class.getName());
    }


    private void setUINavColors(AppCompatImageView ivNavBg, MaterialCheckBox mbNavIcon, AppCompatTextView tvNavText, int invisible, int light_grey) {
        // ivNavBg.setVisibility(invisible);
        mbNavIcon.setCheckedState(invisible == 0 ? MaterialCheckBox.STATE_CHECKED : MaterialCheckBox.STATE_UNCHECKED);
        tvNavText.setTextColor(getResources().getColor(light_grey));
    }


    @Override
    public ActivityHomeBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityHomeBinding.inflate(inflater);
    }

    @Override
    public HomeActivityViewModel setUpViewModel(ViewModelProvider modelProvider) {
        return modelProvider.get(HomeActivityViewModel.class);
    }


    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        //TODO clear binding
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {

    }
}