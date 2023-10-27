package com.onetechsol.ipayment.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.ui.basefiles.BaseDialog;

public class UserDefinedMaterialAlertDialog extends BaseDialog {

    public int prop1 = R.layout.dialog_xml;

    public UserDefinedMaterialAlertDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_xml;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
