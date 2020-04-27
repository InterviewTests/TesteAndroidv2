package br.com.raphael.everis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.raphael.everis.R
import br.com.raphael.everis.extensions.format
import br.com.raphael.everis.extensions.toCurrency
import br.com.raphael.everis.model.Statement
import kotlinx.android.synthetic.main.item_statement.view.*

class StatementsAdapter(
    val items: MutableList<Statement>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        return StatementViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context
        if (holder is StatementViewHolder) {
            val item = items[holder.adapterPosition]

            holder.tvTipo.text = item.title
            holder.tvDescricao.text = item.desc
            holder.tvData.text = item.date.format()
            holder.tvValor.text = item.value.toCurrency

        }
    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTipo: TextView by lazy { itemView.tv_tipo }
        val tvData: TextView by lazy { itemView.tv_data }
        val tvDescricao: TextView by lazy { itemView.tv_descricao }
        val tvValor: TextView by lazy { itemView.tv_valor }
    }
}