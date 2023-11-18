package com.onetechsol.ipayment.ui.screen.sellearn;

import static com.onetechsol.ipayment.utils.ApiConstant.BASE_URL_IMAGE_SERVICE;

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
import com.onetechsol.ipayment.databinding.ServiceFragClickListener;
import com.onetechsol.ipayment.pojo.AffiliateModel;
import com.onetechsol.ipayment.pojo.DepartmentModel;
import com.onetechsol.ipayment.pojo.RecommendedProductItem;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.adapter.AffiliateDepartmentAdapter;
import com.onetechsol.ipayment.ui.adapter.RecommendedProductAdapter;
import com.onetechsol.ipayment.ui.adapter.SellEarnViewPagerAdapter;
import com.onetechsol.ipayment.ui.adapter.SideMenuListAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.dashboard.DashboardActivity;
import com.onetechsol.ipayment.utils.Constant;
import com.onetechsol.ipayment.utils.Utilities;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.disposables.Disposable;

public class SellEarnFragment extends BaseFragment<SellEarnViewModel, FragmentSellEarnBinding> implements SellEarnClickListener, ServiceFragClickListener {


    private Disposable subscribe;
    private int position;
    String colorSelected = "#292929";
    String colorUnSelected = "#BAC0C6";

    public SellEarnFragment() {
    }

    public static SellEarnFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("pos",position);
        SellEarnFragment fragment = new SellEarnFragment();
        fragment.setArguments(args);
        return fragment;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(requireArguments() != null) {
            position = requireArguments().getInt("pos", 0);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewBinding().setSellEarnClickListener(this);

        RecommendedProductAdapter recommendedProductAdapter = new RecommendedProductAdapter();
        viewBinding().setRecommendedProductAdapter(recommendedProductAdapter);

        getAffiliateAPi(position);

        List<RecommendedProductItem> recommendedProductItems = new ArrayList<>();
        recommendedProductItems.add(new RecommendedProductItem("Saving Account", 500, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.SAVINGS_ACC, "#F0F6FA", "#62899D", 200));
        recommendedProductItems.add(new RecommendedProductItem("Demat Account", 300, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.DEMAT_ACC, "#F7BE7D", "#9C784F", 100));
        recommendedProductItems.add(new RecommendedProductItem("Credit Card", 2000, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.CREDIT_CARD, "#EADDFF", "#D0BCFF", 150));
        recommendedProductItems.add(new RecommendedProductItem("Personal Loan", 1250, Utilities.getDrawableUrl(R.drawable.idfc), SellEarnType.PERSONAL_LOAN, "#EFB8C8", "#492532", 120));

        recommendedProductAdapter.setItems(recommendedProductItems);


        viewBinding().vpProductItems.setUserInputEnabled(false);
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

    }


    private void getAffiliateAPi(int position) {
        onShowLoading();
        compositeDisposable().add(viewModel().getDepartmentList(position)
                .subscribe(getDepartmentListResponse -> {
                    onHideLoading();


                    SideMenuListAdapter sideMenuListAdapter = new SideMenuListAdapter();
                    sideMenuListAdapter.setServiceFragClickListener(this);
                    sideMenuListAdapter.setItems(getDepartmentListResponse.getDepartmentList());
                    viewBinding().rvProducts.setAdapter(sideMenuListAdapter);


                    SellEarnViewPagerAdapter sellEarnViewPagerAdapter = new SellEarnViewPagerAdapter(requireActivity().getSupportFragmentManager(), getLifecycle());
                    sellEarnViewPagerAdapter.setServiceModelList(getDepartmentListResponse.getDepartmentList());
                    viewBinding().vpProductItems.setAdapter(sellEarnViewPagerAdapter);

                    viewBinding().vpProductItems.setCurrentItem(position);


                }, throwable -> {
                    onHideLoading();
                })
        );

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
    public void openCategory(int position) {

        viewBinding().vpProductItems.setCurrentItem(position);

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