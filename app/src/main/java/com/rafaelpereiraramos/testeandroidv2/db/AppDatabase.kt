package com.rafaelpereiraramos.testeandroidv2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafaelpereiraramos.testeandroidv2.db.dao.StatementDao
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Database(entities = [UserTO::class, StatementTO::class],
    version = 2,
    exportSchema = false)
@TypeConverters(value = [DateConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun statementDao(): StatementDao

    companion object {
        const val DB_NAME = "testeAndroidV2.db"
    }
}