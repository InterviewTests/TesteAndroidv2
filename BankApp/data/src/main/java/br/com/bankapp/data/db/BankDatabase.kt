package br.com.bankapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.db.entity.UserAccountEntity

@Database(entities = [UserAccountEntity::class], version = 1, exportSchema = false)
abstract class BankDatabase : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao

    companion object{
        val DATABASE_NAME: String = "bank_db"
    }
}