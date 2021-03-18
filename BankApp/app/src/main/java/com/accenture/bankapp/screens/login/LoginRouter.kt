package com.accenture.bankapp.screens.login

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.accenture.bankapp.network.api.RetrofitBuilder
import com.accenture.bankapp.screens.dashboard.DashboardFragment
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.ref.WeakReference

class LoginRouter : View.OnClickListener, TextView.OnEditorActionListener {
    private val ioScope = CoroutineScope(Dispatchers.IO + CoroutineName("LoginRouterIOScope"))
    private val mainScope = CoroutineScope(Dispatchers.Main + CoroutineName("LoginRouterMainScope"))
    private val apiService = RetrofitBuilder.apiService

    var loginFragment: WeakReference<LoginFragment>? = null
    var loginFragmentInput: WeakReference<LoginFragmentInput>? = null

    override fun onClick(v: View?) {
        performLogin()
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            performLogin()

            return true
        }

        return false
    }

    private fun saveSession(context: Context, user: String, password: String) {
        Timber.i("saveSession: Saving user session")

        val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

        sharedPreferences.edit().putString("user", user).putString("password", password).apply()
    }

    private fun performLogin() {
        val context = loginFragment?.get()?.context
        val inputUser = loginFragment?.get()?.inputUser
        val inputUserText = loginFragment?.get()?.inputUserEditText?.text.toString()
        val inputPassword = loginFragment?.get()?.inputPassword
        val inputPasswordText = loginFragment?.get()?.inputPasswordEditText?.text.toString()

        loginFragmentInput?.get()?.verifyUser()
        loginFragmentInput?.get()?.verifyPassword()

        if (inputUser!!.isErrorEnabled || inputPassword!!.isErrorEnabled) {
            Timber.i("onClick: The user $inputUserText or password $inputPasswordText is invalid")
            return
        }

        loginFragmentInput?.get()?.enableLoading()

        ioScope.launch {
            try {
                Timber.i("onClick: Trying to login with user $inputUserText and password $inputPasswordText")

                val response = apiService.requestLogin(inputUserText, inputPasswordText)

                mainScope.launch {
                    try {
                        loginFragmentInput?.get()?.disableLoading()

                        if (response.isSuccessful) {
                            if (response.body()?.error?.code ?: 0 == 0) {
                                val userAccount = response.body()?.userAccount!!
                                val dashboardFragment = DashboardFragment()
                                val args = Bundle()

                                Timber.i("${this.coroutineContext[CoroutineName]?.name}: Login successfully. User: $userAccount")

                                saveSession(context!!, inputUserText, inputPasswordText)

                                args.putSerializable("userAccount", userAccount)
                                dashboardFragment.arguments = args
                                loginFragment?.get()?.loginFragmentListener?.startDashboardFragment(dashboardFragment)
                            } else {
                                val error = response.body()?.error!!

                                Timber.i("${this.coroutineContext[CoroutineName]?.name}: Login failed: ${error.code}: ${error.message}")
                                loginFragmentInput?.get()?.enableError(error.message)
                            }
                        } else {
                            val error = "Request failed: ${response.code()}"

                            Timber.e("${this.coroutineContext[CoroutineName]?.name}: $error")
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        }
                    } catch (t: Throwable) {
                        val error = "Error updating the UI"

                        Timber.e(t, "${this.coroutineContext[CoroutineName]?.name}: $error")
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (t: Throwable) {
                val error = "Error requesting login"

                Timber.e(t, "${this.coroutineContext[CoroutineName]?.name}: $error")
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}