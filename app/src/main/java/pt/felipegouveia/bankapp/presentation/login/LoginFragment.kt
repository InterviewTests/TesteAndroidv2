package pt.felipegouveia.bankapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.login_fragment.*
import pt.felipegouveia.bankapp.databinding.LoginFragmentBinding
import pt.felipegouveia.bankapp.presentation.MainActivity
import pt.felipegouveia.bankapp.presentation.entity.Status
import javax.inject.Inject

class LoginFragment: DaggerFragment(), View.OnClickListener {

    lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private lateinit var viewModel : LoginViewModel
    private lateinit var binding : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        val viewModel : LoginViewModel by viewModels { viewModelFactory }
        this.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
//                    toastMessage(getString(R.string.error_retrieving_recent_searches), binding.root)
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { response ->
                       /* if (response.isNotEmpty()) {
                            searchedUsersAdapter.update(response)
                        } else {
                            *//*TODO("Show empty list view or retry message")*//*
                        }*/
                    }
                }
                Status.BAD_USER -> TODO()
                Status.BAD_PASSWORD -> TODO()
                null -> TODO()
            }
        })

    }

    override fun onClick(v: View?) {
        viewModel.login((activity as MainActivity).networkAvailable)
    }
}