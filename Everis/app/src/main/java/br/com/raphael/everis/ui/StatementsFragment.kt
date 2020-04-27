package br.com.raphael.everis.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.raphael.everis.R
import br.com.raphael.everis.extensions.toCurrency
import br.com.raphael.everis.ui.adapters.StatementsAdapter
import br.com.raphael.everis.viewmodel.StatementsViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_statements.*

class StatementsFragment : Fragment() {

    private val adapterStatements by lazy {
        StatementsAdapter(
            mutableListOf()
        )
    }

    private lateinit var viewModel: StatementsViewModel
    private val args: StatementsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatementsViewModel::class.java)

        println("-----------" + args.userAccount)

        observerError()
        observerSuccess()
        observerLoading()

        setupRecyclerView()

        popularData()
    }

    private fun observerError(){
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.atencao))
                    .setContentText(it)
                    .setConfirmText(getString(R.string.ok))
                    .show()
            }
        })
    }

    private fun observerSuccess(){
        viewModel.success.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterStatements.items.clear()
                adapterStatements.items.addAll(it)
                adapterStatements.notifyDataSetChanged()
            }
        })
    }

    private fun observerLoading(){
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            pb_loading.isVisible = it
        })
    }

    private fun setupRecyclerView(){
        rv_statements.apply {
            layoutManager = LinearLayoutManager(this@StatementsFragment.activity, LinearLayoutManager.VERTICAL,false)
            adapter = this@StatementsFragment.adapterStatements
        }
    }

    private fun popularData(){
        tv_nome.text = args.userAccount.name
        tv_conta.text = getString(R.string.agencia_conta, args.userAccount.agency, args.userAccount.bankAccount)
        tv_saldo.text = args.userAccount.balance.toCurrency

        viewModel.getStatements(args.userAccount.userId)
    }
}
