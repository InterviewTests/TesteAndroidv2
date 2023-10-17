package fingerfire.com.extractbank.di

import fingerfire.com.extractbank.network.SetupRetrofit
import org.koin.dsl.module

class NetworkModules {
    fun getNetworkModules() = module {
        single {
            SetupRetrofit.getRetrofit()
        }
    }
}