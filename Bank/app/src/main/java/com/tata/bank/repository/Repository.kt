package com.tata.bank.repository

import android.content.Context
import com.tata.bank.login.LoginCredentials
import com.tata.bank.security.CipherData
import com.tata.bank.security.SecurityWorker

class Repository(private val context: Context) {

//    private var encryptedCredentials: CipherData? = null
    private val preferences by lazy { Preferences(context) }

    fun saveCredentials(loginCredentials: LoginCredentials) {
        val login = "${loginCredentials.user}&${loginCredentials.password}"
        val encryptedCredentials = SecurityWorker.encrypt(login.toByteArray(Charsets.UTF_8))

        encryptedCredentials?.let {
            preferences.saveEncryptedCredentials(it)
        }
    }

    fun getCredentials(): LoginCredentials? {

        val encryptedCredentials = preferences.getEncryptedCredentials()

//        encryptedCredentials?.let {
            val decryptedBytes = SecurityWorker.decrypt(encryptedCredentials)
            val decryptedData = decryptedBytes?.toString(Charsets.UTF_8)
            val loginDataList = decryptedData?.split("&")

            loginDataList?.let { credentialsList ->
                if (credentialsList.size == 2) {
                    return LoginCredentials(loginDataList[0], loginDataList[1])
                }
            }
//        }

        return null
    }
}