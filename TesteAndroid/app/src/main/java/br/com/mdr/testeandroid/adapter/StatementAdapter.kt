package br.com.mdr.testeandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.testeandroid.databinding.DashboardStatementItemBinding
import br.com.mdr.testeandroid.model.business.Statement

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */
class StatementAdapter: RecyclerView.Adapter<StatementAdapter.StatementViewHolder>(), AdapterItemsContract {

    private var itens: MutableList<Statement>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DashboardStatementItemBinding.inflate(inflater, parent, false)
        return StatementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        val statement = itens!![position]
        holder.bind(statement)
    }

    override fun getItemCount() = if (itens != null) itens!!.size else 0

    @Suppress("UNCHECKED_CAST")
    override fun replaceItens(list: List<*>) {
        this.itens = list as MutableList<Statement>
        notifyDataSetChanged()
    }

    class StatementViewHolder(private val binding: DashboardStatementItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(statement: Statement) {
            binding.statement = statement
        }
    }
}