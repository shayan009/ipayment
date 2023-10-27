package com.onetechsol.ipayment.ui.screen.service.insurance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityPremiumInsuranceBinding;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class BuyInsuranceActivity extends BaseActivity<BuyInsuranceViewModel, ActivityPremiumInsuranceBinding> implements BuyInsuranceClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onShowLoading();

        WebSettings webSettings = viewBinding().customWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        viewBinding().customWebview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        compositeDisposable().add(viewModel().getInsuranceDetail()
                .subscribe(buyInsuranceDetailResponse -> {

                    if (buyInsuranceDetailResponse.status().equals("1") && buyInsuranceDetailResponse.txnStatus().equals("2")) {

                        String reloadUrl = buyInsuranceDetailResponse.reloadUrl();

                        viewBinding().customWebview.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                view.loadUrl(reloadUrl);
                                return true;
                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                onHideLoading();
                            }

                            @Override
                            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                //Toast.makeText(getApplicationContext(), "Error:" + error.getDescription(), //Toast.LENGTH_SHORT).show();
                            }
                        });

                        viewBinding().customWebview.loadUrl(reloadUrl);

                    } else {
                        onHideLoading();
                        showAlertDialog("Buy Insurance", buyInsuranceDetailResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                }).show();
                    }


                }, throwable -> {
                    onHideLoading();
                }));
    }


    @Override
    public ActivityPremiumInsuranceBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityPremiumInsuranceBinding.inflate(inflater);
    }

    @Override
    public BuyInsuranceViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(BuyInsuranceViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_premium_insurance;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

}