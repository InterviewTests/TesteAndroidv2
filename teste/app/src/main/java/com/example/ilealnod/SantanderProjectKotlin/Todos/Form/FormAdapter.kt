package com.example.ilealnod.SantanderProjectKotlin.Todos.Form


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfo
import com.example.ilealnod.SantanderProjectKotlin.R
import kotlinx.android.synthetic.main.form_item.view.*

//Adapter da Recycler view

class FormAdapter(private val notes: List<AccountInfo>, private val context: Context) :
    Adapter<FormAdapter.ViewHolder>() {
    var model:FormActivity?=null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.form_item, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val accountInfo = notes[position]
         model=FormActivity()
        viewHolder.let {

            it.title.text = accountInfo.title
            it.desc.text = accountInfo.desc
            it.date.text = model?.formateDate(accountInfo.date.toString())
            it.value.text =("R$ "+accountInfo.value.toString())
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.tv_title_pagamento!!
        val desc = itemView.tv_desc!!
        val date = itemView.tv_data!!
        val value = itemView.tv_valorconta!!
    }
}
