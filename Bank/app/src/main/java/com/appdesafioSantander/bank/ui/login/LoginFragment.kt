package com.appdesafioSantander.bank.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.appdesafioSantander.bank.R
import com.appdesafioSantander.bank.model.Login
import com.appdesafioSantander.bank.model.LoginViewModel
import com.appdesafioSantander.bank.utils.ViewUtils

class LoginFragment : Fragment(), LoginListener {

    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.loginListener = this
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginBtn = view.findViewById<Button>(R.id.login_btn)
        loginBtn.setOnClickListener {
            loginViewModel.onLoginButtonClick(view)
        }
    }

    override fun onStarted(view: View) {
        super.onStarted(view)
        ViewUtils.show(view)
    }

    override fun onSuccess(view: View, login: LiveData<Login>) {
        super.onSuccess(view, login)
        login.observe(this, Observer {
            if(it.error.isNullOrEmpty()){
                val bundle = bundleOf("user" to it.userAccount)
                view.findNavController().navigate(R.id.action_Login_to_Statements, bundle)
            } else {
                ViewUtils.toast(activity!!.baseContext, "${it.error["message"]}")
            }
        })
    }

    override fun onFailure(view: View, msg: String) {
        super.onFailure(view, msg)
        ViewUtils.hide(view)
        ViewUtils.toast(activity!!.baseContext, msg)
    }
}