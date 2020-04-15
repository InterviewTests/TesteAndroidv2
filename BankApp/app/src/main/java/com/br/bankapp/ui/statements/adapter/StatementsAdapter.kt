package com.br.bankapp.ui.statements.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.bankapp.R
import com.br.bankapp.databinding.StatementItemBinding
import com.br.bankapp.model.Statement

class StatementsAdapter(var items: List<Statement>) : RecyclerView.Adapter<StatementsAdapter.ViewHolder>() , AdapterItemsContract{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding: StatementItemBinding = StatementItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
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

    class ViewHolder(val binding: StatementItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statement: Statement) {
            binding.item = statement
            binding.executePendingBindings()
        }
    }
}
