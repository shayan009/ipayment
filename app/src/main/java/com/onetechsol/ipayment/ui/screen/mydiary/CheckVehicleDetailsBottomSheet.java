package com.onetechsol.ipayment.ui.screen.mydiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.BottomSheetCheckVehicleBinding;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

public class CheckVehicleDetailsBottomSheet extends CurvedBottomSheetDialogFragment<BottomSheetCheckVehicleBinding, MyGromoDiaryViewModel> {


    @Override
    public int getLayoutRes() {
        return R.layout.bottom_sheet_check_vehicle;
    }

    @Override
    public MyGromoDiaryViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(MyGromoDiaryViewModel.class);
    }

    @Override
    public BottomSheetCheckVehicleBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
