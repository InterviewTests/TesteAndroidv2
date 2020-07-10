package br.com.mdr.testeandroid.flow.signin

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.databinding.SignInFragmentBinding
import br.com.mdr.testeandroid.extensions.*
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.util.Constants.Companion.USER_KEY
import com.google.gson.Gson
import kotlinx.android.synthetic.main.sign_in_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {
    private var binding: SignInFragmentBinding? = null
    //Caso a viewmodel venha a ser utilizada por mais de um Fragment, pode ser utilizado o escopo sharedViewModel()
    private val viewModel: SignInViewModel by viewModel()
    private val preferences: SharedPreferences by inject()
    private val preferencesEditor: SharedPreferences.Editor by inject()
    private val gson: Gson by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater)
        binding?.let {
            it.handler = viewModel.signInHandler
            setupListeners(it)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        checkLoggedUser()
        activity?.setStatusBarColor(R.color.white)
        activity?.setLightStatusBar(true)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupObservables() {
        viewModel.errorLive.observe( viewLifecycleOwner, Observer { error ->
            if (error?.code != 0)
                error?.message?.let { showErrorSnack(it) }
        })

        viewModel.signInHandler.signInPresenter.buttonEnabledLive.observe(viewLifecycleOwner, Observer { isEnabled ->
            binding.apply {
                btnSignIn?.isEnabled = isEnabled
                val buttonColor =
                    if (isEnabled)
                        R.color.colorAccent
                    else
                        R.color.disabled_background
                btnSignIn?.changeBackgroundColor(buttonColor, requireContext())
            }
        })

        viewModel.signInHandler.signInPresenter.maskedUserName.observe(viewLifecycleOwner, Observer { maskedUserName ->
            binding?.let { edtUserName.setText(maskedUserName) }
        })

        viewModel.showUserInfo.observe(viewLifecycleOwner, Observer {
            binding?.showUserAccount = it
        })

        viewModel.userLive.observe(viewLifecycleOwner, Observer { user ->
            binding?.let {
                if (viewModel.isUserLogged) {
                    it.user = user
                    it.showUserAccount = user?.userId != null
                    val welComeString = "${resources.getString(R.string.hello_user)} ${user?.name}"
                    it.welcomeLabel = welComeString
                } else {
                    saveUser(user)
                    it.btnSignInUser.callOnClick()
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding?.apply { showLoading = it }
        })
    }

    private fun saveUser(user: User) {
        val strUser = gson.toJson(user)
        preferencesEditor.putString(USER_KEY, strUser)
        preferencesEditor.apply()
    }

    private fun checkLoggedUser() {
        val strUser = preferences.getString(USER_KEY, "")
        val user = gson.fromJson(strUser, User::class.java)
        viewModel.setUser(user)
    }

    private fun setupListeners(binding: SignInFragmentBinding) {
        binding.apply {
            clickListener = viewModel.manageOnClick()
        }
    }

}