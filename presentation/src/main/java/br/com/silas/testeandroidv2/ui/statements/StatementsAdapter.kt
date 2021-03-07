package br.com.silas.testeandroidv2.ui.statements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.domain.statements.Statements
import br.com.silas.testeandroidv2.R
import br.com.silas.testeandroidv2.databinding.StatementsItemListBinding
import br.com.silas.testeandroidv2.util.Validate
import br.com.silas.testeandroidv2.util.Validate.formatDate
import java.util.*

class StatementsAdapter(private val statementsList: List<Statements>) :
    RecyclerView.Adapter<StatementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind: StatementsItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.statements_item_list,
            parent,
            false
        )
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.statement = statementsList[position]
        holder.binding.textViewValue.text = Validate.formatDoubleToString(statementsList[position].value, true)
        holder.binding.textViewDate.text = Validate.formatDate(statementsList[position].date)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = statementsList.size


    inner class ViewHolder(val binding: StatementsItemListBinding) :
        RecyclerView.ViewHolder(binding.root)


}