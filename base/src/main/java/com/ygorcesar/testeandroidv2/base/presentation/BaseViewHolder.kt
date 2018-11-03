package com.ygorcesar.testeandroidv2.base.presentation

import android.support.v7.widget.RecyclerView
import android.view.View

class BaseViewHolder<T>(val view: View, private val executeBinding: (view: View, item: T) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bindView(item: T) {
        this.executeBinding(view, item)
    }
}