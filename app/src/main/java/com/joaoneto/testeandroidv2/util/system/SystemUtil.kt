package com.joaoneto.testeandroidv2.util.system

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class SystemUtil {

    companion object {
        fun hideKeyboard(activity: Activity) {
            val view = activity.currentFocus
            if (view != null) {
                val input = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                input.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}