package com.onetechsol.ipayment.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.DepartmentItemBinding;
import com.onetechsol.ipayment.databinding.DepartmentItemCLickListener;
import com.onetechsol.ipayment.pojo.DepartmentModel;
import com.onetechsol.ipayment.pojo.SellEarnModel;
import com.onetechsol.ipayment.pojo.SellEarnType;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class AffiliateDepartmentAdapter extends BaseRecyclerViewAdapter<DepartmentItemBinding, DepartmentModel, AffiliateDepartmentAdapter.DepartmentViewHolder> {


    public AffiliateDepartmentAdapter() {
        super();
    }

    @Override
    public List<DepartmentModel> getItems() {
        return super.getItems();
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DepartmentItemBinding sellEarnItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.department_item, parent, false);
        return new DepartmentViewHolder(sellEarnItemBinding, getItems());
    }

    public static class DepartmentViewHolder extends BaseViewHolder<DepartmentItemBinding, DepartmentModel> implements DepartmentItemCLickListener {

        public DepartmentViewHolder(DepartmentItemBinding departmentItemBinding, List<DepartmentModel> items) {
            super(departmentItemBinding);
        }


        @Override
        public void onBind(DepartmentModel departmentModel) {
            Log.d("homeItemList", departmentModel.toString());


            binding().setDepartmentModel(departmentModel);
            binding().setDepartmentItemCLickListener(this);

            List<SellEarnModel> sellEarnModels = new ArrayList<>();
            departmentModel.sellEarnModels().forEach(sellEarnModel -> {

                if (sellEarnModel.sellEarnType() == SellEarnType.CREDIT_CARD || sellEarnModel.sellEarnType() == SellEarnType.DEMAT_ACC || sellEarnModel.sellEarnType() == SellEarnType.INVESTMENT || sellEarnModel.sellEarnType() == SellEarnType.SAVINGS_ACC)
                    sellEarnModels.add(sellEarnModel);
            });

            HomeSellEarnAdapter homeSellEarnAdapter = new HomeSellEarnAdapter();
            homeSellEarnAdapter.setItems(sellEarnModels);
            binding().setHomeSellEarnAdapter(homeSellEarnAdapter);

            binding().executePendingBindings();


        }

        @Override
        public void openSellEarnMore() {

        }
    }

}
