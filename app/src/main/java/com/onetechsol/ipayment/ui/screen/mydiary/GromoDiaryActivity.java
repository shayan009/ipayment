package com.onetechsol.ipayment.ui.screen.mydiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityGromoDiaryBinding;
import com.onetechsol.ipayment.databinding.GromoDiaryClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

public class GromoDiaryActivity extends BaseActivity<MyGromoDiaryViewModel, ActivityGromoDiaryBinding> implements GromoDiaryClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding().setGromoClickListener(this);

    }


    @Override
    public ActivityGromoDiaryBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityGromoDiaryBinding.inflate(inflater);
    }

    @Override
    public MyGromoDiaryViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(MyGromoDiaryViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_gromo_diary;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void openCheckVehicle(View view) {
        CheckVehicleDetailsBottomSheet checkVehicleDetailsBottomSheet = new CheckVehicleDetailsBottomSheet();
        checkVehicleDetailsBottomSheet.show(getSupportFragmentManager(), CheckVehicleDetailsBottomSheet.class.getSimpleName());
    }

    @Override
    public void openCheckChallan(View view) {

    }

    @Override
    public void onBackPressed(View view) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}