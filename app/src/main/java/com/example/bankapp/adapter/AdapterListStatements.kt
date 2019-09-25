package com.example.bankapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.model.Statement

class AdapterListStatements (private val itens: ArrayList<Statement>):
    RecyclerView.Adapter<ListStatementsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListStatementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListStatementsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: ListStatementsViewHolder, position: Int) {
        val statement: Statement = itens[position]
        holder.bind(statement)
    }

    fun setItens(lItens: List<Statement>){
        itens.addAll(lItens)
    }
}