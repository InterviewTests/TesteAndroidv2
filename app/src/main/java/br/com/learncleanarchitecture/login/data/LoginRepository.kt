package br.com.learncleanarchitecture.login.data

import android.util.Log
import br.com.learncleanarchitecture.AppController.Companion.db
import br.com.learncleanarchitecture.BuildConfig
import br.com.learncleanarchitecture.BuildConfig.databaseFake
import br.com.learncleanarchitecture.login.data.api.LoginApi
import br.com.learncleanarchitecture.login.data.api.LoginData
import br.com.learncleanarchitecture.login.data.room.LoginEntity
import br.com.learncleanarchitecture.login.data.room.LoginTaskDAO
import br.com.learncleanarchitecture.login.presentation.Login
import br.com.learncleanarchitecture.util.CryptoFakeUtil
import br.com.learncleanarchitecture.util.DataResponse
import br.com.learncleanarchitecture.util.Response
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginRepository {

    companion object {
        fun getInstance(): LoginRepository {
            var repo : LoginRepository? = null

            if (repo == null) {
                repo = LoginRepository()
            }

            return repo
        }

        const val TAG = "LoginRepository"
        const val ERROR_MSG = "LoginRepository -> api -> doLogin -> LoginResult = null"
    }

    fun doLogin(
        username: String,
        password: String,
        callback: Response<Login>
    ) {

        val api = LoginApi()
        api.doLogin(username, password)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ loginResponse ->
                if (loginResponse.error?.message == "") {

                    loginResponse.username = username
                    loginResponse.pass = criptoPassword(password)

                    saveInDatabase(loginResponse, callback)
                } else {
                    loginResponse.error?.let { callback.onError(it) }
                }
            }, { e ->
                Log.e(TAG, e.message)
                callback.onThrowable(e)
            })
    }

    private fun saveInDatabase(
        loginData: LoginData?,
        callback: Response<Login>
    ) {
        val loginEntity: LoginEntity? = MapperLogin.loginDataToLoginEntity(loginData)

        val loginCrudDao = db?.let {
            it.getLoginDao()
        }

        if (LoginTaskDAO.saveLogin(loginCrudDao!!, loginEntity!!, null)) {
            makeLoginObject(loginData, callback)
        }
    }

    private fun makeLoginObject(
        loginResponse: LoginData?,
        response: Response<Login>
    ) {
        val login1: Login? = MapperLogin.loginDataToLogin(loginResponse)
        login1?.let {
            Log.d(LoginApi.TAG, "LoginRepository -> api -> doLogin -> LoginResult = $login1")
            response.onSuccess(it)
        }
    }

    private fun criptoPassword(password: String): String {
        return CryptoFakeUtil.encode(password)
    }

    fun verifyHaveUser(dataResponse: DataResponse<Login>) {
        val loginCrudDao = db?.let {
            it.getLoginDao()
        }
        if(BuildConfig.databaseFake) {
            dataResponse.onSuccess(Login())
        } else {
            LoginTaskDAO.checkHaveUser(loginCrudDao!!, dataResponse)
        }
    }
}
