package br.com.teste.santander.statements.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.teste.santander.R
import br.com.teste.santander.databinding.ActivityStatementsBinding
import br.com.teste.santander.model.Statement
import br.com.teste.santander.model.UserAccount
import br.com.teste.santander.statements.StatementsConfigurator
import br.com.teste.santander.statements.interactor.StatementsInteractor
import br.com.teste.santander.statements.view.adapter.StatementsListAdapter

class StatementsActivity : AppCompatActivity(), StatementsView {
    companion object {
        val USER_ACCOUNT_PARAM = "user_account_param"
    }

    var interactor: StatementsInteractor? = null
    private lateinit var binding: ActivityStatementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statements)

        init()
    }

    override fun init() {
        StatementsConfigurator.INSTANCE.configure(this)

        val userAccount = intent.extras?.getParcelable<UserAccount>(USER_ACCOUNT_PARAM)
        binding.statemetsHeader.user = userAccount

        if (userAccount?.userId != null && interactor != null) {
            binding.progressBar.visibility = View.VISIBLE
            interactor!!.getStatements(this, userAccount.userId)
        }
    }

    override fun onRequestStatementsSuccess(itens: List<Statement>) {
        binding.progressBar.visibility = View.GONE
        //binding.root.findViewById<RecyclerView>(R.id.recyclerViewStatements).adapter = StatementsListAdapter(this, itens)
        binding.recyclerViewStatements.adapter = StatementsListAdapter(this, itens)
        binding.recyclerViewStatements.layoutManager = LinearLayoutManager(this)
    }

    override fun onRequestStatementsError(error: String) {
        binding.progressBar.visibility = View.GONE
        showMessage(error)
        logout(binding.root)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun logout(view: View) {
        super.onBackPressed()
    }
}
