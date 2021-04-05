package com.accenture.bankapp.screens.login

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import br.com.colman.simplecpfvalidator.isCpf
import com.accenture.bankapp.R
import com.accenture.bankapp.screens.dashboard.DashboardFragment
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
import java.util.regex.Pattern

interface LoginFragmentInput {
    fun verifyUser()
    fun verifyPassword()
    fun enableLoading()
    fun disableLoading()
    fun enableError(error: String)
    fun disableError()
}

interface LoginFragmentListener {
    fun startDashboardFragment(dashboardFragment: DashboardFragment)
}

class LoginFragment : Fragment(), LoginFragmentInput {
    lateinit var loginRouter: LoginRouter
    lateinit var loginFragmentListener: LoginFragmentListener

    lateinit var inputUser: TextInputLayout
    lateinit var inputUserEditText: EditText
    lateinit var inputPassword: TextInputLayout
    lateinit var inputPasswordEditText: EditText
    lateinit var textLoginError: TextView
    lateinit var progressLoading: ProgressBar
    lateinit var buttonLogin: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView: Creating the Login Fragment View")

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        LoginConfigurator.configureFragment(this)
        bindViews(view)
        configureViews()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            loginFragmentListener = activity as LoginFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement LoginFragmentListener")
        }
    }

    override fun verifyUser() {
        Timber.i("verifyUser: Validating the User Input")

        val error = "User must be a valid email or CPF"
        val user = inputUser.editText?.text.toString()

        if (Patterns.EMAIL_ADDRESS.matcher(user).matches() || user.isCpf(listOf('.', '/'))) {
            Timber.i("verifyUser: $user is a valid user")
            inputUser.isErrorEnabled = false
            inputUser.error = ""
        } else {
            Timber.i("verifyUser: $user is a invalid user")
            inputUser.isErrorEnabled = true
            inputUser.error = error
        }
    }

    override fun verifyPassword() {
        Timber.i("verifyPassword: Validating the Password Input")

        val passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[!@\$%^&])(?=.*[a-zA-Z0-9]).*\$")
        val error = "Password must have at least one capital letter, one special character and one alphanumeric"
        val password = inputPassword.editText?.text.toString()

        if (passwordPattern.matcher(password).matches()) {
            Timber.i("verifyPassword: $password is a valid password")
            inputPassword.isErrorEnabled = false
            inputPassword.error = ""
        } else {
            Timber.i("verifyPassword: $password is a invalid password")
            inputPassword.isErrorEnabled = true
            inputPassword.error = error
        }
    }

    override fun enableLoading() {
        progressLoading.visibility = View.VISIBLE
    }

    override fun disableLoading() {
        progressLoading.visibility = View.GONE
    }

    override fun enableError(error: String) {
        textLoginError.text = error
        textLoginError.visibility = View.VISIBLE
    }

    override fun disableError() {
        textLoginError.text = ""
        textLoginError.visibility = View.GONE
    }

    private fun bindViews(view: View) {
        Timber.i("bindViews: Binding Login Fragment views")

        inputUser = view.findViewById(R.id.inputUser)
        inputUserEditText = inputUser.editText!!
        inputPassword = view.findViewById(R.id.inputPassword)
        inputPasswordEditText = inputPassword.editText!!
        textLoginError = view.findViewById(R.id.textLoginError)
        progressLoading = view.findViewById(R.id.progressLoading)
        buttonLogin = view.findViewById(R.id.buttonLogin)
    }

    private fun configureViews() {
        Timber.i("configureViews: Configuring the Login Fragment views")

        val sharedPreferences = this.context!!.getSharedPreferences(this.context!!.packageName, Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("user", null)
        val password = sharedPreferences.getString("password", null)

        if (user != null && password != null) {
            inputUserEditText.setText(user)
            inputPasswordEditText.setText(password)
        }

        inputUserEditText.setOnEditorActionListener(loginRouter)
        inputUserEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                disableError()
                verifyUser()
            }
        }

        inputPasswordEditText.setOnEditorActionListener(loginRouter)
        inputPasswordEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                disableError()
                verifyPassword()
            }
        }

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                progressLoading.indeterminateDrawable.colorFilter = PorterDuffColorFilter(
                    this.context!!.getColor(R.color.primary_color),
                    PorterDuff.Mode.SRC_IN
                )
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 -> {
                progressLoading.indeterminateDrawable.colorFilter = PorterDuffColorFilter(
                    this.context!!.resources.getColor(R.color.primary_color),
                    PorterDuff.Mode.SRC_IN
                )
            }
            else -> {
                val drawableProgress = DrawableCompat.wrap(progressLoading.indeterminateDrawable)
                DrawableCompat.setTint(drawableProgress, ContextCompat.getColor(this.context!!, R.color.primary_color))
                progressLoading.indeterminateDrawable = DrawableCompat.unwrap(drawableProgress)
            }
        }

        buttonLogin.setOnClickListener(loginRouter)
    }
}