package com.onetechsol.ipayment.databinding;

import android.view.View;

public interface DashboardItemClickListener {

    void openDiary(View view);

    void openReferEarn(View view);

    void openSupport(View view);

    void openLeaderBoard(View view);

    void openCreditScore(View view);

    void goBack();

    void openProfile();

    void upgrade();

    void onCancelClick();

    void upgradeNow(String amount);

}
