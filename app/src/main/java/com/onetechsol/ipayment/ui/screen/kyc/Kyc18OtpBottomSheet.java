package com.onetechsol.ipayment.ui.screen.kyc;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.Kyc18OtpClickListener;
import com.onetechsol.ipayment.databinding.Kyc18OtpSheetBinding;
import com.onetechsol.ipayment.pojo.Kyc18OtpUIData;
import com.onetechsol.ipayment.pojo.PidOptions;
import com.onetechsol.ipayment.ui.screen.fingerprint.FingerPrintBottomSheet;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;

import io.reactivex.disposables.CompositeDisposable;

public class Kyc18OtpBottomSheet extends CurvedBottomSheetDialogFragment<Kyc18OtpSheetBinding, Kyc18OtpViewModel> implements Kyc18OtpClickListener, FingerPrintBottomSheet.OnClickListener {


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private OnClickListener onClickListener;

    private FingerPrintBottomSheet fingerPrintBottomSheet;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.kyc18_otp_sheet;
    }

    @Override
    public Kyc18OtpViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(Kyc18OtpViewModel.class);
    }


    @Override
    public Kyc18OtpSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setKyc18OtpClickListener(this);
        boolean isCaptureRequired = prefManager().getIsCaptureRequired();
        Kyc18OtpUIData kyc18OtpUIData = new Kyc18OtpUIData(isCaptureRequired, "Verify Kyc", "Enter Otp", "Submit");
        viewBinding().setKyc18OtpUIData(kyc18OtpUIData);
        viewBinding().executePendingBindings();
    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void closeBottomSheet() {
        dismiss();
    }

    @Override
    public void applyOtp(String otp) {

        onShowLoading();
        compositeDisposable().add(viewModel().startKyc18("1", otp).subscribe(startKyc18Response -> {
            onHideLoading();
            String status = startKyc18Response.status();
            if (status.equals("1") && startKyc18Response.txnStatus().equals("2")) {
                fingerPrintBottomSheet = new FingerPrintBottomSheet();
                fingerPrintBottomSheet.setStep("2");
                fingerPrintBottomSheet.setOnClickListener(this);
                fingerPrintBottomSheet.show(getParentFragmentManager(), FingerPrintBottomSheet.class.getName());
            } else {
                showToastAlertDialog("Otp Validation Failed. Please retry", startKyc18Response.message(), false)
                        .setOnClickListener(() -> {

                            viewBinding().etOtp1.setText("");
                            viewBinding().etOtp2.setText("");
                            viewBinding().etOtp3.setText("");
                            viewBinding().etOtp4.setText("");
                            viewBinding().etOtp5.setText("");
                            viewBinding().etOtp6.setText("");

                            viewBinding().etOtp1.requestFocus();

                        }).show(getParentFragmentManager(), "ShowToastAlert");
            }
        }, throwable -> {
            onHideLoading();
        }));

    }

    @Override
    public void hitOnBoardingCk() {
        if (null != fingerPrintBottomSheet)
            fingerPrintBottomSheet.dismiss();
        onClickListener.recheckOnBoardingCK();
    }

    public interface OnClickListener {
        void recheckOnBoardingCK();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    @Override
    public void dismiss() {

        showToastAlertDialog("Finger Print Capture", "Please capture your fingerprint", false)
                .setOnClickListener(() -> {
                    fingerPrintBottomSheet = new FingerPrintBottomSheet();
                    fingerPrintBottomSheet.setStep("2");
                    fingerPrintBottomSheet.setOnClickListener(this);
                    fingerPrintBottomSheet.show(getParentFragmentManager(), FingerPrintBottomSheet.class.getName());
                }).show(getParentFragmentManager(), "showToastAlertDialog");

    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
