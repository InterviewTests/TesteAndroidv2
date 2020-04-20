package com.jfgjunior.bankapp

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.*
import android.util.Patterns
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

// region View
fun View.shouldBeVisible(value: Boolean) {
    this.visibility = if (value) View.VISIBLE else View.GONE
}
// endregion

// region TextView
fun TextView.setValueAsCurrency(value: Float) {
    text = NumberFormat.getCurrencyInstance(Locale.getDefault())
        .format(value)
}
// endregion

// region Fragment
fun Fragment.hideKeyboard() {
    view?.let {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
// endregion

// region TextInputLayout
fun TextInputLayout.showError(stringId: Int) {
    error = resources.getString(stringId)
    isErrorEnabled = true
}

fun TextInputLayout.hideError() {
    error = null
    isErrorEnabled = false
}
// endregion