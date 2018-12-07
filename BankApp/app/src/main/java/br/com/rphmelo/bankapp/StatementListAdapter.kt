package br.com.rphmelo.bankapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_statement_list.view.*

class StatementListAdapter(private val list: List<StatementModel>,
                           private val context: Context) : RecyclerView.Adapter<StatementListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_statement_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(itemList: StatementModel) {
            val title = itemView.lblItemTitle
            val description = itemView.lblDescription
            val date = itemView.lblDate
            val value = itemView.lblValue

            title?.text = itemList.title
            description?.text = itemList.description
            date?.text = itemList.date
            value?.text = itemList.value.toString()
        }
    }
}