package com.onetechsol.ipayment.ui.screen.service.recharge.electricity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityElectricityBillPayBinding;
import com.onetechsol.ipayment.databinding.ElectricityPayClickListener;
import com.onetechsol.ipayment.pojo.ElectricityBillPayDto;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.pojo.ServiceCategoryType;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.OperatorCircleBottomSheet;
import com.onetechsol.ipayment.ui.screen.service.recharge.RechargeViewModel;

import java.util.Objects;

public class BillInsurancePayActivity extends BaseActivity<RechargeViewModel, ActivityElectricityBillPayBinding> implements ElectricityPayClickListener, OperatorCircleBottomSheet.AdapterCallback {

    String title = "";
    String operatorTitle = "";
    private ServiceCategoryModel serviceCategoryModel;
    private String mobile;
    private UserLocation userLocation;
    private OperatorCircleBottomSheet operatorBottomSheet;
    private OpCircleItemDto selectedOperator;
    private ElectricityBillPayDto electricityBillPayDto;
    private int currentState = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        serviceCategoryModel = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getParcelable(ServiceCategoryModel.class.getName());


        mobile = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getString("mobile");
        UserLoginSession userSession = viewModel().prefManager().getUserSession();


        operatorBottomSheet = new OperatorCircleBottomSheet();
        operatorBottomSheet.setAdapterCallback(this);


        if (operatorBottomSheet.getBottomSheetDialog() != null) {
            operatorBottomSheet.getBottomSheetDialog().getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    currentState = newState;
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });

        }


        viewBinding().setElectricityPayClickListener(this);

        if (userSession != null) {

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.ELECTRICITY) {
                title = "Electricity Bill Payment";
                operatorTitle = "Electricity Board";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.RECHARGE_POSTPAID) {
                title = "PostPaid Bill Payment";
                operatorTitle = "PostPaid Operator";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.RECURRING_DEPOSIT) {
                title = "Recurring Deposit Payment";
                operatorTitle = "Recurring Deposit Operator";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.HOSPITAL_AND) {
                title = "Hospital and Pathology Bill Payment";
                operatorTitle = "Hospital Pathology";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.HOSPITAL) {
                title = "Hospital Bill Payment";
                operatorTitle = "Hospital";
            }


            if (serviceCategoryModel.serviceType() == ServiceCategoryType.EDUCATION_FEE) {
                title = "Education Fees Payment";
                operatorTitle = "School";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.HOUSING_SOCIETY) {
                title = "Housing Society Payment";
                operatorTitle = "Housing";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.MUNICIPAL_TAXES) {
                title = "Municipal Taxes Payment";
                operatorTitle = "Munical";

            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.SUBSCRIPTION) {
                title = "Subscription Payment";
                operatorTitle = "Subscriber";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.CLUBS_ASSOCIATION) {
                title = "Clubs Association Payment";
                operatorTitle = "Institute";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.CABLE_TV) {
                title = "CableTv Payment";
                operatorTitle = "Provider";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.WATER) {
                title = "Water Payment";
                operatorTitle = "Corporation";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.LANDLINE) {
                title = "Landline Payment";
                operatorTitle = "Landline Operator";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.CREDIT_CARD) {
                title = "Credit Card Payment";
                operatorTitle = "Credit card";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.PIPED_GAS) {
                title = "Piped Gas Payment";
                operatorTitle = "Gas company";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.LOAN) {
                title = "Loan Payment";
                operatorTitle = "loan company";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.LPG_GAS) {
                title = "LPG Gas Payment";
                operatorTitle = "gas company";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.FASTAG_PAYMENT) {
                title = "FastTag Payment";
                operatorTitle = "bank";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.BROADBAND) {
                title = "Broadband Payment";
                operatorTitle = "Bradband name";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.INSURANCE_PERMIUM) {
                title = "Insurance Premium";
                operatorTitle = "Insurer";
            }

            if (serviceCategoryModel.serviceType() == ServiceCategoryType.HEALTH_INSURANCE) {
                title = "Health Insurance";
                operatorTitle = "Provided";
            }


            electricityBillPayDto = new ElectricityBillPayDto(operatorTitle, title);
            viewBinding().setElectricityBillPayDto(electricityBillPayDto);

            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();
            userLocation = viewModel().prefManager().getCurrentLocation();

            onShowLoading();
            compositeDisposable().add(viewModel().getOperator(serviceCategoryModel.categoryId())
                    .subscribe(getOperatorResponse -> {
                        operatorBottomSheet.setOperatorCircleItemModel(getOperatorResponse);
                        onHideLoading();

                    }, throwable -> {
                        onHideLoading();

                    }));



        /*    ((BottomSheetDialog) Objects.requireNonNull(operatorBottomSheet.getDialog())).getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

                    currentState = newState;

                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });*/

        }


    }

    @Override
    public ActivityElectricityBillPayBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityElectricityBillPayBinding.inflate(inflater);
    }

    @Override
    public RechargeViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(RechargeViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_electricity_bill_pay;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void goBack() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void openOperator() {
            if (operatorBottomSheet != null)
                operatorBottomSheet.show(getSupportFragmentManager(), "Operator");
    }


    @Override
    public void selectOpCircle(OpCircleItemDto opCircleItemDto) {

        if (operatorBottomSheet != null)
            operatorBottomSheet.dismiss();

        electricityBillPayDto = new ElectricityBillPayDto(operatorTitle, title);
        viewBinding().setElectricityBillPayDto(electricityBillPayDto);

        viewBinding().tvOpName.setText(opCircleItemDto.title());
        selectedOperator = opCircleItemDto;


        Glide.with(viewBinding().getRoot()).load(Uri.parse(selectedOperator.image())).into(viewBinding().ivOpLogo);

        getOperatorDetails(opCircleItemDto);

        // callback for do something
      /*  BottomSheetBehavior.from().setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        btn_bottom_sheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btn_bottom_sheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });*/

    }


    private void getOperatorDetails(OpCircleItemDto opCircleItemDto) {

        onShowLoading();
        compositeDisposable().add(viewModel().getOperatorInfo(opCircleItemDto.id(), serviceCategoryModel.categoryId(), opCircleItemDto.title(), electricityBillPayDto)
                .subscribe(getOperatorInfoResponse -> {

                    viewBinding().setElectricityBillPayDto(electricityBillPayDto);
                    onHideLoading();

                }, throwable -> {
                    onHideLoading();

                }));

    }

    @Override
    public void fetchBill(String txnNumber, String txnCustomerNo, String txnAd1, String txnAd2, String txnAd3, String txnAd4) {

        if (electricityBillPayDto != null) {

            onShowLoading();

            compositeDisposable().add(viewModel().fetchBill(serviceCategoryModel.categoryId(), selectedOperator, txnNumber, electricityBillPayDto.txnNumberName(),
                            electricityBillPayDto.txnNumberRegex(), txnCustomerNo, txnAd1, electricityBillPayDto.txnAd1Name(), electricityBillPayDto.txnAd1Regex(),
                            txnAd2, electricityBillPayDto.txnAd2Name(), electricityBillPayDto.txnAd2Regex(),
                            txnAd3, electricityBillPayDto.txnAd3Name(), electricityBillPayDto.txnAd3Regex(),
                            txnAd4)
                    .subscribe(fetchBillResponse -> {
                        onHideLoading();
                        showAlertDialog("Electricity Bill Payment", fetchBillResponse.message(), true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();


                                    fetchBillResponse.setServiceCategoryModel(serviceCategoryModel);
                                    fetchBillResponse.setElectricityBillPayDto(electricityBillPayDto);

                                    if (fetchBillResponse.status().equals("1")) {
                                        BillPaymentBottomSheet billPaymentBottomSheet = new BillPaymentBottomSheet();
                                        billPaymentBottomSheet.setFetchBillDetails(fetchBillResponse);
                                        billPaymentBottomSheet.show(getSupportFragmentManager(), BillPaymentBottomSheet.class.getName());
                                    }


                                }).show();

                    }, throwable -> {
                        onHideLoading();
                    }));


        }


    }
}