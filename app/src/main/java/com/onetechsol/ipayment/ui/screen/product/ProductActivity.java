package com.onetechsol.ipayment.ui.screen.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityProductBinding;
import com.onetechsol.ipayment.databinding.ProductClickListener;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.adapter.ProductFilterListAdapter;
import com.onetechsol.ipayment.ui.adapter.ProductListAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailActivity;

public class ProductActivity extends BaseActivity<ProductViewModel, ActivityProductBinding> implements ProductClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String sellEarnProductId = getIntent().getStringExtra("sellEarnProductId");
        viewBinding().tvTitleCC.setText(SellEarnType.get(sellEarnProductId).name());

        onShowLoading();

        compositeDisposable().add(viewModel().getAffiliateServiceList(sellEarnProductId)
                .subscribe(productModelList -> {

                    ProductListAdapter productListAdapter = new ProductListAdapter();
                    ProductFilterListAdapter productFilterListAdapter = new ProductFilterListAdapter();

                    productListAdapter.setCreditCardProductList(productModelList);
                    productListAdapter.setCreditCardClickListener(this);

                    productFilterListAdapter.setProductNames(productModelList);
                    productFilterListAdapter.setProductClickListener(this);

                    viewBinding().setProductListAdapter(productListAdapter);
                    viewBinding().setProductFilterListAdapter(productFilterListAdapter);

                    onHideLoading();
                }, throwable -> {
                    Log.e("throwable ->", throwable.toString());
                }));
        viewBinding().setProductClickListener(this);

    }

    @Override
    public ActivityProductBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityProductBinding.inflate(inflater);
    }

    @Override
    public ProductViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_product;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onProductSelected(String product) {

        /*if (productListAdapter != null && productListAdapter.creditCardProductList() != null && productListAdapter.creditCardProductList().size() > 0) {

            List<ProductModel> productModels = productListAdapter.creditCardProductList();
            List<ProductModel> collect = productModels.stream().filter(f -> f.shortName().equals(product)).collect(Collectors.toList());
            productListAdapter.setCreditCardProductList(collect);

        }*/

    }

    @Override
    public void onProductSelected(ProductModel productModel) {

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId", productModel.id());
        startActivity(intent);

    }
}