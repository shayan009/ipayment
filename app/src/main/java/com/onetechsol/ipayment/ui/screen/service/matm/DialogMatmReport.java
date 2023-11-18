package com.onetechsol.ipayment.ui.screen.service.matm;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.DialogMatmReportBinding;
import com.onetechsol.ipayment.databinding.MatmReportOnClickListener;
import com.onetechsol.ipayment.ui.basefiles.BaseDialogFragment;

public class DialogMatmReport extends BaseDialogFragment<DialogMatmReportBinding> implements MatmReportOnClickListener {


    private double transAmount = 0;
    private double balAmount = 0;
    private int type;
    private String transId;
    private String message;
    private String bankName;
    private String txnStatus;

    public DialogMatmReport(double transAmount, double balAmount, int type, String transId, String message, String bankName, String txnStatus) {
        super();
        this.transAmount = transAmount;
        this.balAmount = balAmount;
        this.type = type;
        this.transId = transId;
        this.message = message;
        this.bankName = bankName;
        this.txnStatus = txnStatus;
    }


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

    int arr [] ={R.drawable.ic_success,R.drawable.warning,R.drawable.ic_failed};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().tvBankInfo.setText(bankName);
        viewBinding().tvAmount.setText(""+transAmount);
        viewBinding().tvTxnInfo.setText(transId);
        viewBinding().tvBalance.setText(""+balAmount);
        viewBinding().tvSubTitleUpgrade.setText(message);

        Uri uriSuccess = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + arr[0]);
        Uri uriPending = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + arr[1]);
        Uri uriFailed = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + arr[2]);




        if(txnStatus.equals("1")) {
            Glide.with(requireContext()).load(uriSuccess).into(viewBinding().ivSuccessFailed);
        } else if (txnStatus.equals("2")) {
            Glide.with(requireContext()).load(uriPending).into(viewBinding().ivSuccessFailed);
        } else if (txnStatus.equals("3")) {
            Glide.with(requireContext()).load(uriFailed).into(viewBinding().ivSuccessFailed);
        }

        viewBinding().setMatmReportOnClickListener(this);
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
