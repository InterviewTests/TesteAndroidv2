
package com.jeanjnap.bankapp.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = compare(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = compare(oldItem, newItem)

    private fun compare(oldItem: T, newItem: T) = oldItem == newItem
}
