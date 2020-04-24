package com.joaoneto.testeandroidv2.mainscreen.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaoneto.testeandroidv2.R
import com.joaoneto.testeandroidv2.mainscreen.model.StatementModel
import kotlinx.android.synthetic.main.adapter_item_list.view.*
import java.text.NumberFormat

class StatementsAdapter(private val statements: List<StatementModel>) :
    RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_item_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(statements[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val operationTypeTextView = itemView.operationTypeTextView
        private val operationDateTextView = itemView.operationDateTextView
        private val operationValueTextView = itemView.operationValueTextView
        private val operationDescriptionTextView = itemView.operationDescriptionTextView


        fun bindView(statement: StatementModel) {

            val date = "${statement.date?.substring(8, 9)}/${statement.date?.substring(
                5,
                6
            )}/${statement.date?.substring(0, 3)}"

            operationDateTextView.text = date


            operationDescriptionTextView.text = statement.desc
            operationTypeTextView.text = statement.title
            operationValueTextView.text = NumberFormat.getCurrencyInstance().format(statement.value)


        }
    }
}