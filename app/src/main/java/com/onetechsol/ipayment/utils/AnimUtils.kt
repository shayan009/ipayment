package com.techexactly.tryyourluckowner.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.TranslateAnimation


class AnimUtils(private val context: Context?) {
    companion object {

        private val mShortAnimationDuration = 150
        fun animateViewToHide(view: View) {
            view.animate()
                ?.alpha(0f)
                ?.setDuration(mShortAnimationDuration.toLong())
                ?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = View.GONE
                    }
                })
        }

        // slide the view from below itself to the current position
        fun slideDown(view: View, llDomestic: View) {
            animateViewToShow(llDomestic)
            val animation = ObjectAnimator.ofFloat(view, "translationY", 0f)
            animation.duration = 300
            animation.start()
        }

        fun animateViewToShow(view: View) {
            view.alpha = 0f
            view.visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            view.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration.toLong())
                .setListener(null)
        }

        // slide the view from its current position to below itself
        fun slideUp(view: View, llDomestic: View) {
            animateViewToHide(llDomestic)

            val animation = ObjectAnimator.ofFloat(view, "translationY", 0f)
            animation.duration = 300
            animation.start()

        }

        fun TranslateAnim(tv_bottom_status: View, i: Int) {
            val animObj = TranslateAnimation(tv_bottom_status.width.toFloat(), 0f, 0f, 0f)
            animObj.duration = i.toLong()
            tv_bottom_status.startAnimation(animObj)
        }

    }

}
