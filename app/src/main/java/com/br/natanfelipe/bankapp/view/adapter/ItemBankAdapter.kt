package com.br.natanfelipe.bankapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.natanfelipe.bankapp.R
import com.br.natanfelipe.bankapp.model.Statement
import kotlinx.android.synthetic.main.item_layout.view.*


class ItemBankAdapter (val context : Context, val accountInfo : MutableList<Statement>): RecyclerView.Adapter<ItemBankAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {

        val statement = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ItemViewHolder(statement)
    }

    override fun getItemCount(): Int {
       return accountInfo.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.tv_title.text = accountInfo[position].title
        holder.view.tv_desc.text = accountInfo[position].desc
        holder.view.tv_date.text = accountInfo[position].date
        holder.view.tv_value.text = accountInfo[position].value.toString()
    }

    class ItemViewHolder(val view : View) : RecyclerView.ViewHolder(view)
}