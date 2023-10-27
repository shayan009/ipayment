package com.onetechsol.ipayment.widgets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DialogToastAlertBinding;
import com.onetechsol.ipayment.databinding.ToastOnClickListener;
import com.onetechsol.ipayment.pojo.ToastItem;
import com.onetechsol.ipayment.ui.basefiles.BaseDialogFragment;

public class ToastAlertDialog extends BaseDialogFragment<DialogToastAlertBinding> implements ToastOnClickListener {


    private OnClickListener onClickListener;
    private String title;
    private String subTitle;
    private boolean showCancel;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_toast_alert;
    }

    public OnClickListener onClickListener() {
        return onClickListener;
    }

    public ToastAlertDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public ToastAlertDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ToastAlertDialog setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public ToastAlertDialog setShowCancel(boolean showCancel) {
        this.showCancel = showCancel;
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ToastItem data = new ToastItem(title, subTitle, showCancel);

        viewBinding().mbCancel.setVisibility(showCancel ? View.VISIBLE : View.GONE);
        viewBinding().view5.setVisibility(showCancel ? View.VISIBLE : View.GONE);

        viewBinding().setToastItem(data);
        viewBinding().setToastOnClickListener(this);
        viewBinding().executePendingBindings();

    }

    @Override
    public DialogToastAlertBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onCancelClick() {
        dismiss();
    }

    @Override
    public void onClickOk() {
        dismiss();
        onClickListener.onClickOK();
    }

    public interface OnClickListener {
        void onClickOK();
    }
}
