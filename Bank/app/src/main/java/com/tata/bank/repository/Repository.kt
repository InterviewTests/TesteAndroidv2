package com.tata.bank.repository

import android.content.Context
import android.os.Build
import com.google.gson.GsonBuilder
import com.tata.bank.database.AppDatabase
import com.tata.bank.database.LoginCredentialEntity
import com.tata.bank.login.LoginCredentials
import com.tata.bank.security.CipherData
import com.tata.bank.security.SecurityWorker
import com.tata.bank.security.SecurityWorkerLegacy
import io.reactivex.Maybe

class Repository(context: Context) {

    private val database by lazy { AppDatabase.invoke(context) }
    private val gson = GsonBuilder().create()

    private val security by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SecurityWorker(context)
        } else {
            SecurityWorkerLegacy(context)
        }
    }

    fun saveCredentials(loginCredentials: LoginCredentials): Maybe<Long> {
        val login = "${loginCredentials.user}&${loginCredentials.password}"
        val encryptedCredentials = security.encrypt(login.toByteArray(Charsets.UTF_8))
        val credentialsStr = gson.toJson(encryptedCredentials)

        val credentialsEntity = LoginCredentialEntity(credentials = credentialsStr)
        return database.credentialsDao().insert(credentialsEntity)
    }

    fun getCredentials(): Maybe<LoginCredentials> {
        return database.credentialsDao().getCredentials()
            .filter { it.isNotEmpty() }
            .map {
                val credentialsJson = it.first().credentials
                val credentials = gson.fromJson(credentialsJson, CipherData::class.java)

                val decryptedBytes = security.decrypt(credentials)
                val decryptedData = decryptedBytes?.toString(Charsets.UTF_8)
                decryptedData?.split("&")
            }
            .filter { it.isNotEmpty() && it.size >= 2 }
            .map {
                LoginCredentials(it[0], it[1])
            }
    }

    fun cleanStoredCredentials() {
        database.clearAllTables()
    }
}