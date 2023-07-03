package com.nandoligeiro.safrando.datasource.login.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nandoligeiro.safrando.data.login.datasource.LocalLoginDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalLoginDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalLoginDataSource {

    companion object {
        val USER = stringPreferencesKey("USER")
    }

    override suspend fun saveLogin(user: String) {

        dataStore.edit { preferences ->
            preferences[USER] = user
        }
    }

    override suspend fun getLocalLogin(): Flow<String> = dataStore.data.map { preference ->
        preference[USER] ?: ""
    }
}
