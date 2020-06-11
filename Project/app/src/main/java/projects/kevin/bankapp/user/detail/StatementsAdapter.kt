package projects.kevin.bankapp.user.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_bank_statement.view.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.utils.formatMoney

class StatementsAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var protocoList: MutableList<BankStatements> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return DietItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bank_statement, parent, false))
    }

    fun setListView(protocoList: ArrayList<BankStatements>) {
        this.protocoList.addAll(protocoList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return protocoList.size
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as DietItemViewHolder).bind(protocoList[position])
    }

    class DietItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView = itemView
        fun bind(items: BankStatements) {
            with(items) {
                itemView.statementDate.text = date
                itemView.statementDescription.text = desc
                itemView.statementName.text = title
                itemView.statementValue.text = formatMoney(value)
            }
        }
    }

}