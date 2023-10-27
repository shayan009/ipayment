package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.Bill;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerListener;

public interface BillItemClickListener extends BaseRecyclerListener {

    void onClickItem(Bill bill);

}
