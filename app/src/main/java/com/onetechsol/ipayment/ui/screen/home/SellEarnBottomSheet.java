package com.onetechsol.ipayment.ui.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.SellEarnBottomsheetBinding;
import com.onetechsol.ipayment.databinding.SellEarnSheetOnClickListener;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.ui.adapter.HomeSellEarnAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import java.util.List;

public class SellEarnBottomSheet extends CurvedBottomSheetDialogFragment<SellEarnBottomsheetBinding, HomeFragmentViewModel> implements SellEarnSheetOnClickListener {


    private List<SellEarnModel> homeItemList;


    public List<SellEarnModel> homeItemList() {
        return homeItemList;
    }

    public SellEarnBottomSheet setHomeItemList(List<SellEarnModel> homeItemList) {
        this.homeItemList = homeItemList;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.sell_earn_bottomsheet;
    }

    @Override
    public HomeFragmentViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(HomeFragmentViewModel.class);
    }

    @Override
    public SellEarnBottomsheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        HomeSellEarnAdapter homeSellEarnAdapter = new HomeSellEarnAdapter();
        homeSellEarnAdapter.setItems(homeItemList);
        viewBinding().rvSellEarnList.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        viewBinding().setHomeSellEarnAdapter(homeSellEarnAdapter);
        viewBinding().setSellEarnSheetOnClickListener(this);
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
}
