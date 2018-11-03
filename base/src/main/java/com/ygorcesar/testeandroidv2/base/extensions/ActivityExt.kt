package com.ygorcesar.testeandroidv2.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.widget.Toast

inline fun <reified T : Activity> Context.startActivity(clearBackStack: Boolean = false) =
    startActivity(Intent(this, T::class.java).apply {
        if (clearBackStack) {
            clearBackStack()
        }
    })

fun Intent.clearTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

fun Intent.clearBackStack(): Intent = apply {
    clearTask()
    newTask()
}

fun Activity.toast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, stringResId, duration).show()

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()