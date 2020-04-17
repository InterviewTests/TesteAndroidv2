package br.com.rcp.bank.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rcp.bank.database.dao.AccountDAO
import br.com.rcp.bank.database.dao.LoginDAO
import br.com.rcp.bank.database.models.Account
import br.com.rcp.bank.database.models.Login

@Database(version = 3, entities = [Account::class, Login::class])
abstract class Database: RoomDatabase() {
	abstract fun loginDAO()		: LoginDAO
	abstract fun accountDAO()	: AccountDAO
}