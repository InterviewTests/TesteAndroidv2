package dev.ornelas.bankapp.data

import android.content.Context
import dev.ornelas.banckapp.domain.repository.StatementRepository
import dev.ornelas.banckapp.domain.repository.UserRepository
import dev.ornelas.bankapp.data.datasource.api.retrofit.BankApiService
import dev.ornelas.bankapp.data.datasource.local.SharedPreferenceDataSource
import dev.ornelas.bankapp.data.repository.StatmentRepositoryImpl
import dev.ornelas.bankapp.data.repository.UserRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DataComponent {
    val userRepository: UserRepository
    val statementRepository: StatementRepository
}

class DataContainer(context: Context) : DataComponent {

    private val URL_BASE = "https://bank-app-test.herokuapp.com/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val bankApi by lazy { retrofit.create(BankApiService::class.java) }

    private val sharedPreferenceDataSource = SharedPreferenceDataSource(context = context)

    override val userRepository = UserRepositoryImpl(
        bankApiService = bankApi,
        sharedPreferenceDataSource = sharedPreferenceDataSource
    )

    override val statementRepository = StatmentRepositoryImpl(bankApiService = bankApi)
}