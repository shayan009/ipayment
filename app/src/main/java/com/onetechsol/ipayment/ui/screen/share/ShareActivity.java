package com.onetechsol.ipayment.ui.screen.share;

import static com.onetechsol.ipayment.app.MainApp.getContext;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityShareBinding;
import com.onetechsol.ipayment.databinding.ShareClickListener;
import com.onetechsol.ipayment.pojo.BenefitModel;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetailData;
import com.onetechsol.ipayment.ui.adapter.BenefitAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShareActivity extends BaseActivity<ShareViewModel, ActivityShareBinding> implements ShareClickListener {


    public static File dir = new File(new File(Environment.getExternalStorageDirectory(), "ipayment"), "affiliate");
    private Disposable subscribe;
    private GetAffiliateProductDetailData product;
    private Disposable subscribe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String productId = getIntent().getStringExtra("productId");

        BenefitAdapter benefitAdapter = new BenefitAdapter();
        viewBinding().setBenefitAdapter(benefitAdapter);
        viewBinding().setShareClickListener(this);
        onShowLoading();
        compositeDisposable().add(viewModel().getAffiliateProductDetail(productId)
                .subscribe(productGraphQLResponse -> {

                    product = productGraphQLResponse.data();

                    viewBinding().tvTitleShare.setText(product.name());


                    if (product.images().size() > 0)
                        Glide.with(viewBinding().getRoot()).load(ApiConstant.BASE_URL_IMAGE_SERVICE + product.images().get(0).url()).apply(RequestOptions.fitCenterTransform().error(R.drawable.hdfc_content)).into(viewBinding().ivShareImg);
                    else
                        Glide.with(viewBinding().getRoot()).load("https://fastly.picsum.photos/id/1/5000/3333.jpg?hmac=Asv2DU3rA_5D1xSe22xZK47WEAN0wjWeFOhzd13ujW4").apply(RequestOptions.fitCenterTransform().error(R.drawable.hdfc_content)).into(viewBinding().ivShareImg);


                    List<BenefitModel> benefitModelList = product.benefitModels();

                    if (benefitModelList.size() > 0)
                        benefitAdapter.setBenefitList(benefitModelList);
                    viewBinding().setProduct(product);
                    onHideLoading();
                    onShareLink(product);

                }, throwable -> {
                    onHideLoading();
                    showAlertDialog("Error Occured", throwable.getMessage(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                            }).show();
                }));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscribe != null) {
            subscribe.dispose();
        }
    }

    @Override
    public ActivityShareBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityShareBinding.inflate(inflater);
    }

    @Override
    public ShareViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(ShareViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_share;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void onShareLink(GetAffiliateProductDetailData getAffiliateProductDetailData) {

        try {



            /*if(product.images() == null || product.images().size() == 0) {
                showAlertDialog("Share Alert","Share is not available as there is no image to share",true)
                        .setPositiveButton("OK",(dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            getOnBackPressedDispatcher().onBackPressed();
                        }).show();
            } else {*/
            String contentImage = product.iconUrl();
            if (product.images() != null && product.images().size() > 0) {
                contentImage = ApiConstant.BASE_URL_IMAGE_SERVICE + getAffiliateProductDetailData.images().get(0).url();
            }

            StringBuilder out = new StringBuilder();

            if (getAffiliateProductDetailData.benefitModels() != null && getAffiliateProductDetailData.benefitModels().size() > 0) {

                List<String> collect = getAffiliateProductDetailData.benefitModels().stream().map(m -> m.name()).collect(Collectors.toList());

                for (String o : collect) {
                    out.append(o);
                    out.append("\n");
                }

            }


            String input = "https://partner.ipayments.in/share-link?service=" + product.id() + "&company=" + prefManager.getUserSession().companyVersion() + "&referral=" + prefManager.getUserSession().userName();
            //String cipherText = new AESUtil().encrypt(input, "AESC123");

            //Log.d("cipherText ::::::", cipherText);
            // Log.d("actualText ::::::", new AESUtil().decrypt(cipherText,"AESC123"));

            //String newURlLink = product.getLink().trim() +"/"+cipherText;

            out.append(input);
            out.append("\n");


            if (product.images() != null && product.images().size() > 0) {

                io.reactivex.disposables.Disposable subscribe1 = Observable.just(contentImage)
                        .subscribeOn(Schedulers.io())
                        .map(s -> Glide
                                .with(getContext())
                                .asBitmap()
                                .load(s)
                                .submit().get())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {

                            Intent share = new Intent(Intent.ACTION_SEND);

                            String s = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, product.id(), product.id());

                            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(s));
                            share.putExtra(Intent.EXTRA_TEXT, out.toString());
                            share.setType("text/*");
                            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            startActivity(Intent.createChooser(share, "Share"));

                        }, throwable -> {

                            //Toast.makeText(this, throwable.getMessage(), //Toast.LENGTH_SHORT).show();

                        });


            } else {
                Intent share = new Intent(Intent.ACTION_SEND);

                share.putExtra(Intent.EXTRA_TEXT, out.toString());
                share.setType("text/*");
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(share, "Share"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
                return;
            }
            createDir();
        }
    }

    public void createDir() {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


}