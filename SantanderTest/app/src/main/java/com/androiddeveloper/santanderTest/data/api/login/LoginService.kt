package com.androiddeveloper.santanderTest.data.api.login

import com.androiddeveloper.santanderTest.data.model.userdata.LoginResponse
import com.androiddeveloper.santanderTest.shared.network.Factory
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object LoginService {

    fun requestClientData(userData: LoginRequest): Flowable<Response<LoginResponse>> {
        return Factory.buildApi(LoginEndpoint::class.java)
            .login(userData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestMockClientData(userData: LoginRequest): Flowable<Response<LoginResponse>> {
        return Factory.buildMockApi(LoginEndpoint::class.java)
            .login(userData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}