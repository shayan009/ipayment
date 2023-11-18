package com.onetechsol.ipayment.ui.screen.sellearn;


import static com.onetechsol.ipayment.utils.ApiConstant.BASE_URL_IMAGE_SERVICE;

import com.onetechsol.ipayment.pojo.AffiliateModel;
import com.onetechsol.ipayment.pojo.DepartmentModel;
import com.onetechsol.ipayment.pojo.GetDepartmentListResponse;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.pojo.ServiceType;
import com.onetechsol.ipayment.ui.adapter.AffiliateDepartmentAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SellEarnViewModel extends BaseViewModel {


    public Observable<GetDepartmentListResponse> getDepartmentList(int position) {


        return iModelRepository().getDepartmentList()
                .map(m -> {

                    String colorSelected = "#292929";
                    String colorUnSelected = "#BAC0C6";

                    List<DepartmentModel> departmentModels = m.data().departmentList();

                    ArrayList<ServiceModel> serviceModelList = new ArrayList<>();

                    for (int i = 0, departmentModelsSize = departmentModels.size(); i < departmentModelsSize; i++) {
                        DepartmentModel departmentModel = departmentModels.get(i);

                        List<AffiliateModel> affiliateServiceList = departmentModel.affiliateModels();
                        List<SellEarnModel> sellEarnModelList = new ArrayList<>();

                        affiliateServiceList.forEach(sellEarn -> {
                            sellEarnModelList.add(new SellEarnModel(sellEarn.id(), sellEarn.label(), 0, BASE_URL_IMAGE_SERVICE + sellEarn.img(), 12, SellEarnType.get(sellEarn.id())));
                        });

                        sellEarnModelList.sort(Comparator.comparing(SellEarnModel::id));

                        departmentModel.setSellEarnModels(sellEarnModelList);


                        if (i == position)
                            serviceModelList.add(new ServiceModel(i, departmentModel.id(), departmentModel.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + departmentModel.img(), SellEarnType.get(departmentModel.label().trim()), "", true, colorSelected,departmentModel.getSellEarnModels()));
                        else
                            serviceModelList.add(new ServiceModel(i, departmentModel.id(), departmentModel.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + departmentModel.img(), null, "", false, colorUnSelected,departmentModel.getSellEarnModels()));

                    }



                    m.setDepartmentList(serviceModelList);

                    return m;

                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
