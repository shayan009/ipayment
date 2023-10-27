package com.onetechsol.ipayment.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.PrepaidPlanItemBinding;
import com.onetechsol.ipayment.databinding.PrepaidPlanItemClickListener;
import com.onetechsol.ipayment.databinding.RechargeOnClickListener;
import com.onetechsol.ipayment.pojo.Benefits;
import com.onetechsol.ipayment.pojo.PrepaidPlanMobileDto;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class PrepaidPlansAdapter extends BaseRecyclerViewAdapter<PrepaidPlanItemBinding, PrepaidPlanMobileDto, PrepaidPlansAdapter.PrepaidPlanHolder> {


    private RechargeOnClickListener rechargeOnClickListener;


    public PrepaidPlansAdapter() {
    }

    public RechargeOnClickListener planOnClickListener() {
        return rechargeOnClickListener;
    }

    public PrepaidPlansAdapter setPlanOnClickListener(RechargeOnClickListener rechargeOnClickListener) {
        this.rechargeOnClickListener = rechargeOnClickListener;
        return this;
    }

    @NonNull
    @Override
    public PrepaidPlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PrepaidPlanItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.prepaid_plan_item, parent, false);
        return new PrepaidPlanHolder(inflate);
    }


    public class PrepaidPlanHolder extends BaseViewHolder<PrepaidPlanItemBinding, PrepaidPlanMobileDto> implements PrepaidPlanItemClickListener {


        public PrepaidPlanHolder(PrepaidPlanItemBinding prepaidPlanItemBinding) {
            super(prepaidPlanItemBinding);

        }

        @Override
        public void onBind(PrepaidPlanMobileDto prepaidMobilePlan) {

            Log.d("planItem", prepaidMobilePlan.toString());

            if (prepaidMobilePlan.details().contains("--")) {

                StringBuilder desc = new StringBuilder();

                String[] splitPlans = prepaidMobilePlan.details().split("--");
                desc.append(splitPlans[0]);
                String data = "";
                String sms = "";

                if (splitPlans.length >= 2) {
                    String[] split = splitPlans[1].replaceAll("//s", "").split(":");
                    if (split.length == 2)
                        data = split[1];
                }

                if (splitPlans.length >= 3) {
                    String[] split = splitPlans[2].replaceAll("//s", "").split(":");
                    if (split.length == 2)
                        sms = split[1];
                }


                desc.append(" | DATA : ").append(data);
                desc.append(" | SMS : ").append(sms);
                Log.d("data", desc.toString());

                List<Benefits> benefitsList = new ArrayList<>();

                for (int j = 3; j < splitPlans.length; j++) {
                    String[] benefits = splitPlans[j].replaceAll("//s", "").split(":");

                    if (benefits.length == 1) {
                        benefitsList.add(new Benefits(j, benefits[0], "", ""));
                    } else if (benefits.length == 2) {
                        benefitsList.add(new Benefits(j, benefits[0], benefits[1], ""));
                    }

                }

                Log.d("data", benefitsList.toString());

                prepaidMobilePlan.setDataAvailable(!StringUtils.isEmpty(data));
                prepaidMobilePlan.setData(data);
                prepaidMobilePlan.setBenefitsList(benefitsList);
                prepaidMobilePlan.setDetails(desc.toString());
            }

            binding().setPrepaidPlanMobileDto(prepaidMobilePlan);
            binding().setPrepaidPlanItemClickListener(this);
            binding().executePendingBindings();

        }

        @Override
        public void selectedPrepaidPlan(PrepaidPlanMobileDto prepaidMobilePlan) {
            //Toast.makeText(itemView.getContext(), prepaidMobilePlan.amount(), //Toast.LENGTH_SHORT).show();
            rechargeOnClickListener.selectedPrepaidPlan(prepaidMobilePlan);
        }
    }

}
