package com.onetechsol.ipayment.ui.basefiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.onetechsol.ipayment.widgets.ToastAlertDialog;

import org.jetbrains.annotations.NotNull;


public interface IBaseBottomSheet {


    void onShowLoading();

    void onHideLoading();

    void setProgressDialog(Context context);

    @NotNull
    ViewBinding viewBinding();

    @NotNull
    ViewModel viewModel();

    View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    void hideKeyboard(View view);

    boolean isConnectedToNetwork();

    ToastAlertDialog showToastAlertDialog(String title, String desc, boolean isCancel);
}
