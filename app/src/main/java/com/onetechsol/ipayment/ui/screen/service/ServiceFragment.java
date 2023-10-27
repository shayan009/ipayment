package com.onetechsol.ipayment.ui.screen.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentServiceBinding;
import com.onetechsol.ipayment.databinding.ServiceFragClickListener;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.SideMenuListAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.dashboard.DashboardActivity;
import com.onetechsol.ipayment.ui.screen.service.subservice.ServiceCategoryFragment;
import com.onetechsol.ipayment.utils.Constant;

import java.util.ArrayList;
import java.util.Objects;


public class ServiceFragment extends BaseFragment<ServiceViewModel, FragmentServiceBinding> implements ServiceFragClickListener {


    private ArrayList<ServiceModel> serviceModelArrayList;
    private int position;

    public ServiceFragment() {

    }


    public static ServiceFragment newInstance(ArrayList<ServiceModel> serviceModels, int position) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(Constant.SERVICE_LIST, serviceModels);
        args.putInt(Constant.POSITION, position);
        ServiceFragment fragment = new ServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_service;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        String colorSelected = "#292929";
        String colorUnSelected = "#BAC0C6";
        if (userSession != null) {

            serviceModelArrayList = requireArguments().getParcelableArrayList(Constant.SERVICE_LIST);

            Log.d("serviceModelArrayList", serviceModelArrayList.toString());

            position = requireArguments().getInt(Constant.POSITION);

            SideMenuListAdapter sideMenuListAdapter = new SideMenuListAdapter();

            sideMenuListAdapter.setServiceFragClickListener(this);
            viewBinding().setServiceFragClickListener(this);

            for (int i = 0; i < serviceModelArrayList.size(); i++) {
                if (Objects.equals(serviceModelArrayList.get(i).title(), serviceModelArrayList.get(position).title())) {
                    serviceModelArrayList.get(i).setSelected(true);
                    serviceModelArrayList.get(i).setColor(colorSelected);
                } else {
                    serviceModelArrayList.get(i).setSelected(false);
                    serviceModelArrayList.get(i).setColor(colorUnSelected);
                }
            }

            sideMenuListAdapter.setItems(serviceModelArrayList);
            viewBinding().rvServiceMenu.setAdapter(sideMenuListAdapter);


            //ServiceViewPagerAdapter serviceViewPagerAdapter = new ServiceViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
            //serviceViewPagerAdapter.setServiceModelArrayList(serviceModelArrayList);
            // viewBinding().vpServiceItems.setAdapter(serviceViewPagerAdapter);


            /*viewBinding().vpServiceItems.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (serviceModelArrayList.size() > 0)
                        viewBinding().tvTitleSellEarn.setText(serviceModelArrayList.get(position).title());


                }

                @Override
                public void onPageScrollStateChanged(int state) {
              }
            });
*/

            // viewBinding().vpServiceItems.setUserInputEnabled(false);
            openCategory(position);
            // viewBinding().vpServiceItems.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        }


    }


    @Override
    public ServiceViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ServiceViewModel.class);
    }

    @Override
    public FragmentServiceBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void goBack() {
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openDashboard() {
        startActivity(new Intent(getActivity(), DashboardActivity.class));
    }

    @Override
    public void openCategory(int position) {

        // //Toast.makeText(requireContext(), "Side menu selected - " + serviceModelArrayList.get(position).toString(), //Toast.LENGTH_SHORT).show();

        ServiceCategoryFragment serviceCategoryFragment = ServiceCategoryFragment.newInstance(serviceModelArrayList.get(position), position);

        onAttachFragment(viewBinding().frameLayoutCategory.getId(), serviceCategoryFragment, ServiceCategoryFragment.class.getName());
        viewBinding().tvTitleSellEarn.setText(serviceModelArrayList.get(position).title());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    @Override
    public void onRefresh() {

    }
}