package com.onetechsol.ipayment.ui.basefiles;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseDialogFragment<VB extends ViewDataBinding> extends DialogFragment {


    @NonNull
    private VB viewBinding = null;

    public abstract int getLayoutRes();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewBinding = setupViewBinding(inflater, null);
        builder.setView(viewBinding.getRoot());
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
    }


    public abstract VB setupViewBinding(LayoutInflater inflater, ViewGroup container);


    public VB viewBinding() {
        return viewBinding;
    }

    @Override
    public View getView() {
        return viewBinding.getRoot();
    }

}


