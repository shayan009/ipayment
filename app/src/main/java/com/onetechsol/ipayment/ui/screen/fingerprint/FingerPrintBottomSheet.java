package com.onetechsol.ipayment.ui.screen.fingerprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.FingerPrintClickListener;
import com.onetechsol.ipayment.databinding.FingerPrintSheetBinding;
import com.onetechsol.ipayment.pojo.AepsRequestDto;
import com.onetechsol.ipayment.pojo.Kyc18OtpUIData;
import com.onetechsol.ipayment.pojo.Opts;
import com.onetechsol.ipayment.pojo.PidOptions;
import com.onetechsol.ipayment.ui.screen.service.matm.DialogMatmReport;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.disposables.CompositeDisposable;

public class FingerPrintBottomSheet extends CurvedBottomSheetDialogFragment<FingerPrintSheetBinding, FingerPrintVideModel> implements FingerPrintClickListener, DialogMatmReport.DialogCallback {

    private ActivityResultLauncher<Intent> firgerCaptureResult;
    private OnClickListener onClickListener;
    private ActivityResultLauncher<Intent> twoFactorAuth;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    private AepsRequestDto aepsRequestDto;
    private String step;


    public AepsRequestDto getAepsRequestDto() {
        return aepsRequestDto;
    }

    public void setAepsRequestDto(AepsRequestDto aepsRequestDto) {
        this.aepsRequestDto = aepsRequestDto;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.finger_print_sheet;
    }


    @Override
    public FingerPrintVideModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(FingerPrintVideModel.class);
    }

    @Override
    public FingerPrintSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Kyc18OtpUIData kyc18OtpUIData = new Kyc18OtpUIData(true, "Capture fingerprint", "Please click capture button to start fingerprint capture", "Capture");
        viewBinding().setKyc18OtpUIData(kyc18OtpUIData);
        viewBinding().setFingerPrintClickListener(this);
        viewBinding().executePendingBindings();


        firgerCaptureResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

            if (result.getResultCode() == Activity.RESULT_OK) {
                try {
                    Intent data = result.getData();

                    if (data != null) {
                        String pidData = data.getStringExtra("PID_DATA");

                        if (pidData != null) {
                            if (step.equals("5")) {
                                setAepsOpCaptcha(pidData);
                            } else {
                                setCaptcha(pidData);
                            }

                        }

                    }
                } catch (Exception e) {
                    Log.e("Error", "Error while deserialize pid data", e);
                }
            }

        });

    }

    private void setAepsOpCaptcha(String pidData) {

        try {

            Toast.makeText(getContext(), "setAepsOpCaptcha.pidData :" + pidData, Toast.LENGTH_SHORT).show();

            String urlenco = URLEncoder.encode(pidData, "utf-8");

            showToastAlertDialog("Capture Successfull", urlenco, false).setOnClickListener(() -> {
                onShowLoading();

                compositeDisposable().add(viewModel().authenticateAepsOperation(urlenco, aepsRequestDto.getAmount(), aepsRequestDto.getMobileNo(), aepsRequestDto.getAdhar(), aepsRequestDto.getBankName(), aepsRequestDto.getType())
                        .subscribe(authAepsOpResponse -> {

                            onHideLoading();

                            if (authAepsOpResponse.status().equals("1") && authAepsOpResponse.getTxnStatus().equals("1")) {

                                showToastAlertDialog("Capture Success", authAepsOpResponse.message(), false)
                                        .setOnClickListener(() -> {

                                            this.dismiss();

                                            DialogMatmReport dialogMatmReport = new DialogMatmReport();
                                            dialogMatmReport.setDialogCallback(this);
                                            dialogMatmReport.show(getParentFragmentManager(), DialogMatmReport.class.getName());

                                        })
                                        .show(requireActivity().getSupportFragmentManager(), "ShowToastAlert");

                            } else {
                                showToastAlertDialog("Capture failed", authAepsOpResponse.message(), false)
                                        .show(requireActivity().getSupportFragmentManager(), "ShowToastAlert");
                            }

                        }, throwable -> {
                            onHideLoading();
                            showToastAlertDialog("Capture Error", throwable.getMessage(), false)
                                    .show(requireActivity().getSupportFragmentManager(), "ShowToastAlert");
                        }));
            }).show(getParentFragmentManager(), "setCaptcha");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCaptcha(String pidData) {
        try {

            Toast.makeText(getContext(), "pidData :" + pidData, Toast.LENGTH_SHORT).show();

            String urlenco = URLEncoder.encode(pidData, "utf-8");

            showToastAlertDialog("Capture Successfull", urlenco, false).setOnClickListener(() -> {
                onShowLoading();

                compositeDisposable().add(viewModel().startKyc18(step, urlenco).subscribe(startKyc18Response -> {

                    onHideLoading();

                    if (startKyc18Response.status().equals("1") && startKyc18Response.txnStatus().equals("1")) {

                        showToastAlertDialog("Capture Success", startKyc18Response.message(), false)
                                .setOnClickListener(() -> {
                                    dismiss();
                                    onClickListener.hitOnBoardingCk();
                                })
                                .show(requireActivity().getSupportFragmentManager(), "ShowToastAlert");

                    } else {
                        showToastAlertDialog("Capture failed", startKyc18Response.message(), false)
                                .show(requireActivity().getSupportFragmentManager(), "ShowToastAlert");
                    }

                }, throwable -> {
                    onHideLoading();
                }));
            }).show(getParentFragmentManager(), "setCaptcha");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void hideKeyboard(View view) {

    }

    @Override
    public void closeBottomSheet() {
        dismiss();
        onClickListener.dismiss();

    }

    @Override
    public void captureFingerPrint() {
        try {
            String pidOption = getPIDOptions("2.0", "1000", "E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=");

            if (step.equals("4") || step.equals("5")) {
                pidOption = getPIDOptions("2.0", "1000", "");
            }

            if (pidOption != null) {
                Log.e("PidOptions", pidOption);
                Intent intent1 = new Intent();
                intent1.setAction("in.gov.uidai.rdservice.fp.CAPTURE");
                intent1.putExtra("PID_OPTIONS", pidOption);
                firgerCaptureResult.launch(intent1);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

    }

    @Override
    public void printReceipt() {

    }

    @Override
    public void downloadReceipt() {

    }

    public interface OnClickListener {

        void hitOnBoardingCk();

        void dismiss();

    }

    private String getPIDOptions(String pidVer, String timeOut, String wadh) {
        try {
            int fingerCount = 2;

            String posh = "UNKNOWN";

            Opts opts = new Opts();
            opts.fCount = String.valueOf(fingerCount);
            /*opts.fType = String.valueOf(fingerType);*/
            opts.fType = "2";
            opts.iCount = "0";
            opts.iType = "0";
            opts.pCount = "0";
            opts.pType = "0";
            /* opts.format = String.valueOf(fingerFormat);*/
            opts.format = "0"; //0,1
            opts.pidVer = pidVer;
            opts.timeout = timeOut;
            if (step.equals("4") || step.equals("2")) {
                opts.wadh = wadh;
            }
            opts.posh = posh;
            opts.env = "P";
            PidOptions pidOptions = new PidOptions();
            pidOptions.ver = "1.0";
            pidOptions.Opts = opts;
            Serializer serializer = new Persister();
            StringWriter writer = new StringWriter();
            serializer.write(pidOptions, writer);
            return writer.toString();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable().clear();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
