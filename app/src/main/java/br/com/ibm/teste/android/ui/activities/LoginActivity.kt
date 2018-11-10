package br.com.ibm.teste.android.ui.activities

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import br.com.ibm.teste.android.R
import br.com.ibm.teste.android.services.api.user.IUserAccountListener
import br.com.ibm.teste.android.services.api.user.UserAccountService
import br.com.ibm.teste.android.services.models.UserRequest
import br.com.ibm.teste.android.services.models.UserResponse
import br.com.ibm.teste.android.services.sharedpreferences.UserAccountPreferences
import br.com.ibm.teste.android.ui.generics.GenericActivity
import br.com.ibm.teste.android.utils.Converter
import br.com.ibm.teste.android.utils.Utils
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class LoginActivity : GenericActivity(), IUserAccountListener {

    @BindView(R.id.progressBar) lateinit var mProgressBar: ProgressBar
    @BindView(R.id.edt_user) lateinit var mUser: EditText
    @BindView(R.id.edt_password) lateinit var mPassword: EditText
    @BindView(R.id.btn_login) lateinit var mButtonLogin: Button
    private lateinit var userRequest : UserRequest

    override fun setLayout() {
        setContentView(R.layout.act_login)
        ButterKnife.bind(this)
    }

    override fun loadingMethods() {
        getUserLogged()
    }

    @OnClick(R.id.btn_login)
    fun onClickButtonLogin() {
        checkInternet()
    }

    override fun showLoading() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mProgressBar.visibility = View.GONE
    }

    override fun responseError(messageError: String) {
        Utils.showMessage(messageError)
    }

    override fun responseSuccess(userResponse: UserResponse) {
        saveUserResponse(userResponse)
    }

    private fun saveUserResponse(userResponse: UserResponse) {
        UserAccountPreferences.userSaved = Converter.convertToJson(userResponse)
        UserAccountPreferences.userLoginData = Converter.convertUserRequestToJson(userRequest)
        openMainActivity()
    }

    private fun openMainActivity() {

    }

    private fun getUserLogged() {
        val userLogged = Converter.convertJsonToUserRequest(UserAccountPreferences.userLoginData)
        mUser.setText(userLogged?.user)
        mPassword.setText(userLogged?.password)
    }

    /**
     * Check if there is internet on the phone to call post service
     */
    private fun checkInternet() {
        if (!Utils.isConnected(this)) {
            messageWithoutInternet()
        } else {
            validateFields()
        }
    }

    /**
     * Validate login data for login
     */
    private fun validateFields(){
        if (mUser.text.isEmpty()) {
            Utils.showMessage(getString(R.string.message_error_field_user))
            return
        }

        if (mPassword.text.isEmpty()) {
            Utils.showMessage(getString(R.string.message_error_field_password))
            return
        }

        userRequest = UserRequest(mUser.text.toString(),
                mPassword.text.toString())
        login(userRequest)
    }

    /**
     * Login in bank account
     */
    private fun login(userRequest: UserRequest) =
            UserAccountService(this)
                .login(userRequest)


    /**
     * Show message without Internet
     */
    private fun messageWithoutInternet() {
        Toast.makeText(this, getString(R.string.message_error_no_internet), Toast.LENGTH_SHORT)
                .show()
    }
}
