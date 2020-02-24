package br.com.flaviokreis.santanderv2.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.flaviokreis.santanderv2.BankApplication
import br.com.flaviokreis.santanderv2.data.preferences.LoginPreference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val app: BankApplication) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun providePreferences(): SharedPreferences {
        return app.getSharedPreferences("bank_account", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLoginPreference(preferences: SharedPreferences): LoginPreference {
        return LoginPreference(preferences)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.baseContext
    }
}