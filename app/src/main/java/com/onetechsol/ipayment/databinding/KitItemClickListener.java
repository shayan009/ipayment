package com.onetechsol.ipayment.databinding;

import com.onetechsol.ipayment.pojo.KitData;

public interface KitItemClickListener {

    void onClickItem(KitData kitData);

    void buyKit(KitData kitData);
}
