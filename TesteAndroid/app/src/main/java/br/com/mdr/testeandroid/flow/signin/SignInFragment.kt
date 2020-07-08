package br.com.mdr.testeandroid.flow.signin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.databinding.SignInFragmentBinding
import br.com.mdr.testeandroid.extensions.*
import br.com.mdr.testeandroid.flow.error.IErrorListViewPresenter
import br.com.mdr.testeandroid.flow.error.IErrorManager
import br.com.mdr.testeandroid.flow.error.ParamError
import br.com.mdr.testeandroid.util.Constants.Companion.LOG_TAG
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.sign_in_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignInFragment : Fragment(), IErrorManager {
    private var btnLogin: MaterialButton? = null
    private var binding: SignInFragmentBinding? = null
    //Caso a viewmodel venha a ser utilizada por mais de um Fragment, pode ser utilizado o escopo sharedViewModel()
    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater)
        binding?.let {
            it.handler = viewModel.signInHandler
            btnLogin = btnSignIn
            setupListeners(it)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        activity?.setStatusBarColor(R.color.white)
        activity?.setLightStatusBar(true)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun showErrors(errorList: IErrorListViewPresenter) {
        for (error in errorList.errorList) {
            val errorResource = error.messageIdName.toLowerCase(Locale.getDefault())
            val errorMessage =
                requireContext().stringResourceByName(errorResource) ?: requireContext().getString(
                    error.messageId()
                )
            binding?.apply {
                when (error.param) {
                    ParamError.USER.param -> setInputError(
                        true, SignInErrorType.USER, errorMessage
                    )
                    ParamError.PASSWORD.param -> setInputError(
                        true, SignInErrorType.PASSWORD, errorMessage
                    )
                    else -> context?.showErrorSnack(root, errorMessage)
                }
            }
        }
    }

    private fun setupObservables() {
        viewModel.signInHandler.signInPresenter.hasError.observe(
            viewLifecycleOwner,
            Observer {
                binding.apply {
                    val errorType = viewModel.signInHandler.signInPresenter.getErrorType(it)
                    setInputError(it, errorType, getString(R.string.invalid_user))
                }
            })
        viewModel.signInHandler.signInPresenter.errorLive.observe(
            viewLifecycleOwner,
            Observer { errorList ->
                errorList?.let {
                    showErrors(it)
                }
            })

        viewModel.signInPresenterLive.observe(viewLifecycleOwner, Observer { presenter ->
            btnLogin?.apply {
                changeBackgroundColor(presenter.loginButtonBackground, requireContext())
                isActivated = presenter.isLoginButtonEnabled
            }
        })

        viewModel.signInHandler.signInPresenter.maskedUserName.observe(viewLifecycleOwner, Observer { maskedUserName ->
            binding?.let { edtUserName.setText(maskedUserName) }
        })

        viewModel.signInHandler.signInPresenter.userLive.observe(viewLifecycleOwner, Observer { user ->
            if (user?.userId != null) {
                val direction = SignInFragmentDirections.actionSignInFragmentToDashboardFragment(usuario = user)
                findNavController().navigate(direction)
            }
        })
    }

    private fun setupListeners(binding: SignInFragmentBinding) {
        binding.apply {
            edtUserName.onFocusChangeListener = viewModel.signInHandler.onUserNameFocusChange()
            btnSignIn.setOnClickListener(viewModel.manageOnClick())
        }
    }

    private fun setInputError(hasError: Boolean, signInError: SignInErrorType, errorMessage: String?) {
        binding?.apply {
            when (signInError.errorType) {
                SignInErrorType.USER.errorType -> {
                    inputUserName.isErrorEnabled = hasError
                    inputUserName.error = if (hasError) errorMessage else null
                }
                SignInErrorType.PASSWORD.errorType -> {
                    inputPassword.isErrorEnabled = hasError
                    inputPassword.error = if (hasError) errorMessage else null
                }
                SignInErrorType.NULL.errorType -> {
                    inputUserName.isErrorEnabled = false
                }
            }
        }
    }

}