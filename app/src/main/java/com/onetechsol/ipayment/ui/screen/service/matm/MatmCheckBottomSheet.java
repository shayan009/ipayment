package com.onetechsol.ipayment.ui.screen.service.matm;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.fingpay.microatmsdk.MicroAtmLoginScreen;
import com.fingpay.microatmsdk.utils.Constants;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.MatmCheckSheetBinding;
import com.onetechsol.ipayment.databinding.MatmWithdrawClickListener;
import com.onetechsol.ipayment.databinding.RemitterCheckClickListener;
import com.onetechsol.ipayment.databinding.RemitterCheckSheetBinding;
import com.onetechsol.ipayment.pojo.MatmServiceResponseData;
import com.onetechsol.ipayment.pojo.RecentRemitterItem;
import com.onetechsol.ipayment.ui.adapter.RecentRemitterAdapter;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTActivity;
import com.onetechsol.ipayment.ui.screen.service.dmt.DMTViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;
import com.onetechsol.ipayment.widgets.CurvedBottomSheetDialogFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.disposables.Disposable;

public class MatmCheckBottomSheet extends CurvedBottomSheetDialogFragment<MatmCheckSheetBinding, MatmViewModel> implements MatmWithdrawClickListener, DialogMatmReport.DialogCallback {


    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private String referenceNumber;
    private ActivityResultLauncher<Intent> receiptDownload, receiptPrint;

    @Override
    public int getLayoutRes() {
        return R.layout.matm_check_sheet;
    }

    @Override
    public MatmViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(MatmViewModel.class);
    }


    @Override
    public MatmCheckSheetBinding setupViewBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding().setMatmWithdrawClickListener(this);

        receiptDownload = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                    }

                });

        receiptPrint = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                    }

                });

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();

                        if (null != data) {

                            boolean status = data.getBooleanExtra(Constants.TRANS_STATUS, false);
                            String response = data.getStringExtra(Constants.MESSAGE);
                            double transAmount = data.getDoubleExtra(Constants.TRANS_AMOUNT, 0);
                            double balAmount = data.getDoubleExtra(Constants.BALANCE_AMOUNT, 0);
                            String bankRrn = data.getStringExtra(Constants.RRN);
                            String transType = data.getStringExtra(Constants.TRANS_TYPE);
                            int type = data.getIntExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
                            String cardNum = data.getStringExtra(Constants.CARD_NUM);
                            String rrn = data.getStringExtra(Constants.RRN);
                            String tType = data.getStringExtra(Constants.TRANS_TYPE);
                            String bankName = data.getStringExtra(Constants.BANK_NAME);
                            String cardType = data.getStringExtra(Constants.CARD_TYPE);
                            String terminalId = data.getStringExtra(Constants.TERMINAL_ID);
                            String fpId = data.getStringExtra(Constants.FP_TRANS_ID);
                            String transId = data.getStringExtra(Constants.TXN_ID);

                            compositeDisposable().add(viewModel().passMicroAtmResponseBE(status, response, response, transAmount, balAmount, bankRrn, transType, type, cardNum, rrn, tType, bankName, cardType, terminalId, fpId, transId, referenceNumber)
                                    .subscribe(matmMicroAmtFeedBackResponse -> {

                                        if (matmMicroAmtFeedBackResponse.status().equals("1") &
                                                matmMicroAmtFeedBackResponse.txn_status().equals("1")) {

                                            DialogMatmReport dialogMatmReport = new DialogMatmReport();
                                            dialogMatmReport.setDialogCallback(this);
                                            dialogMatmReport.show(getParentFragmentManager(), DialogMatmReport.class.getName());


                                        }

                                    }, throwable -> {

                                    }));

                        }


                    }
                });


    }


    private void printReceiptDownload(String type, String printTxn) {

        String url = ApiConstant.BASE_PRINT_REPORT + "?type=" + type + "&user=" + prefManager().getUsername() + "&id=" + printTxn;
        Uri myAction = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(myAction);
        intent.setPackage("com.android.chrome");
        if (StringUtils.equals("0", type)) {
            receiptDownload.launch(intent);
        } else if (StringUtils.equals("1", type)) {
            receiptPrint.launch(intent);
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
    public void submit(String amount) {
        fetchMatmDetails(amount, "WITHDRAWAL");
    }

    @Override
    public void checkBalance() {
        fetchMatmDetails("0", "BALANCEINFO");
    }

    private void fetchMatmDetails(String amount, String mode) {

        compositeDisposable().add(viewModel().getMatmDetails(amount, mode)
                .subscribe(matmServiceResponse -> {

                    if (matmServiceResponse.status().equals("1")) {

                        referenceNumber = matmServiceResponse.data().referenceNumber();

                        callMicroAtm(matmServiceResponse.data());


                    } else {
                        showToastAlertDialog("Matm Balance info", matmServiceResponse.message(), false)
                                .setOnClickListener(() -> {

                                }).show(getParentFragmentManager(), MatmCheckBottomSheet.class.getName());
                    }

                }, throwable -> showToastAlertDialog("Error", throwable.getMessage(), false)
                        .setOnClickListener(() -> {

                        }).show(getParentFragmentManager(), MatmCheckBottomSheet.class.getName())));
    }

    private void callMicroAtm(MatmServiceResponseData data) {

        Intent intent = new Intent(getActivity(), MicroAtmLoginScreen.class); //OFFPPM
        intent.putExtra(Constants.MERCHANT_USERID, data.subMerchantId());
        // this MERCHANT_USERID be given by FingPay depending on the merchant, only that value need to sent from App to SDK

        intent.putExtra(Constants.MERCHANT_PASSWORD, data.subMerchantPass());
        // this MERCHANT_PASSWORD be given by FingPay depending on the merchant, only that value need to sent from App to SDK

        intent.putExtra(Constants.AMOUNT, data.amount());
        intent.putExtra(Constants.REMARKS, data.remarks());


        intent.putExtra(Constants.MOBILE_NUMBER, data.mobileNumber());
        // send a valid 10 digit mobile number from the app to SDK

        intent.putExtra(Constants.AMOUNT_EDITABLE, false);

        //String s = "fingpay" + String.valueOf(new Date().getTime());
        String s = data.referenceNumber();
        intent.putExtra(Constants.TXN_ID, data.referenceNumber());

        // some dummy value is given in TXN_ID for now but some proper value should come from App to SDK

        intent.putExtra(Constants.SUPER_MERCHANTID, data.supMerchantId());
        // this SUPER_MERCHANT_ID be given by FingPay to you, only that value need to sent from App to SDK

        intent.putExtra(Constants.IMEI, "1234");
        Double lat = Double.valueOf(data.latitude());
        Double lng = Double.valueOf(data.longitude());
        //double lat = 17.4442015, lng = 78.4808421;  // get current location and send these values
        intent.putExtra(Constants.LATITUDE, lat);
        intent.putExtra(Constants.LONGITUDE, lng);
        intent.putExtra(Constants.MICROATM_MANUFACTURER, Constants.MoreFun);
        if (data.transactionType().equals("ATMBE")) {
            intent.putExtra(Constants.TYPE, Constants.BALANCE_ENQUIRY);
        } else if (data.transactionType().equals("ATMCW")) {
            intent.putExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
        }
        intent.putExtra("transactionType", data.transactionType());
        intent.putExtra("amount", data.amount());
        someActivityResultLauncher.launch(intent);
    }


    @Override
    public boolean isConnectedToNetwork() {
        return false;
    }

    @Override
    public void printReceipt() {

    }

    @Override
    public void downloadReceipt() {

    }
}
