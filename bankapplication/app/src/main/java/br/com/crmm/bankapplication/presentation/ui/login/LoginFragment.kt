package br.com.crmm.bankapplication.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.crmm.bankapplication.databinding.LoginFragmentLayoutBinding
import br.com.crmm.bankapplication.presentation.ui.common.AbstractFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : AbstractFragment(){

    private lateinit var binding: LoginFragmentLayoutBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = LoginFragmentLayoutBinding.inflate(
        inflater, container, false
    ).apply {
        lifecycleOwner = this@LoginFragment.viewLifecycleOwner
    }

}