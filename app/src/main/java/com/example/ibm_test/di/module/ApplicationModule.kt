package com.example.ibm_test.di.module


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.ibm_test.utils.Connectivity
import com.example.ibm_test.utils.ConnectivityContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideConnectivityManager() : ConnectivityContract {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return Connectivity(connectivityManager = connectivityManager)
    }

}