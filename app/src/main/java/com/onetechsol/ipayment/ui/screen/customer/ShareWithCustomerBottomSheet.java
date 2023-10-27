package com.onetechsol.ipayment.ui.screen.customer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ShareUrlSheetOnClickListener;
import com.onetechsol.ipayment.databinding.ShareWithCustomerSheetBinding;
import com.onetechsol.ipayment.pojo.ShareLinkDirectionModel;
import com.onetechsol.ipayment.ui.adapter.ShareLinkCustomerAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ShareWithCustomerBottomSheet extends CurvedBottomSheetDialogFragment<ShareWithCustomerSheetBinding, AddCustomerViewModel> implements ShareUrlSheetOnClickListener {


    private String productName;
    private String name;
    private String url;
    private String mobileNumber;

    @Override
    public int getLayoutRes() {
        return R.layout.share_with_customer_sheet;
    }

    @Override
    public AddCustomerViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AddCustomerViewModel.class);
    }

    @Override
    public ShareWithCustomerSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewBinding().setShareUrlSheetOnClickListener(this);
        viewBinding().executePendingBindings();
        viewBinding().mrbWhatsApp.setChecked(true);
        viewBinding().mrbWhatsApp.addOnCheckedStateChangedListener((checkBox, state) -> {
            if (checkBox.isChecked()) {
                viewBinding().mrbSMS.setChecked(false);
            }
        });

        viewBinding().mrbSMS.addOnCheckedStateChangedListener((checkBox, state) -> {
            if (checkBox.isChecked()) {
                viewBinding().mrbWhatsApp.setChecked(false);
            }
        });


        List<ShareLinkDirectionModel> stringList = new ArrayList<>();
        stringList.add(new ShareLinkDirectionModel("0", "This link is for above customer only, do not share with anyone else."));
        stringList.add(new ShareLinkDirectionModel("1", "Ask customer to save your number for clikable product link on WhatsApp."));
        stringList.add(new ShareLinkDirectionModel("2", "Your default SMS or WhatsApp app will open tp send the link"));
        stringList.add(new ShareLinkDirectionModel("3", "Your SMS is chargable by your phone operator"));
        ShareLinkCustomerAdapter shareLinkCustomerAdapter = new ShareLinkCustomerAdapter();
        shareLinkCustomerAdapter.setItems(stringList);
        viewBinding().setShareLinkCustomerAdapter(shareLinkCustomerAdapter);
    }


    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = requireActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    public String productName() {
        return productName;
    }

    public String mobileNumber() {
        return mobileNumber;
    }

    public ShareWithCustomerBottomSheet setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public ShareWithCustomerBottomSheet setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String name() {
        return name;
    }

    public ShareWithCustomerBottomSheet setName(String name) {
        this.name = name;
        return this;
    }

    public String url() {
        return url;
    }

    public ShareWithCustomerBottomSheet setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }

    @Override
    public void closeSheet() {
        dismiss();
    }

    @Override
    public void shareLink() {

        String s = prefManager().getUserSession().loginName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hey ").append(name).append("\n");
        stringBuilder.append("I am thrilled that you are interested in ").append(productName).append(". To start your application, click on the link below. In case the link is not clickable, please save my number in your contacts list first.\n\n");
        stringBuilder.append(url).append("\n\n");
        stringBuilder.append("Feel free to call me if you need any help!\n");
        stringBuilder.append("Your Financial Adviser,\n");
        stringBuilder.append(s);

        if (viewBinding().mrbWhatsApp.isChecked()) {
          /*  Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
            startActivity(whatsappIntent);*/


            boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
            if (isWhatsappInstalled) {

                openWhatsApp(mobileNumber, stringBuilder.toString());
              /*  Uri uri = Uri.parse("smsto:" +mobileNumber);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                sendIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
                dismiss();
                requireActivity().finish();*/

            } else {
                //Toast.makeText(requireContext(), "WhatsApp not Installed", //Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("market://details?id=com.whatsapp");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
                dismiss();
                requireActivity().finish();
            }
        }

        if (viewBinding().mrbSMS.isChecked()) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + mobileNumber));  // This ensures only SMS apps respond
            intent.putExtra("sms_body", stringBuilder.toString());
            startActivity(intent);
            dismiss();
            requireActivity().finish();
        }
    }

    private void openWhatsApp(String smsNumber, String text) {
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            //Toast.makeText(getContext(), "Error/n" + e.toString(), //Toast.LENGTH_SHORT).show();
        }

    }
}
