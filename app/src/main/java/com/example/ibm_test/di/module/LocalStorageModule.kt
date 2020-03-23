package com.example.ibm_test.di.module

import android.content.Context
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.localstorage.UserStorageImp
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class LocalStorageModule {
    @Provides
    fun provideUserStorage(context: Context) : UserStorage{
        val sharedPreferences= context.getSharedPreferences(UserStorageImp.USER_INFO, Context.MODE_PRIVATE)
        return UserStorageImp(sharedPreferences)
    }
}