package com.xyzbank.xyzbankapp.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xyzbank.xyzbankapp.R
import com.xyzbank.xyzbankapp.commons.BuilderHelper
import com.xyzbank.xyzbankapp.ui.account.AccountActivity
import java.text.SimpleDateFormat
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var welcome: String
    private val SUCCESSLOGGEDDATE: String = "successfully_login_date"
    private val SUCCESSFULLYLOGGEDIN: String = "successfully_logged_in"
    private var prefs: SharedPreferences? = null
    private val ACCACT_REQUEST_CODE: Int = 1000
    private var login: Button? = null
    private var password: EditText? = null
    private var username: EditText? = null
    private var tv_message: TextView? = null
    private lateinit var myAccount: LoggedInUserView
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = getSharedPreferences("${packageName}.user_settings", Context.MODE_PRIVATE)

        dateFormat = SimpleDateFormat(getString(R.string.date_format_text), Locale.getDefault())

        username = findViewById(R.id.username)
        val lu = prefs!!.getString(SUCCESSFULLYLOGGEDIN, "")
        password = findViewById(R.id.password)
        if (lu != "") {
            username!!.setText(lu)
            username!!.setSelection(lu!!.length)
            username!!.nextFocusDownId = R.id.password
            password!!.requestFocus()
            username!!.isEnabled = false
        }
        login = findViewById(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login!!.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username!!.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password!!.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }

            if (savedInstanceState == null) {
                val accinf = AccountActivity.newInstance(this, myAccount.accountInfo)
                startActivityForResult(accinf, ACCACT_REQUEST_CODE)
            }
        })

        tv_message = findViewById(R.id.user_message)
        welcome = getString(R.string.user_welcome_msg)
        tv_message!!.text = getString(R.string.empty_user_msg)

        username!!.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username!!.text.toString(),
                    password!!.text.toString()
            )
        }

        password?.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username!!.text.toString(),
                    password!!.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                       if (loginViewModel.verifyUser(username!!.text.toString(), password!!.text.toString())) {
                           password!!.requestFocus()
                           password!!.setSelection(password!!.text.toString().length)
                           tv_message!!.text = welcome.format(username!!.text.toString())
                       }
                    }
                }
                false
            }

            login!!.setOnClickListener {
                loading.visibility = View.VISIBLE
                doLogin(username!!.text.toString(), password!!.text.toString())
            }
        }
    }

    private fun doLogin(username: String, password: String) {
        // verify if user name passed is email or number
        val nm = if (username.contains("@"))
            username.split("@")[0]
        else
            username
        if (BuilderHelper().CheckNetwork(this@LoginActivity))
            loginViewModel.login(nm, password)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        myAccount = model
        tv_message!!.text = welcome.format(model.accountInfo.name)

        val editor = prefs!!.edit()
        editor.putString(SUCCESSFULLYLOGGEDIN, username!!.text.toString())
        val c = Calendar.getInstance()
        val date = c.time
        editor.putString(SUCCESSLOGGEDDATE, dateFormat.format(date))
        editor.apply()

        username!!.isEnabled = false
        password!!.isEnabled = false
        login!!.isEnabled = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACCACT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                username!!.isEnabled = true
                username!!.setText(prefs!!.getString(SUCCESSFULLYLOGGEDIN, ""))
                password!!.isEnabled = true
                password!!.setText("")
                password!!.requestFocus()
                password!!.hasFocus()
                tv_message!!.text = getString(R.string.last_successfully_login_msg).format(prefs!!.getString(SUCCESSLOGGEDDATE, ""))
                login!!.isEnabled = false
            }
        }
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
