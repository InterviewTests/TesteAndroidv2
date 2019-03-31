package br.com.rms.bankapp.utils.extensions

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat

const val DEFAULT_DURATION = 250L

fun View.fadeIn(duration: Long = DEFAULT_DURATION): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .alpha(1f)

}

fun View.fadeOut(duration: Long = DEFAULT_DURATION): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .alpha(0f)

}

fun View.translateUp(duration: Long = DEFAULT_DURATION, translateValue: Float): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
        .translationY(translateValue)
}

fun View.translateDown(duration: Long = DEFAULT_DURATION, translateValue: Float = 0f): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .setInterpolator(AccelerateInterpolator())
        .translationY(translateValue)
}

fun View.scaleUp(duration: Long = DEFAULT_DURATION): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .setInterpolator(BounceInterpolator())
        .scaleX(1.2f)
        .scaleY(1.2f)
}

fun View.scaleDown(duration: Long = DEFAULT_DURATION): ViewPropertyAnimatorCompat? {
    return ViewCompat.animate(this)
        .setDuration(duration)
        .setInterpolator(BounceInterpolator())
        .scaleX(1f)
        .scaleY(1f)
}