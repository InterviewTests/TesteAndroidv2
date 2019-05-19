package com.br.projetotestesantanter.statementscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.api.model.Statement

class StatementAdapter(private val listStatement: ArrayList<Statement>, private val context: Context) :
    RecyclerView.Adapter<ItemStatementViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemStatementViewHolder {

        return ItemStatementViewHolder(LayoutInflater.from(context).inflate(R.layout.item_statement, parent, false))

    }

    override fun getItemCount(): Int {
        return listStatement.size
    }

    override fun onBindViewHolder(holder: ItemStatementViewHolder, position: Int) {

        holder.bindInfo(listStatement[position])
    }

}
