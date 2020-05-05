package com.paulokeller.bankapp.ui.statements

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulokeller.bankapp.R
import com.paulokeller.bankapp.ui.login.LoginActivity
import com.paulokeller.bankapp.data.models.AppState
import com.paulokeller.bankapp.data.models.Statements
import kotlinx.android.synthetic.main.statements_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext


class StatementsFragment : Fragment(), KodeinAware {
    override val kodeinContext = kcontext<Fragment> (this)
    override val kodein by closestKodein()

    private val viewModelFactory: StatementsViewModelFactory by instance()

    private var adapter: StatementsListAdapter? = null

    companion object {
        fun newInstance() = StatementsFragment()
    }

    private lateinit var viewModel: StatementsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statements_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kodeinTrigger?.trigger()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatementsViewModel::class.java)

        setupRecycler()
        setupObserver()
        setupUserInfo()
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun setupUserInfo() {
        val bundle: Bundle? = activity?.intent?.extras
        if (bundle != null) {
            val userName = bundle.getString("name", "")
            val userAccount = bundle.getString("account", "")
            val userBalance = bundle.getString("balance", "")
            name.text = userName
            account.text = userAccount
            balance.text = userBalance
        }
    }

    private fun setupObserver() {
        viewModel.fetchStatements()

        val resultObserver = Observer<AppState<Statements>> { result ->
            if (result.errorMessage != null) {
                Toast.makeText(activity, result.errorMessage, Toast.LENGTH_LONG).show()
            } else {
                val state = result.data as Statements
                adapter?.setStatementList(state.results)
            }
        }
        viewModel.statementsState.observe(viewLifecycleOwner, resultObserver)
    }

    private fun setupRecycler() {
        adapter = StatementsListAdapter(R.layout.statement_list_item)
        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerViewStatements)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }
}
