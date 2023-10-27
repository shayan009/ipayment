package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.BeneficiaryModel;
import com.onetechsol.ipayment.ui.basefiles.baseadapter.BaseRecyclerListener;

public interface BeneficiaryItemClickListener extends BaseRecyclerListener {

    void onClickItem(BeneficiaryModel beneficiaryModel);
}
