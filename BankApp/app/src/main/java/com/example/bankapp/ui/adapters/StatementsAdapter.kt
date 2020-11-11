package com.example.bankapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.util.Conversores
import com.example.domain.entities.Statement
import kotlinx.android.synthetic.main.statement_item.view.*
import kotlin.math.absoluteValue

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
        return statements.size
    }

    override fun onBindViewHolder(holder: StatementsAdapter.ViewHolder, position: Int) {
        val statement = statements[position]

        holder.let {
            holder.dataEmissaoTextView.text = Conversores().converterDataddMMyyyy(statement.data!!)
            holder.tituloTextView.apply {
                text = statement.titulo
                if (statement.valor!! < 0) this.setTextColor(Color.parseColor("#ff4d4d"))
                else this.setTextColor(Color.parseColor("#32ff7e"))
            }
            holder.descricaoTextView.text = statement.descricao
            holder.valorTextView.text =
                Conversores().converterValorParaMoeda(statement.valor!!.absoluteValue)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataEmissaoTextView = itemView.textview_dataEmissao
        val descricaoTextView = itemView.textview_descricao
        val tituloTextView = itemView.textview_titulo
        val valorTextView = itemView.textview_valor
    }
}