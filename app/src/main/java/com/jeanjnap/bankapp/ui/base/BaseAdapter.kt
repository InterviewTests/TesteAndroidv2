package com.jeanjnap.bankapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

open class BaseAdapter<T>(
    @LayoutRes val view: Int,
    val onBind: (T, ViewDataBinding) -> Unit
) : ListAdapter<T, BaseViewHolder>(BaseDiffUtil<T>()) {

    private var items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            onBind(it, holder.viewDataBinding)
            return
        }
    }

    override fun submitList(list: List<T>?) {
        if (items.isNotEmpty()) items.clear()
        list?.let { items.addAll(it) }
        super.submitList(items)
        notifyDataSetChanged()
    }
}
