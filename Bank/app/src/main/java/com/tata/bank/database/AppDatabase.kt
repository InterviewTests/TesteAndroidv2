package com.tata.bank.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoginCredentialEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun credentialsDao(): LoginCredentialDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        operator fun invoke(context: Context) = instance
            ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it}
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, "bank_database")
            .build()
    }
}