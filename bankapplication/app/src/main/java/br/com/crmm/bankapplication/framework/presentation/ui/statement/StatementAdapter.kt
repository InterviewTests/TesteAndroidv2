package br.com.crmm.bankapplication.framework.presentation.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.crmm.bankapplication.databinding.StatementViewHolderLayoutBinding
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.StatementDataResponseDTO

class StatementAdapter: RecyclerView.Adapter<StatementViewHolder>() {

    private val statements = ArrayList<StatementDataResponseDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder(
            StatementViewHolderLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = statements.size

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.setItem(statements[position])
    }

    fun addAll(statements: List<StatementDataResponseDTO>){
        clearAll()
        this.statements.addAll(statements)
        notifyDataSetChanged()
    }

    private fun clearAll() = statements.clear()
}