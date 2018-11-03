package com.ygorcesar.testeandroidv2.login.data

import com.ygorcesar.testeandroidv2.base.common.network.NetworkHandler
import com.ygorcesar.testeandroidv2.base.data.BaseRemoteRepository
import com.ygorcesar.testeandroidv2.managers.SessionManager
import io.reactivex.Completable
import javax.inject.Inject

interface LoginRepository {

    fun login(user: String, password: String): Completable

    class Remote
    @Inject constructor(
        private val loginService: LoginService,
        private val userAccountMapper: UserAccountMapper,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), LoginRepository {

        override fun login(user: String, password: String): Completable =
            request(userAccountMapper) {
                loginService.login(user, password)
            }.doOnSuccess { userAccount ->
                SessionManager.setCurrentUserAccount(userAccount)
            }.ignoreElement()
    }
}