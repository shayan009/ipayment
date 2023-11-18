package com.onetechsol.ipayment.ui.screen.home;


import static com.onetechsol.ipayment.utils.ApiConstant.BASE_URL_IMAGE_SERVICE;

import android.net.Uri;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.FragmentHomeBinding;
import com.onetechsol.ipayment.pojo.AffiliateModel;
import com.onetechsol.ipayment.pojo.DepartmentModel;
import com.onetechsol.ipayment.pojo.GetAffiliateServiceList;
import com.onetechsol.ipayment.pojo.GetDepartmentListResponse;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.pojo.ServiceList;
import com.onetechsol.ipayment.pojo.ServiceListRequest;
import com.onetechsol.ipayment.pojo.ServiceListResponse;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.pojo.ServiceType;
import com.onetechsol.ipayment.pojo.WalletListRequest;
import com.onetechsol.ipayment.pojo.WalletListResponse;
import com.onetechsol.ipayment.ui.adapter.GromoReviewAdapter;
import com.onetechsol.ipayment.ui.adapter.GromoTutorialAdapter;
import com.onetechsol.ipayment.ui.adapter.ScreenSlidePagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeFragmentViewModel extends BaseViewModel {


    public Observable<ServiceListResponse> getServiceList() {

        ServiceListRequest serviceListRequest = new ServiceListRequest(ApiConstant.BASIC_VERSION);


        String colorSelected = "#292929";
        String colorUnSelected = "#BAC0C6";

        return iModelRepository().getServiceList(serviceListRequest)
                .map(m -> {


                    if (m.status().equals("1")) {

                        ArrayList<ServiceModel> serviceModelList = new ArrayList<>();

                        List<ServiceList> serviceLists = m.data().serviceList();

                        for (int i = 0; i < serviceLists.size(); i++) {
                            ServiceList serviceList = serviceLists.get(i);

                            if (!serviceList.label().equalsIgnoreCase("Others Service")) {

                                if (i == 0)
                                    serviceModelList.add(new ServiceModel(i, serviceList.id(), serviceList.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + serviceList.img(), ServiceType.get(serviceList.label().trim()), "", true, colorSelected));
                                else
                                    serviceModelList.add(new ServiceModel(i, serviceList.id(), serviceList.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + serviceList.img(), ServiceType.get(serviceList.label().trim()), "", false, colorUnSelected));
                            }
                        }
                        m.setServiceModelList(serviceModelList);

                    }

                    return m;
                })

                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<GetDepartmentListResponse> getDepartmentList() {


        return iModelRepository().getDepartmentList()
                .map(m -> {

                    List<DepartmentModel> departmentModels = m.data().departmentList();

                    departmentModels.forEach(departmentModel -> {

                        List<AffiliateModel> affiliateServiceList = departmentModel.affiliateModels();
                        List<SellEarnModel> sellEarnModelList = new ArrayList<>();

                        affiliateServiceList.forEach(sellEarn -> sellEarnModelList.add(new SellEarnModel(sellEarn.id(), sellEarn.label(), 0, BASE_URL_IMAGE_SERVICE + sellEarn.img(), 12, SellEarnType.get(sellEarn.id()))));

                        sellEarnModelList.sort(Comparator.comparing(SellEarnModel::id));
                        departmentModel.setSellEarnModels(sellEarnModelList);
                    });

                    m.data().setDepartmentList(departmentModels);


                    return m;

                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GetAffiliateServiceList> getAffiliateServiceList() {

        return iModelRepository().getAffiliateList()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WalletListResponse> getWalletList(String bearerAuth, String userName) {

        WalletListRequest walletListRequest = new WalletListRequest(ApiConstant.BASIC_VERSION);


        return iModelRepository().getWalletList(walletListRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getHomeFragmentData(FragmentHomeBinding fragmentHomeBinding, ScreenSlidePagerAdapter viewPagerAdapter, GromoTutorialAdapter gromoTutorialAdapter, GromoReviewAdapter gromoReviewAdapter) {


        Uri uriCC = Uri.parse("https://firebasestorage.googleapis.com/v0/b/affiliate-api-63d14.appspot.com/o/dematac.png?alt=media&token=99fff911-29f9-4fc9-af1c-662204d7d0e6");
        Uri uriSaving = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_saving);
        Uri uriInsurance = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.ic_insurance);
        Uri uriDemat = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.dematac);


    }

}
