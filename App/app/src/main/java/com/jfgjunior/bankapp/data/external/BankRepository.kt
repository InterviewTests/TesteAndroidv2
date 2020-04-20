package com.jfgjunior.bankapp.data.external

import com.jfgjunior.bankapp.data.models.UserCredentials
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BankRepository : Repository, KoinComponent {

    private val credentialsManager by inject<CredentialsManager>()

    private val api: BankAPI = Retrofit.Builder()
        .baseUrl(BankAPI.BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(BankAPI::class.java)

    override fun loginUser(userCredentials: UserCredentials) =
        api.loginUser(userCredentials)

    override fun getTransactions(userId: Int) =
        api.getTransactions(userId)

    override fun saveUser(userCredentials: UserCredentials) =
        credentialsManager.saveCredentials(userCredentials)

    override fun getUser() = credentialsManager.getCredentials()

    override fun clearUser() = credentialsManager.clearUser()
}