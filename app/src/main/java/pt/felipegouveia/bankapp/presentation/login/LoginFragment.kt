package pt.felipegouveia.bankapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_fragment.*
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.databinding.LoginFragmentBinding
import pt.felipegouveia.bankapp.presentation.base.BaseFragment
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.util.extension.toast
import javax.inject.Inject

class LoginFragment: BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel : LoginViewModel by viewModels { viewModelFactory }
    private lateinit var binding : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            this.vm = viewModel
            this.lifecycleOwner = this@LoginFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setLoginBody(LoginBody())
        if(savedInstanceState != null){
            login_edt_user.setText(savedInstanceState.get("user").toString())
            login_edt_user.setText(savedInstanceState.get("password").toString())
        }
        login_btn_login.setOnClickListener(this)
        setupUiObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("user", login_edt_user.text.toString())
        outState.putString("password", login_edt_password.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun setupUiObservers(){
        viewModel.loginResult.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Status.ERROR -> {
                    requireActivity().toast(R.string.login_error_message)
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { response ->
                        val userId = response.userAccount?.userId ?: BAD_USER_ID
                        if(response.userAccount?.userId == BAD_USER_ID){
                            requireActivity().toast(R.string.login_error_message)
                        } else {
                            val action = LoginFragmentDirections.actionLoginFragmentToStatementsFragment(userId)
                            findNavController().navigate(action)
                        }
                    }
                }
                Status.BAD_USER -> requireActivity().toast(R.string.login_error_bad_user)
                Status.BAD_PASSWORD -> requireActivity().toast(R.string.login_error_bad_password)
                null -> requireActivity().toast(R.string.login_error_message)
            }
        })
    }

    override fun onClick(v: View?) {
        viewModel.verifyShouldLogin(networkAvailable)
    }

    companion object {
        const val BAD_USER_ID = -1
    }
}