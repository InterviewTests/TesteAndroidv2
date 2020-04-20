package com.jfgjunior.bankapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jfgjunior.bankapp.*
import com.jfgjunior.bankapp.data.models.User
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userEditText.setText(loginViewModel.user)
        passwordEditText.setText(loginViewModel.password)
        loginButton.isEnabled = false
        observeEvents()
        setupListeners()
    }

    override fun onDestroy() {
        loginViewModel.onDestroy()
        super.onDestroy()
    }

    private fun navigateToAccount(user: User) {
        val actionAccount = LoginFragmentDirections.actionLoginFragmentToAccountFragment(user)
        findNavController().navigate(actionAccount)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            hideKeyboard()
            errorMessage.shouldBeVisible(false)
            loginViewModel.tryLogin()
        }

        userEditText.doOnTextChanged { text, _, _, _ ->
            loginViewModel.user = text.toString()
        }

        passwordEditText.doOnTextChanged { text, _, _, _ ->
            loginViewModel.password = text.toString()
        }
    }

    private fun observeEvents() {
        with(loginViewModel) {
            successLogin.observe(viewLifecycleOwner, Observer { user ->
                navigateToAccount(user)
            })

            failLogin.observe(viewLifecycleOwner, Observer { error ->
                errorMessage.shouldBeVisible(true)
                errorMessage.text =
                    if (error.message.isBlank()) resources.getString(R.string.unknown_error) else error.message
            })

            invalidPassword.observe(viewLifecycleOwner, Observer { isValid ->
                if (isValid) {
                    passwordLayout.hideError()
                    passwordEditText.background.clearColorFilter()
                } else {
                    passwordLayout.showError(R.string.invalid_password)
                }
            })

            invalidUser.observe(viewLifecycleOwner, Observer { isValid ->
                if (isValid) {
                    userLayout.hideError()
                } else {
                    userLayout.showError(R.string.invalid_user)
                }
            })

            showLoading.observe(viewLifecycleOwner, Observer { isVisible ->
                loadingIndicator.shouldBeVisible(isVisible)
                loginButton.isEnabled = !isVisible
            })
        }
    }
}