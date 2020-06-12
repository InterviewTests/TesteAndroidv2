package com.appdesafioSantander.bank.ui.statements

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingAdapters {
    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, list: List<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterItemsContract) {
                    it.replaceItems(list)
                }
            }
        }
    }

}