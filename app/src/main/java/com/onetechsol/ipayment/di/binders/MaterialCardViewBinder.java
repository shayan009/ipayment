package com.onetechsol.ipayment.di.binders;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.google.android.material.card.MaterialCardView;

public class MaterialCardViewBinder {

    @BindingAdapter("android:visibility")
    public static void setVisibility(MaterialCardView view, int visibility) {
        view.setVisibility(visibility);
    }

    @InverseBindingAdapter(attribute = "android:visibility")
    public static int getVisibility(MaterialCardView view) {
        return view.getVisibility();
    }
}
