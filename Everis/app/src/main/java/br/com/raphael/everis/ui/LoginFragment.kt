package br.com.raphael.everis.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import br.com.raphael.everis.R
import br.com.raphael.everis.helpers.FormatarAgency
import br.com.raphael.everis.viewmodel.LoginViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observerError()
        observerSuccess()
        observerLoading()

        observerData()

        btn_login.setOnClickListener {
            login()
        }

        til_user.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setUser(s.toString())
            }
        })

        til_password.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setPassword(s.toString())
            }
        })


    }

    private fun observerError(){
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.atencao))
                    .setContentText(it)
                    .setConfirmText(getString(R.string.ok))
                    .show()
            }
        })
    }

    private fun observerSuccess(){
        viewModel.success.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = LoginFragmentDirections.actionLoginFragmentToStatementsFragment(userAccount = it)
                view?.findNavController()?.navigate(action)
            }
        })
    }

    private fun observerLoading(){
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            pb_loading.isVisible = it
            btn_login.isVisible = !it
        })
    }

    private fun login(){
        btn_login.isEnabled = false
        viewModel.postLogin().forEach { fieldError ->
            btn_login.isEnabled = true
            when (fieldError.fieldId) {
                R.id.til_user -> {
                    tie_user.error = getString(fieldError.errorStringResourceId)
                }
                R.id.til_password -> {
                    tie_password.error = getString(fieldError.errorStringResourceId)
                }

            }
        }
    }

    private fun observerData(){
        viewModel.data.observe(viewLifecycleOwner, Observer {
            it.let {
                cl_data.isVisible = true
                tv_name.text = it.name
                tv_agency.text = it.desc
            }
        })
    }

}
