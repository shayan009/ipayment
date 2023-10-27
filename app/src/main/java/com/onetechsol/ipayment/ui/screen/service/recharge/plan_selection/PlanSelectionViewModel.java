package com.onetechsol.ipayment.ui.screen.service.recharge.plan_selection;

import com.onetechsol.ipayment.pojo.GetOperatorCircleRequest;
import com.onetechsol.ipayment.pojo.GetOperatorInfoRequest;
import com.onetechsol.ipayment.pojo.GetOperatorInfoResponse;
import com.onetechsol.ipayment.pojo.GetOperatorRequest;
import com.onetechsol.ipayment.pojo.MobilePrepaidPlansRequest;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.pojo.OperatorCircleItem;
import com.onetechsol.ipayment.pojo.OperatorCircleItemModel;
import com.onetechsol.ipayment.pojo.OperatorItem;
import com.onetechsol.ipayment.pojo.PostRechargeRequest;
import com.onetechsol.ipayment.pojo.PostRechargeResponse;
import com.onetechsol.ipayment.pojo.PrepaidMobilePlan;
import com.onetechsol.ipayment.pojo.PrepaidPlanMobileDto;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlanSelectionViewModel extends BaseViewModel {

    public Observable<OperatorCircleItemModel> getOperator(String serviceId) {

        GetOperatorRequest getOperatorRequest = new GetOperatorRequest(serviceId);

        return iModelRepository().getOperator(getOperatorRequest).map(m -> {

            List<OpCircleItemDto> opCircleItemDtos = new ArrayList<>();

            List<OperatorItem> operatorItems = m.data().operatorItemList();

            for (int i = 0; i < operatorItems.size(); i++) {
                OperatorItem operatorItem = operatorItems.get(i);
                opCircleItemDtos.add(new OpCircleItemDto(operatorItem.id(), operatorItem.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + operatorItem.img().replaceAll("\\/", "/"), 1));
            }

            opCircleItemDtos.removeIf(p -> p.id().equalsIgnoreCase("0"));

            return new OperatorCircleItemModel(true, "Select Operator", "Search Operator", "Enter your text", opCircleItemDtos);
        }).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<OperatorCircleItemModel> getOperatorCircle() {

        GetOperatorCircleRequest getOperatorCircleRequest = new GetOperatorCircleRequest(ApiConstant.BASIC_VERSION);

        return iModelRepository().getOperatorCircle(getOperatorCircleRequest).map(m -> {

            List<OpCircleItemDto> opCircleItemDtos = new ArrayList<>();

            List<OperatorCircleItem> operatorItems = m.data().operatorCircleItems();

            for (int i = 0; i < operatorItems.size(); i++) {

                OperatorCircleItem operatorItem = operatorItems.get(i);
                opCircleItemDtos.add(new OpCircleItemDto(operatorItem.id(), operatorItem.label(), "", 2));

            }

            opCircleItemDtos.removeIf(p -> p.id().equalsIgnoreCase("0"));


            return new OperatorCircleItemModel(true, "Select Circle", "Search Circle", "Enter your text", opCircleItemDtos);

        }).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GetOperatorInfoResponse> getOperatorInfo(String operator, String serviceCode) {

        GetOperatorInfoRequest getOperatorCircleRequest = new GetOperatorInfoRequest(operator, serviceCode);

        return iModelRepository().getOperatorInfo(getOperatorCircleRequest).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<PrepaidPlanMobileDto>> prepaidPlans(String serviceCode, String operator, String circle, String plan, String txnPlan) {

        MobilePrepaidPlansRequest mobilePrepaidPlansRequest = new MobilePrepaidPlansRequest(serviceCode, serviceCode, circle, plan, txnPlan);

        return iModelRepository().prepaidPlans(mobilePrepaidPlansRequest).map(m -> {

            List<PrepaidPlanMobileDto> prepaidPlanMobileDtos = new ArrayList<>();

            List<PrepaidMobilePlan> prepaidMobilePlans = m.data().prepaidMobilePlanList();

            for (int i = 0; i < prepaidMobilePlans.size(); i++) {

                PrepaidMobilePlan prepaidMobilePlan = prepaidMobilePlans.get(i);

                PrepaidPlanMobileDto prepaidPlanMobileDto = new PrepaidPlanMobileDto();
                prepaidPlanMobileDto.setId(String.valueOf(i));
                prepaidPlanMobileDto.setValidityPassed(prepaidMobilePlan.validity().equalsIgnoreCase("N.A."));
                prepaidPlanMobileDto.setAmount(prepaidMobilePlan.rs());
                prepaidPlanMobileDto.setValidity(prepaidMobilePlan.validity());
                prepaidPlanMobileDto.setDetails(prepaidMobilePlan.desc());
                prepaidPlanMobileDtos.add(prepaidPlanMobileDto);
            }

            return prepaidPlanMobileDtos;

        }).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<PostRechargeResponse> rechargeMobile(String circle, String serviceCode, String operator, String txnNumber, String txnCustomerNo, String txnAd1, String txnAd2, String txnAd3, String txnAd4, String txnAmt, String txnBillNo, String txnCustomerName, String txnFetch, String securityS, String tpin, String otp) {

        PostRechargeRequest postRechargeRequest = new PostRechargeRequest(circle, serviceCode, operator, txnNumber, txnCustomerNo, txnAd1, txnAd2, txnAd3, txnAd4, txnAmt, txnBillNo, txnCustomerName, txnFetch, securityS, tpin, otp);

        return iModelRepository().rechargeMobile(postRechargeRequest).observeOn(AndroidSchedulers.mainThread());
    }


}
