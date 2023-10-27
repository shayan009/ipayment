package com.onetechsol.ipayment.ui.basefiles;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.widgets.ToastAlertDialog;

import org.jetbrains.annotations.NotNull;


public interface IBaseView {

    Context getBaseContext();


    void setProgressDialog(Context context);

    void onAttachFragment(@IdRes int id, Fragment fragment, String tag);

    void onAttachFragment(int id, @NonNull Fragment fragment, @NonNull String tag, boolean addToBackStack);

    void onDetachFragment(@NonNull String tag);

    void removeAllBackStackFragments();

    @NotNull
    ViewBinding viewBinding();

    @NotNull
    ViewModel viewModel();

    MaterialAlertDialogBuilder showAlertDialog(String title, String desc, boolean isCancel);

    ToastAlertDialog showToastAlertDialog(String title, String desc, boolean isCancel);

    void onShowLoading();

    void onHideLoading();

    void onFailed(@NonNull String message);

    void onError(@NonNull String error);

    void onSuccess(@NonNull String message);

    void initializeSnackBar(View view);

    void showSnackBarMessage(String message);

    void hideKeyboard(View view);

    Bundle getBundleData();

    boolean isConnectedToNetwork();
}
