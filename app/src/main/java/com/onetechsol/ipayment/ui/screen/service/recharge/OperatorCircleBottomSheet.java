package com.onetechsol.ipayment.ui.screen.service.recharge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.OpCircleSheetOnClickListener;
import com.onetechsol.ipayment.databinding.OperatorCircleBottomsheetBinding;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.pojo.OperatorCircleItemModel;
import com.onetechsol.ipayment.ui.adapter.OperatorCircleAdapter;
import com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection.PlanSelectionViewModel;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

public class OperatorCircleBottomSheet extends CurvedBottomSheetDialogFragment<OperatorCircleBottomsheetBinding, PlanSelectionViewModel> implements OpCircleSheetOnClickListener, OperatorCircleAdapter.AdapterCallback {


    private OperatorCircleItemModel operatorCircleItemModel;
    private AdapterCallback adapterCallback;

    public OperatorCircleItemModel operatorCircleItemModel() {
        return operatorCircleItemModel;
    }

    public OperatorCircleBottomSheet setOperatorCircleItemModel(OperatorCircleItemModel operatorCircleItemModel) {
        this.operatorCircleItemModel = operatorCircleItemModel;
        return this;
    }

    public AdapterCallback adapterCallback() {
        return adapterCallback;
    }

    public OperatorCircleBottomSheet setAdapterCallback(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.operator_circle_bottomsheet;
    }

    @Override
    public PlanSelectionViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PlanSelectionViewModel.class);
    }

    @Override
    public OperatorCircleBottomsheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        OperatorCircleAdapter operatorCircleAdapter = new OperatorCircleAdapter();
        operatorCircleAdapter.setOpCircleItemDtoList(operatorCircleItemModel.opCircleItemDtoList());
        operatorCircleAdapter.setCallback(this);
        viewBinding().setOperatorCircleAdapter(operatorCircleAdapter);
        viewBinding().setOperatorCircleItemModel(operatorCircleItemModel);
        viewBinding().setOpCircleSheetOnClickListener(this);
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
            adapterCallback.selectOpCircle(opCircleItemDto);
    }

    public interface AdapterCallback {
        void selectOpCircle(OpCircleItemDto opCircleItemDto);
    }
}
