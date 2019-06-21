package com.santander.app.core.util.extension

import android.view.View
import android.widget.TextView

fun View?.onClick(action: () -> Unit) {
    this?.setOnClickListener {
        action.invoke()
    }
}

fun TextView?.getString() = this?.text?.toString().orEmpty()

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}