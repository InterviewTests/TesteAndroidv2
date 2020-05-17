package br.com.crmm.bankapplication.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.crmm.bankapplication.databinding.LoginFragmentLayoutBinding
import br.com.crmm.bankapplication.presentation.ui.common.AbstractFragment
import br.com.crmm.bankapplication.presentation.ui.login.state.LoginState
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
        viewModel = this@LoginFragment.viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginState()
    }

    private fun loginState(){
        viewModel.loginState.observe(viewLifecycleOwner, Observer {state ->
            val msg = when(state){
                LoginState.InvalidInputUsernameState -> "Invalid username input"
                LoginState.InvalidInputPasswordState -> "Invalid password input"
                LoginState.LoadingState -> "Loading..."
                else -> state.toString()
            }
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        })
    }
}