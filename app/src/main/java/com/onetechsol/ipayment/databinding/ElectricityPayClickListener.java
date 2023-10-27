package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.OpCircleItemDto;

public interface ElectricityPayClickListener {

    void goBack();

    void openOperator();

    void selectOpCircle(OpCircleItemDto opCircleItemDto);

    void fetchBill(String txnNumber, String txnCustomerNo, String txnAd1, String txnAd2, String txnAd3, String txnAd4);
}
