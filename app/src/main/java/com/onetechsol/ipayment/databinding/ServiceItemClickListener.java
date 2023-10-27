package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ServiceModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerListener;

public interface ServiceItemClickListener extends BaseRecyclerListener {

    void onClickItem(ServiceModel serviceModel);
}
