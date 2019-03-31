package br.com.rms.bankapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.StatementDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.data.local.database.entity.User

const val DATABASE_NAME = "bankapp.db"

@Database(entities = [User::class, Account::class, Statement::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun accountDao(): AccountDao

    abstract fun statementDao(): StatementDao
}