package com.accenture.santander.statements


import android.app.Activity
import android.view.View
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.repository.deviceRepository.UserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.interector.dataManager.storag.StoragManager
import com.accenture.santander.interector.remote.service.Connect
import com.accenture.santander.interector.remote.service.IConnect
import com.accenture.santander.interector.remote.service.statement.IServiceStatement
import com.accenture.santander.interector.remote.service.statement.ServiceStatement
import dagger.Module
import dagger.Provides


@Module
class StatementModulo(
    val context: Activity? = null,
    val view: View? = null,
    val statementFragment: StatementFragment? = null,
    val statementPresenter: StatementPresenter? = null,
    val statementInteractor: StatementInteractor? = null
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
    fun provideStatementRouter(): StatementRouter {
        return StatementRouter(view!!)
    }

    @Provides
    fun provideStatementInteractorInput(): StatementContracts.StatementInteractorInput {
        return StatementInteractor(context!!, statementPresenter!!, ServiceStatement())
    }

    @Provides
    fun provideStatementPresenterInput(): StatementContracts.StatementPresenterInput {
        return StatementPresenter(context!!, view!!, statementFragment!!)
    }

    @Provides
    fun provideConnect(): IConnect {
        return Connect(context!!)
    }
}