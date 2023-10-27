package com.onetechsol.ipayment.ui.screen.home;

import com.onetechsol.ipayment.pojo.CustomerUpgradeInfoResponse;
import com.onetechsol.ipayment.pojo.ServiceList;
import com.onetechsol.ipayment.pojo.ServiceListRequest;
import com.onetechsol.ipayment.pojo.ServiceListResponse;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.pojo.ServiceType;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeActivityViewModel extends BaseViewModel {

    public Observable<ServiceListResponse> getServiceList(int position) {

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

                                if (i == position)
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

    public Observable<CustomerUpgradeInfoResponse> customerToMerchantUpgradeInfo() {
        return iModelRepository().customerToMerchantUpgradeInfo()
                .observeOn(AndroidSchedulers.mainThread());
    }


}
