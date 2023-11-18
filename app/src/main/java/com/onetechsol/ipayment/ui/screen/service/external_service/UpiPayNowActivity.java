package com.onetechsol.ipayment.ui.screen.service.external_service;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityUpiPayNowBinding;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;

public class UpiPayNowActivity extends BaseActivity<UpiPayNowViewModel, ActivityUpiPayNowBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intents = getIntent();
        String strUrl = intents.getStringExtra("serviceUrl");

        Log.d("SERVICE URL  : {}", strUrl);
        onShowLoading();
        initWebViewSettings(strUrl);

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (viewBinding().webViewKycUpload.canGoBack()) {
                    viewBinding().webViewKycUpload.goBack();
                } else {
                   finish();
                }
            }
        });

    }


    private void initWebViewSettings(String strUrl) {
        WebView webViewKycUpload = viewBinding().webViewKycUpload;

        WebSettings webSettings = webViewKycUpload.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webViewKycUpload.loadUrl(strUrl);


        webViewKycUpload.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }

            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });


        webViewKycUpload.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String strUrl) {
                if (strUrl.startsWith("upi:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(strUrl));
                    startActivity(intent);
                    return true;
                }
//                if (strUrl.startsWith("http:") || strUrl.startsWith("https:")) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(strUrl));
//                    startActivity(intent);
//                    return true;
//                }
                if (strUrl.startsWith("app:")) {
                    Intent goToHome = new Intent(UpiPayNowActivity.this, HomeActivity.class);
                    startActivity(goToHome);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                return false;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                onHideLoading();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Error:" + description, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public ActivityUpiPayNowBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityUpiPayNowBinding.inflate(inflater);
    }

    @Override
    public UpiPayNowViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(UpiPayNowViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_upi_pay_now;
    }

    @Override
    public void onRefresh() {

    }
}