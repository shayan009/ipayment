package com.onetechsol.ipayment.ui.screen.productdetails;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityProductDetailBinding;
import com.onetechsol.ipayment.databinding.ProductDetailClickListener;
import com.onetechsol.ipayment.pojo.ApprovalRatingType;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetailData;
import com.onetechsol.ipayment.pojo.GoalModel;
import com.onetechsol.ipayment.pojo.InstructionModel;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.pojo.TermsConditionsModel;
import com.onetechsol.ipayment.pojo.WhomToSellModel;
import com.onetechsol.ipayment.ui.adapter.ProductDetailViewPagerAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.customer.AddCustomerActivity;
import com.onetechsol.ipayment.ui.screen.share.ShareActivity;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class ProductDetailActivity extends BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding> implements ProductDetailClickListener {

    private String productId;
    private ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String colorSelected = "#6C6F74";
        String colorUnSelected = "#BAC0C6";

        productId = getIntent().getStringExtra("productId");

        viewBinding().setProductDetailClickListener(this);
        onShowLoading();
        compositeDisposable().add(viewModel().getAffiliateProductDetail(productId)
                .subscribe(getAffiliateProductDetail -> {
                    onHideLoading();
                    GetAffiliateProductDetailData product = getAffiliateProductDetail.data();
                    Log.d("Products.data", product.toString());

                    SellEarnType sellEarnType = SellEarnType.get(product.sellEarnId());
                    List<BenefitModel> benefitModels = new ArrayList<>();
                    List<GoalModel> goalModels = new ArrayList<>();


                    for (BenefitModel benefit : product.benefitModels()) {
                        benefitModels.add(new BenefitModel(benefit.id(), benefit.name(), "", ApiConstant.BASE_URL_IMAGE_SERVICE + benefit.image()));
                    }

                    if (sellEarnType == SellEarnType.DEMAT_ACC) {
                        goalModels = product.goalModels();
                    }

                    productModel = new ProductModel(product.id(), product.type(), sellEarnType, Double.parseDouble(product.minAccountBalance()),
                            Integer.parseInt(product.accountCreationTime()), "", product.name(), benefitModels, goalModels, product.joinFee(), product.annualFee(),
                            ApprovalRatingType.get(String.valueOf(product.approvalRate())).name(), product.maxEarn(), ApiConstant.BASE_URL_IMAGE_SERVICE + product.iconUrl(),
                            product.shortName(), "#EADDFF", "#46297B", "#46297B");
                    initAdapter(product);

                }, throwable -> {
                    onHideLoading();
                }));

    }


    void setButtonSelectedColor(int pos) {
        switch (pos) {
            case 0: {
                viewBinding().btDetails.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
                viewBinding().btEarnings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btShareContent.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                break;
            }

            case 1: {
                viewBinding().btDetails.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btEarnings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btShareContent.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
                break;
            }

            case 2: {
                viewBinding().btDetails.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btEarnings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.palete)));
                viewBinding().btShareContent.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                break;
            }

        }
    }

    private void initAdapter(GetAffiliateProductDetailData product) {


        ArrayList<BenefitModel> benefitModels = new ArrayList<>();
        ArrayList<WhomToSellModel> whomToSellModels = new ArrayList<>();
        ArrayList<InstructionModel> instructionModels = new ArrayList<>();
        ArrayList<TermsConditionsModel> termsConditionsModels = new ArrayList<>();
        ArrayList<ContentModel> contentImages = new ArrayList<>();
        ArrayList<ContentModel> contentVideos = new ArrayList<>();

        benefitModels = product.benefitModels();
        whomToSellModels = product.whomToSellModels();
        instructionModels = product.instructionModels();
        termsConditionsModels = product.termsConditionsModels();
        contentImages = product.images();
        contentVideos = product.videoList();

        ArrayList<ContentModel> imageContent = new ArrayList<>();
        ArrayList<ContentModel> videoContent = new ArrayList<>();

        if(contentImages.isEmpty()) {
            imageContent.add(new ContentModel("1",1,ApiConstant.BASE_URL_IMAGE_SERVICE+"logo/icon/1012-img-1693033017.jpg"));
            imageContent.add(new ContentModel("2",1,ApiConstant.BASE_URL_IMAGE_SERVICE+"logo/icon/1012-img-1693033017.jpg"));
            imageContent.add(new ContentModel("3",1,ApiConstant.BASE_URL_IMAGE_SERVICE+"logo/icon/1012-img-1693033017.jpg"));
        } else {
            for (ContentModel contentModel : contentImages) {
                imageContent.add(new ContentModel(contentModel.id(),1,ApiConstant.BASE_URL_IMAGE_SERVICE+contentModel.url()));
            }
        }


        if(contentVideos.isEmpty()) {
            videoContent.add(new ContentModel("1",2,"https://www.youtube.com/watch?v:O7ro3sh2rkw"));
            videoContent.add(new ContentModel("2",2,"https://www.youtube.com/watch?v:O7ro3sh2rkw"));
        } else {
            for (ContentModel contentModel : contentVideos) {
                videoContent.add(new ContentModel(contentModel.id(),2,ApiConstant.BASE_URL_IMAGE_SERVICE+contentModel.url()));
            }
        }

        contentImages = imageContent;
        contentVideos = videoContent;


        viewBinding().tvTitleProduct.setText(product.name());
        ProductDetailViewPagerAdapter productDetailViewPagerAdapter = new ProductDetailViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), benefitModels, whomToSellModels, instructionModels, termsConditionsModels, contentImages, contentVideos,product);
        viewBinding().setProductDetailViewPagerAdapter(productDetailViewPagerAdapter);
        viewBinding().vpProductItems.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                setButtonSelectedColor(position);
                super.onPageSelected(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        viewBinding().vpProductItems.setCurrentItem(0);
        viewBinding().setProductModel(productModel);
    }

    @Override
    public ActivityProductDetailBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityProductDetailBinding.inflate(inflater);
    }

    @Override
    public ProductDetailViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ProductDetailViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openDetails() {
        viewBinding().vpProductItems.setCurrentItem(0);
    }

    @Override
    public void openShareContent() {
        viewBinding().vpProductItems.setCurrentItem(1);
    }

    @Override
    public void openEarning() {
        viewBinding().vpProductItems.setCurrentItem(2);
    }

    @Override
    public void shareLink(ProductModel productModel) {

        Log.d("productModel", productModel.toString());
        Intent intent = new Intent(viewBinding().getRoot().getContext(), ShareActivity.class);
        intent.putExtra("productId", productModel.id());
        viewBinding().getRoot().getContext().startActivity(intent);

    }

    @Override
    public void addCustomer(ProductModel productModel) {

        Intent intent = new Intent(this, AddCustomerActivity.class);
        intent.putExtra("productModel", productModel);
        startActivity(intent);

    }
}