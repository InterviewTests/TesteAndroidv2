package com.br.projetotestesantanter.refactor.statementScreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.Utils

class StatementAdapter(private val listStatement: ArrayList<StatementModel.Statement>,
                       private val context: Context) : RecyclerView.Adapter<StatementAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_statement,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return listStatement.size
    }

    override fun onBindViewHolder(itemStatement: ViewHolder, position: Int) {

        var statement = listStatement[position]
        itemStatement.bind(statement)
    }

    class ViewHolder  (itemStatement : View) : RecyclerView.ViewHolder(itemStatement){

        var txt_payment : TextView? = null
        var txt_data_payment : TextView? = null
        var txt_desc_payment : TextView? = null
        var txt_value_payment : TextView? = null

        init {
            txt_payment = itemStatement.findViewById(R.id.txt_payment)
            txt_data_payment = itemStatement.findViewById(R.id.txt_data_payment)
            txt_desc_payment = itemStatement.findViewById(R.id.txt_desc_payment)
            txt_value_payment = itemStatement.findViewById(R.id.txt_value_payment)

        }

        fun bind(statement : StatementModel.Statement) {
            txt_payment?.text = statement.title
            txt_desc_payment?.text = statement.description
            txt_value_payment?.text = Utils.converMoney(statement.value)
            txt_data_payment?.text = Utils.stringData(statement.date)
        }
    }

}
