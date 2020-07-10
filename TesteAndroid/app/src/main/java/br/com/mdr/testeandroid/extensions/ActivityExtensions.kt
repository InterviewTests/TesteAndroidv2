package br.com.mdr.testeandroid.extensions

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */

fun Activity.setLightStatusBar(isLight: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window?.decorView?.systemUiVisibility = if (isLight) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
}

fun Activity.setStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window?.statusBarColor = ContextCompat.getColor(this, color)
}