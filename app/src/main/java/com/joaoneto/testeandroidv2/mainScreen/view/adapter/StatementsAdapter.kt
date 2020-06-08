package com.joaoneto.testeandroidv2.mainScreen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaoneto.testeandroidv2.R
import com.joaoneto.testeandroidv2.databinding.AdapterItemListBinding
import com.joaoneto.testeandroidv2.mainScreen.model.StatementModel
import com.joaoneto.testeandroidv2.util.system.Formatter

class StatementsAdapter(private val statements: List<StatementModel>) :
    RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_item_list,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(statements[position])

    class ViewHolder(private val binding: AdapterItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(statement: StatementModel) {

            binding.executePendingBindings()
            binding.statement = statement
            binding.formatter = Formatter()

        }
    }
}