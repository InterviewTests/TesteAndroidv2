package com.appdesafioSantander.bank.ui.statements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdesafioSantander.bank.databinding.StatementItemBinding
import com.appdesafioSantander.bank.model.Statement


class StatementsAdapter(var items: List<Statement>) : RecyclerView.Adapter<StatementsAdapter.ViewHolder>() ,
    AdapterItemsContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding: StatementItemBinding = StatementItemBinding.inflate(inflater, parent, false)
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

    class ViewHolder(val binding: StatementItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statement: Statement) {
            binding.item = statement
            binding.executePendingBindings()
        }
    }
}
