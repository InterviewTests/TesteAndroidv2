package com.example.projetobank.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetobank.R
import com.example.projetobank.data.model.Statement
import kotlinx.android.synthetic.main.home_item.view.*

class HomeAdapter(
    private val statments: List<Statement>,
    private val context: Context

) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, posicao: Int) {
        val statement = statments[posicao]
        with(holder) {
            data.text = statement.date
            descricao.text = statement.desc
            titulo.text = statement.title
            valor.text = statement.value.toString()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val data = itemView.tvData
        val descricao = itemView.tvDescricao
        val titulo = itemView.tvTitulo
        val valor = itemView.tvValue
    }

}
