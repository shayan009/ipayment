package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.PlanItem;
import com.onetechsol.ipayment.pojo.PrepaidPlanMobileDto;

public interface RechargeOnClickListener {

    void goBack();

    void openOperator();

    void openState();

    void proceedPay(String amount);

    void selectedPlan(PlanItem planItem);

    void selectedPrepaidPlan(PrepaidPlanMobileDto prepaidMobilePlan);
}
