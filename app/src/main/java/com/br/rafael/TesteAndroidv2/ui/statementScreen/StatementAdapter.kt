package com.br.rafael.TesteAndroidv2.ui.statementScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.rafael.TesteAndroidv2.R
import com.br.rafael.TesteAndroidv2.Util.Utils
import com.br.rafael.TesteAndroidv2.data.model.Statement

class StatementAdapter(private val listStatement: ArrayList<Statement>,
                       private val context: Context) : RecyclerView.Adapter<StatementAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementAdapter.ViewHolder {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val statement = listStatement[position]
        holder.bind(statement)
    }

    class ViewHolder  (itemStatement : View) : RecyclerView.ViewHolder(itemStatement){

        private var txt_payment : TextView? = null
        private var txt_data_payment : TextView? = null
        private var txt_desc_payment : TextView? = null
        private var txt_value_payment : TextView? = null

        init {
            txt_payment = itemStatement.findViewById(R.id.txt_payment)
            txt_data_payment = itemStatement.findViewById(R.id.txt_data_payment)
            txt_desc_payment = itemStatement.findViewById(R.id.txt_desc_payment)
            txt_value_payment = itemStatement.findViewById(R.id.txt_value_payment)

        }

        fun bind(statement : Statement) {
            txt_payment?.text = statement.title
            txt_desc_payment?.text = statement.description
            txt_value_payment?.text = Utils.converMoney(statement.value)
            txt_data_payment?.text = Utils.stringData(statement.date)
        }
    }


}