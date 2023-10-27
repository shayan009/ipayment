package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.CustomerItemBinding;
import com.onetechsol.ipayment.databinding.MyCustomerItemClickListener;
import com.onetechsol.ipayment.databinding.MyCustomerOnClickListener;
import com.onetechsol.ipayment.pojo.MyCustomerDetail;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

public class CustomerListAdapter extends BaseRecyclerViewAdapter<CustomerItemBinding, MyCustomerDetail, CustomerListAdapter.CustomerListHolder> {


    private MyCustomerOnClickListener myCustomerOnClickListener;

    public CustomerListAdapter() {
        super();
    }

    public MyCustomerOnClickListener myCustomerOnClickListener() {
        return myCustomerOnClickListener;
    }

    public CustomerListAdapter setMyCustomerOnClickListener(MyCustomerOnClickListener myCustomerOnClickListener) {
        this.myCustomerOnClickListener = myCustomerOnClickListener;
        return this;
    }

    @NonNull
    @Override
    public CustomerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomerItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.customer_item, parent, false);
        return new CustomerListHolder(inflate);
    }


    public class CustomerListHolder extends BaseViewHolder<CustomerItemBinding, MyCustomerDetail> implements MyCustomerItemClickListener {


        public CustomerListHolder(CustomerItemBinding customerItemBinding) {
            super(customerItemBinding);
        }

        @Override
        public void onBind(MyCustomerDetail myCustomerDetail) {
            binding().setMyCustomerDetail(myCustomerDetail);
            binding().setMyCustomerItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void sendSMS(MyCustomerDetail myCustomerDetail) {
            if (myCustomerOnClickListener != null)
                myCustomerOnClickListener.shareLink(0, myCustomerDetail.customerName(), myCustomerDetail.serviceCategoryName(), myCustomerDetail.link(), myCustomerDetail.mobile());
        }

        @Override
        public void sendWhatsApp(MyCustomerDetail myCustomerDetail) {
            if (myCustomerOnClickListener != null)
                myCustomerOnClickListener.shareLink(1, myCustomerDetail.customerName(), myCustomerDetail.serviceCategoryName(), myCustomerDetail.link(), myCustomerDetail.mobile());
        }
    }
}
