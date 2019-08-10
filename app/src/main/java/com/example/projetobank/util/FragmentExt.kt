package com.example.projetobank.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction

const val TAG_DIALOG = "dialog"

fun Fragment.pegaFragmentTranscation(): FragmentTransaction {
    val ft = fragmentManager!!.beginTransaction()
    val prev = fragmentManager!!.findFragmentByTag(TAG_DIALOG)
    if (prev != null) {
        ft.remove(prev)
    }
    ft.addToBackStack(null)
    return ft
}
