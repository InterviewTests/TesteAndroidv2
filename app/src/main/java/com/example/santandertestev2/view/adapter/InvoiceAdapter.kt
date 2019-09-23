package com.example.santandertestev2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santandertestev2.R
import com.example.santandertestev2.domain.Util.AppUtil
import com.example.santandertestev2.domain.model.controller.InvoiceItem

class InvoiceAdapter(private val myDataset: List<InvoiceItem>): RecyclerView.Adapter<InvoiceAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelType = itemView.findViewById<TextView>(R.id.itemType)
        val labeldescription = itemView.findViewById<TextView>(R.id.itemDesc)
        val labelDate = itemView.findViewById<TextView>(R.id.itemDate)
        val labelValue = itemView.findViewById<TextView>(R.id.itemValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.labelType.text = myDataset[position].title
        holder.labeldescription.text =  myDataset[position].desc
        holder.labelDate.text =  myDataset[position].date
        myDataset[position].value?.let {
            holder.labelValue.text =  "R$" + AppUtil.formatMoneyBr(it)
        }
    }


}