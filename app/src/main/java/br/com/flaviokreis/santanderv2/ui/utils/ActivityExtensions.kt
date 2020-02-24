package br.com.flaviokreis.santanderv2.ui.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.dismissKeyboard() {
    val inputMethodManager = getSystemService( Context.INPUT_METHOD_SERVICE ) as InputMethodManager
    if( inputMethodManager.isAcceptingText )
        inputMethodManager.hideSoftInputFromWindow( this.currentFocus?.windowToken, 0)
}