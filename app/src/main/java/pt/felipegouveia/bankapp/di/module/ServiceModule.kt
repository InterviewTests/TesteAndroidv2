package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.Provides
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.statements.api.StatementsService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    /**
     * Provides the Login Network service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Login Network service implementation.
     */
    @Provides
    @Singleton
    internal fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    /**
     * Provides the Statements Network service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Statements Network service implementation.
     */
    @Provides
    @Singleton
    internal fun provideStatementsService(retrofit: Retrofit): StatementsService {
        return retrofit.create(StatementsService::class.java)
    }

}