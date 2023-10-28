package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.ContentModel;

public interface ShareContentOnClickListener {

    void onClickItem(ContentModel ContentModel);

    void shareWhatsApp(ContentModel contentModel);
    void shareDirectLink(ContentModel contentModel);

}
