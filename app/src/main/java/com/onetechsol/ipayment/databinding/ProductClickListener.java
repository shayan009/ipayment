package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ProductModel;

public interface ProductClickListener {

    void goBack();

    void onProductSelected(String name);

    void onProductSelected(ProductModel productModel);
}
