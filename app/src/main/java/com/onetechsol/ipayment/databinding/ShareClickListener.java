package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ContentModel;
import com.onetechsol.ipayment.pojo.GetAffiliateProductDetailData;

import java.util.ArrayList;

public interface ShareClickListener {

    void goBack();

    void onShareLink(GetAffiliateProductDetailData getAffiliateProductDetailData);
}
