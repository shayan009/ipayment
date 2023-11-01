package com.onetechsol.ipayment.ui.screen.service.aeps.aeps1;

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
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.AddCustSelectionOnClickListener;
import com.onetechsol.ipayment.databinding.AddCustSleectionSheetBinding;
import com.onetechsol.ipayment.databinding.DeviceSelectionSheetBinding;
import com.onetechsol.ipayment.pojo.AddCustSelectionItem;
import com.onetechsol.ipayment.pojo.AddCustSelectionItemModel;
import com.onetechsol.ipayment.pojo.DeviceSelectionItem;
import com.onetechsol.ipayment.ui.adapter.AddCustSelectionAdapter;
import com.onetechsol.ipayment.ui.adapter.DeviceSelectionAdapter;
import com.onetechsol.ipayment.ui.screen.customer.AddCustomerViewModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DeviceSelectionBottomSheet extends CurvedBottomSheetDialogFragment<DeviceSelectionSheetBinding, AEPSOperationViewModel> implements AddCustSelectionOnClickListener, DeviceSelectionAdapter.AdapterCallback {



    private AdapterCallback adapterCallback;


    public AdapterCallback adapterCallback() {
        return adapterCallback;
    }

    public DeviceSelectionBottomSheet setAdapterCallback(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.device_selection_sheet;
    }

    @Override
    public AEPSOperationViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AEPSOperationViewModel.class);
    }

    @Override
    public DeviceSelectionSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        List<DeviceSelectionItem> deviceSelectionItems  = new ArrayList<>();

        Uri inquiry = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.device);
        deviceSelectionItems.add(new DeviceSelectionItem("0", "Mantra", inquiry.toString(), 1));
        deviceSelectionItems.add(new DeviceSelectionItem("1", "Morpho", inquiry.toString(), 1));
        deviceSelectionItems.add(new DeviceSelectionItem("2", "Startek", inquiry.toString(), 1));
        deviceSelectionItems.add(new DeviceSelectionItem("3", "Evolute", inquiry.toString(), 1));

        DeviceSelectionAdapter deviceSelectionAdapter = new DeviceSelectionAdapter();

        deviceSelectionAdapter.setItems(deviceSelectionItems);
        deviceSelectionAdapter.setCallback(this);

        viewBinding().setDeviceSelectionAdapter(deviceSelectionAdapter);
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
    public void onItemClicked(DeviceSelectionItem deviceSelectionAdapter) {
        if (adapterCallback != null)
            adapterCallback.selectDevice(deviceSelectionAdapter);
    }


    public interface AdapterCallback {
        void selectDevice(DeviceSelectionItem deviceSelectionItem);
    }
}
