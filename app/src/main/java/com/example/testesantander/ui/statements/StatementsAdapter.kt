package com.example.testesantander.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testesantander.R
import com.example.testesantander.domain.model.StatementsData
import com.example.testesantander.utils.MoneyUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_statements.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class StatementsAdapter(private val statementsList: Array<StatementsData>): RecyclerView.Adapter<StatementsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_statements, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.tvDate.context
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(statementsList[position].date)
        sdf.applyPattern("dd/MM/yyyy")
        val value = statementsList[position].value



        holder.tvTitle.text = statementsList[position].title
        holder.tvDesc.text = statementsList[position].desc
        holder.tvDate.text = sdf.format(date).toString()
        holder.tvValue.text = MoneyUtil.moneyPtBr(value)
    }


    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer
}