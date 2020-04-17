package br.com.rcp.bank.injection.modules

import br.com.rcp.bank.annotations.Service
import br.com.rcp.bank.repositories.AccountRepository
import br.com.rcp.bank.repositories.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
	@Service @Provides fun getLoginRepository()		= LoginRepository()
	@Service @Provides fun getAccountRepository()	= AccountRepository()
}