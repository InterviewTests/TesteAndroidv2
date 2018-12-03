package com.example.thiagoevoa.bank.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.thiagoevoa.bank.R
import com.example.thiagoevoa.bank.model.Statement
import com.example.thiagoevoa.bank.util.formateDate
import kotlinx.android.synthetic.main.item_statement.view.*

class StatementAdapter (private val objects: MutableList<Statement>): RecyclerView.Adapter<StatementAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false))
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.descTitle.text = objects[position].title
        holder.date.text = formateDate(objects[position].date!!)
        holder.desc.text = objects[position].desc
        holder.value.text = objects[position].value.toString()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var descTitle: TextView = view.txt_desc_title
        var date: TextView = view.txt_date
        var desc: TextView = view.txt_desc
        var value: TextView = view.txt_value
    }
}