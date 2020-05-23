package br.com.bankapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.bankapp.data.db.dao.StatementDao
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.db.entity.StatementEntity
import br.com.bankapp.data.db.entity.UserAccountEntity

@Database(entities = [UserAccountEntity::class, StatementEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BankDatabase : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun statementDao(): StatementDao

    companion object{
        val DATABASE_NAME: String = "bank_db"
    }
}