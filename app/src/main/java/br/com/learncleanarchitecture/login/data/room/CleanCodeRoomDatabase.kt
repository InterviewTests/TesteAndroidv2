package br.com.learncleanarchitecture.login.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room


@Database(entities = [LoginEntity::class], version = 1, exportSchema = false)
abstract class CleanCodeRoomDatabase : RoomDatabase() {

    abstract fun getLoginDao(): LoginCrudDAO

    companion object {

        @Volatile
        private var INSTANCE: CleanCodeRoomDatabase? = null

        internal fun getDatabase(context: Context): CleanCodeRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CleanCodeRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CleanCodeRoomDatabase::class.java, "clean_code_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}