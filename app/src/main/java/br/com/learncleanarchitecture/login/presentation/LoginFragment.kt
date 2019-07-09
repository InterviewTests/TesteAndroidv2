package br.com.learncleanarchitecture.login.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import br.com.learncleanarchitecture.R
import br.com.learncleanarchitecture.login.domain.LoginRouter
import br.com.learncleanarchitecture.login.domain.LoginConfigurator
import br.com.learncleanarchitecture.login.domain.LoginInteractorInput
import kotlinx.android.synthetic.main.login_fragment.*


interface LoginFragmentInput {
    fun responseServiceLogin(login: Login?)
    fun showLoading()
    fun hideLoading()
    fun usernameError(error: String)
    fun callLogin()
    fun passwordError(passwordError: String)
    fun showErrorResponse(error: String)
    fun putUsernameAndPasswordInFields(loginResponse: LoginResponse)
}

class LoginFragment : Fragment(), LoginFragmentInput, LoginFragmentListener {


    companion object {
        fun newInstance() = LoginFragment()
        const val TAG = "LoginFragment"
    }

    //region widgets
    lateinit var llAlertErrorUser: LinearLayout
    lateinit var llAlertErrorPassword: LinearLayout
    lateinit var tvErrorUser: TextView
    lateinit var tvErrorPasword: TextView
    lateinit var btnLogin: Button
    //endregion

    lateinit var viewModel: LoginViewModel
    lateinit var homeFragmentListener: LoginFragmentListener
    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        setFindByIds(view)
        LoginConfigurator.configureFragment(this, viewModel)
        verifyHaveUser()

        btnLogin.setOnClickListener {
            showLoading()
            output.validateFields(et_user.text.toString(), et_password.text.toString())
        }

        return view
    }

    fun verifyHaveUser() {
        output.verifyHaveUser(viewModel)
    }

    override fun putUsernameAndPasswordInFields(loginResponse: LoginResponse) {

        et_user?.setText(loginResponse.login?.getUsername())

        et_password?.setText(loginResponse.login?.getPass())
    }

    private fun setFindByIds(view: View) {
        btnLogin = view.findViewById(R.id.btn_login)

        tvErrorUser = view.findViewById(R.id.tv_error_user)
        tvErrorPasword = view.findViewById(R.id.tv_error_password)

        llAlertErrorUser = view.findViewById(R.id.ll_alert_red_user)
        llAlertErrorPassword = view.findViewById(R.id.ll_alert_red_password)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            homeFragmentListener = activity as LoginFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement LoginFragmentListener")
        }
    }

    override fun responseServiceLogin(login: Login?) {
        hideLoading()
        Log.d(TAG, "responseServiceLogin = $login")
        router.showNextScreen(router.determineNextScreen(login))
    }

    override fun startHomeFragment(fragment: Fragment) {
        val ft = activity?.supportFragmentManager?.beginTransaction()

        ft?.let {
            ft.replace(R.id.container, fragment)
            ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            ft.commit()
        }
    }

    override fun callLogin() {

        val loginRequest = LoginRequest(
            et_user.text.toString(),
            et_password.text.toString()
        )

        output.doLogin(loginRequest)
    }

    override fun showLoading() {
        progress_circular.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_circular.visibility = View.GONE
    }

    override fun usernameError(error: String) {
        hideLoading()
        tvErrorUser.visibility = View.VISIBLE
        llAlertErrorUser.visibility = View.VISIBLE
        tvErrorUser.text = error
    }

    override fun passwordError(passwordError: String) {
        hideLoading()
        tvErrorPasword.visibility = View.VISIBLE
        llAlertErrorPassword.visibility = View.VISIBLE
        tvErrorPasword.text = passwordError
    }

    override fun showErrorResponse(error: String) {
        hideLoading()
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}

interface LoginFragmentListener {
    fun startHomeFragment(fragment: Fragment)
}
