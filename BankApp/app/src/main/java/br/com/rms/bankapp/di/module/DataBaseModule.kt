package br.com.rms.bankapp.di.module

import android.app.Application
import androidx.room.Room
import br.com.rms.bankapp.data.local.database.AppDatabase
import br.com.rms.bankapp.data.local.database.DATABASE_NAME
import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.StatementDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule{

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase{
        return Room.databaseBuilder(
            application, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun provideAccountDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @Singleton
    internal fun provideStatementDao(appDatabase: AppDatabase): StatementDao {
        return appDatabase.statementDao()
    }

}