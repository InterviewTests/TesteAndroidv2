package com.nandoligeiro.safrando.domain.login.usecase.postLogin

import com.nandoligeiro.safrando.di.IoDispatcher
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterUseCase
import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import com.nandoligeiro.safrando.domain.login.repository.LoginRepository
import com.nandoligeiro.safrando.domain.login.result.LoginResult
import com.nandoligeiro.safrando.domain.login.usecase.checkLogin.CheckLoginUseCase
import com.nandoligeiro.safrando.domain.localUser.usecase.saveLocalUser.SaveUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostLoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val checkLoginUseCase: CheckLoginUseCase,
    private val currencyFormatterUseCase: CurrencyFormatterUseCase,
    private val saveDataStoreUseCase: SaveUserUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PostLoginUseCase {

    override suspend fun invoke(
        user: String, password: String
    ): LoginResult<LoginDomain> = withContext(ioDispatcher) {
        if (checkLoginUseCase(user, password)) {
            try {
                val result = loginRepository.postLogin(user, password)
                val changedResult = result.copy(balance = currencyFormatterUseCase(result.balance))
                saveDataStoreUseCase(user)
                LoginResult.Success(changedResult)
            } catch (e: Exception) {
                LoginResult.Error(e)
            }
        } else {
            LoginResult.CheckFieldError
        }
    }
}
