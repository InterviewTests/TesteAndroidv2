package pt.felipegouveia.bankapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pt.felipegouveia.bankapp.util.persistence.BankSharedPreferences
import pt.felipegouveia.bankapp.util.security.CipherWrapper
import pt.felipegouveia.bankapp.util.security.KeyStoreWrapper
import javax.inject.Singleton

@Module
class SecurityModule {

    @Provides
    @Singleton
    fun provideKeyStoreWrapper(context: Application): KeyStoreWrapper {
        return KeyStoreWrapper(context)
    }

    @Provides
    @Singleton
    fun provideCipherWrapper(): CipherWrapper {
        return CipherWrapper(CipherWrapper.TRANSFORMATION_ASYMMETRIC)
    }

    @Provides
    @Singleton
    fun provideBankSharedPreferences(context: Application, cipher: CipherWrapper, keyStoreWrapper: KeyStoreWrapper): BankSharedPreferences {
        return BankSharedPreferences(context, cipher, keyStoreWrapper)
    }

}