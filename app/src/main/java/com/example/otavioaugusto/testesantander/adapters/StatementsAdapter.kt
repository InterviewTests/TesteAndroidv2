package com.example.otavioaugusto.testesantander.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.model.StatementListItem
import kotlinx.android.synthetic.main.item_statements.view.*
import java.text.FieldPosition

class StatementsAdapter(val lista:List<StatementListItem>, val contexto:Context) : RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_statements, parent, false)
        val view = ViewHolder(v)
        return  view

    }

    override fun getItemCount(): Int {
       return lista.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = lista[position].title
        holder.desc.text = lista[position].desc
        holder.date.text = lista[position].date
        holder.value.text = lista[position].value.toString()

    }


    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val title = itemView.txtItemTitle
        val desc = itemView.txtItemDescricao
        val date = itemView.txtItemDate
        val value = itemView.txtItemValue

    }
}