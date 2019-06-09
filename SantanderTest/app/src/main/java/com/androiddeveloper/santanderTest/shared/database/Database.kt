package com.androiddeveloper.santanderTest.shared.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddeveloper.santanderTest.data.model.userdata.EncryptedData
import com.androiddeveloper.santanderTest.data.model.userdata.UserDao

@androidx.room.Database(entities = [EncryptedData::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun userDataDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, Database::class.java, "SantanderTest.db")
                .build()

        fun closeDatabase() {
            INSTANCE = null
        }
    }
}