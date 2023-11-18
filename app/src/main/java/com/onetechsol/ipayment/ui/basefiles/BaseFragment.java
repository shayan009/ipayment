package com.onetechsol.ipayment.ui.basefiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.data.locale.pref.IPrefManager;
import com.onetechsol.ipayment.di.Injectable;
import com.onetechsol.ipayment.widgets.ProgressLoaderDialog;
import com.onetechsol.ipayment.widgets.ToastAlertDialog;
import com.tbruyelle.rxpermissions3.RxPermissions;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseFragment<VM extends ViewModel, VB extends ViewDataBinding> extends Fragment implements IBaseView, SwipeRefreshLayout.OnRefreshListener, Injectable {


    public IPrefManager prefManager;
    private Context context;
    private BaseActivity<VM, VB> baseActivity;
    private ProgressLoaderDialog progressLoaderDialog;
    private RxPermissions rxPermissions;
    @NonNull
    private VB viewBinding = null;
    private VM viewModel = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public abstract int getLayoutRes();

    public RxPermissions rxPermissions() {
        return rxPermissions;
    }

    public CompositeDisposable compositeDisposable() {
        return compositeDisposable;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = setUpViewModel(new ViewModelProvider(this));
        viewBinding = setupViewBinding(inflater, container);
        prefManager = baseActivity.prefManager;
        setProgressDialog(viewBinding.getRoot().getContext());
        rxPermissions = baseActivity.rxPermissions();
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void setProgressDialog(Context context) {
        progressLoaderDialog = new ProgressLoaderDialog(context);
        progressLoaderDialog.setCancelable(false);
        progressLoaderDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onAttachFragment(@IdRes int id, @NonNull Fragment fragment, @NonNull String tag) {
        baseActivity.onAttachFragment(id, fragment, tag);
    }

    @Override
    public ToastAlertDialog showToastAlertDialog(String title, String desc, boolean isCancel) {
        return baseActivity.showToastAlertDialog(title, desc, isCancel);
    }

    public MaterialAlertDialogBuilder showAlertDialog(String title, String desc, boolean isCancel) {
        return baseActivity.showAlertDialog(title, desc, isCancel);
    }

    @Override
    public void onAttachFragment(@IdRes int id, @NonNull Fragment fragment, @NonNull String tag, boolean addToBackStack) {
        baseActivity.onAttachFragment(id, fragment, tag, addToBackStack);
    }

    @Override
    public void onDetachFragment(@NonNull String tag) {
        baseActivity.onDetachFragment(tag);
    }

    @Override
    public void removeAllBackStackFragments() {
        baseActivity.removeAllBackStackFragments();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        baseActivity = (BaseActivity<VM, VB>) context;
    }

    @Override
    public Context getBaseContext() {
        return context;
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
    public void onFailed(@NonNull String message) {
        baseActivity.onFailed(message);
    }

    @Override
    public void onError(@NonNull String error) {
        baseActivity.onError(error);
    }

    @Override
    public void onSuccess(@NonNull String message) {
        baseActivity.onSuccess(message);
    }

    @Override
    public void initializeSnackBar(View view) {
        baseActivity.initializeSnackBar(view);
    }

    @Override
    public void showSnackBarMessage(String message) {
        baseActivity.showSnackBarMessage(message);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void hideKeyboard(View view) {
        baseActivity.hideKeyboard(view);
    }

    @Override
    public Bundle getBundleData() {
        return getArguments();
    }

    @Override
    public boolean isConnectedToNetwork() {
        return baseActivity.isConnectedToNetwork();
    }

}
