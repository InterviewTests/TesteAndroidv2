package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.Provides
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginMapper
import pt.felipegouveia.bankapp.data.login.repository.LoginRepositoryImpl
import pt.felipegouveia.bankapp.data.statements.api.StatementsService
import pt.felipegouveia.bankapp.data.statements.model.StatementsMapper
import pt.felipegouveia.bankapp.data.statements.repository.StatementsRepositoryImpl
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.presentation.login.entity.mapper.LoginPresentationMapper
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper
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
     * Provides StatementsMapper.
     */
    @Provides
    @Singleton
    internal fun provideStatementsMapper(): StatementsMapper {
        return StatementsMapper()
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
     * Provides StatementsPresentationMapper.
     */
    @Provides
    @Singleton
    internal fun provideStatementsPresentationMapper(): StatementsPresentationMapper {
        return StatementsPresentationMapper()
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

    /**
     * Provides StatementsRepositoryImpl.
     */
    @Provides
    @Singleton
    internal fun provideStatementsRepositoryImpl(statementsService: StatementsService,
                                            statementsMapper: StatementsMapper): StatementsRepository {
        return StatementsRepositoryImpl(statementsService, statementsMapper)
    }
}