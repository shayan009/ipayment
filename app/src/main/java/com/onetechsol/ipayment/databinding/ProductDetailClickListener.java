package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ProductModel;

public interface ProductDetailClickListener {

    void goBack();

    void openDetails();

    void openShareContent();

    void openEarning();

    void shareLink(ProductModel productModel);

    void addCustomer(ProductModel productModel);

}
