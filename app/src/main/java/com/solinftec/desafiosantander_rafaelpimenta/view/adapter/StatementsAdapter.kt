package com.solinftec.desafiosantander_rafaelpimenta.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solinftec.desafiosantander_rafaelpimenta.databinding.ListItemBinding
import com.solinftec.desafiosantander_rafaelpimenta.model.Statement
import com.solinftec.desafiosantander_rafaelpimenta.util.AdapterItensContract

class StatementsAdapter(var items: List<Statement>) : RecyclerView.Adapter<StatementsAdapter.ViewHolder>() ,
    AdapterItensContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding: ListItemBinding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun replaceItems(list: List<*>) {
        this.items = list as List<Statement>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statement: Statement) {
            binding.details = statement
            binding.executePendingBindings()
        }
    }
}