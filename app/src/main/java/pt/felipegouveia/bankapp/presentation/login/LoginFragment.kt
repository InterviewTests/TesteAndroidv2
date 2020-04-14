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
import pt.felipegouveia.bankapp.util.persistence.BankSharedPreferences
import javax.inject.Inject

class LoginFragment: BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefs: BankSharedPreferences

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
            lifecycleOwner = this@LoginFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setLoginBody(LoginBody())
        if(savedInstanceState != null){
            login_edt_user.setText(savedInstanceState.get(SAVED_INSTANCE_STATE_USER).toString())
            login_edt_password.setText(savedInstanceState.get(SAVED_INSTANCE_STATE_PASSWORD).toString())
        }
        login_btn_login.setOnClickListener(this)
        setupUiObservers()
        retrieveLastUser()
    }

    fun retrieveLastUser() {
        sharedPrefs.readDecryptedString(PREFS_LOGIN_KEY).let {
            if (it?.isNotEmpty() == true) login_txt_last_user.text =
                getString(R.string.login_last_user, it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SAVED_INSTANCE_STATE_USER, login_edt_user.text.toString())
        outState.putString(SAVED_INSTANCE_STATE_PASSWORD, login_edt_password.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun setupUiObservers(){
        viewModel.loginResult.observe(viewLifecycleOwner, Observer { it ->
                when (it?.status) {
                    Status.ERROR -> {
                        requireActivity().toast(R.string.login_error_unknown)
                    }
                    Status.SUCCESSFUL -> {
                        it.data?.let { response ->
                            if(response.userAccount?.userId == BAD_USER_ID){
                                requireActivity().toast(R.string.login_error_unknown)
                            } else {
                                sharedPrefs.saveEncryptedString(PREFS_LOGIN_KEY, response.userAccount?.name?: EMPTY_NAME)
                                val action = LoginFragmentDirections.actionLoginFragmentToStatementsFragment(response.userAccount)
                                findNavController().navigate(action)
                            }
                        }
                    }
                    Status.BAD_USER -> requireActivity().toast(R.string.login_error_bad_user)
                    Status.BAD_PASSWORD -> requireActivity().toast(R.string.login_error_bad_password)
                    Status.NO_NETWORK -> requireActivity().toast(R.string.login_error_network_unavailable)
                }
        })
    }

    override fun onClick(v: View?) {
        viewModel.verifyShouldLogin(networkAvailable)
    }

    companion object {
        const val BAD_USER_ID = -1
        const val EMPTY_NAME = ""
        const val PREFS_LOGIN_KEY = "PREFS_LOGIN_KEY"
        const val SAVED_INSTANCE_STATE_USER = "user"
        const val SAVED_INSTANCE_STATE_PASSWORD = "password"
    }
}