package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ReportItem;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerListener;

public interface ReportItemClickListener extends BaseRecyclerListener {

    void onClickItem(ReportItem reportItem);
}
