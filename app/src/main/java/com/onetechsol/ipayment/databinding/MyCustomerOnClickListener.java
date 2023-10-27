package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.MyCustomer;

public interface MyCustomerOnClickListener extends CommonOperation {


    void selectedPlan(MyCustomer myCustomerDetails);

    void shareLink(int type, String customerName, String productName, String url, String mobileNumber);
}
