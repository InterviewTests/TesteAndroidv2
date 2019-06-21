package com.santander.app.core.util.extension

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.santander.app.R

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, it, duration).show()
    }
}

fun Context.toast(message: Int?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, getString(it), duration).show()
    }
}

data class ActionButton(val content: String, val action: (() -> Unit)? = null)

fun Context.displayDialog(
    title: String? = null,
    message: String,
    positiveAction: ActionButton = ActionButton(content = getString(R.string.action_ok)),
    negativeAction: ActionButton? = null
): AlertDialog {
    return AlertDialog.Builder(this).apply {
        title?.run { setTitle(this) }
        setMessage(message)

        positiveAction.run {
            setPositiveButton(content) { _, _ ->
                action?.invoke()
            }
        }

        negativeAction?.run {
            setNegativeButton(content) { _, _ ->
                action?.invoke()
            }
        }

    }.show()
}

