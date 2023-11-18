package com.onetechsol.ipayment.ui.screen.customer;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.databinding.ActivityAddCustomerBinding;
import com.onetechsol.ipayment.databinding.AddCustClickListener;
import com.onetechsol.ipayment.pojo.AddCustSelectionItem;
import com.onetechsol.ipayment.pojo.AddCustSelectionItemModel;
import com.onetechsol.ipayment.pojo.ProductModel;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerActivity extends BaseActivity<AddCustomerViewModel, ActivityAddCustomerBinding> implements AddCustClickListener, AddCustomerSelectionBottomSheet.AdapterCallback {

    private AddCustomerSelectionBottomSheet selectEmploymentTypeSheet;
    private AddCustomerSelectionBottomSheet selectIncomeRangeSheet;
    private AddCustomerSelectionBottomSheet selectHasCCYesNoSheet;
    private ProductModel productModel;
    private String incomeRange = "", employmentType = "", hasCC = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productModel = getIntent().getParcelableExtra("productModel");

        viewBinding().setAddCustClickListener(this);

        setUpEmployementType();
        setUpIncomeRange();
        setUpHasCCYesNo();
        viewBinding().tvTitleCC.setText(productModel.name());

    }

    private void setUpHasCCYesNo() {

        selectHasCCYesNoSheet = new AddCustomerSelectionBottomSheet();

        Uri yes = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.yes);
        Uri no = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.no);


        List<AddCustSelectionItem> selectHasCCYesNoList = new ArrayList<>();
        selectHasCCYesNoList.add(new AddCustSelectionItem("0", "Yes", yes.toString(), 3));
        selectHasCCYesNoList.add(new AddCustSelectionItem("1", "No", no.toString(), 3));


        AddCustSelectionItemModel addCustSelectionItemModel = new AddCustSelectionItemModel("Select your answer", "", "", selectHasCCYesNoList);

        selectHasCCYesNoSheet.setAddCustSelectionItemModel(addCustSelectionItemModel);
        selectHasCCYesNoSheet.setAdapterCallback(this);

    }

    private void setUpIncomeRange() {

        selectIncomeRangeSheet = new AddCustomerSelectionBottomSheet();

        Uri inquiry = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.cash);
        List<AddCustSelectionItem> selectIncomeRangeList = new ArrayList<>();
        selectIncomeRangeList.add(new AddCustSelectionItem("1", "BELOW 15000", inquiry.toString(), 2));
        selectIncomeRangeList.add(new AddCustSelectionItem("2", "15000 - 20000", inquiry.toString(), 2));
        selectIncomeRangeList.add(new AddCustSelectionItem("3", "20000 - 25000", inquiry.toString(), 2));
        selectIncomeRangeList.add(new AddCustSelectionItem("4", "25000 - 30000", inquiry.toString(), 2));
        selectIncomeRangeList.add(new AddCustSelectionItem("5", "Above 30000", inquiry.toString(), 2));

        AddCustSelectionItemModel addCustSelectionItemModel = new AddCustSelectionItemModel("Select your Income Range", "", "", selectIncomeRangeList);

        selectIncomeRangeSheet.setAddCustSelectionItemModel(addCustSelectionItemModel);
        selectIncomeRangeSheet.setAdapterCallback(this);


    }

    private void setUpEmployementType() {


        selectEmploymentTypeSheet = new AddCustomerSelectionBottomSheet();

        Uri employmentType = Uri.parse("android.resource://" + MainApp.getContext().getPackageName() + "/" + R.drawable.employment);

        List<AddCustSelectionItem> selectEmployTypeList = new ArrayList<>();
        selectEmployTypeList.add(new AddCustSelectionItem("1", "Salaried", employmentType.toString(), 1));
        selectEmployTypeList.add(new AddCustSelectionItem("2", "Self Employed", employmentType.toString(), 1));
        selectEmployTypeList.add(new AddCustSelectionItem("3", "Student/Not Working", employmentType.toString(), 1));
        AddCustSelectionItemModel addCustSelectionItemModel = new AddCustSelectionItemModel("Select your Employment Type", "", "", selectEmployTypeList);

        selectEmploymentTypeSheet.setAddCustSelectionItemModel(addCustSelectionItemModel);
        selectEmploymentTypeSheet.setAdapterCallback(this);

    }

    @Override
    public ActivityAddCustomerBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityAddCustomerBinding.inflate(inflater);
    }

    @Override
    public AddCustomerViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(AddCustomerViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_add_customer;
    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openEmployment() {
        if (selectEmploymentTypeSheet != null)
            selectEmploymentTypeSheet.show(getSupportFragmentManager(), "1");
    }

    @Override
    public void openIncome() {
        if (selectIncomeRangeSheet != null)
            selectIncomeRangeSheet.show(getSupportFragmentManager(), "2");
    }

    @Override
    public void openHasCC() {

        if (selectHasCCYesNoSheet != null)
            selectHasCCYesNoSheet.show(getSupportFragmentManager(), "3");

    }

    @Override
    public void addCustomer(String name, String email, String mobile, String age, String pinCode) {

        if (StringUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter customer's name", Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter customer's email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(mobile)) {
            Toast.makeText(this, "Please enter customer's mobile", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(age)) {
            Toast.makeText(this, "Please enter customer's age", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(pinCode)) {
            Toast.makeText(this, "Please enter customer's pinCode", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(employmentType)) {
            Toast.makeText(this, "Please enter customer's employment type", Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isEmpty(incomeRange)) {
            Toast.makeText(this, "Please enter customer's income range", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(hasCC)) {
            Toast.makeText(this, "Please verify if customer has existing credit card or not", Toast.LENGTH_SHORT).show();
            return;
        }

        onShowLoading();
        compositeDisposable().add(viewModel().addCustomerAffiliate(name, mobile, email, pinCode, productModel.id(), age, employmentType, incomeRange, hasCC.equals("0"))
                .subscribe(addCustomerResponse -> {
                    onHideLoading();

                    if (addCustomerResponse.status().equals("1")) {
                        showAlertDialog("Add Customer", "Customer has been added", true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {


                                    ShareWithCustomerBottomSheet shareWithCustomerBottomSheet = new ShareWithCustomerBottomSheet();
                                    shareWithCustomerBottomSheet.setProductName(productModel.name());
                                    shareWithCustomerBottomSheet.setName(name);
                                    shareWithCustomerBottomSheet.setMobileNumber(mobile);
                                    shareWithCustomerBottomSheet.setUrl(addCustomerResponse.reloadUrl());
                                    shareWithCustomerBottomSheet.show(getSupportFragmentManager(), ShareWithCustomerBottomSheet.class.getName());

                                }).show();
                    } else {
                        showAlertDialog("Add Customer", addCustomerResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                }).show();
                    }

                }, th -> {
                    onHideLoading();
                    showAlertDialog("Error", th.getMessage(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                            }).show();
                }));

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void selectOpCircle(AddCustSelectionItem addCustSelectionItem) {


        if (addCustSelectionItem.type() == 1) {
            viewBinding().tvEmploymentType.setText(addCustSelectionItem.title());
            employmentType = addCustSelectionItem.id();
            if (selectEmploymentTypeSheet != null)
                selectEmploymentTypeSheet.dismiss();
        } else if (addCustSelectionItem.type() == 2) {
            viewBinding().tvIncomeType.setText(addCustSelectionItem.title());
            incomeRange = addCustSelectionItem.id();
            if (selectIncomeRangeSheet != null)
                selectIncomeRangeSheet.dismiss();
        } else if (addCustSelectionItem.type() == 3) {
            viewBinding().tvHasCC.setText(addCustSelectionItem.title());
            hasCC = addCustSelectionItem.id();
            if (selectHasCCYesNoSheet != null)
                selectHasCCYesNoSheet.dismiss();
        }

    }
}