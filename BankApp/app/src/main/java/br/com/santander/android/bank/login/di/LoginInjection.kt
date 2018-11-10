package br.com.santander.android.bank.login.di

import br.com.santander.android.bank.core.di.Injection
import br.com.santander.android.bank.login.domain.interactor.LoginInteractor
import br.com.santander.android.bank.login.repository.LoginRepository
import br.com.santander.android.bank.login.repository.LoginService

internal object LoginInjection {

    val interactor by lazy { LoginInteractor(repository) }

    private val loginService by lazy { LoginService() }

    private val repository by lazy {
        LoginRepository(
            loginService = loginService,
            preferences = Injection.preferences
        )
    }
}