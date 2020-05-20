package br.com.bankapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import br.com.bankapp.BuildConfig
import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.repository.UserAccountRepositoryImpl
import br.com.bankapp.data.source.LoginDataSource
import br.com.bankapp.data.source.UserAccountDataSource
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.domain.repository.UserAccountRepository
import br.com.bankapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilderOne(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).also {
                it.connectTimeout(20, TimeUnit.SECONDS)
                it.readTimeout(15, TimeUnit.SECONDS)
                it.writeTimeout(15, TimeUnit.SECONDS)
            }.build()

    @Provides
    fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder:  Gson, okhttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDb(context: Context): BankDatabase {
        return Room
            .databaseBuilder(context, BankDatabase::class.java, BankDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPrefsHelper.PREF_USER,
            Context.MODE_PRIVATE
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideBankApiService(retrofitBuilder: Retrofit.Builder): BankAppApiService {
        return retrofitBuilder
            .build()
            .create(BankAppApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserAccountDao(db: BankDatabase): UserAccountDao {
        return db.userAccountDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLoginDataSource(
        bankAppApiService: BankAppApiService,
        userAccountDao: UserAccountDao,
        sharedPrefsHelper: SharedPrefsHelper
    ): LoginDataSource {
        return LoginDataSource(bankAppApiService, userAccountDao, sharedPrefsHelper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserAccountDataSource(
        userAccountDao: UserAccountDao
    ): UserAccountDataSource {
        return UserAccountDataSource(userAccountDao)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserAccountRepository(
        loginDataSource: LoginDataSource,
        userAccountDataSource: UserAccountDataSource
    ): UserAccountRepository {
        return UserAccountRepositoryImpl(loginDataSource, userAccountDataSource)
    }
}