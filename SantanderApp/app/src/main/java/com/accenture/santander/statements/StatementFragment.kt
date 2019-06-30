package com.accenture.santander.statements


import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.santander.R
import com.accenture.santander.databinding.FragmentStatementBinding
import com.accenture.santander.recyclerview.adapter.StatementAdapter
import com.accenture.santander.utils.StatusBar
import com.accenture.santander.viewmodel.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_statement.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatementFragment : Fragment(), StatementContracts.StatementPresenterOutput {

    private lateinit var binding: FragmentStatementBinding
    private lateinit var accounViewModel: AccountViewModel
    private lateinit var statements: StatementViewModel
    private lateinit var statementAdapter: StatementAdapter

    @Inject
    lateinit var iStatementPresenterInput: StatementContracts.StatementPresenterInput


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatementBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerStatementComponents
            .builder()
            .statementModule(StatementModule(activity!!, binding.root, statementFragment = this))
            .build()
            .inject(this)

        lifecycleScope.launch {

            iStatementPresenterInput.statusBarColor(activity!!)

            accounViewModel =
                activity?.run { ViewModelProviders.of(this).get(AccountViewModel::class.java) } ?: AccountViewModel()
            statements = activity?.run { ViewModelProviders.of(this).get(StatementViewModel::class.java) }
                ?: StatementViewModel()

            binding.account = accounViewModel.account
            statementAdapter = StatementAdapter(statements.statementLiveData.value ?: ArrayList())

            binding.statementListStatement.apply {
                adapter = statementAdapter
                layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            }

            statements.statementLiveData.observe(this@StatementFragment,
                Observer {
                    iStatementPresenterInput.cleanAndAddStatements(it)
                    statementAdapter.notifyDataSetChanged()
                })

            accounViewModel.accountLiveData.observe(this@StatementFragment,
                Observer {
                    accounViewModel.account.agency = it.agency
                    accounViewModel.account.balance = it.balance
                    accounViewModel.account.bankAccount = it.bankAccount
                    accounViewModel.account.name = it.name
                })

            iStatementPresenterInput.searchLogout(activity!!)

            statement_img_logout.setOnClickListener {
                iStatementPresenterInput.logout()
            }

            statement_refrash_statements.setOnRefreshListener {
                iStatementPresenterInput.loadStatements()
            }

            iStatementPresenterInput.soliciteData()

            iStatementPresenterInput.loadStatements()
        }
    }

    override fun visibleRefrash() {
        statement_refrash_statements.isRefreshing = true
    }

    override fun goneRefrash() {
        statement_refrash_statements.isRefreshing = false
    }

    override fun apresentationData(user: Account) {
        accounViewModel.accountLiveData.value = user
    }

    override fun loadLogout(drawable: Drawable) {
        binding.statementImgLogout.setImageDrawable(drawable)
    }

    override fun apresentationStatements(statements: MutableList<Statement>) {
        this.statements.statementLiveData.value = statements
    }

    override fun getStatements(): MutableList<Statement> {
        return this.statementAdapter.getStatements()
    }

    override fun cleanData() {
        iStatementPresenterInput.cleanStatements()
        statementAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { iStatementPresenterInput.onDestroyStatusBarColor(it) }
    }

    override fun mensageLogout() {
        Toast.makeText(activity!!, R.string.logout_mensage, Toast.LENGTH_LONG).show()
    }

    override fun errorStatements() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun errorService(mensage: String?) {
        Snackbar.make(binding.root, mensage ?: activity!!.getText(R.string.fail_result_request), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun failRequest() {
        Snackbar.make(binding.root, R.string.fail_request, Snackbar.LENGTH_LONG).show()
    }

    override fun failNetWork() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun failImageLogout() {
        Toast.makeText(activity, R.string.fail_load_image, Toast.LENGTH_LONG).show()
    }
}
