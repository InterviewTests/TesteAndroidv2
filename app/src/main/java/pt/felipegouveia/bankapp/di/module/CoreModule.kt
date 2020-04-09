package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.Provides
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginMapper
import pt.felipegouveia.bankapp.data.login.repository.LoginRepositoryImpl
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.presentation.login.entity.mapper.LoginPresentationMapper
import javax.inject.Singleton

@Module
class CoreModule {

    /**
     * Provides LoginMapper.
     */
    @Provides
    @Singleton
    internal fun provideLoginMapper(): LoginMapper {
        return LoginMapper()
    }

    /**
     * Provides LoginPresentationMapper.
     */
    @Provides
    @Singleton
    internal fun provideLoginPresentationMapper(): LoginPresentationMapper {
        return LoginPresentationMapper()
    }

    /**
     * Provides LoginRepositoryImpl.
     */
    @Provides
    @Singleton
    internal fun provideLoginRepositoryImpl(loginService: LoginService,
                                          loginMapper: LoginMapper): LoginRepository {
        return LoginRepositoryImpl(loginService, loginMapper)
    }
}