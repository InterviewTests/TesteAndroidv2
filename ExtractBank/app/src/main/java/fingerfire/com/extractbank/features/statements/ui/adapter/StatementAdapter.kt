package fingerfire.com.extractbank.features.statements.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fingerfire.com.extractbank.databinding.ItemStatementBinding
import fingerfire.com.extractbank.features.statements.data.StatementsResponse
import fingerfire.com.extractbank.utils.Utils

class StatementAdapter(
    private val statementList: List<StatementsResponse>
) : RecyclerView.Adapter<StatementAdapter.StatementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder(
            ItemStatementBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        val statement = statementList[position]
        with(holder) {
            binding.tvType.text = statement.type
            binding.tvValue.text = Utils.formatToCurrency(statement.value)
            binding.tvDate.text = statement.date
            binding.tvTypeTransaction.text = statement.typeTransaction
        }
    }


    override fun getItemCount(): Int {
        return statementList.size
    }

    class StatementViewHolder(val binding: ItemStatementBinding) :
        RecyclerView.ViewHolder(binding.root)

}
