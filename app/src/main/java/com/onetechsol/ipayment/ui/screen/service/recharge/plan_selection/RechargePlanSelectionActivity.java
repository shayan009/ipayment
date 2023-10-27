package com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.ActivityPlanSelectionBinding;
import com.onetechsol.ipayment.databinding.RechargeOnClickListener;
import com.onetechsol.ipayment.pojo.FetchBillDetails;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.pojo.PlanItem;
import com.onetechsol.ipayment.pojo.PlanSelectionMobileDto;
import com.onetechsol.ipayment.pojo.PrepaidPlanMobileDto;
import com.onetechsol.ipayment.pojo.ServiceCategoryModel;
import com.onetechsol.ipayment.session.UserLocation;
import com.onetechsol.ipayment.session.UserLoginSession;
import com.onetechsol.ipayment.ui.adapter.PlanTitleAdapter;
import com.onetechsol.ipayment.ui.adapter.PrepaidPlansAdapter;
import com.onetechsol.ipayment.ui.basefiles.BaseActivity;
import com.onetechsol.ipayment.ui.screen.service.recharge.OperatorCircleBottomSheet;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class RechargePlanSelectionActivity extends BaseActivity<PlanSelectionViewModel, ActivityPlanSelectionBinding> implements RechargeOnClickListener, OperatorCircleBottomSheet.AdapterCallback {

    private ServiceCategoryModel serviceCategoryModel;
    private UserLocation userLocation;
    private OperatorCircleBottomSheet operatorCircleBottomSheet;
    private OperatorCircleBottomSheet operatorBottomSheet;
    private OpCircleItemDto selectedOpCircleItemDto;
    private OpCircleItemDto selectedOperator;
    private String mobile;
    private ArrayList<PlanItem> planItemList;
    private PrepaidPlansAdapter prepaidPlansAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceCategoryModel = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getParcelable(ServiceCategoryModel.class.getName());
        Log.d("serviceCategoryModel.id ->", String.valueOf(serviceCategoryModel.id()));
        Log.d("serviceCategoryModel.categoryId ->", String.valueOf(serviceCategoryModel.categoryId()));

        mobile = getIntent().getBundleExtra(ServiceCategoryModel.class.getName()).getString("mobile");
        UserLoginSession userSession = viewModel().prefManager().getUserSession();

        if (userSession != null) {


            String bearerAuth = "Bearer " + viewModel().prefManager().getLoginToken();
            String userName = viewModel().prefManager().getUsername();
            userLocation = viewModel().prefManager().getCurrentLocation();
            String serviceId = serviceCategoryModel.categoryId();
            planItemList = new ArrayList<>();

            if (serviceCategoryModel.categoryId().equals("1")) {

                planItemList.add(new PlanItem(0, "FULLTT", true));
                planItemList.add(new PlanItem(1, "TOPUP", false));
                planItemList.add(new PlanItem(2, "DATA", false));
                planItemList.add(new PlanItem(3, "SMS", false));
                planItemList.add(new PlanItem(4, "Romaing", false));
                planItemList.add(new PlanItem(5, "FRC", false));
                planItemList.add(new PlanItem(6, "STV", false));

                setUpPlanTitle();

                if (planItemList.size() > 0) {
                    prepaidPlansAdapter = new PrepaidPlansAdapter();
                    prepaidPlansAdapter.setPlanOnClickListener(this);
                    viewBinding().setPrepaidPlansAdapter(prepaidPlansAdapter);
                }

            } else if (serviceCategoryModel.categoryId().equals("2")) {

                viewBinding().tvOpName.setTextSize(14);
                viewBinding().tvChangeOp.setTextSize(14);

            }

            viewBinding().setRechargeOnClickListener(this);

            Glide.with(viewBinding().getRoot()).load(Uri.parse(serviceCategoryModel.imagePath())).into(viewBinding().ivPlanimg);

            PlanSelectionMobileDto planSelectionMobileDto = new PlanSelectionMobileDto(mobile, mobile, serviceCategoryModel.imagePath(), serviceId);
            viewBinding().setPlanSelectionMobileDto(planSelectionMobileDto);

            onShowLoading();

            compositeDisposable().add(viewModel().getOperator(serviceId)
                    .subscribe(getOperatorResponse -> {

                        operatorBottomSheet = new OperatorCircleBottomSheet();
                        operatorBottomSheet.setOperatorCircleItemModel(getOperatorResponse);
                        operatorBottomSheet.setAdapterCallback(this);

                        getOperatorCircle();

                    }, throwable -> {
                        onHideLoading();
                    }));


        }


    }

    private void getOperatorCircle() {
        compositeDisposable().add(viewModel().getOperatorCircle()
                .subscribe(getOperatorCircleResponse -> {

                    operatorCircleBottomSheet = new OperatorCircleBottomSheet();
                    operatorCircleBottomSheet.setOperatorCircleItemModel(getOperatorCircleResponse);
                    operatorCircleBottomSheet.setAdapterCallback(this);
                    onHideLoading();

                }, throwable -> {
                    onHideLoading();
                }));
    }

    private void setUpPlanTitle() {
        PlanTitleAdapter planTitleAdapter = new PlanTitleAdapter();
        planTitleAdapter.setItems(planItemList);
        planTitleAdapter.setPlanOnClickListener(this);

        viewBinding().setPlanTitleAdapter(planTitleAdapter);
        viewBinding().setRechargeOnClickListener(this);
    }


    @Override
    public ActivityPlanSelectionBinding setupViewBinding(LayoutInflater inflater) {
        return ActivityPlanSelectionBinding.inflate(inflater);
    }


    @Override
    public PlanSelectionViewModel setUpViewModel(ViewModelProvider viewModelProvider) {
        return viewModelProvider.get(PlanSelectionViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_plan_selection;
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
    public void openState() {
        if (operatorCircleBottomSheet != null)
            operatorCircleBottomSheet.show(getSupportFragmentManager(), "Circle");
    }

    @Override
    public void proceedPay(String amount) {
        proceedToPay(amount);
    }

    private void proceedToPay(String amount) {
        if (selectedOperator == null) {
            openOperator();
        } else if (selectedOpCircleItemDto == null && serviceCategoryModel.categoryId().equals("1")) {
            openState();
        } else if (StringUtils.isEmpty(amount)) {
            //Toast.makeText(this, "Please enter an amount", //Toast.LENGTH_SHORT).show();
        } else if (!StringUtils.isEmpty(amount) && Integer.parseInt(amount) < 10) {
            //Toast.makeText(this, "Min recharge amount is 10", //Toast.LENGTH_SHORT).show();
        } else {
            FetchBillDetails fetchBillDetails = new FetchBillDetails();
            fetchBillDetails.setTxnNumber(mobile);
            fetchBillDetails.setServiceCategoryModel(serviceCategoryModel);
            fetchBillDetails.setOpCircleItemDto(selectedOperator);
            fetchBillDetails.setCircleItemDto(selectedOpCircleItemDto);
            RechargeBottomSheet rechargeBottomSheet = new RechargeBottomSheet();
            rechargeBottomSheet.setFetchBillDetails(fetchBillDetails);
            rechargeBottomSheet.setAmount(amount);
            rechargeBottomSheet.show(getSupportFragmentManager(), RechargeBottomSheet.class.getName());

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void selectOpCircle(OpCircleItemDto opCircleItemDto) {
        if (opCircleItemDto.type() == 1) {
            viewBinding().tvOpName.setText(opCircleItemDto.title());
            selectedOperator = opCircleItemDto;
            if (operatorBottomSheet != null)
                operatorBottomSheet.dismiss();

            Glide.with(viewBinding().getRoot()).load(Uri.parse(selectedOperator.image())).into(viewBinding().ivPlanimg);


            if (selectedOpCircleItemDto != null && serviceCategoryModel.categoryId().equals("1")) {
                selectedPlan(planItemList.get(0));
            }

            /*if (selectedOpCircleItemDto == null && serviceCategoryModel.categoryId().equals("1")) {
                openState();
            }*/

        } else if (opCircleItemDto.type() == 2) {

            viewBinding().tvStateName.setText(opCircleItemDto.title());
            selectedOpCircleItemDto = opCircleItemDto;
            operatorCircleBottomSheet.dismiss();

            if (selectedOperator != null && serviceCategoryModel.categoryId().equals("1")) {
                selectedPlan(planItemList.get(0));
            }
        }
    }

    @Override
    public void selectedPrepaidPlan(PrepaidPlanMobileDto prepaidMobilePlan) {

        proceedToPay(prepaidMobilePlan.amount());

    }

    @Override
    public void selectedPlan(PlanItem planItem) {

        planItemList.forEach(planItem1 -> {
            planItem1.setSelected(planItem1.id() == planItem.id());
        });

        setUpPlanTitle();

        if (selectedOperator == null) {
            //Toast.makeText(this, "Please select an Operator first", //Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedOpCircleItemDto == null) {
            //Toast.makeText(this, "Please select an Circle first", //Toast.LENGTH_SHORT).show();
            return;
        }

        onShowLoading();
        compositeDisposable().add(viewModel().prepaidPlans(serviceCategoryModel.categoryId(), selectedOperator.id(), selectedOpCircleItemDto.id(), planItem.planName(), mobile)
                .subscribe(prepaidMobilePlansResponse -> {
                    onHideLoading();
                    if (prepaidMobilePlansResponse.isEmpty()) {

                        showAlertDialog("Prepaid Plans", "There is no plans for this type", true)
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                }).show();
                    } else
                        prepaidPlansAdapter.setItems(prepaidMobilePlansResponse, true);


                }, throwable -> {
                    showAlertDialog("Error", throwable.getMessage(), true)
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                            }).show();
                }));

    }


    @Override
    public void onRefresh() {

    }

}