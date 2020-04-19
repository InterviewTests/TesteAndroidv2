package br.com.rcp.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.rcp.bank.R
import br.com.rcp.bank.databinding.FragmentLoginBinding
import br.com.rcp.bank.repositories.LoginRepository
import br.com.rcp.bank.ui.fragments.base.AbstractFragment
import br.com.rcp.bank.ui.fragments.viewmodels.LoginVM

class LoginFragment: AbstractFragment<FragmentLoginBinding, LoginVM, LoginRepository>() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder                  = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        service                 = ViewModelProvider(this)[LoginVM::class.java]
        binder.service          = service
        binder.lifecycleOwner   = viewLifecycleOwner
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObservers() {
        service.successful.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(R.id.toDetailsFragment, bundleOf("details" to it))
            }
        })
    }
}
