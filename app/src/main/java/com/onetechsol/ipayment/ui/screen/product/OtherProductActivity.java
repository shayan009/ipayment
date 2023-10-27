package com.onetechsol.ipayment.ui.screen.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityOtherProductBinding;
import com.onetechsol.ipayment.databinding.ProductClickListener;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.adapter.HomeSellEarnAdapter;
import com.onetechsol.ipayment.ui.adapter.OtherProductListAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.productdetails.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherProductActivity extends BaseActivity<ProductViewModel, ActivityOtherProductBinding> implements ProductClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<SellEarnModel> affiliateList = getIntent().getParcelableArrayListExtra("affiliateList");
        String sellEarnProductId = getIntent().getStringExtra("sellEarnProductId");

        viewBinding().tvTitleCC.setText(SellEarnType.get(sellEarnProductId).name());

        List<SellEarnModel> affiliateListNew = new ArrayList<>();
        affiliateList.forEach(sellEarnModel -> {
            if (!Objects.equals(sellEarnModel.id(), sellEarnProductId)) {
                affiliateListNew.add(sellEarnModel);
            }
        });
        HomeSellEarnAdapter homeSellEarnAdapter = new HomeSellEarnAdapter();
        //homeSellEarnAdapter.setItems(affiliateListNew);
        viewBinding().rvOtherService.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3, RecyclerView.VERTICAL, false));
        viewBinding().rvOtherService.setAdapter(homeSellEarnAdapter);
        viewBinding().setProductClickListener(this);
        onShowLoading();
        compositeDisposable().add(viewModel().getAffiliateServiceList(sellEarnProductId)
                .subscribe(affiliateProductModels -> {

                    OtherProductListAdapter otherProductListAdapter = new OtherProductListAdapter();
                    otherProductListAdapter.setItems(affiliateProductModels);
                    otherProductListAdapter.setCreditCardClickListener(this);
                    viewBinding().setOtherProductListAdapter(otherProductListAdapter);
                    onHideLoading();
                }, throwable -> {
                    onHideLoading();
                    Log.e("throwable ->", throwable.toString());
                }));


    }

    @Override
    public ActivityOtherProductBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityOtherProductBinding.inflate(inflater);
    }

    @Override
    public ProductViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_other_product;
    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void onProductSelected(String name) {

    }

    @Override
    public void onProductSelected(ProductModel productModel) {

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId", productModel.id());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

    }
}