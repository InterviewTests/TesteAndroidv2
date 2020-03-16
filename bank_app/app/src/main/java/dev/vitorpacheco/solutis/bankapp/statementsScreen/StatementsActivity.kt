package dev.vitorpacheco.solutis.bankapp.statementsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.google.android.material.card.MaterialCardView
import dev.vitorpacheco.solutis.bankapp.BankApp
import dev.vitorpacheco.solutis.bankapp.R
import dev.vitorpacheco.solutis.bankapp.extensions.format
import dev.vitorpacheco.solutis.bankapp.loginScreen.UserAccount
import kotlinx.android.synthetic.main.activity_statements.*
import kotlinx.android.synthetic.main.item_statement.view.*

interface StatementsActivityInput {
    fun displayStatementsData(viewModel: StatementsViewModel?)
}

class StatementsActivity : AppCompatActivity(), StatementsActivityInput {

    var output: StatementsInteractorInput? = null
    var router: StatementsRouter? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var idlingResource: CountingIdlingResource

    private var userAccount: UserAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        userAccount = intent.getParcelableExtra(BankApp.APP_INTENT_ACCOUNT_EXTRA)

        viewManager = LinearLayoutManager(this)
        viewAdapter = StatementAdapter(arrayListOf())

        recyclerView = findViewById<RecyclerView>(R.id.statementsList).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        idlingResource = CountingIdlingResource(StatementsActivity::class.java.simpleName)

        StatementsConfigurator.INSTANCE.configure(this)

        userAccount?.userId?.let {
            accountValue.text =
                getString(R.string.agencia_conta, userAccount?.bankAccount, userAccount?.agency)
            balanceValue.text = userAccount?.balance?.format()

            val statementsRequest = StatementsRequest(it)
            output?.fetchStatementsData(statementsRequest)
        }
    }

    override fun displayStatementsData(viewModel: StatementsViewModel?) {
        viewModel?.statements?.let {
            (recyclerView.adapter as StatementAdapter).updateDataSet(newDataset = ArrayList(it))
        }
    }

    companion object {
        var TAG = StatementsActivity::class.java.simpleName
    }

    class StatementAdapter(var dataSet: ArrayList<StatementViewModel>) :
        RecyclerView.Adapter<StatementAdapter.StatementViewHolder>() {

        class StatementViewHolder(itemView: MaterialCardView) : RecyclerView.ViewHolder(itemView)

        fun updateDataSet(newDataset: ArrayList<StatementViewModel>) {
            dataSet = newDataset
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_statement, parent, false) as MaterialCardView

            return StatementViewHolder(itemView)
        }

        override fun getItemCount() = dataSet.size

        override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
            val statement = dataSet[position]

            holder.itemView.statementTitle.text = statement.title
            holder.itemView.statementDesc.text = statement.desc
            holder.itemView.statementDate.text = statement.date
            holder.itemView.statementValue.text = statement.value
        }

    }

}