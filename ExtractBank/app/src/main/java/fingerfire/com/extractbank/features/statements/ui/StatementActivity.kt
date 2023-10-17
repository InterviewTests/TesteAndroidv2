package fingerfire.com.extractbank.features.statements.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.extractbank.R
import fingerfire.com.extractbank.databinding.ActivityStatementBinding
import fingerfire.com.extractbank.features.login.ui.LoginActivity
import fingerfire.com.extractbank.features.statements.data.StatementsResponse
import fingerfire.com.extractbank.features.statements.ui.adapter.StatementAdapter
import fingerfire.com.extractbank.features.statements.ui.viewstate.StatementViewState
import fingerfire.com.extractbank.model.User
import fingerfire.com.extractbank.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatementBinding
    private lateinit var statementAdapter: StatementAdapter
    private val viewModel: StatementViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        observeStatement()
        viewModel.getStatementsForUser("1")

        val extras = intent.extras
        if (extras != null && extras.containsKey("USER")) {
            val user = extras.getSerializable("USER") as User
            loadUser(user)
        }

        binding.ivLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadUser(user: User) {
        binding.apply {
            tvName.text = user.name
            tvBalance.text = Utils.formatToCurrency(user.amount)
            tvAccount.text = Utils.formatAccountNumber(user.agency, user.account)
        }
    }

    private fun observeStatement() {
        viewModel.statementLiveData.observe(this) { statementViewState ->
            when (statementViewState) {
                is StatementViewState.Success -> {
                    setupRecyclerView()
                    initAdapter(statementViewState.data)
                }

                is StatementViewState.Error -> {
                    Utils.showError(getString(R.string.error_statement_load), this)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvStatement.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvStatement.setHasFixedSize(true)
    }

    private fun initAdapter(statementsList: List<StatementsResponse>) {
        statementAdapter = StatementAdapter(statementsList)
        binding.rvStatement.adapter = statementAdapter
    }
}
