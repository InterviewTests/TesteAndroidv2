package com.rafaelpereiraramos.testeandroidv2.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafaelpereiraramos.testeandroidv2.db.AppDatabase
import com.rafaelpereiraramos.testeandroidv2.db.dao.ParameterDao
import com.rafaelpereiraramos.testeandroidv2.db.dao.StatementDao
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Module
class DiskIOModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
            /*For demonstration only, migration schema should be included into the app*/
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideStatementDao(db: AppDatabase): StatementDao = db.statementDao()

    @Provides
    @Singleton
    fun provideParameterDao(db: AppDatabase): ParameterDao = db.parameterDao()
}