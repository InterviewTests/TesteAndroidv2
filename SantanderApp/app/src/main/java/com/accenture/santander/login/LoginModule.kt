package com.accenture.santander.login


import android.app.Activity
import android.view.View
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.repository.deviceRepository.UserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.interector.dataManager.storag.StoragManager
import com.accenture.santander.interector.remote.service.Connect
import com.accenture.santander.interector.remote.service.IConnect
import com.accenture.santander.interector.remote.service.login.IServiceLogin
import com.accenture.santander.interector.remote.service.login.ServiceLogin
import com.accenture.santander.statements.StatementFragment
import com.accenture.santander.statements.StatementInteractor
import com.accenture.santander.statements.StatementPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LoginModule(
    val context: Activity? = null,
    val view: View? = null,
    val loginFragment: LoginFragment? = null,
    val loginPresenter: LoginPresenter? = null
) {

    @Provides
    fun provideIUserRepository(): IUserRepository = UserRepository(context!!)

    @Provides
    fun provideIStoragManager(): IStoragManager = StoragManager(context!!)

    @Provides
    fun provideIServiceLogin(): IServiceLogin = ServiceLogin()

    @Provides
    fun provideLoginRouter(): LoginRouter = LoginRouter(view!!)

    @Provides
    fun provideLoginPresenterInput(): LoginContracts.LoginPresenterInput {
        return LoginPresenter(context!!, view!!, loginFragment!!)
    }

    @Provides
    fun provideLoginInteractorInput(): LoginContracts.LoginInteractorInput =
        LoginInteractor(context!!, loginPresenter!!, ServiceLogin())

    @Provides
    fun provideConnect(): IConnect = Connect(context!!)

}