package com.xyzbank.xyzbankapp.ui.login

import android.os.AsyncTask
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xyzbank.bankapp.data.model.UserInfo
import com.xyzbank.xyzbankapp.R
import com.xyzbank.xyzbankapp.data.LoginRepository
import com.xyzbank.xyzbankapp.data.Result
import com.xyzbank.xyzbankapp.data.model.JSONParser
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private lateinit var myAccount: UserInfo
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    enum class TypeOfTextInName {EMAIL, CPF}
    private lateinit var typeName: TypeOfTextInName

    fun verifyUser(username: String, password: String): Boolean {
        // First - validate user from UI
        val result = loginRepository.login(username, password)

        if (result is Result.Error) {
            _loginResult.value = LoginResult(error = R.string.login_failed)
            return false
        }
        return true
    }

    fun login(username: String, password: String) {
        // First - validate user from UI
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            // load user account
            LoadUserAccount().execute("$username|$password")
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }


    /**
     * Request data from the api using a thread.
     *
     * @param [String]: the user name and password.
     * return true or false.
     */
    inner class LoadUserAccount : AsyncTask<String, String, Any>() {

        private lateinit var password: String
        private lateinit var username: String

        override fun onPreExecute() {
            Log.d("temp", "temp")
        }

        override fun doInBackground(vararg voids: String?): Any {
            username = voids[0]!!.split("|")[0]
            password = voids[0]!!.split("|")[1]
            // get the user account
            return JSONParser.getUserAccount(username, password)
        }

        override fun onPostExecute(results: Any?) {
            if (results != null) {
                // See Response in Logcat for understand JSON Results and make DeveloperList
                Log.i("onPostExecute: ", (results as UserInfo).userid.toString())
                myAccount = results
                _loginResult.value = LoginResult(success = LoggedInUserView(accountInfo = myAccount))

            }
        }
    } // end of inner class

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    /**
     * Verify the text typed in the name field is cpf or email based.
     *
     * @param text: the text typed in the name field.
     * return true or false.
     */
    private fun isUserNameValid(text: String): Boolean {
        var result = false
        if (text.contains('@')) {
            typeName = TypeOfTextInName.EMAIL
            result = Patterns.EMAIL_ADDRESS.matcher(text).matches()
        } else {
            if (verifyCharInName(text)){
                result = true
                typeName = TypeOfTextInName.CPF
            }
        }
        return result
    }

    /**
     * Verify the text typed in the name field is digit based.
     *
     * @param text: the text typed in the name field.
     * return true or false.
     */
    private fun verifyCharInName(text: String): Boolean {
        val pDig = Pattern.compile("(.*\\d.*)")
        val mdg: Matcher = pDig.matcher(text)
        return (mdg.matches())
    }

    // A placeholder password validation check
    /**
     * Verify the text typed in the password field have
     *  - at last a capital letter
     *  - at last one digit
     *  - at lest one symbol
     *
     * @param text: the text typed in the password field.
     * return true or false.
     */
    private fun isPasswordValid(password: String): Boolean {
        val r = password.length > 5 && verifyCharInPassword(password)
        return r
    }

    /**
     * Verify password typed util.
     *
     * @param text: the text typed in the password field.
     * return true or false.
     */
    private fun verifyCharInPassword(text: String): Boolean {
        val pLow = Pattern.compile("(.*[a-z].*)")
        val pUpp = Pattern.compile("(.*[A-Z].*)")
        val pDig = Pattern.compile("(.*\\d.*)")
        val pSym = Pattern.compile("(.*[:?!@#$%^&*()].*)")
        val mlc: Matcher = pLow.matcher(text)
        val muc: Matcher = pUpp.matcher(text)
        val mdg: Matcher = pDig.matcher(text)
        val msb: Matcher = pSym.matcher(text)

        return mlc.matches() && muc.matches() && mdg.matches() && msb.matches()
    }
}
