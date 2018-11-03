package com.ygorcesar.testeandroidv2.base.presentation

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseRecyclerViewAdapter<T>(
    private var items: MutableList<T> = mutableListOf(),
    @LayoutRes val layoutResId: Int,
    private val bindView: (view: View, item: T) -> Unit
) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val viewItem = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return BaseViewHolder(viewItem, bindView)
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindView(getItem(position))
    }

    fun setItems(items: MutableList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

    fun addItem(item: T) {
        this.items.add(item)
        notifyItemInserted(itemCount)
    }

    fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

}