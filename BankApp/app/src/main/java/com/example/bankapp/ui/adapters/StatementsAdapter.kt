package com.example.bankapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.domain.entidades.Statement
import kotlinx.android.synthetic.main.statement_item.view.*

class StatementsAdapter(private val statements: List<Statement>, private val context: Context) :
    RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatementsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.statement_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: StatementsAdapter.ViewHolder, position: Int) {
        val statement = statements[position]

        holder.let {
            holder.dataEmissaoTextView.text = statement.data
            holder.descricaoTextView.text = statement.descricao
            holder.tituloTextView.text = statement.titulo
            holder.valorTextView.text = statement.valor.toString()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dataEmissaoTextView = itemView.textview_dataEmissao
        val descricaoTextView = itemView.textview_descricao
        val tituloTextView = itemView.textview_titulo
        val valorTextView = itemView.textview_valor


    }
}