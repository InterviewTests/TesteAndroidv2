package com.paulokeller.bankapp.login

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.paulokeller.bankapp.R
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        loginButton.setOnClickListener {
            val password = passwordTextField.text.toString()
            val user = passwordTextField.text.toString()

            val state = viewModel.login(user, password)

            if (state.errorMessage != null) {
                Toast.makeText(activity, state.errorMessage, Toast.LENGTH_LONG).show()
            } else {
                //TODO: implement navigation
            }


        }
    }

}
