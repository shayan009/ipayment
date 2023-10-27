package com.onetechsol.ipayment.ui.screen.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AddCustSelectionOnClickListener;
import com.onetechsol.ipayment.databinding.AddCustSleectionSheetBinding;
import com.onetechsol.ipayment.pojo.AddCustSelectionItem;
import com.onetechsol.ipayment.pojo.AddCustSelectionItemModel;
import com.onetechsol.ipayment.ui.adapter.AddCustSelectionAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

public class AddCustomerSelectionBottomSheet extends CurvedBottomSheetDialogFragment<AddCustSleectionSheetBinding, AddCustomerViewModel> implements AddCustSelectionOnClickListener, AddCustSelectionAdapter.AdapterCallback {


    private AddCustSelectionItemModel addCustSelectionItemModel;
    private AdapterCallback adapterCallback;

    public AddCustSelectionItemModel addCustSelectionItemModel() {
        return addCustSelectionItemModel;
    }

    public AddCustomerSelectionBottomSheet setAddCustSelectionItemModel(AddCustSelectionItemModel addCustSelectionItemModel) {
        this.addCustSelectionItemModel = addCustSelectionItemModel;
        return this;
    }

    public AdapterCallback adapterCallback() {
        return adapterCallback;
    }

    public AddCustomerSelectionBottomSheet setAdapterCallback(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.add_cust_sleection_sheet;
    }

    @Override
    public AddCustomerViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AddCustomerViewModel.class);
    }

    @Override
    public AddCustSleectionSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AddCustSelectionAdapter addCustSelectionAdapter = new AddCustSelectionAdapter();
        addCustSelectionAdapter.setItems(addCustSelectionItemModel.addCustSelectionItems());
        addCustSelectionAdapter.setCallback(this);
        viewBinding().setAddCustSelectionAdapter(addCustSelectionAdapter);
        viewBinding().setAddCustSelectionItemModel(addCustSelectionItemModel);
        viewBinding().setAddCustSelectionOnClickListener(this);
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
    public void closeSheet() {
        dismiss();
    }

    @Override
    public void onItemClicked(AddCustSelectionItem addCustSelectionItem) {
        if (adapterCallback != null)
            adapterCallback.selectOpCircle(addCustSelectionItem);
    }

    public interface AdapterCallback {
        void selectOpCircle(AddCustSelectionItem addCustSelectionItem);
    }
}
