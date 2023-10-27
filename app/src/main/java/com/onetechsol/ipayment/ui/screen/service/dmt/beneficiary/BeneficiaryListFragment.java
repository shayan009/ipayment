package com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.onetechsol.ipayment.databinding.FragmentBeneficiaryListBinding;
import com.onetechsol.ipayment.pojo.BeneficiaryModel;
import com.onetechsol.ipayment.ui.adapter.BeneficiaryAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTViewModel;
import com.onetechsol.ipayment.ui.screen.service.dmt.money_transfer.DMTTransferMoneyBottomSheet;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class BeneficiaryListFragment extends BaseFragment<DMTViewModel, FragmentBeneficiaryListBinding> {


    private String remMobile;
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO API CALL
            getAllBeneficiary();
        }
    };

    public BeneficiaryListFragment() {
        // Required empty public constructor
    }

    public static BeneficiaryListFragment newInstance(String remMobile) {
        BeneficiaryListFragment fragment = new BeneficiaryListFragment();
        Bundle args = new Bundle();
        args.putString("remMobile", remMobile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(mMessageReceiver, new IntentFilter("message_addbene_intent"));


        if (getArguments() != null && StringUtils.isNoneEmpty(getArguments().getString("remMobile"))) {
            remMobile = getArguments().getString("remMobile");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllBeneficiary();
    }

    private void getAllBeneficiary() {
        compositeDisposable().add(viewModel().getBeneficiaryList(remMobile)
                .subscribe(getBeneficiaryResponse -> {

                    List<BeneficiaryModel> beneficiaryModels = getBeneficiaryResponse.data().beneficiaryList();
                    Log.d("beneficiaryModels", beneficiaryModels.toString());

                    BeneficiaryAdapter beneficiaryAdapter = new BeneficiaryAdapter();
                    beneficiaryAdapter.setBeneficiaryModelList(beneficiaryModels);
                    beneficiaryAdapter.setCallback(beneficiaryModel -> {

                        DMTTransferMoneyBottomSheet dmtTransferMoneyBottomSheet = new DMTTransferMoneyBottomSheet();
                        dmtTransferMoneyBottomSheet.setBeneficiaryModel(beneficiaryModel);
                        dmtTransferMoneyBottomSheet.setRemMob(remMobile);
                        dmtTransferMoneyBottomSheet.show(getParentFragmentManager(), DMTTransferMoneyBottomSheet.class.getName());

                    });
                    viewBinding().setBeneficiaryAdapter(beneficiaryAdapter);


                }, throwable -> {

                }));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_beneficiary_list;
    }


    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }

    @Override
    public FragmentBeneficiaryListBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }
}