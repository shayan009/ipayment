package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ProductModel;

public interface ProductItemClickListener {

    void onClickItem(ProductModel productModel);

    void shareLink(ProductModel productModel);

    void addCustomer(ProductModel productModel);
}
