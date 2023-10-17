package fingerfire.com.extractbank.di

import android.app.Application
import android.content.SharedPreferences
import fingerfire.com.extractbank.features.login.data.LoginRepository
import fingerfire.com.extractbank.features.statements.data.StatementRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class DataModule {
    private fun getSharedPrefs(androidApplication: Application): SharedPreferences {
        return androidApplication.getSharedPreferences(
            "default",
            android.content.Context.MODE_PRIVATE
        )
    }

    fun getDataModules() = module {
        single {
            getSharedPrefs(androidApplication())
        }

        single<SharedPreferences.Editor> {
            getSharedPrefs(androidApplication()).edit()
        }
        factory {
            LoginRepository(get(), get())
        }

        factory {
            StatementRepository(get())
        }
    }
}