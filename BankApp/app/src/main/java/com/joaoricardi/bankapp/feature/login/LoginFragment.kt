package com.joaoricardi.bankapp.feature.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.joaoricardi.bankapp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    companion object{
        val TAG = "login_fragment"
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpStates()
        setBtnClick()
    }

    private fun setUpStates(){
        viewModel.state.observeForever {state ->
            when(state){
                is LoginViewModel.ScreenState.Initial -> {
                    loginButton.visibility = View.VISIBLE
                    loadingProgressId.visibility  = View.GONE
                    errorFeedBackId.visibility = View.GONE
                }

                is LoginViewModel.ScreenState.Loading -> {
                    loginButton.visibility = View.GONE
                    loadingProgressId.visibility  = View.VISIBLE
                    errorFeedBackId.visibility = View.GONE
                }

                is LoginViewModel.ScreenState.Error -> {
                    loginButton.visibility = View.GONE
                    loadingProgressId.visibility  = View.GONE
                    errorFeedBackId.visibility = View.VISIBLE

                }
            }
        }

    }

    private fun setBtnClick(){
        loginButton.setOnClickListener {
            val email = emailEditId.editableText.toString()
            val password = passwordEditId.editableText.toString()
            viewModel.login(
                email,password
            )
        }
    }

    override fun onResume() {
        emailEditId.editableText.clear()
        passwordEditId.editableText.clear()
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}
