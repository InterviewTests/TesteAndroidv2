package com.jisellemartins.bank.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jisellemartins.bank.databinding.ItemListBinding
import com.jisellemartins.bank.model.Statements

class ListStatementsAdapter(private val statements: List<Statements>) :
    RecyclerView.Adapter<ListStatementsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val textType = binding.textType
        val textTitle = binding.textTitle
        val textValue = binding.textValue
        val textDate = binding.textDate
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textType.text = statements[position].type
        viewHolder.textTitle.text = statements[position].title
        viewHolder.textValue.text = "R$ " + statements[position].value.toString()
        viewHolder.textDate.text = statements[position].date
    }
    override fun getItemCount() = statements.size
}