package com.onetechsol.ipayment.ui.screen.customer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentMyCustomerBinding;
import com.onetechsol.ipayment.databinding.MyCustomerOnClickListener;
import com.onetechsol.ipayment.pojo.MyCustomer;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.CustomerFilterListAdapter;
import com.onetechsol.ipayment.ui.adapter.CustomerListAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;

import java.util.List;

public class MyCustomerFragment extends BaseFragment<MyCustomerViewModel, FragmentMyCustomerBinding> implements MyCustomerOnClickListener {


    private CustomerListAdapter customerListAdapter;
    private CustomerFilterListAdapter customerFilterListAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {
            onShowLoading();
            compositeDisposable().add(viewModel().getCustomerList("", "")
                    .subscribe(myCustomers -> {
                        onHideLoading();
                        Log.d("MyCustomer", myCustomers.toString());
                        customerFilterListAdapter = new CustomerFilterListAdapter();
                        customerFilterListAdapter.setItems(myCustomers);
                        customerFilterListAdapter.setMyCustomerOnClickListener(this);
                        viewBinding().setCustomerFilterListAdapter(customerFilterListAdapter);

                        customerListAdapter = new CustomerListAdapter();
                        customerListAdapter.setMyCustomerOnClickListener(this);

                        if (myCustomers.size() > 0)
                            customerListAdapter.setItems(myCustomers.get(0).myCustomerDetailList());
                        viewBinding().setCustomerListAdapter(customerListAdapter);

                    }, th -> {
                        onHideLoading();
                    }));
        }
    }

    @Override
    public MyCustomerViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(MyCustomerViewModel.class);
    }

    @Override
    public FragmentMyCustomerBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_my_customer;
    }

    @Override
    public void goBack() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void selectedPlan(MyCustomer myCustomerDetails) {

        List<MyCustomer> items = customerFilterListAdapter.items();

        items.forEach(myCustomer -> {
            myCustomer.setSelected(myCustomer.serviceName().equals(myCustomerDetails.serviceName()));
        });
        Log.d("MyCustomer", items.toString());

        customerFilterListAdapter = new CustomerFilterListAdapter();
        customerFilterListAdapter.setItems(items);
        customerFilterListAdapter.setMyCustomerOnClickListener(this);
        viewBinding().setCustomerFilterListAdapter(customerFilterListAdapter);

        if (customerListAdapter != null) {
            customerListAdapter.setItems(myCustomerDetails.myCustomerDetailList());
        }

    }

    @Override
    public void shareLink(int type, String customerName, String productName, String url, String mobileNumber) {
        String s = prefManager.getUserSession().loginName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hi ").append(customerName).append("\n");
        stringBuilder.append("Your ").append(productName).append(" application is incomplete. Please use the below link to complete the application.\n");
        stringBuilder.append(url).append("\n");
        stringBuilder.append("IF you have any questions or need assistance , please do not hesitate to reach out.\n");
        stringBuilder.append("Your Financial Adviser.\n");
        stringBuilder.append(s);

        if (type == 1) {
          /*  Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
            startActivity(whatsappIntent);*/


            boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
            if (isWhatsappInstalled) {

                openWhatsApp(mobileNumber, stringBuilder.toString());

            } else {
                //Toast.makeText(requireContext(), "WhatsApp not Installed", //Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("market://details?id=com.whatsapp");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
            }
        }

        if (type == 0) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + mobileNumber));  // This ensures only SMS apps respond
            intent.putExtra("sms_body", stringBuilder.toString());
            startActivity(intent);
        }

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