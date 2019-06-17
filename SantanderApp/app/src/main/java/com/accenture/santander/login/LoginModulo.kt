package com.accenture.santander.login


import android.app.Activity
import android.content.Context
import android.view.View
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.repository.deviceRepository.UserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.interector.dataManager.storag.StoragManager
import com.accenture.santander.interector.remote.service.Connect
import com.accenture.santander.interector.remote.service.IConnect
import com.accenture.santander.interector.remote.service.login.IServiceLogin
import com.accenture.santander.interector.remote.service.login.ServiceLogin
import dagger.Module
import dagger.Provides


@Module
class LoginModulo(
    val context: Activity? = null,
    val view: View? = null,
    val loginFragment: LoginFragment? = null,
    val loginPresenter: LoginPresenter? = null,
    val loginInteractor: LoginInteractor? = null
) {

    @Provides
    fun provideIUserRepository(): IUserRepository {
        return UserRepository(context!!)
    }

    @Provides
    fun provideIStoragManager(): IStoragManager {
        return StoragManager(context!!)
    }

    @Provides
    fun provideIServiceLogin(): IServiceLogin {
        return ServiceLogin()
    }

    @Provides
    fun provideLoginRouter(): LoginRouter {
        return LoginRouter(view!!)
    }

    @Provides
    fun provideLoginInteractorInput(): LoginContracts.LoginInteractorInput {
        return LoginInteractor(context!!, loginPresenter!!,ServiceLogin())
    }

    @Provides
    fun provideLoginPresenterInput(): LoginContracts.LoginPresenterInput {
        return LoginPresenter(context!!, view!!, loginFragment!!)
    }

    @Provides
    fun provideConnect(): IConnect {
        return Connect(context!!)
    }
}