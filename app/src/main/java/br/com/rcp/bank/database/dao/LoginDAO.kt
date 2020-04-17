package br.com.rcp.bank.database.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.rcp.bank.database.dao.base.BaseDAO
import br.com.rcp.bank.database.models.Login

@Dao
abstract class LoginDAO: BaseDAO<Login>() {
	// Only one login is saved
	@Query("select * from login where identifier = 1")
	abstract fun getLogin(): Login?

	@Query("delete from login")
	abstract fun clear()
}