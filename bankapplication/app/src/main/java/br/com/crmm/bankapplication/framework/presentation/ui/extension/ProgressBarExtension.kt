package br.com.crmm.bankapplication.framework.presentation.ui.extension

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.invisible(){
    visibility = View.INVISIBLE
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}