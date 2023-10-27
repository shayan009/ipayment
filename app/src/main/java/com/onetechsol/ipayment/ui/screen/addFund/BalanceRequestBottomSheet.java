package com.onetechsol.ipayment.ui.screen.addFund;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.AddFundOnClickListener;
import com.onetechsol.ipayment.databinding.BalanceReqSheetBinding;
import com.onetechsol.ipayment.pojo.FundReqAccount;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.ui.adapter.AddFundPayModeViewPagerAdapter;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BalanceRequestBottomSheet extends CurvedBottomSheetDialogFragment<BalanceReqSheetBinding, BalanceRequestViewModel> implements AddFundOnClickListener, AccountListBottomSheet.AdapterCallback {


    private FundReqAccount fundReqAccount;
    private AccountListBottomSheet accountListBottomSheet;

    @Override
    public int getTheme() {
        return R.style.BottomCurvedGreySheetDialogTheme;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.balance_req_sheet;
    }

    @Override
    public BalanceRequestViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(BalanceRequestViewModel.class);
    }

    @Override
    public BalanceReqSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setAddFundOnClickListener(this);

        viewBinding().vpPayMode.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                setButtonSelectedColor(position);
                super.onPageSelected(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        onShowLoading();
        compositeDisposable().add(viewModel().fundRequestCheck()
                .subscribe(checkFundReqResponse -> {
                    onHideLoading();
                    if (checkFundReqResponse.status().equals("1")) {
                        List<OpCircleItemDto> opCircleItemDtoList = new ArrayList<>();
                        checkFundReqResponse.fundReqAccounts().stream().filter(t -> StringUtils.isNotEmpty(t.id())).forEach(fundReqAccount -> opCircleItemDtoList.add(new OpCircleItemDto(fundReqAccount.id(), fundReqAccount.label(), "", 3)));

                        accountListBottomSheet = new AccountListBottomSheet();
                        accountListBottomSheet.setOpCircleItemDtoList(opCircleItemDtoList);
                        accountListBottomSheet.setAdapterCallback(this);

                    }
                }, throwable -> {
                    onHideLoading();
                }));


        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String fundStatus = intent.getStringExtra("fundStatus");
                if (StringUtils.isNoneEmpty(fundStatus) && fundStatus.equals("1")) {
                    dismiss();
                }

            }
        }, new IntentFilter("fundRequest"));

        /*if(fundReqAccount != null) {
            setViewPagerAdapter(fundReqAccount);
        }*/

    }

    void setButtonSelectedColor(int pos) {
        switch (pos) {
            case 0: {
                viewBinding().btPayViaQR.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                viewBinding().btPayViaUpi.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                break;
            }

            case 1: {
                viewBinding().btPayViaQR.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.float_transparent)));
                viewBinding().btPayViaUpi.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                break;
            }

        }
    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void closeBottomSheet() {
        dismiss();
    }

    @Override
    public void openAccountList() {
        if (accountListBottomSheet != null)
            accountListBottomSheet.show(getParentFragmentManager(), AccountListBottomSheet.class.getName());
    }

    @Override
    public void openQRCode() {
        viewBinding().vpPayMode.setCurrentItem(0);
    }

    @Override
    public void openUpiId() {
        viewBinding().vpPayMode.setCurrentItem(1);
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
    public void selectAccount(OpCircleItemDto opCircleItemDto) {

        if (accountListBottomSheet != null)
            accountListBottomSheet.dismiss();

        viewBinding().tvAccountName.setText(opCircleItemDto.title());
        onShowLoading();
        compositeDisposable().add(viewModel().fundRequestCheckDetails(opCircleItemDto.id())
                .subscribe(checkFundReqDetailResponse -> {
                    onHideLoading();
                    if (checkFundReqDetailResponse.status().equals("1")) {

                        viewBinding().mcvPayMode.setVisibility(View.VISIBLE);
                        viewBinding().vpPayMode.setVisibility(View.VISIBLE);
                        fundReqAccount = checkFundReqDetailResponse.fundReqAccount();
                        setViewPagerAdapter(checkFundReqDetailResponse.fundReqAccount());
                    }

                }, throwable -> {
                    onHideLoading();
                }));
    }

    private void setViewPagerAdapter(FundReqAccount fundReqAccount) {

        AddFundPayModeViewPagerAdapter offerWalletViewPagerAdapter = new AddFundPayModeViewPagerAdapter(requireActivity().getSupportFragmentManager(), getLifecycle(), fundReqAccount);
        viewBinding().vpPayMode.setAdapter(offerWalletViewPagerAdapter);
        viewBinding().vpPayMode.setCurrentItem(0);
    }

}
