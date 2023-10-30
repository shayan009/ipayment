package com.onetechsol.ipayment.ui.screen.service.matm;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DialogMatmReportBinding;
import com.onetechsol.ipayment.databinding.MatmReportOnClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseDialogFragment;
import com.onetechsol.ipayment.utils.ApiConstant;

public class DialogMatmReport extends BaseDialogFragment<DialogMatmReportBinding> implements MatmReportOnClickListener {


    @Override
    public int getLayoutRes() {
        return R.layout.dialog_matm_report;
    }

    private DialogCallback dialogCallback;


    public DialogCallback getDialogCallback() {
        return dialogCallback;
    }

    public void setDialogCallback(DialogCallback dialogCallback) {
        this.dialogCallback = dialogCallback;
    }

    @Override
    public DialogMatmReportBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return  DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onClickOk() {

    }

    @Override
    public void printReport() {
        dialogCallback.printReceipt();
    }




    @Override
    public void downloadReport() {
        dialogCallback.downloadReceipt();
    }

   public interface DialogCallback {
        void printReceipt();
        void downloadReceipt();
    }
}
