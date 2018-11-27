package com.rafaelpereiraramos.testeandroidv2.view.statement

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafaelpereiraramos.testeandroidv2.R
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyMoneyMask
import com.rafaelpereiraramos.testeandroidv2.util.StringUtil.Companion.applyShortDateFormat

/**
 * Created by Rafael P. Ramos on 27/11/2018.
 */
class StatementListAdapter(
    private val statements: List<StatementTO>
) : RecyclerView.Adapter<StatementListAdapter.StatementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder = StatementViewHolder.create(parent)

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int)  = holder.bind(statements[position])

    class StatementViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val promptTitle: TextView = view.findViewById(R.id.prompt_title)
        val promptDesc: TextView = view.findViewById(R.id.prompt_desc)
        val promptDate: TextView = view.findViewById(R.id.prompt_date)
        val promptValue: TextView = view.findViewById(R.id.prompt_value)

        fun bind(statement: StatementTO) {
            promptTitle.text = statement.title
            promptDesc.text = statement.desc
            promptDate.text = applyShortDateFormat(statement.date)
            promptValue.text = applyMoneyMask(statement.value)

            if (statement.value < 0)
                promptValue.setTextColor(Color.rgb(124,10,2))
            else
                promptValue.setTextColor(Color.rgb(0, 168, 107))
        }

        companion object {
            fun create(parent: ViewGroup): StatementViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_statement, parent, false)
                return StatementViewHolder(view)
            }
        }
    }
}