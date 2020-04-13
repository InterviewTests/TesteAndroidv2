package pt.felipegouveia.bankapp.presentation.statements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.databinding.StatementItemBinding
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementPresentation
import java.util.*

class StatementsAdapter: RecyclerView.Adapter<StatementsAdapter.StatementsViewHolder>() {

    private var statementList = ArrayList<StatementPresentation>()

    fun update(newList: List<StatementPresentation>) {
        statementList.clear()
        val diffResult = DiffUtil.calculateDiff(StatementDiffCallback(statementList, newList))
        diffResult.dispatchUpdatesTo(this)
        statementList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementsViewHolder {
        val binding: StatementItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.statement_item, parent, false)
        return StatementsViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return if(statementList.isNotEmpty()) statementList.size else 0
    }

    override fun onBindViewHolder(holder: StatementsViewHolder, position: Int) {
       val statement = statementList[position]
        statement.let {
            holder.apply {
                bind(it)
            }
        }
    }

    class StatementsViewHolder(private val binding: StatementItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statement: StatementPresentation?) {
            binding.apply {
                this.statement = statement
            }
        }
    }
}