package br.com.rcp.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.rcp.bank.R
import br.com.rcp.bank.database.models.Account
import br.com.rcp.bank.databinding.FragmentDetailsBinding
import br.com.rcp.bank.repositories.AccountRepository
import br.com.rcp.bank.ui.adapters.StatementsAdapter
import br.com.rcp.bank.ui.fragments.base.AbstractFragment
import br.com.rcp.bank.ui.fragments.viewmodels.DetailsVM
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

class DetailsFragment : AbstractFragment<FragmentDetailsBinding, DetailsVM, AccountRepository>() {
	private	lateinit	var account	: Account
	private	lateinit	var	adapter	: StatementsAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binder                  	= DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
		service                 	= ViewModelProvider(this)[DetailsVM::class.java]
		account						= arguments?.getSerializable("details") as Account
		adapter						= StatementsAdapter()
		binder.model          		= account
		binder.lifecycleOwner   	= viewLifecycleOwner
		binder.collection.adapter	= adapter
		setHasOptionsMenu(true)
		return binder.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		CoroutineScope(Dispatchers.IO).launch { service.getStatements(account.identifier) }
		setObservers()
		setToolbarTitle()
		toolbar.inflateMenu(R.menu.main_menu)
		toolbar.setOnMenuItemClickListener {
			findNavController().navigate(R.id.toLoginFragment)
			true
		}
		super.onViewCreated(view, savedInstanceState)
	}

	private fun setToolbarTitle() {
		toolbar.title = account.name
	}

	private fun setObservers() {
		service.collection.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
			adapter.setData(it)
			adapter.notifyDataSetChanged()
		})
	}
}

@BindingAdapter("currency")
fun TextView.setPrice(price: Double?) {
	text = price?.absoluteValue?.toBRLCurrency() ?: 0.0.toBRLCurrency()
}

private fun Double.toBRLCurrency() : String {
	val		locale		= Locale("pt", "BR")
	val		formatter	= NumberFormat.getCurrencyInstance(locale)
	formatter.currency	= Currency.getInstance("BRL")
	return formatter.format(this)
}