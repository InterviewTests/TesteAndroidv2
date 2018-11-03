package com.ygorcesar.testeandroidv2.base.extensions

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun RecyclerView?.setLinearLayout(
    orientation: Int = LinearLayoutManager.VERTICAL,
    hasFixedSize: Boolean = false,
    isReverse: Boolean = false
) {
    this?.apply {
        setHasFixedSize(hasFixedSize)
        layoutManager = LinearLayoutManager(this.context, orientation, isReverse)
    }
}