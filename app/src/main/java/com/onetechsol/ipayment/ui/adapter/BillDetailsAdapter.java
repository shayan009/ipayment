package com.onetechsol.ipayment.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.databinding.BillItemBinding;
import com.onetechsol.ipayment.databinding.BillItemClickListener;
import com.onetechsol.ipayment.databinding.BillPaymentSheetClickListener;
import com.onetechsol.ipayment.pojo.Bill;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerViewAdapter;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseViewHolder;

import org.apache.commons.lang3.StringUtils;


public class BillDetailsAdapter extends BaseRecyclerViewAdapter<BillItemBinding, Bill, BillDetailsAdapter.BillViewHolder> {

    private BillPaymentSheetClickListener billPaymentSheetClickListener;

    public BillDetailsAdapter() {
        super();
    }

    public BillPaymentSheetClickListener billPaymentSheetClickListener() {
        return billPaymentSheetClickListener;
    }

    public BillDetailsAdapter setBillPaymentSheetClickListener(BillPaymentSheetClickListener billPaymentSheetClickListener) {
        this.billPaymentSheetClickListener = billPaymentSheetClickListener;
        return this;
    }

    @NonNull
    @Override
    public BillDetailsAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BillItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.bill_item, parent, false);
        return new BillViewHolder(inflate);

    }

    public class BillViewHolder extends BaseViewHolder<BillItemBinding, Bill> implements BillItemClickListener {


        public BillViewHolder(BillItemBinding billItemBinding) {
            super(billItemBinding);

        }

        @Override
        public void onBind(Bill bill) {

            double billAmount = StringUtils.isNotEmpty(bill.amount()) ? Double.parseDouble(bill.amount()) / 100 : 0.0;
            bill.setAmount(String.valueOf(billAmount));
            binding().setBill(bill);
            binding().setBillItemClickListener(this);
            binding().executePendingBindings();
        }

        @Override
        public void onClickItem(Bill bill) {
            if (billPaymentSheetClickListener != null)
                billPaymentSheetClickListener.submit(String.valueOf(bill.amount()));
        }
    }

}
