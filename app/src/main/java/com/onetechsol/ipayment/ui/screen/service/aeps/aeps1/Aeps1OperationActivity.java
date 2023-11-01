package com.onetechsol.ipayment.ui.screen.service.aeps.aeps1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.chip.Chip;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityAeps1OperationBinding;
import com.onetechsol.ipayment.databinding.Aeps1OperationClickListener;
import com.onetechsol.ipayment.databinding.SideSheetDataOnClickListener;
import com.onetechsol.ipayment.pojo.AepsBankModel;
import com.onetechsol.ipayment.pojo.AepsRequestDto;
import com.onetechsol.ipayment.pojo.BeneficiaryBankModel;
import com.onetechsol.ipayment.pojo.DeviceSelectionItem;
import com.onetechsol.ipayment.pojo.ReportItem;
import com.onetechsol.ipayment.pojo.SideSheetItem;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.fingerprint.FingerPrintBottomSheet;
import com.onetechsol.ipayment.widgets.CustomSideSheetSpinner;

import java.util.ArrayList;
import java.util.List;

public class Aeps1OperationActivity extends BaseActivity<AEPSOperationViewModel, ActivityAeps1OperationBinding> implements Aeps1OperationClickListener, CustomSideSheetSpinner.SideSpinnerCallback, SideSheetDataOnClickListener, DeviceSelectionBottomSheet.AdapterCallback, FingerPrintBottomSheet.OnClickListener {

    private CustomSideSheetSpinner sideSheetDialog;
    private DeviceSelectionBottomSheet deviceSelectionBottomSheet;
    private FingerPrintBottomSheet fingerPrintBottomSheet;

    private int[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding().setAeps1OperationClickListener(this);

        type = new int[]{getIntent().getIntExtra("type", 1)};

        char space = ' ';

        viewBinding().foldingCell.initialize(30, 1000, Color.WHITE, 0);
        initSideSheet();



        switch (type[0]) {

            case 1:
                viewBinding().chipBalInfo.setChecked(true);
                // viewBinding().foldingCell.toggle(false);
                break;
            case 2:
                viewBinding().chipWithdraw.setChecked(true);
                //viewBinding().foldingCell.toggle(false);
                break;

            case 3:
                viewBinding().chipMiniStatemnet.setChecked(true);
                //viewBinding().foldingCell.toggle(false);
                break;
        }


        viewBinding().etAdharNoAeps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }
        });

        viewBinding().chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.size() > 0) {
                Chip chip = viewBinding().chipGroup.findViewById(checkedIds.get(0));

                type[0] = Integer.parseInt(chip.getTag().toString());

                inititateUI();

            }
        });

        deviceSelectionBottomSheet = new DeviceSelectionBottomSheet();
        deviceSelectionBottomSheet.setAdapterCallback(this);


        inititateUI();
    }

    private void inititateUI() {
        if (type[0] == 1) {
            viewBinding().tvOperationsText.setText("Balance Inquiry");
            viewBinding().mcvWithdrawAmt.setVisibility(View.GONE);

        } else if (type[0] == 2) {
            viewBinding().tvOperationsText.setText("Balance Withdraw");
            viewBinding().mcvWithdrawAmt.setVisibility(View.VISIBLE);

        } else if (type[0] == 3) {
            viewBinding().tvOperationsText.setText("Mini Statement");
            viewBinding().mcvWithdrawAmt.setVisibility(View.GONE);
        }
    }


    private void initSideSheet() {

        sideSheetDialog = new CustomSideSheetSpinner(this);
        ((CustomSideSheetSpinner) sideSheetDialog).setSideSheetDataOnClickListener(this);
        sideSheetDialog.setSideSpinnerCallback(this);

        searchBank("pun");

    }


    private void searchBank(String query) {
        compositeDisposable().add(viewModel().getAepsBankList(query)
                .subscribe(beneficiaryBankModels -> {
                    List<SideSheetItem> sideSheetItems = new ArrayList<>();

                    for (int i = 0; i < beneficiaryBankModels.size(); i++) {
                        AepsBankModel aepsBankModel = beneficiaryBankModels.get(i);
                        sideSheetItems.add(new SideSheetItem(aepsBankModel.name(), aepsBankModel.value(), true, (i - 1), false));
                    }

                        sideSheetDialog.setSideSheetItems(sideSheetItems);

                }, throwable -> {
                    onHideLoading();
                }));
    }


    @Override
    public ActivityAeps1OperationBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityAeps1OperationBinding.inflate(inflater);
    }

    @Override
    public AEPSOperationViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AEPSOperationViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_aeps1_operation;
    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void submitAepsOperation(String bankName, String mobileNo, String adhar, String amount) {

        AepsRequestDto aepsRequestDto = new AepsRequestDto(bankName, mobileNo, type[0], amount, adhar);
        fingerPrintBottomSheet = new FingerPrintBottomSheet();
        fingerPrintBottomSheet.setAepsRequestDto(aepsRequestDto);
        fingerPrintBottomSheet.setStep("5");
        fingerPrintBottomSheet.setCancelable(false);
        fingerPrintBottomSheet.setOnClickListener(this);
        fingerPrintBottomSheet.show(getSupportFragmentManager(), FingerPrintBottomSheet.class.getName());

    }

    @Override
    public void openBankList() {

        if (sideSheetDialog != null)
            sideSheetDialog.show();

    }

    @Override
    public void openDevice() {

        if (deviceSelectionBottomSheet != null)
            deviceSelectionBottomSheet.show(getSupportFragmentManager(), DeviceSelectionBottomSheet.class.getName());
    }

    @Override
    public void openMode() {
        viewBinding().foldingCell.toggle(false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemSearched(String search) {
        searchBank(search);
    }

    @Override
    public void selectSideSheetItem(SideSheetItem stateList) {

        viewBinding().tvBankNameText.setText(stateList.name());
        if (sideSheetDialog != null)
            sideSheetDialog.dismiss();
    }

    @Override
    public void selectDevice(DeviceSelectionItem deviceSelectionItem) {


    }

    @Override
    public void hitOnBoardingCk() {

    }

    @Override
    public void dismiss() {

    }


}