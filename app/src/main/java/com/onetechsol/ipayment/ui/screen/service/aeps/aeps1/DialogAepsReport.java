package com.onetechsol.ipayment.ui.screen.service.aeps.aeps1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DialogAepsReportBinding;
import com.onetechsol.ipayment.databinding.DialogMatmReportBinding;
import com.onetechsol.ipayment.databinding.MatmReportOnClickListener;
import com.onetechsol.ipayment.pojo.AepsRequestDto;
import com.onetechsol.ipayment.pojo.AuthAepsOpResponse;
import com.onetechsol.ipayment.ui.basefiles.BaseDialogFragment;

public class DialogAepsReport extends BaseDialogFragment<DialogAepsReportBinding> implements MatmReportOnClickListener {

    private AuthAepsOpResponse authAepsOpResponse;
    private AepsRequestDto aepsRequestDto;


    public AepsRequestDto getAepsRequestDto() {
        return aepsRequestDto;
    }

    public void setAepsRequestDto(AepsRequestDto aepsRequestDto) {
        this.aepsRequestDto = aepsRequestDto;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_aeps_report;
    }

    public AuthAepsOpResponse getAuthAepsOpResponse() {
        return authAepsOpResponse;
    }

    public void setAuthAepsOpResponse(AuthAepsOpResponse authAepsOpResponse) {
        this.authAepsOpResponse = authAepsOpResponse;
    }

    private DialogCallback dialogCallback;

    public DialogCallback getDialogCallback() {
        return dialogCallback;
    }

    public void setDialogCallback(DialogCallback dialogCallback) {
        this.dialogCallback = dialogCallback;
    }

    @Override
    public DialogAepsReportBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = "";
        if (aepsRequestDto.getType() == 1) {
            title = "Balance Info";
        } else if (aepsRequestDto.getType() == 2) {
            title = "Balance Withdrawal";
        }
        else if (aepsRequestDto.getType() == 3) {
            title = "mini Statement";
            viewBinding().ivDownloadReceipt.setVisibility(View.VISIBLE);
        }

        viewBinding().tvTitleUpgrade.setText(title);
        viewBinding().setMatmReportOnClickListener(this);
        viewBinding().setAepsRequestDto(aepsRequestDto);
        viewBinding().setAuthAepsOpResponse(authAepsOpResponse);
        viewBinding().executePendingBindings();

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
