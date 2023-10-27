package com.onetechsol.ipayment.ui.screen.insurance;

import static com.onetechsol.ipayment.utils.Utilities.getDrawableUrl;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.tabs.TabLayoutMediator;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityInsuranceBinding;
import com.onetechsol.ipayment.databinding.InsuranceClickListener;
import com.onetechsol.ipayment.pojo.ImageSliderItem;
import com.onetechsol.ipayment.pojo.InsuranceProduct;
import com.onetechsol.ipayment.ui.adapter.InsuranceProductAdapter;
import com.onetechsol.ipayment.ui.adapter.ScreenSlidePagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class InsuranceActivity extends BaseActivity<InsuranceViewModel, ActivityInsuranceBinding> implements InsuranceClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenSlidePagerAdapter viewPagerAdapter = new ScreenSlidePagerAdapter(this);
        InsuranceProductAdapter insuranceProductAdapter = new InsuranceProductAdapter();
        List<ImageSliderItem> imageSliderItems = new ArrayList<>();
        imageSliderItems.add(new ImageSliderItem(1, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        imageSliderItems.add(new ImageSliderItem(2, "https://fastly.picsum.photos/id/273/200/200.jpg?hmac=q1g4PnYVQHWkGBWnLmy3VaiQHuPGrZXnpZK986TwkFg"));
        viewPagerAdapter.setImageSliderItems(imageSliderItems);
        viewBinding().vpBannerInsurance.setAdapter(viewPagerAdapter);


        List<InsuranceProduct> insuranceProductList = new ArrayList<>();
        insuranceProductList.add(new InsuranceProduct(0, getDrawableUrl(R.drawable.ic_car), "CAR", true));
        insuranceProductList.add(new InsuranceProduct(1, getDrawableUrl(R.drawable.ic_vehicle), "Com. Vehicle", true));
        insuranceProductList.add(new InsuranceProduct(2, getDrawableUrl(R.drawable.ic_health), "Health", true));
        insuranceProductList.add(new InsuranceProduct(3, getDrawableUrl(R.drawable.ic_termlife), "Life/Term", true));
        insuranceProductList.add(new InsuranceProduct(5, getDrawableUrl(R.drawable.ic_investment), "Investment", true));
        insuranceProductList.add(new InsuranceProduct(6, getDrawableUrl(R.drawable.ic_investment), "Group", false));
        insuranceProductList.add(new InsuranceProduct(7, getDrawableUrl(R.drawable.ic_investment), "2 Wheeler", false));
        insuranceProductList.add(new InsuranceProduct(8, getDrawableUrl(R.drawable.ic_investment), "Corona", false));
        insuranceProductList.add(new InsuranceProduct(9, getDrawableUrl(R.drawable.ic_investment), "Other", false));
        new TabLayoutMediator(viewBinding().tabLayout, viewBinding().vpBannerInsurance,
                (tab, position) -> viewBinding().vpBannerInsurance.setCurrentItem(position, true)
        ).attach();

        viewBinding().rvInsuranceProducts.setLayoutManager(new GridLayoutManager(this, 3));
        viewBinding().setInsuranceProductAdapter(insuranceProductAdapter);
        insuranceProductAdapter.setInsuranceProductList(insuranceProductList);
        viewBinding().setInsuranceClickListener(this);

    }


    @Override
    public ActivityInsuranceBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityInsuranceBinding.inflate(inflater);
    }

    @Override
    public InsuranceViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(InsuranceViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_insurance;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }
}