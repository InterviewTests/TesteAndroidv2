package com.santander.app.core.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: D, position: Int, isLastItem: Boolean)
}