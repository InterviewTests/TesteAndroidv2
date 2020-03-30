package com.example.bankapp.features.details.presentantion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.features.details.data.service.StatementResponse
import com.example.base.extensions.toBrazilCalendar
import com.example.base.extensions.toMoneyFormat

class RecyclerAdapter( private val list: List<StatementResponse>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_card,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: StatementResponse = list[position]
        holder.payment.text = data.title
        holder.date.text = data.date.toBrazilCalendar()
        holder.type.text = data.desc
        holder.value.text = data.value.toMoneyFormat()
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val payment: TextView = itemView.findViewById(R.id.payment)
        val date: TextView = itemView.findViewById(R.id.date)
        val type: TextView = itemView.findViewById(R.id.type)
        val value: TextView = itemView.findViewById(R.id.value)
    }

}
