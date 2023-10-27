package com.onetechsol.ipayment.ui.screen.addFund;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FragmentUpiPayModeBinding;
import com.onetechsol.ipayment.databinding.UpiPayClickListener;
import com.onetechsol.ipayment.pojo.FundReqAccount;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;


public class UpiPayModeFragment extends BaseFragment<BalanceRequestViewModel, FragmentUpiPayModeBinding> implements UpiPayClickListener {

    private static final String ARG_PARAM1 = "fundReqAccount";

    FundReqAccount fundReqAccount;

    public UpiPayModeFragment() {
    }

    public static UpiPayModeFragment newInstance(FundReqAccount fundReqAccount) {
        UpiPayModeFragment fragment = new UpiPayModeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, fundReqAccount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fundReqAccount = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_upi_pay_mode;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("fundReqAccount", fundReqAccount.toString());

        viewBinding().setUpiPayClickListener(this);

    }

    @Override
    public BalanceRequestViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(BalanceRequestViewModel.class);
    }

    @Override
    public FragmentUpiPayModeBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void fundRequest(String upiId, String amount, String tpin) {

        onShowLoading();
        compositeDisposable().add(viewModel().fundRequest(amount, "1", fundReqAccount.id(), upiId, "1", tpin, "")
                .subscribe(fundRequestResponse -> {

                    onHideLoading();
                    showAlertDialog("Fund Request", fundRequestResponse.message(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                                if ("1".equals(fundRequestResponse.status()) && "1".equals(fundRequestResponse.txnStatus())) {
                                    Intent intent = new Intent("fundRequest");
                                    intent.putExtra("fundStatus", "1");
                                    LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
                                }

                            }).show();

                }, throwable -> {

                }));

    }

    @Override
    public void onRefresh() {

    }
}