package br.com.bank.android.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.bank.android.R
import br.com.bank.android.databinding.ItemStatamentsBinding
import br.com.bank.android.home.presentation.data.Stataments

class StatamentsViewHolder private constructor(private val binding: ItemStatamentsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(stataments: Stataments) {
        binding.stataments = stataments
    }

    companion object {
        fun create(parent: ViewGroup): StatamentsViewHolder {
            val itemMenuBinding: ItemStatamentsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_stataments, parent, false
            )
            return StatamentsViewHolder(itemMenuBinding)
        }
    }
}