package br.com.ibm.teste.android.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.ibm.teste.android.R
import br.com.ibm.teste.android.services.models.Statement
import br.com.ibm.teste.android.utils.Utils

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:54
 */
class StatementAdapter (private val data: List<Statement>,
                        private val context: Context) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_statements, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = data[position]

        holder.mTitle.text = statement.title
        holder.mDescription.text = statement.desc
        holder.mDate.text = Utils.formatDate(statement.date)
        holder.mValue.text = Utils.formatNumber(statement.value)
        if (holder.mValue.text.contains("-")) {
            holder.mValue.setTextColor(holder.itemView.resources.getColor(R.color.colorRed))
        } else {
            holder.mValue.setTextColor(holder.itemView.resources.getColor(R.color.colorGreen))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.statement_title)
        val mDescription: TextView = itemView.findViewById(R.id.statement_description)
        val mDate: TextView = itemView.findViewById(R.id.statement_date)
        val mValue: TextView = itemView.findViewById(R.id.statement_value)
    }
}