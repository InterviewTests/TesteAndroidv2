package br.com.rcp.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

    private fun setBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }
}
