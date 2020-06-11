package projects.kevin.bankapp.user.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_bank_statement.view.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.utils.formatMoney
import projects.kevin.bankapp.utils.parseDate
import projects.kevin.bankapp.utils.turnToPositiveValue

class StatementsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var statementList: MutableList<BankStatements> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DietItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bank_statement, parent, false))
    }

    fun setListView(statementList: ArrayList<BankStatements>) {
        this.statementList.addAll(statementList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DietItemViewHolder).bind(statementList[position])
    }

    class DietItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView = itemView
        fun bind(items: BankStatements) {
            with(items) {
                itemView.statementDate.text = parseDate(date)
                itemView.statementDescription.text = desc
                itemView.statementName.text = title
                itemView.statementValue.text = "${DetailActivity.MONEY_TYPE}${formatMoney(turnToPositiveValue(value))}"
            }
        }
    }

}