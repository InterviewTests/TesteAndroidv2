package com.tata.bank.repository

import android.content.Context
import android.os.Build
import com.tata.bank.login.LoginCredentials
import com.tata.bank.security.SecurityWorker
import com.tata.bank.security.SecurityWorkerLegacy

class Repository(context: Context) {

    private val preferences by lazy { Preferences(context) }
    private val security by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SecurityWorker(context)
        } else {
            SecurityWorkerLegacy(context)
        }
    }

    fun saveCredentials(loginCredentials: LoginCredentials) {
        val login = "${loginCredentials.user}&${loginCredentials.password}"
        val encryptedCredentials = security.encrypt(login.toByteArray(Charsets.UTF_8))

        encryptedCredentials?.let {
            preferences.saveEncryptedCredentials(it)
        }
    }

    fun getCredentials(): LoginCredentials? {
        preferences.getEncryptedCredentials()?.let {
            val decryptedBytes = security.decrypt(it)
            val decryptedData = decryptedBytes?.toString(Charsets.UTF_8)
            val loginDataList = decryptedData?.split("&")

            loginDataList?.let { credentialsList ->
                if (credentialsList.size == 2) {
                    return LoginCredentials(loginDataList[0], loginDataList[1])
                }
            }
        }

        return null
    }

    fun cleanStoredCredentials() {
        preferences.clean()
    }
}