package com.onetechsol.ipayment.di.binders;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class ImageViewBinder {

    @BindingAdapter("android:visibility")
    public static void setVisibility(ImageView view, int visibility) {
        view.setVisibility(visibility);
    }

    @InverseBindingAdapter(attribute = "android:visibility")
    public static int getVisibility(ImageView view) {
        return view.getVisibility();
    }

}
