package com.develop.zupp_bank.presentation.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.develop.zupp_bank.R
import com.develop.zupp_bank.domain.models.StatementList
import com.develop.zupp_bank.framework.Tools.formatDate
import com.develop.zupp_bank.framework.Tools.formatForBrazilianCurrency
import kotlinx.android.synthetic.main.item_list.view.*


class DataAdapter(val context: Context, val statementList: List<StatementList>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        val statment = statementList[position]


        holder?.let {

            it.titleConta.text = statment.title
            it.valorConta.text = formatForBrazilianCurrency(statment.value)
            it.dataConta.text = formatDate(statment.date)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleConta = itemView.tvNameConta
        val dataConta = itemView.tvData
        val valorConta = itemView.tvValor

    }

}