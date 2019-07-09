package br.com.learncleanarchitecture.login.data.api

import android.util.Log
import br.com.learncleanarchitecture.util.GenericApi.createApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class LoginApi {
    val service: LoginEndpointsApi = createApi(LoginEndpointsApi::class.java)

    fun doLogin(username: String, password: String): Observable<LoginData>? {

        var loginRequestApi = LoginRequestApi(username, password)
        return service.login(loginRequestApi)
            ?.map { login ->
                Log.d(TAG, "LoginApi -> doLogin -> LoginResult = $login")
                LoginData(
                    login?.userAccount?.userId,
                    login?.userAccount?.name,
                    login?.userAccount?.bankAccount,
                    login?.userAccount?.agency,
                    login?.userAccount?.balance,
                    error = Error(
                        login?.error?.code,
                        login?.error?.message
                    )
                )
            }
    }

    companion object {
        const val TAG = "LoginApi"
    }

    data class LoginRequestApi(var user: String = "", var password: String = "")
}

