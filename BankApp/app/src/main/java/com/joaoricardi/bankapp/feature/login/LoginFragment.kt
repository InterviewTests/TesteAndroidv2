package com.joaoricardi.bankapp.feature.login

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.joaoricardi.bankapp.R
import com.joaoricardi.bankapp.feature.home.HomeFragment
import com.joaoricardi.bankapp.models.login.UserAccont
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
                    resetState()
                }
            }
        }

    }

    private fun navigateToHome(userAccont: UserAccont){
        val manager = activity!!.supportFragmentManager
        val homeFragment = HomeFragment.newInstance(userAccont)
        manager.beginTransaction()
            .replace(
                R.id.container,
                homeFragment
            )
            .addToBackStack(HomeFragment.TAG)
            .commit()
    }

    private fun setUpObserver(){
        viewModel.userAccont.observe(this, Observer {
            if(it != null) {
                navigateToHome(it)
                viewModel.clearUserAccoount()

            }
        })
    }

    private fun resetState(){
        Handler().postDelayed({
            viewModel.resetState()
        },3000)
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
