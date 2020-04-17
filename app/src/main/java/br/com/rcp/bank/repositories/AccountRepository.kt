package br.com.rcp.bank.repositories

import br.com.rcp.bank.database.models.Account
import br.com.rcp.bank.repositories.base.Repository
import kotlinx.coroutines.Dispatchers

class AccountRepository: Repository() {
	suspend fun persist(account: Account): Result<Unit> {
		return HandleResult(Dispatchers.IO) { database.accountDAO().save(account) }
	}

	suspend fun get(): Result<Account?> {
		return HandleResult(Dispatchers.IO) { database.accountDAO().getAccount() }
	}
}