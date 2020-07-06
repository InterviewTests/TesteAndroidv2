package br.com.mdr.testeandroid.extensions

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

/**
 * @author Marlon D. Rocha
 * @since 05/07/2020
 */

fun MaterialButton.changeBackgroundColor(@ColorRes color: Int, context: Context) {
    val buttonColor = ContextCompat.getColor(context, color)
    backgroundTintList = ColorStateList.valueOf(buttonColor)
}
