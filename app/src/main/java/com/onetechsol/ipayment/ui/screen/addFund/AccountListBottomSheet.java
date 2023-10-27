package com.onetechsol.ipayment.ui.screen.addFund;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AccountListBottomsheetBinding;
import com.onetechsol.ipayment.databinding.AccountListSheetOnClickListener;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.ui.adapter.OperatorCircleAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.util.List;

public class AccountListBottomSheet extends CurvedBottomSheetDialogFragment<AccountListBottomsheetBinding, BalanceRequestViewModel> implements AccountListSheetOnClickListener, OperatorCircleAdapter.AdapterCallback {


    private AdapterCallback adapterCallback;

    private List<OpCircleItemDto> opCircleItemDtoList;

    public List<OpCircleItemDto> opCircleItemDtoList() {
        return opCircleItemDtoList;
    }

    public AccountListBottomSheet setOpCircleItemDtoList(List<OpCircleItemDto> opCircleItemDtoList) {
        this.opCircleItemDtoList = opCircleItemDtoList;
        return this;
    }

    public AdapterCallback adapterCallback() {
        return adapterCallback;
    }

    public AccountListBottomSheet setAdapterCallback(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.account_list_bottomsheet;
    }

    @Override
    public BalanceRequestViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(BalanceRequestViewModel.class);
    }

    @Override
    public AccountListBottomsheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        OperatorCircleAdapter operatorCircleAdapter = new OperatorCircleAdapter();
        operatorCircleAdapter.setOpCircleItemDtoList(opCircleItemDtoList);
        operatorCircleAdapter.setCallback(this);
        viewBinding().setOperatorCircleAdapter(operatorCircleAdapter);
        viewBinding().setAccountListSheetOnClickListener(this);
        viewBinding().executePendingBindings();
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

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }

    @Override
    public void search(String text) {

    }

    @Override
    public void closeSheet() {
        dismiss();
    }

    @Override
    public void onItemClicked(OpCircleItemDto opCircleItemDto) {
        if (adapterCallback != null)
            adapterCallback.selectAccount(opCircleItemDto);
    }

    public interface AdapterCallback {
        void selectAccount(OpCircleItemDto opCircleItemDto);
    }
}
