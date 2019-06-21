package com.santander.app.core.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, VH : BaseViewHolder<D>>(private var dataSource: MutableList<D>) : RecyclerView.Adapter<VH>(), Filterable {

    private val original: MutableList<D> = dataSource

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(getItemView(), parent, false)
        return instantiateViewHolder(view)
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(item = dataSource[position], position = position, isLastItem = position == dataSource.size - 1)
    }

    abstract fun getItemView(): Int

    abstract fun instantiateViewHolder(view: View): VH

    open fun comparableFilter(item: D, query: String): Boolean {
        return item.toString().contains(query)
    }

    fun getDataSource() = dataSource

    fun getItem(position: Int): D = dataSource[position]

    fun add(item: D) {
        val position = dataSource.count()
        dataSource.add(item)
        notifyItemInserted(position)
    }

    fun addAll(list: List<D>?) {
        list?.let {
            val positionStart = dataSource.count()
            dataSource.addAll(list)
            notifyItemRangeInserted(positionStart, list.count())
        }
    }

    fun remove(item: D) {
        val position = dataSource.indexOf(item)
        if (position > -1) {
            dataSource.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val results = when {
                    constraint.isNullOrEmpty() -> {
                        original
                    }

                    else -> {
                        original.filter {
                            comparableFilter(item = it, query = constraint.toString())
                        }.toMutableList()
                    }
                }
                return FilterResults().apply {
                    values = results
                    count = results.size
                }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {

                @Suppress("UNCHECKED_CAST")
                (results?.values as? MutableList<D>)?.let {
                    dataSource = it
                    notifyDataSetChanged()
                }
            }
        }
    }
}