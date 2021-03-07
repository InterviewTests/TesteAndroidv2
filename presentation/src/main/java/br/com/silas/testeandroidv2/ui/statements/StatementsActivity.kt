package br.com.silas.testeandroidv2.ui.statements

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.silas.domain.statements.Statements
import br.com.silas.domain.user.User
import br.com.silas.testeandroidv2.R
import br.com.silas.testeandroidv2.databinding.ActivityStatementsBinding
import br.com.silas.testeandroidv2.util.Validate
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatementsBinding
    private lateinit var statementsAdapter: StatementsAdapter
    private val statementsViewModel: StatementsViewModel by viewModel()

    companion object {
        private const val EXTRA_USER = "extra_user"
        fun start(context: Context, user: User) {
            val intent = Intent(context, StatementsActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statements)
//        Validate.statusBarTranslucent(this.window)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val w: Window = window
//            w.setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }

        initializeItensAppBar()
        fetchStatements()
        observerStatements()
        observerErrorStatement()
        observerLoading()
        logout()

    }

    private fun getUserExtra() = intent.getSerializableExtra(EXTRA_USER) as User

    private fun initializeItensAppBar() {
        val user = getUserExtra()

        binding.itemStatements.textViewUserName.text = user.name
        binding.itemStatements.textViewAccount.text =
            user.bankAccount.plus(" / " + Validate.formatAgency(user.agency.toString()))
        binding.itemStatements.textviewBalance.text =
            Validate.formatDoubleToString(user.balance, true)
    }

    private fun fetchStatements() {
        statementsViewModel.loadStatements(getUserExtra().id)
    }

    private fun observerStatements() {
        statementsViewModel.result.observe(this, {
            initializeRecyclerView(it)
        })
    }

    private fun observerErrorStatement() {
        statementsViewModel.errorStatements.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun initializeRecyclerView(statementsList: List<Statements>) {

        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewStatements.layoutManager = linearLayoutManager
        statementsAdapter = StatementsAdapter(statementsList)

        binding.recyclerViewStatements.adapter = statementsAdapter
    }

    private fun observerLoading() {
        statementsViewModel.loading.observe(this, {
            when (it) {
                true -> binding.progressBarStatements.visibility = View.VISIBLE
                false -> binding.progressBarStatements.visibility = View.GONE
            }
        })
    }

    private fun logout() {
        binding.itemStatements.imageButtonLogout.setOnClickListener {
            this.finish()
        }
    }
}