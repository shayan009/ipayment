package com.onetechsol.ipayment.ui.screen.kyc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.onetechsol.ipayment.pojo.Opts;
import com.onetechsol.ipayment.pojo.PidOptions;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.pojo.ServiceCategoryType;
import com.onetechsol.ipayment.ui.screen.service.aeps.aeps1.AEPS1Activity;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.disposables.CompositeDisposable;

public class Kyc18OtpBottomSheet extends CurvedBottomSheetDialogFragment<Kyc18OtpSheetBinding, Kyc18OtpViewModel> implements Kyc18OtpClickListener {


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ActivityResultLauncher<Intent> startActivityIntent;
    private Kyc18OtpClickListener kyc18OtpClickListener;

    @Override
    public int getLayoutRes() {
        return R.layout.kyc18_otp_sheet;
    }

    @Override
    public Kyc18OtpViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(Kyc18OtpViewModel.class);
    }

    public Kyc18OtpClickListener kyc18OtpClickListener() {
        return kyc18OtpClickListener;
    }

    public Kyc18OtpBottomSheet setKyc18OtpClickListener(Kyc18OtpClickListener kyc18OtpClickListener) {
        this.kyc18OtpClickListener = kyc18OtpClickListener;
        return this;
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


        startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

            if (result.getResultCode() == Activity.RESULT_OK) {
                try {
                    Intent data = result.getData();

                    if (data != null) {
                        String pidData = data.getStringExtra("PID_DATA");
                        if (pidData != null) {
                            setCaptcha(pidData);
                        }

                    }
                } catch (Exception e) {
                    Log.e("Error", "Error while deserialize pid data", e);
                }
            }

        });

    }

    private void setCaptcha(String pidData) {
        //Toast.makeText(getContext(), "pidData :" + pidData, //Toast.LENGTH_SHORT).show();
        try {
            String urlenco = URLEncoder.encode(pidData, "utf-8");

            onShowLoading();
            compositeDisposable().add(viewModel().startKyc18("2", urlenco).subscribe(startKyc18Response -> {
                //Toast.makeText(getContext(), startKyc18Response.message(), //Toast.LENGTH_SHORT).show();

                onHideLoading();
                if (startKyc18Response.status().equals("1")) {

                    String txnStatus = startKyc18Response.txnStatus();

                    if (txnStatus.equals("1")) {
                        dismiss();

                        Intent intent = new Intent(getActivity(), AEPS1Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(ServiceCategoryModel.class.getName(), new ServiceCategoryModel(1, "33", "AEPS 1", "https://partner.ipayments.in/logo/icon/service_33.png?v=2.9.172302009", ServiceCategoryType.AEPS_1, "", false));
                        intent.putExtra(ServiceCategoryModel.class.getName(), bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }

            }, throwable -> {

            }));
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
    }

    @Override
    public void applyOtp(String otp) {
        //Toast.makeText(getContext(), "OTP : " + otp, //Toast.LENGTH_SHORT).show();

        if (!prefManager().getIsCaptureRequired()) {

            compositeDisposable().add(viewModel().startKyc18("1", otp).subscribe(startKyc18Response -> {
                String status = startKyc18Response.status();
                if (status.equals("1")) {
                    String statusAlart = startKyc18Response.txnStatus();
                    if (statusAlart.equals("1")) {
                        showTextDialog(startKyc18Response.message());
                    } else if (statusAlart.equals("2")) {

                        prefManager().setIsCaptureRequired(true);
                        //Open capture dialog
                        Kyc18OtpUIData kyc18OtpUIData = new Kyc18OtpUIData(true, "Capture fingerprint", "Please click capture button to start fingerprint capture", "Capture");
                        viewBinding().setKyc18OtpUIData(kyc18OtpUIData);
                        viewBinding().executePendingBindings();
                    }

                } else {
                    showTextDialog(startKyc18Response.message());
                }
            }, throwable -> {

            }));
        } else {

            try {
                String pidOption = getPIDOptions();
                if (pidOption != null) {
                    Log.e("PidOptions", pidOption);
                    Intent intent1 = new Intent();
                    intent1.setAction("in.gov.uidai.rdservice.fp.CAPTURE");
                    intent1.putExtra("PID_OPTIONS", pidOption);
                    startActivityIntent.launch(intent1);
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

        }

    }

    private String getPIDOptions() {
        try {

            String posh = "UNKNOWN";
            Opts opts = new Opts();
            opts.fCount = "0";
            /*opts.fType = String.valueOf(fingerType);*/
            opts.fType = "2";
            opts.iCount = "0";
            opts.iType = "0";
            opts.pCount = "0";
            opts.pType = "0";
            /* opts.format = String.valueOf(fingerFormat);*/
            opts.format = "0"; //0,1
            opts.pidVer = "2.0";
            opts.timeout = "10000";
//            opts.otp = "123456";
            opts.wadh = "E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=";
            opts.posh = posh;
            /* opts.env = spinnerEnv.getSelectedItem().toString();*/
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

    private void showTextDialog(String message) {
        new MaterialAlertDialogBuilder(getContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3).setTitle("Alert!").setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName())));

            }
        }).setNegativeButton("Cancel", null).show();
    }

    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }
}
