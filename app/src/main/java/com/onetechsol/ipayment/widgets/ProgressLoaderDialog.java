package com.onetechsol.ipayment.widgets;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;

import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.ui.basefiles.BaseDialog;
import com.onetechsol.ipayment.widgets.alert.OptAnimationLoader;

import java.util.Objects;

public class ProgressLoaderDialog extends BaseDialog {


    private View mDialogView;

    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Animation mOverlayOutAnim;

    public ProgressLoaderDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
    }

    public ProgressLoaderDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_progress_loader;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDialogView = Objects.requireNonNull(getWindow()).getDecorView().findViewById(android.R.id.content);

        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);

        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // dialog overlay fade out
        mOverlayOutAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1 - interpolatedTime;
                getWindow().setAttributes(attributes);
            }
        };
        mOverlayOutAnim.setDuration(120);

    }


    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);

    }

    /**
     * The real Dialog.cancel() will be invoked async-ly after the animation finishes.
     */

    /**
     * The real Dialog.cancel() will be invoked async-ly after the animation finishes.
     */

    @Override
    public void cancel() {
        dismissWithAnim();
    }


    public void dismissWithAnim() {
        mDialogView.startAnimation(mModalOutAnim);
    }
}
