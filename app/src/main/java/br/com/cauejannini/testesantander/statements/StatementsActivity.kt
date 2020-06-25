package br.com.cauejannini.testesantander.statements

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cauejannini.testesantander.R
import br.com.cauejannini.testesantander.commons.Utils
import br.com.cauejannini.testesantander.login.UserAccount
import kotlinx.android.synthetic.main.activity_statements.*

interface StatementsActivityInput {
    fun displayStatements(statements: List<Statement>)
    fun displayStatementsError(message: String)
    fun displayUserData(userDataModel: UserDataModel)
}

class StatementsActivity : AppCompatActivity(), StatementsActivityInput {

    companion object {
        val EXTRA_KEY_USER_ACCOUNT = "EXTRA_KEY_USER_ACCOUNT"
    }

    lateinit var output: StatementsInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        StatementsConfigurator.configure(this)

        val userAccount = intent.getSerializableExtra(EXTRA_KEY_USER_ACCOUNT) as UserAccount?

        fetchStatements(userAccount)

        swipeRefreshLayout.setOnRefreshListener {
            fetchStatements(userAccount)
        }

    }

    fun fetchStatements(userAccount: UserAccount?) {

        output.fetchStatements(userAccount)
        swipeRefreshLayout.isRefreshing = true
    }

    override fun displayStatements(statements: List<Statement>) {
        rvStatements.layoutManager = LinearLayoutManager(this)
        rvStatements.adapter = StatementsRecyclerViewAdapter(statements)
        swipeRefreshLayout.isRefreshing = false
    }

    override fun displayStatementsError(message: String) {
        Utils.showToast(this, message)
        swipeRefreshLayout.isRefreshing = false
    }

    override fun displayUserData(userDataModel: UserDataModel) {
        tvUserName.text = userDataModel.userName
        tvContaNumero.text = userDataModel.agenciaConta
        tvSaldo.text = userDataModel.saldo
    }

}