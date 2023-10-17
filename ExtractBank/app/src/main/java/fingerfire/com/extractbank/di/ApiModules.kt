package fingerfire.com.extractbank.di

import fingerfire.com.extractbank.api.BankApi
import org.koin.dsl.module
import retrofit2.Retrofit

class ApiModules {
    fun getApiModules() = module {
        factory<BankApi> {
            get<Retrofit>().create(BankApi::class.java)
        }
    }
}