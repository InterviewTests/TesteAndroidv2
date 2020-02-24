package br.com.flaviokreis.santanderv2.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.flaviokreis.santanderv2.R
import br.com.flaviokreis.santanderv2.data.model.Statement
import br.com.flaviokreis.santanderv2.databinding.FragmentStatementBinding

class StatementRecyclerViewAdapter(private val mValues: List<Statement>)
    : RecyclerView.Adapter<StatementRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil
            .inflate<FragmentStatementBinding>(
                LayoutInflater.from(parent.context),
                R.layout.fragment_statement,
                parent,
                false,
                null
            )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.binding.statement = item

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View, val binding: FragmentStatementBinding) : RecyclerView.ViewHolder(mView)
}
