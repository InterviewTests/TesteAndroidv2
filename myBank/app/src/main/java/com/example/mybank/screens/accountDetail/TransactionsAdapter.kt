package com.example.mybank.screens.accountDetail

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.remote.model.RecordTransaction
import com.example.mybank.data.remote.model.RecordTransactionResponse
import com.example.mybank.utils.Helper
import kotlinx.android.synthetic.main.list_item.view.*

class TransactionsAdapter(val context: Context, val transactions: List<RecordTransaction>?) : RecyclerView.Adapter<TransactionsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transaction = transactions!![position]
        holder.setData(transaction, position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(transaction: RecordTransaction, position: Int) {
            itemView.tv_transactionData.text = transaction.date?.let { Helper.formatDate(it) }
            itemView.tv_transactionDescription.text = transaction.desc

            if (transaction.value!! > 0) {
                itemView.tv_transactionValue.text = "R$" + transaction.value
                itemView.tv_transactionValue.setTextColor(Color.GREEN)
            } else {
                itemView.tv_transactionValue.text = "-R$" + (transaction.value * -1)
                itemView.tv_transactionValue.setTextColor(Color.RED)
            }

            itemView.tv_transactionLabel.text = transaction.title
        }
    }
}