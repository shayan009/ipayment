package com.onetechsol.ipayment.ui.basefiles;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.sidesheet.SideSheetCallback;
import com.google.android.material.sidesheet.SideSheetDialog;

public abstract class BaseSideSheet extends SideSheetDialog {

    Context context;

    public BaseSideSheet(@NonNull Context context) {
        super(context);
        this.context = context;

    }

    public BaseSideSheet(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public abstract int getLayoutRes();

    public abstract View getLayoutView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBehavior().addCallback(new SideSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View sheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View sheet, float slideOffset) {

            }
        });
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        setContentView(getLayoutView());


    }


}
