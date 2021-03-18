package com.accenture.bankapp.screens.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.bankapp.R
import com.accenture.bankapp.network.models.Statement
import timber.log.Timber
import java.text.MessageFormat
import java.text.NumberFormat
import java.util.*

internal class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.textTitle)
    val textDate: TextView = itemView.findViewById(R.id.textDate)
    val textDesc: TextView = itemView.findViewById(R.id.textDesc)
    val textValue: TextView = itemView.findViewById<TextView>(R.id.textValue)
}

class StatementsRecyclerAdapter(
    private var context: Context,
    private var listStatements:  MutableList<Statement>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Timber.i("onCreateViewHolder: Creating View Holder")

        return StatementViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.card_statement,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StatementViewHolder) {
            val statement = listStatements[position]

            Timber.i("onBindViewHolder: Binding Holder $holder with Statement $statement")

            val formattedDate = MessageFormat("{2}/{1}/{0}").format(statement.date.split("-").toTypedArray())
            val formattedValue = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(statement.value)

            holder.textTitle.text = statement.title
            holder.textDate.text = formattedDate
            holder.textDesc.text = statement.desc
            holder.textValue.text = formattedValue
        }
    }

    override fun getItemCount(): Int {
        return listStatements.size
    }
}