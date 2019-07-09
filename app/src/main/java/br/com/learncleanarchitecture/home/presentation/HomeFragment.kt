package br.com.learncleanarchitecture.home.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import br.com.learncleanarchitecture.R
import br.com.learncleanarchitecture.login.presentation.Login
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private var login: Login? = null
    private lateinit var tvMoney: TextView
    private lateinit var tvName: TextView
    private lateinit var tvAccount: TextView

    private lateinit var listView: ListView

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        login = this.arguments?.getParcelable("login")

        btn_exit.setOnClickListener {
            activity?.finish()
        }

        bindViews(view!!)
        diplayData(login)
        loadStatment()
    }

    private fun loadStatment() {
        progress_circular.visibility = View.VISIBLE
        login?.userId?.let {
            val homeRequest = HomeRequest(it)
            viewModel.loadStatments(homeRequest)

            viewModel.listStatment.observe(this,
                Observer<List<Statment>> {
                    loadList(it)
                })
        }
    }

    private fun loadList(list: List<Statment>?) {
        progress_circular.visibility = View.GONE
        list?.let {
            setList(it)
        }
    }

    private fun setList(it: List<Statment>) {

        val adapter = HomeAdapter(activity!!, it)
        listView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun diplayData(login: Login?) {
        login?.let {
            tvName.text = login.name
            tvAccount.text = "${login.bankAccount} / ${login.agency}"
            tvMoney.text = "R$ ${login.balance}"
        }
    }

    private fun bindViews(view: View) {
        tvName = view.findViewById(R.id.tv_name)
        tvAccount = view.findViewById(R.id.tv_account)
        tvMoney = view.findViewById(R.id.tv_money)
        listView = view.findViewById(R.id.listView)
    }
}
