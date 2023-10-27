package com.onetechsol.ipayment.databinding;

import android.view.View;

public interface HomeItemCLickListener {
    void openDashboard();

    void openNotification(View view);

    void openSupport(View view);

    void openDiary(View view);

    void openOfferWallet(View view);

    void openSellEarnMore();

    void openServices();

    void startFundTransfer();

    void startBalanceRequest();

    void openTransactionList();
}
