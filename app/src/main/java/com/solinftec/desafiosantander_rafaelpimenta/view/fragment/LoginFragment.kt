package com.solinftec.desafiosantander_rafaelpimenta.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.databinding.LoginFragmentBinding
import com.solinftec.desafiosantander_rafaelpimenta.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel


        lifecycle.addObserver(viewModel)

        binding.btnLogin.setOnClickListener {
            viewModel.login()

            it.findNavController().navigate(R.id.action_loginFragment_to_statementsFragment)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
        lifecycle.removeObserver(viewModel)
    }

}