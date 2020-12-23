package com.solinftec.desafiosantander_rafaelpimenta.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingAdapters {
    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, list: List<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterItensContract) {
                    it.replaceItems(list)
                }
            }
        }
    }

}