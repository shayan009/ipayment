package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.MyCustomerDetail;

public interface MyCustomerItemClickListener {

    void sendSMS(MyCustomerDetail myCustomerDetail);

    void sendWhatsApp(MyCustomerDetail myCustomerDetail);

}
