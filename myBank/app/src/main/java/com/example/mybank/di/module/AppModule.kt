package com.example.mybank.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mybank.data.Repository
import com.example.mybank.data.local.UserDatabase
import com.example.mybank.data.local.dao.UserDao
import com.example.mybank.data.remote.MyBankApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

@Module(includes = [ViewModelModule::class])
class AppModule {

    //-------- Application Context
    @Provides
    @Singleton
    fun provideAppContext(application: Application): Context {
        return application
    }

    //-------- API

    @Provides
    @Named("MyBankApi")
    @Singleton
    internal fun getMyBankApi(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMyBankApi(@Named("MyBankApi") retrofit: Retrofit): MyBankApi {
        return retrofit.create(MyBankApi::class.java)
    }

    //-------- Database

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "UserDB.db").build()
    }

    @Provides
    @Singleton
    internal fun provideUserDao(database: UserDatabase): UserDao {
        return database.usuarioDao()
    }

    //-------- Repository
    @Provides
    @Singleton
    internal fun provideRepository(userDatabase: UserDatabase, myBankApi: MyBankApi): Repository {
        return Repository(userDatabase, myBankApi)
    }

}