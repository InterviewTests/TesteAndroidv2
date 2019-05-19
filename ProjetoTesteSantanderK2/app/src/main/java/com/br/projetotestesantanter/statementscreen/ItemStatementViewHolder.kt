package com.br.projetotestesantanter.statementscreen

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.Utils
import com.br.projetotestesantanter.api.model.Statement

class ItemStatementViewHolder (itemStatements : View) : RecyclerView.ViewHolder(itemStatements) {

     var txt_payment : TextView ? = null
     var txt_data_payment : TextView ? = null
     var txt_desc_payment : TextView ? = null
     var txt_value_payment : TextView? = null

    init {
        txt_payment = itemStatements.findViewById(R.id.txt_payment)
        txt_data_payment = itemStatements.findViewById(R.id.txt_data_payment)
        txt_desc_payment = itemStatements.findViewById(R.id.txt_desc_payment)
        txt_value_payment = itemStatements.findViewById(R.id.txt_value_payment)

    }

    fun bindInfo(statement : Statement) {
        txt_payment?.text = statement.title
        txt_desc_payment?.text = statement.desc
        txt_value_payment?.text = Utils.converMoney(statement.value)
        txt_data_payment?.text = Utils.stringData(statement.date)

    }
}