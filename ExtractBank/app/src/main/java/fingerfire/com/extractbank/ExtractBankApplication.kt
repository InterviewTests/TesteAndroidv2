package fingerfire.com.extractbank

import android.app.Application
import fingerfire.com.extractbank.di.ApiModules
import fingerfire.com.extractbank.di.DataModule
import fingerfire.com.extractbank.di.NetworkModules
import fingerfire.com.extractbank.di.UiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExtractBankApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ExtractBankApplication)
            modules(
                listOf(
                    NetworkModules().getNetworkModules(),
                    ApiModules().getApiModules(),
                    UiModules().getViewModules(),
                    DataModule().getDataModules()
                )
            )
        }
    }
}