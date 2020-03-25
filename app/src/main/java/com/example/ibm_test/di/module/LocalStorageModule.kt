package com.example.ibm_test.di.module

import android.content.Context
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.localstorage.UserStorageImp
import dagger.Module
import dagger.Provides


@Module
class LocalStorageModule {
    @Provides
    fun provideUserStorage(context: Context): UserStorage {
        val sharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

            EncryptedSharedPreferences.create(
                UserStorageImp.USER_INFO,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            context.getSharedPreferences(UserStorageImp.USER_INFO, Context.MODE_PRIVATE)
        }

        return UserStorageImp(sharedPreferences)
    }
}