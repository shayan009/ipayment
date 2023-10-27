package com.onetechsol.ipayment.ui.screen.upgrade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DashboardItemClickListener;
import com.onetechsol.ipayment.databinding.DialogUpgradeAlertBinding;
import com.onetechsol.ipayment.databinding.UpgradeOnClickListener;
import com.onetechsol.ipayment.pojo.CustomerUpgradeInfoResponseData;
import com.onetechsol.ipayment.ui.basefiles.BaseDialogFragment;
import com.onetechsol.ipayment.utils.ApiConstant;

public class UpgradePackageAlertDialog extends BaseDialogFragment<DialogUpgradeAlertBinding> implements UpgradeOnClickListener {


    private CustomerUpgradeInfoResponseData data;
    private DashboardItemClickListener dashboardItemClickListener;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_upgrade_alert;
    }

    public DashboardItemClickListener dashboardItemClickListener() {
        return dashboardItemClickListener;
    }

    public UpgradePackageAlertDialog setDashboardItemClickListener(DashboardItemClickListener dashboardItemClickListener) {
        this.dashboardItemClickListener = dashboardItemClickListener;
        return this;
    }

    public CustomerUpgradeInfoResponseData data() {
        return data;
    }

    public UpgradePackageAlertDialog setData(CustomerUpgradeInfoResponseData data) {
        this.data = data;
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(requireContext()).load(ApiConstant.BASE_URL_IMAGE_SERVICE + data.img()).apply(RequestOptions.circleCropTransform()).into(viewBinding().ivPackageImage);
        viewBinding().mbUpgradeNow.setText(String.format("Pay %s", data.learnerAmt()));
        viewBinding().setCustomerUpgradeInfoResponseData(data);
        viewBinding().setUpgradeOnClickListener(this);
        viewBinding().executePendingBindings();

    }

    @Override
    public DialogUpgradeAlertBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onCancelClick() {
        dismiss();
    }

    @Override
    public void upgradeNow(String amount) {
        dismiss();
        dashboardItemClickListener.upgradeNow(amount);
    }
}
