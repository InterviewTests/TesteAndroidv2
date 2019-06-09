package com.androiddeveloper.santanderTest.shared.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.EncryptedData
import com.androiddeveloper.santanderTest.data.model.userdata.UserDao

@androidx.room.Database(entities = [EncryptedData::class], version = 1, exportSchema = false)
abstract class MockDatabase : RoomDatabase() {

    abstract fun userDataDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: MockDatabase? = null

        fun getInstance(context: Context): MockDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context.applicationContext, MockDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        fun closeDatabase() {
            INSTANCE = null
        }
    }
}