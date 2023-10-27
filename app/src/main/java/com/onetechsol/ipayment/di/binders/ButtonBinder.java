package com.onetechsol.ipayment.di.binders;

import android.widget.Button;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class ButtonBinder {

    @BindingAdapter("android:visibility")
    public static void setVisibility(Button materialButton, int visibility) {
        materialButton.setVisibility(visibility);
    }

    @InverseBindingAdapter(attribute = "android:visibility")
    public static int getVisibility(Button materialButton) {
        return materialButton.getVisibility();
    }

    @BindingAdapter("android:text")
    public static void setText(Button materialButton, CharSequence text) {
        materialButton.setText(materialButton.getText().toString());
    }

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static String getTextString(Button materialButton) {
        return materialButton.getText().toString();
    }

}
