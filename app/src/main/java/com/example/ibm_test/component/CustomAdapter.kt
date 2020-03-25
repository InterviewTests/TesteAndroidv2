package com.example.ibm_test.component

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomAdapter<T>(
    private var items: MutableList<T>,
    private val recyclerViewItem: AdapterItem<T>? = null,
    private val itemsViewHolder: (inflater: LayoutInflater, parent: ViewGroup, viewType: Int) -> RecyclerView.ViewHolder,
    private val viewType: (position: Int) -> Int? = { null }
) : RecyclerView.Adapter<CustomViewHolder<T>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return itemsViewHolder(inflater, parent, viewType) as CustomViewHolder<T>
    }

    override fun onBindViewHolder(holder: CustomViewHolder<T>, position: Int) {
        val item = items[position]

        holder.setup(item, recyclerViewItem)
        recyclerViewItem?.position(position)
    }

    override fun getItemViewType(position: Int): Int =
        if (viewType(position) == null) {
            if (items[position] is ProgressViewHolder.LoadMore)
                1
            else
                0
        } else
            viewType(position) ?: -1


    override fun getItemCount(): Int = items.size


    fun loadMoreData(list: MutableList<T>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}

interface AdapterItem<T> {
    fun onClickItem(it: T?)
    fun position(position: Int)
}


class ProgressViewHolder(itemView: View) : CustomViewHolder<ProgressViewHolder.LoadMore>(itemView) {
    override fun buildView(recyclerViewItem: AdapterItem<LoadMore>?) {}
    data class LoadMore(val isLoadMore: Boolean = true)
}

abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var item: T? = null

    fun setup(item: T, recyclerViewItem: AdapterItem<T>?) {
        this.item = item
        buildView(recyclerViewItem)
    }


    fun get(): T? = item


    abstract fun buildView(recyclerViewItem: AdapterItem<T>?)
}