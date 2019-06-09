package com.schaefer.bankapp.currency_screen.activity

import FormatValue
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schaefer.bankapp.R
import com.schaefer.bankapp.model.statement.StatementModel
import com.schaefer.bankapp.util.Constants.Companion.DATE_FORMATED
import com.schaefer.bankapp.util.Constants.Companion.DATE_SERVER
import kotlinx.android.synthetic.main.item_currency.view.*
import java.text.SimpleDateFormat

class StatementAdapter(
    private val statements: List<StatementModel>,
    private val context: Context
) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = statements[position]
        holder.titleItem.text = statement.title
        holder.description.text = statement.desc

        //Format date
        var format = SimpleDateFormat(DATE_SERVER)
        val newDate = format.parse(statement.date)
        format = SimpleDateFormat(DATE_FORMATED)
        holder.date.text = format.format(newDate)

        holder.value.text = FormatValue.formatFloatToString(statement.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statements.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleItem: TextView = itemView.textView_payment_title
        val description: TextView = itemView.textView_payment_description
        val date: TextView = itemView.textView_date
        val value: TextView = itemView.textView_value
    }
}