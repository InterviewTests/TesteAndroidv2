package br.com.rcp.bank.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.rcp.bank.database.dao.base.BaseDAO
import br.com.rcp.bank.database.models.Account

@Dao
abstract class AccountDAO: BaseDAO<Account>() {
	// only one user per once
	@Query("select * from account where identifier = 1")
	abstract fun getAccount(): Account?

	@Query("delete from account")
	abstract fun clear()
}