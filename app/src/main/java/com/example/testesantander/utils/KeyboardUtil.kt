package com.example.testesantander.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

class KeyboardUtil{
    companion object {

        fun hideKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        fun showKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0)
        }

    }
}