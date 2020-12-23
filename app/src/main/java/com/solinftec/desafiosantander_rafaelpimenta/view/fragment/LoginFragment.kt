package com.solinftec.desafiosantander_rafaelpimenta.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.databinding.LoginFragmentBinding
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.util.DialogKeys
import com.solinftec.desafiosantander_rafaelpimenta.util.Helper
import com.solinftec.desafiosantander_rafaelpimenta.util.LoginListener
import com.solinftec.desafiosantander_rafaelpimenta.viewmodel.LoginViewModel


class LoginFragment : Fragment(), LoginListener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.loginListener = this

        lifecycle.addObserver(viewModel)
        initRxObservables()

        binding.btnLogin.setOnClickListener {
            viewModel.login(it)

        }

    }


    override fun onSuccess(view: View, login: LiveData<LoginResponse>) {
        super.onSuccess(view, login)
        login.observe(this, {
            if(it.error.isNullOrEmpty()){
                val bundle = bundleOf("user" to it.userAccount)
                view.findNavController().navigate(R.id.action_loginFragment_to_statementsFragment, bundle)
            }else{
                activity?.applicationContext?.let { it1 -> Helper().toast(it1,"${it.error["message"]}") }
            }

        })
    }

    override fun onFailure(view: View, msg: String) {
        super.onFailure(view, msg)
        activity?.applicationContext?.let { Helper().toast(it, msg) }
    }

    private fun initRxObservables() {
        viewModel.showDialog.observe(this.viewLifecycleOwner, {
            if (it) {
                when (viewModel.msgType) {

                    DialogKeys.ERRO_VALIDACAO -> {
                        activity?.applicationContext?.let { it1 ->
                            Helper().toast(
                                it1,
                                getString(viewModel.msg)
                            )
                        }
                    }
                    DialogKeys.ERRO -> {
                        activity?.applicationContext?.let { it1 ->
                            Helper().toast(
                                it1,
                                viewModel.msgStr
                            )
                        }
                    }

                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
        lifecycle.removeObserver(viewModel)
    }

}