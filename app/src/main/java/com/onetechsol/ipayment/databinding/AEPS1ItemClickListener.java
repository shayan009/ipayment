package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.AEPS1TaskModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerListener;

public interface AEPS1ItemClickListener extends BaseRecyclerListener {

    void onClickItem(AEPS1TaskModel aeps1TaskModel);

}
