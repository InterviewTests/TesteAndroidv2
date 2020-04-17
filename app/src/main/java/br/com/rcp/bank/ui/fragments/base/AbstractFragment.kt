package br.com.rcp.bank.ui.fragments.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.rcp.bank.R
import br.com.rcp.bank.repositories.base.Repository
import br.com.rcp.bank.ui.fragments.viewmodels.AbstractVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AbstractFragment<Binder: ViewDataBinding, ViewModel: AbstractVM<Repo>, Repo: Repository>: Fragment() {
    protected 				val		TAG			= "[${javaClass.canonicalName}]"
    protected	lateinit 	var		binder		: Binder
    protected 	lateinit	var		service		: ViewModel
    private 	lateinit 	var		progress	: AlertDialog

	override fun onActivityCreated(savedInstanceState: Bundle?) {
        setProgressDialog()
        setObservers()
        super.onActivityCreated(savedInstanceState)
    }

    protected fun showToast(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setObservers() {
        service.toast.observe(viewLifecycleOwner, Observer { showToastMessage(it) })
        service.progress.observe(viewLifecycleOwner, Observer { if (it) { progress.show() } else { progress.cancel() } })
    }

    override fun onPause() {
        progress.cancel()
        super.onPause()
    }

    private fun showToastMessage(message: String) {
        if (message.isNotEmpty()) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setProgressDialog() {
        val		builder		= AlertDialog.Builder(requireContext(), R.style.ProgressDialog)
        val		view		= layoutInflater.inflate(R.layout.dialog_loading, null)
        builder.setView(view)
        builder.setCancelable(false)
        progress			= builder.create()
    }
}