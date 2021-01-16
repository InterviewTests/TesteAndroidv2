package com.jeanjnap.bankapp.util.extension

import android.app.Activity
import android.os.Build
import android.view.View
import com.jeanjnap.bankapp.R

@Suppress("DEPRECATION")
fun Activity.changeStatusBarColorToWhite() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.statusBarColor = resources.getColor(R.color.white, theme)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}