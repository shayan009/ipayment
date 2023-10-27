package com.onetechsol.ipayment.ui.screen.customer;

import com.onetechsol.ipayment.pojo.AddCustomerRequest;
import com.onetechsol.ipayment.pojo.AddCustomerResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddCustomerViewModel extends BaseViewModel {


    public Observable<AddCustomerResponse> addCustomerAffiliate(String name, String mobile, String email, String pin, String service, String age, String employmentType, String incomeRange, boolean hasCC) {

        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(name, mobile, email, pin, service, employmentType, age, incomeRange, hasCC);
        return iModelRepository().addCustomerAffiliate(addCustomerRequest)
                .observeOn(AndroidSchedulers.mainThread());
    }


}
