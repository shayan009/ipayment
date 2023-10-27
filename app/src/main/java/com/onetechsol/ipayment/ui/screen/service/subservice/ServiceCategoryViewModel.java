package com.onetechsol.ipayment.ui.screen.service.subservice;

import com.onetechsol.ipayment.pojo.CategoryWiseServiceRequest;
import com.onetechsol.ipayment.pojo.CategoryWiseServiceResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ServiceCategoryViewModel extends BaseViewModel {

    public Observable<CategoryWiseServiceResponse> getCategoryWiseServiceList(String bearerAuth, String userName, String categoryId, String categoryName) {

        CategoryWiseServiceRequest serviceListRequest = new CategoryWiseServiceRequest(ApiConstant.BASIC_VERSION, categoryId, categoryName);

        return iModelRepository().getCategoryWiseServiceList(serviceListRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
