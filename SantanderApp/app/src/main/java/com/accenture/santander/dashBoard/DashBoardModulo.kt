package com.accenture.santander.dashBoard


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
import com.accenture.santander.interector.remote.service.statement.IServiceStatement
import com.accenture.santander.interector.remote.service.statement.ServiceStatement
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DashBoardModulo(
    val context: Activity? = null,
    val view: View? = null,
    val dashBoardFragment: DashBoardFragment? = null,
    val dashBoardPresenter: DashBoardPresenter? = null,
    val dashBoardInteractor: DashBoardInteractor? = null
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
    fun provideIServiceStatement(): IServiceStatement {
        return ServiceStatement()
    }

    @Provides
    fun provideDashBoardRouter(): DashBoardRouter {
        return DashBoardRouter(view!!)
    }

    @Provides
    fun provideDashBoardInteractorInput(): DashBoardContracts.DashBoardInteractorInput {
        return DashBoardInteractor(context!!, dashBoardPresenter!!, ServiceStatement())
    }

    @Provides
    fun provideLoginPresenterInput(): DashBoardContracts.DashBoardPresenterInput {
        return DashBoardPresenter(context!!, view!!, dashBoardFragment!!)
    }

    @Provides
    fun provideConnect(): IConnect {
        return Connect(context!!)
    }
}