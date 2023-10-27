package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.CustomerCatetoryItemBinding;
import com.onetechsol.ipayment.databinding.MyCustomerFilterClickListener;
import com.onetechsol.ipayment.databinding.MyCustomerOnClickListener;
import com.onetechsol.ipayment.pojo.MyCustomer;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

public class CustomerFilterListAdapter extends BaseRecyclerViewAdapter<CustomerCatetoryItemBinding, MyCustomer, CustomerFilterListAdapter.CustomerFilterListHolder> {


    private MyCustomerOnClickListener myCustomerOnClickListener;

    public CustomerFilterListAdapter() {
        super();
    }

    public MyCustomerOnClickListener myCustomerOnClickListener() {
        return myCustomerOnClickListener;
    }

    public CustomerFilterListAdapter setMyCustomerOnClickListener(MyCustomerOnClickListener myCustomerOnClickListener) {
        this.myCustomerOnClickListener = myCustomerOnClickListener;
        return this;
    }

    @NonNull
    @Override
    public CustomerFilterListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomerCatetoryItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.customer_catetory_item, parent, false);
        return new CustomerFilterListHolder(inflate);
    }


    public class CustomerFilterListHolder extends BaseViewHolder<CustomerCatetoryItemBinding, MyCustomer> implements MyCustomerFilterClickListener {

        public CustomerFilterListHolder(CustomerCatetoryItemBinding customerItemBinding) {
            super(customerItemBinding);
        }

        @Override
        public void onBind(MyCustomer myCustomer) {
            binding().setMyCustomer(myCustomer);
            binding().setMyCustomerFilterClickListener(this);
            binding().executePendingBindings();
        }


        @Override
        public void selectedPlan(MyCustomer myCustomer) {

            if (myCustomerOnClickListener != null)
                myCustomerOnClickListener.selectedPlan(myCustomer);

        }
    }
}
