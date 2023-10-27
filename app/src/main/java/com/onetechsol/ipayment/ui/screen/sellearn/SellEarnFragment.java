package com.onetechsol.ipayment.ui.screen.sellearn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentSellEarnBinding;
import com.onetechsol.ipayment.databinding.SellEarnClickListener;
import com.onetechsol.ipayment.ui.adapter.SellEarnViewPagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.dashboard.DashboardActivity;

import org.apache.commons.lang3.StringUtils;

import io.reactivex.rxjava3.disposables.Disposable;

public class SellEarnFragment extends BaseFragment<SellEarnViewModel, FragmentSellEarnBinding> implements SellEarnClickListener {


    private Disposable subscribe;


    public SellEarnFragment() {
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sell_earn;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewBinding().setSellEarnClickListener(this);

        //RecommendedProductAdapter recommendedProductAdapter = new RecommendedProductAdapter();
        //viewBinding().setRecommendedProductAdapter(recommendedProductAdapter);


        String colorSelected = "#6C6F74";
        String colorUnSelected = "#BAC0C6";


        /*subscribe = viewModel().getAffiliateList()
                .subscribe(response -> {

                            PaginatedResult<SellEarn> data = response.getData();
                            sideMenuItemList = new ArrayList<>();
                            data.forEach(sellEarn -> {
                                sideMenuItemList.add(new SideMenuItem(sellEarn.getId(),sellEarn.getName(), SellEarnType.get(sellEarn.getType()) == SellEarnType.INSURANCE,SellEarnType.get(sellEarn.getType()) == SellEarnType.INSURANCE ? colorSelected : colorUnSelected));
                            });
                            SideMenuListAdapter sideMenuListAdapter = new SideMenuListAdapter();
                            sideMenuListAdapter.setSideMenuItemList(sideMenuItemList);
                            viewBinding().rvProducts.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                            viewBinding().rvProducts.setAdapter(sideMenuListAdapter);

                        },
                        error -> Log.e("MyAmplifyApp", "Query failure", error));
*/
/*
        List<RecommendedProductItem> recommendedProductItems = new ArrayList<>();
        recommendedProductItems.add(new RecommendedProductItem(SellEarnType.SAVINGS_ACC.sellEarnType(), 500, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.SAVINGS_ACC,"#F0F6FA","#62899D"));
        recommendedProductItems.add(new RecommendedProductItem(SellEarnType.DEMAT_ACC.sellEarnType(), 300, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.DEMAT_ACC,"#F7BE7D","#9C784F"));
        recommendedProductItems.add(new RecommendedProductItem(SellEarnType.CREDIT_CARD.sellEarnType(), 2000, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.CREDIT_CARD,"#EADDFF","#D0BCFF"));
        recommendedProductItems.add(new RecommendedProductItem(SellEarnType.PERSONAL_LOAN.sellEarnType(), 1250, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.PERSONAL_LOAN,"#EFB8C8","#492532"));

        recommendedProductAdapter.setRecommendedProductItems(recommendedProductItems);*/


        SellEarnViewPagerAdapter sellEarnViewPagerAdapter = new SellEarnViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        viewBinding().vpProductItems.setUserInputEnabled(false);
        viewBinding().vpProductItems.setAdapter(sellEarnViewPagerAdapter);
        viewBinding().vpProductItems.setCurrentItem(0);
        viewBinding().vpProductItems.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewBinding().vpProductItems.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                if(sideMenuItemList != null && sideMenuItemList.size() > 0) {
//                    viewBinding().tvTitleSellEarn.setText(sideMenuItemList.get(position).name());
//                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String sideMenuId = intent.getStringExtra("sideMenuId");
                if (StringUtils.isNoneEmpty(sideMenuId))
                    viewBinding().vpProductItems.setCurrentItem(Integer.parseInt(sideMenuId));
            }
        }, new IntentFilter("selected_menu"));

    }


    @Override
    public SellEarnViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(SellEarnViewModel.class);
    }


    @Override
    public FragmentSellEarnBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void goBack() {
        getActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openDashboard() {
        startActivity(new Intent(getActivity(), DashboardActivity.class));
    }

    @Override
    public void onDestroy() {

        if (subscribe != null) {
            subscribe.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {

    }
}