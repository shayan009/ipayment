package com.onetechsol.ipayment.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.di.Injectable;
import com.onetechsol.ipayment.ui.basefiles.IBaseBottomSheet;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;

public abstract class CurvedBottomSheetDialogFragment<VB extends ViewDataBinding, VM extends ViewModel> extends BottomSheetDialogFragment implements IBaseBottomSheet, Injectable {

    public ProgressLoaderDialog progressLoaderDialog = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private VB viewBinding = null;
    private VM viewModel = null;

    @Override
    public int getTheme() {
        return R.style.BottomCurvedSheetDialogTheme;
    }

    public abstract int getLayoutRes();

    public CompositeDisposable compositeDisposable() {
        return compositeDisposable;
    }

    public MaterialAlertDialogBuilder showAlertDialog(String title, String desc, boolean isCancel) {
        return new MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation)
                .setTitle(title)
                .setMessage(desc)
                .setCancelable(isCancel);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = setUpViewModel(new ViewModelProvider(this));
        viewBinding = setupViewBinding(inflater, container);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setProgressDialog(viewBinding.getRoot().getContext());
        return viewBinding.getRoot();

    }

    @Override
    public void onShowLoading() {
        if (progressLoaderDialog != null)
            progressLoaderDialog.show();
    }

    @Override
    public void onHideLoading() {
        if (progressLoaderDialog != null)
            progressLoaderDialog.dismiss();
    }

    @Override
    public void setProgressDialog(Context context) {
        progressLoaderDialog = new ProgressLoaderDialog(context);
        progressLoaderDialog.setCancelable(false);
        progressLoaderDialog.setCanceledOnTouchOutside(false);
    }

    public abstract VM setUpViewModel(ViewModelProvider viewModelProvider);

    public abstract VB setupViewBinding(LayoutInflater inflater, ViewGroup container);

    @NotNull
    @Override
    public VB viewBinding() {
        return viewBinding;
    }

    @NotNull
    @Override
    public VM viewModel() {
        return viewModel;
    }


    @Override
    public ToastAlertDialog showToastAlertDialog(String title, String desc, boolean isCancel) {

        ToastAlertDialog toastAlertDialog = new ToastAlertDialog();
        toastAlertDialog.setTitle(title);
        toastAlertDialog.setShowCancel(isCancel);
        toastAlertDialog.setSubTitle(desc);
        return toastAlertDialog;
    }

    public PrefManager prefManager() {
        return new PrefManager(getContext());
    }
}
