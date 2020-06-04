package com.joaoricardi.bankapp.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaoricardi.bankapp.R
import com.joaoricardi.bankapp.extensions.asBRL
import com.joaoricardi.bankapp.extensions.toBrDate
import com.joaoricardi.bankapp.models.home.StateMent
import kotlinx.android.synthetic.main.statement_list_item.view.*


class HomeRecyclerAdapter: RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>(){

    var stateMents = listOf<StateMent>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.statement_list_item,parent, false)

        return  ViewHolder(view)
    }

    override fun getItemCount(): Int  = stateMents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = stateMents[position]

        holder.bind(news)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bind(stateMent: StateMent){
            itemView.titletextId.text = stateMent.title
            itemView.descTextId.text = stateMent.desc
            itemView.dataTextId.text = stateMent.date.toBrDate()
            itemView.valorTextId.text = stateMent.value.asBRL(true)
        }
    }

}