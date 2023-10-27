package com.onetechsol.ipayment.ui.screen.service.subservice;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentServiceCategoryBinding;
import com.onetechsol.ipayment.databinding.ServiceCategoryClickListener;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.pojo.ServiceCategoryType;
import com.onetechsol.ipayment.pojo.ServiceList;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.CategoryServiceAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps1.AEPS1Activity;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps2.AEPS2Activity;
import com.onetechsol.ipayment.ui.screen.service.dmt.RemitterCheckBottomSheet;
import com.onetechsol.ipayment.ui.screen.service.insurance.BuyInsuranceActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.MobileRechargeActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.electricity.BillInsurancePayActivity;
import com.onetechsol.ipayment.utils.ApiConstant;
import com.onetechsol.ipayment.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class ServiceCategoryFragment extends BaseFragment<ServiceCategoryViewModel, FragmentServiceCategoryBinding> implements ServiceCategoryClickListener, CategoryServiceAdapter.AdapterCallback {


    private ServiceModel serviceModel;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public ServiceCategoryFragment() {
    }

    public static ServiceCategoryFragment newInstance(ServiceModel serviceModel, int i) {

        Bundle args = new Bundle();
        args.putInt("pos", i);
        args.putParcelable(Constant.SERVICE_MODEL + i, serviceModel);
        ServiceCategoryFragment fragment = new ServiceCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_service_category;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserLoginSession userSession = viewModel().prefManager().getUserSession();


        if (userSession != null) {


            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();

            Log.d("getUserSession:bearerAu", bearerAuth);
            Log.d("getUserSession:userName", userName);

            int pos = requireArguments().getInt("pos", 0);
            serviceModel = requireArguments().getParcelable(Constant.SERVICE_MODEL + pos);
            Log.d("ServiceCF.serviceModel", serviceModel.toString());
            viewBinding().rvServiceCategoryList.setLayoutManager(new GridLayoutManager(requireActivity(), 3, RecyclerView.VERTICAL, false));

            onShowLoading();
            mDisposable.add(viewModel().getCategoryWiseServiceList(bearerAuth, userName, String.valueOf(serviceModel.categoryId()), serviceModel.title())
                    .subscribe(categoryWiseServiceResponse -> {
                        onHideLoading();
                        List<ServiceList> serviceLists = categoryWiseServiceResponse.data().serviceCategoryModels();
                        List<ServiceList> serviceLists2 = categoryWiseServiceResponse.data().getServiceCategoryModels2();

                        List<ServiceList> allServicelists = new ArrayList<>(serviceLists);
                        allServicelists.addAll(serviceLists2);

                        List<ServiceCategoryModel> serviceCategoryModels = new ArrayList<>();

                        if (allServicelists.size() > 0) {

                            for (int i = 0; i < allServicelists.size(); i++) {
                                ServiceList serviceList = allServicelists.get(i);
                                ServiceCategoryType serviceType = ServiceCategoryType.get(serviceList.id());

                                if (!serviceList.label().equalsIgnoreCase(serviceModel.title())) {
                                    serviceCategoryModels.add(new ServiceCategoryModel(i, serviceList.id(), serviceList.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + serviceList.img(), serviceType, "", serviceList.servType().equals("1992")));
                                }
                            }

                            CategoryServiceAdapter categoryServiceAdapter = new CategoryServiceAdapter();
                            categoryServiceAdapter.setServiceCategoryModelList(serviceCategoryModels);
                            categoryServiceAdapter.setCallback(this);
                            viewBinding().rvServiceCategoryList.setAdapter(categoryServiceAdapter);

                        }


                    }, throwable -> {
                        onHideLoading();
                    }));
        }

    }


    @Override
    public ServiceCategoryViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ServiceCategoryViewModel.class);
    }

    @Override
    public FragmentServiceCategoryBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClicked(ServiceCategoryModel serviceCategoryModel) {


        Log.d("serviceModel", serviceModel.categoryId());
        Log.d("serviceCategoryModel", serviceCategoryModel.categoryId());

        if (serviceModel.categoryId().equals("20")) {

            if (ServiceCategoryType.AEPS_1 == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId()))) {
                Intent intent = new Intent(getActivity(), AEPS1Activity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ServiceCategoryModel.class.getName(), serviceCategoryModel);
                intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
                requireActivity().startActivity(intent);
            }
            if (ServiceCategoryType.AEPS_2 == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId()))) {
                Intent intent = new Intent(getActivity(), AEPS2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ServiceCategoryModel.class.getName(), serviceCategoryModel);
                intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
                requireActivity().startActivity(intent);
            }


            if (ServiceCategoryType.DMT == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId()))) {

                RemitterCheckBottomSheet remitterCheckBottomSheet = new RemitterCheckBottomSheet();
                remitterCheckBottomSheet.show(getParentFragmentManager(), RemitterCheckBottomSheet.class.getName());

            }

        }

        if (serviceModel.categoryId().equals("1") || serviceModel.categoryId().equals("3")) {

            Bundle bundle = new Bundle();
            bundle.putParcelable(ServiceCategoryModel.class.getName(), serviceCategoryModel);

            if (ServiceCategoryType.RECHARGE_PREPAID == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId())) || ServiceCategoryType.RECHARGE_DTH == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId()))) {
                Intent intent = new Intent(getActivity(), MobileRechargeActivity.class);
                intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
                requireActivity().startActivity(intent);
            } else if (ServiceCategoryType.BUY_INSURANCE == ServiceCategoryType.get(String.valueOf(serviceCategoryModel.categoryId()))) {

                Intent intent = new Intent(requireActivity(), BuyInsuranceActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(getActivity(), BillInsurancePayActivity.class);
                intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
                requireActivity().startActivity(intent);
            }
        }

        if (serviceModel.categoryId().equals("4")) {

            //TODO More
        }

    }
}