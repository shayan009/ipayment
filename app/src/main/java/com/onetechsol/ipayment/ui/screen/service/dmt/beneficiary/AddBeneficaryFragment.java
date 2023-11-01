package com.onetechsol.ipayment.ui.screen.service.dmt.beneficiary;

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
import com.onetechsol.ipayment.databinding.AddBeneficiaryClickListener;
import com.onetechsol.ipayment.databinding.FragmentAddBenificaryBinding;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.pojo.BeneficiaryBankModel;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.ui.basefiles.BaseFragment;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTViewModel;
import com.onetechsol.ipayment.widgets.CustomSideSheetSpinner;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class AddBeneficaryFragment extends BaseFragment<DMTViewModel, FragmentAddBenificaryBinding> implements AddBeneficiaryClickListener, SideSheetDataOnClickListener, CustomSideSheetSpinner.SideSpinnerCallback {


    private String remMobile;
    private CustomSideSheetSpinner sideSheetDialog;

    public AddBeneficaryFragment() {

    }

    public static AddBeneficaryFragment newInstance(String remMobile) {
        AddBeneficaryFragment fragment = new AddBeneficaryFragment();
        Bundle args = new Bundle();
        args.putString("remMobile", remMobile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void openSpinner() {
        if (sideSheetDialog != null)
            sideSheetDialog.show();
    }


    private void initSideSheet() {
        sideSheetDialog = new CustomSideSheetSpinner(requireActivity());
        ((CustomSideSheetSpinner) sideSheetDialog).setSideSheetDataOnClickListener(this);
        sideSheetDialog.setSideSpinnerCallback(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_add_benificary;
    }


    @Override
    public DMTViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(DMTViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && StringUtils.isNoneEmpty(getArguments().getString("remMobile"))) {
            remMobile = getArguments().getString("remMobile");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinding().setAddBeneficiaryClickListener(this);

        initSideSheet();
        search("s");

    }

    @Override
    public FragmentAddBenificaryBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void addBeneficiary(String bankName, String ifsc, String account, String name) {

        compositeDisposable().add(viewModel().registerBeneficiary(remMobile, name, bankName, ifsc, account)
                .subscribe(addBeneficiaryResponse -> {

                    if (addBeneficiaryResponse.status().equals("1")) {

                        viewBinding().etBenAdd.setText("");
                        viewBinding().etBenBank.setText("");
                        viewBinding().etBenIfc.setText("");
                        viewBinding().etBenName.setText("");

                        Intent intent = new Intent("message_addbene_intent");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(intent);

                    }

                }, throwable -> {

                }));

    }

    @Override
    public void selectSideSheetItem(SideSheetItem sideSheetItem) {
        viewBinding().etBenBank.setText(sideSheetItem.name());
        if (sideSheetDialog != null)
            sideSheetDialog.dismiss();

    }

    @Override
    public void onItemSearched(String search) {

        search(search);

    }

    private void search(String search) {
        compositeDisposable().add(viewModel().getBeneficiaryBankList(search)
                .subscribe(beneficiaryBankModels -> {

                    List<SideSheetItem> sideSheetItems = new ArrayList<>();

                    for (int i = 1; i < beneficiaryBankModels.size(); i++) {
                        BeneficiaryBankModel beneficiaryBankModel = beneficiaryBankModels.get(i);
                        sideSheetItems.add(new SideSheetItem(beneficiaryBankModel.label(), beneficiaryBankModel.value(), true, (i - 1), false));
                    }

                    if (sideSheetDialog != null)
                        sideSheetDialog.setSideSheetItems(sideSheetItems);


                }, throwable -> {

                }));
    }
}