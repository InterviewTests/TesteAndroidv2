package br.com.bankapp.di.login

import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.repository.LoginRepositoryImpl
import br.com.bankapp.data.source.LoginDataSource
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.domain.repository.LoginRepository
import br.com.fortes.appcolaborador.di.main.LoginScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object LoginModule {

    @JvmStatic
    @LoginScope
    @Provides
    fun provideBankApiService(retrofitBuilder: Retrofit.Builder): BankAppApiService {
        return retrofitBuilder
                .build()
                .create(BankAppApiService::class.java)
    }

    @JvmStatic
    @LoginScope
    @Provides
    fun provideLoginDataSource(
        bankAppApiService: BankAppApiService,
        userAccountDao: UserAccountDao,
        sharedPrefsHelper: SharedPrefsHelper
    ): LoginDataSource {
        return LoginDataSource(bankAppApiService, userAccountDao, sharedPrefsHelper)
    }

    @JvmStatic
    @LoginScope
    @Provides
    fun provideLoginRepository(
            loginDataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(loginDataSource)
    }

    @JvmStatic
    @LoginScope
    @Provides
    fun provideUserAccountDao(db: BankDatabase): UserAccountDao {
        return db.userAccountDao()
    }
}

















