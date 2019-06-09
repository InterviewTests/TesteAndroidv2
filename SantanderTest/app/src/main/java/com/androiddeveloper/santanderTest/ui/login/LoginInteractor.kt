package com.androiddeveloper.santanderTest.ui.login

import android.content.Context
import android.util.Log
import com.androiddeveloper.santanderTest.data.api.login.LoginRequest
import com.androiddeveloper.santanderTest.data.api.login.LoginService
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.EncryptedData
import com.androiddeveloper.santanderTest.data.model.userdata.LoginError
import com.androiddeveloper.santanderTest.data.model.userdata.UserRepository
import com.androiddeveloper.santanderTest.manager.EncryptManager
import com.androiddeveloper.santanderTest.manager.UserDataManager
import com.androiddeveloper.santanderTest.shared.base.BaseInteractor
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferenceManager
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferencesEnum
import java.lang.ref.WeakReference

class LoginInteractor : BaseInteractor(), ILoginContract.LoginInteractor {

    val TAG = "LoginInteractorErr"
    lateinit var output: WeakReference<ILoginContract.LoginActInput>
    lateinit var userDao: UserRepository
    var loginPresenter = LoginPresenterInput()
    lateinit var context: Context

    override fun bind(activity: LoginActivity) {
        this.context = activity.applicationContext

        output = WeakReference(activity)
        loginPresenter.output = WeakReference(activity)

        userDao = UserRepository(context)
    }

    override fun fetchUserData(username: String, password: String) {
        val disposable = LoginService.requestClientData(LoginRequest(username, password))
            .subscribe({ res ->
                res.body()?.userAccount?.let { data ->
                    saveUserInDb(data)
                    saveLastUser(username)
                    UserDataManager.data = data
                    output.get()!!.onLoginSuccess()
                } ?: run {
                    res.body()?.error?.let { err ->
                        output.get()?.onLoginError(err)
                    }
                }
            }) { err ->
                Log.d(TAG, err.message)
                output.get()?.onLoginError(
                    LoginError(
                        999,
                        "Não foi possível concluir sua solicitação."
                    )
                )
            }

        compositeDisposable?.add(disposable)
    }

    private fun saveLastUser(username: String) {
        SharedPreferenceManager.setLoggedUserData(
            context,
            SharedPreferencesEnum.KEY_USER_LOGGED,
            true
        )

        val encrypted = EncryptManager.encryptLastUser(username)

        SharedPreferenceManager.setUsername(
            context,
            SharedPreferencesEnum.KEY_USERNAME,
            encrypted
        )
    }

    private fun saveUserInDb(data: Data) {
        val encrypted = EncryptManager.encrypt(data)
        val dataEncrypted = EncryptedData()
        dataEncrypted.data = encrypted
        val disposable = userDao.insertUser(dataEncrypted).subscribe()

        compositeDisposable?.add(disposable)
    }

    override fun isUserValid(username: String, password: String) {
        loginPresenter.isUserValid(username, password)
    }
}