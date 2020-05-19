package br.com.crmm.bankapplication.framework.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.crmm.bankapplication.databinding.LoginFragmentLayoutBinding
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.UserAccountResponseDTO
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractFragment
import br.com.crmm.bankapplication.framework.presentation.ui.extension.*
import br.com.crmm.bankapplication.framework.presentation.ui.login.state.LoginViewState
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

    override fun onChangeState() {
        viewModel.loginViewState.observe(viewLifecycleOwner, Observer { state ->
            safeRunOnUiThread {
                with(binding){
                    loginProgress.invisible()
                    when(state){
                        is LoginViewState.InvalidInputUsernameViewState -> {
                            textInputLayoutUsername.invalidEmailOrCpf()
                        }
                        is LoginViewState.InvalidInputPasswordViewState -> {
                            textInputLayoutUsername.hideError()
                            textInputLayoutPassword.invalidPassword()
                        }
                        is LoginViewState.LoadingViewState -> {
                            textInputLayoutUsername.hideError()
                            textInputLayoutPassword.hideError()
                            loginProgress.show()
                        }
                        is LoginViewState.SuccessfullyLoginState -> {
                            onLoginSuccessfully(state.userAccountResponseDTO)
                        }
                        is LoginViewState.UnsuccessfullyLoginState -> {
                            toast("Login failed! - Code: ${state.code}")
                        }
                        is LoginViewState.UnmappedErrorState -> {
                            toast("Unmapped error!")
                        }
                        is LoginViewState.NoneState -> {}
                    }
                }
            }
        })
    }

    private fun onLoginSuccessfully(userAccountResponseDTO: UserAccountResponseDTO){
        toast("Logged!")
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStatementFragment(userAccountResponseDTO))
        viewModel.resetViewState()
    }
}