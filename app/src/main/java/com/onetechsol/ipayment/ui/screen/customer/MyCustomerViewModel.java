package com.onetechsol.ipayment.ui.screen.customer;

import com.onetechsol.ipayment.pojo.GetCustomerRequest;
import com.onetechsol.ipayment.pojo.MyCustomer;
import com.onetechsol.ipayment.pojo.MyCustomerDetail;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MyCustomerViewModel extends BaseViewModel {

    public Observable<List<MyCustomer>> getCustomerList(String sellEarnId, String status) {

        GetCustomerRequest addCustomerRequest = new GetCustomerRequest(sellEarnId, "1");
        return iModelRepository().customerListAffiliate(addCustomerRequest)
                .map(p -> {

                    List<MyCustomerDetail> customerList = p.getGetCustomerResponseData().getCustomerList();
                    SellEarnType[] values = SellEarnType.values();

                    List<MyCustomer> myCustomers = new ArrayList<>();

                    for (int i = 0; i < values.length; i++) {

                        List<MyCustomerDetail> myCustomerDetails = new ArrayList<>();

                        for (int j = 0; j < customerList.size(); j++) {

                            if (customerList.get(j).sellEarnId().equals(values[i].sellEarnType())) {
                                myCustomerDetails.add(customerList.get(j));
                            }
                        }

                        if (myCustomerDetails.size() > 0) {
                            myCustomers.add(new MyCustomer(values[i].name(), myCustomerDetails, false));
                        }
                    }

                    List<MyCustomer> modifiedMyCustomers = new ArrayList<>();

                    for (int i = 0; i < myCustomers.size(); i++) {

                        MyCustomer myCustomer = myCustomers.get(i);
                        myCustomer.setSelected(i == 0);
                        modifiedMyCustomers.add(myCustomer);

                    }
                    return modifiedMyCustomers;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
