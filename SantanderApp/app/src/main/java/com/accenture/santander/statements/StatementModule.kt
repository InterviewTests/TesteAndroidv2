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
class StatementModule(
    val context: Activity? = null,
    val view: View? = null,
    val statementFragment: StatementFragment? = null,
    val statementPresenter: StatementPresenter? = null
) {

    @Provides
    fun provideIUserRepository(): IUserRepository = UserRepository(context!!)

    @Provides
    fun provideIStoragManager(): IStoragManager = StoragManager(context!!)

    @Provides
    fun provideIServiceStatement(): IServiceStatement = ServiceStatement()

    @Provides
    fun provideStatementRouter(): StatementRouter = StatementRouter(view!!)

    @Provides
    fun provideStatementInteractorInput(): StatementContracts.StatementInteractorInput =
        StatementInteractor(context!!, statementPresenter!!, ServiceStatement())

    @Provides
    fun provideStatementPresenterInput(): StatementContracts.StatementPresenterInput =
        StatementPresenter(context!!, view!!, statementFragment!!)

    @Provides
    fun provideConnect(): IConnect = Connect(context!!)

}